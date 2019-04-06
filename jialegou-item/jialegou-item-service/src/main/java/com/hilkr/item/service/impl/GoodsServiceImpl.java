package com.hilkr.item.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hilkr.common.enums.ExceptionEnum;
import com.hilkr.common.exception.JialegouException;
import com.hilkr.common.vo.PageResult;
import com.hilkr.dal.dao.*;
import com.hilkr.dal.model.*;
import com.hilkr.item.dto.CartDto;
import com.hilkr.item.service.IGoodsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 描述: <br>
 * TODO
 *
 * @author sam
 * @create 2019-03-29
 */
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Slf4j
@Service
// TODO: org.apache.ibatis.binding.BindingException: Invalid bound statement (not found): com.hilkr.dal.dao.StockMapper.insertList
public class GoodsServiceImpl implements IGoodsService {

    @Autowired
    private SpuMapper spuMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private BrandMapper brandMapper;

    @Autowired
    private SpuDetailMapper spuDetailMapper;

    @Autowired
    private SkuMapper skuMapper;

    @Autowired
    private StockMapper stockMapper;

    // @Autowired
    // private AmqpTemplate amqpTemplate;

    // TODO：当查询全部数据的时候，zuul 回报超时错误
    @Override
    public PageResult<Spu> querySpuByPage(Integer page, Integer rows, String key, Boolean saleable) {
        // 分页
        if (rows == -1){
            rows = Integer.MAX_VALUE;
        }
        PageHelper.startPage(page, rows);

        QueryWrapper<Spu> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(key)) {
            queryWrapper.like("title", "%" + key + "%");
        }
        if (saleable != null) {
            queryWrapper.or();
            queryWrapper.eq("saleable", saleable);
        }
        // 默认以上一次更新时间排序
        queryWrapper.orderByDesc("last_update_time");

        // 只查询未删除的商品
        queryWrapper.eq("valid", 1);

        // 查询
        List<Spu> spuList = spuMapper.selectList(queryWrapper);

        if (CollectionUtils.isEmpty(spuList)) {
            throw new JialegouException(ExceptionEnum.SPU_NOT_FOUND);
        }
        // 对查询结果中的分类名和品牌名进行处理
        handleCategoryAndBrand(spuList);

        PageInfo<Spu> pageInfo = new PageInfo<>(spuList);

        return new PageResult<>(pageInfo.getTotal(), spuList);
    }

    @Override
    public SpuDetail querySpuDetailBySpuId(Long spuId) {
        SpuDetail spuDetail = spuDetailMapper.selectById(spuId);
        if (spuDetail == null) {
            throw new JialegouException(ExceptionEnum.SPU_NOT_FOUND);
        }
        return spuDetail;
    }

    @Override
    public List<Sku> querySkuBySpuId(Long spuId) {
        Sku sku = new Sku();
        sku.setSpuId(spuId);
        List<Sku> skuList = skuMapper.selectList(new QueryWrapper<Sku>().setEntity(sku));
        if (CollectionUtils.isEmpty(skuList)) {
            throw new JialegouException(ExceptionEnum.SKU_NOT_FOUND);
        }

        //查询库存
        for (Sku sku1 : skuList) {
            sku1.setStock(stockMapper.selectById(sku1.getId()).getStock());
        }
        return skuList;
    }

    @Transactional
    @Override
    public void deleteGoodsBySpuId(Long spuId) {
        if (spuId == null) {
            throw new JialegouException(ExceptionEnum.INVALID_PARAM);
        }
        //删除spu,把spu中的valid字段设置成false
        Spu spu = new Spu();
        spu.setId(spuId);
        spu.setValid(false);
        int count = spuMapper.updateById(spu);
        if (count == 0) {
            throw new JialegouException(ExceptionEnum.DELETE_GOODS_ERROR);
        }

        //发送消息
        // sendMessage(spuId, "delete");
    }

    @Transactional
    @Override
    public void addGoods(Spu spu) {
        //添加商品要添加四个表 spu, spuDetail, sku, stock四张表
        spu.setSaleable(true);
        spu.setValid(true);
        spu.setCreateTime(new Date());
        spu.setLastUpdateTime(spu.getCreateTime());
        //插入数据
        int count = spuMapper.insert(spu);
        if (count != 1) {
            throw new JialegouException(ExceptionEnum.GOODS_SAVE_ERROR);
        }
        //插入spuDetail数据
        SpuDetail spuDetail = spu.getSpuDetail();
        spuDetail.setSpuId(spu.getId());
        count = spuDetailMapper.insert(spuDetail);
        if (count != 1) {
            throw new JialegouException(ExceptionEnum.GOODS_SAVE_ERROR);
        }

        //插入sku和库存
        saveSkuAndStock(spu);

        //发送消息
        // sendMessage(spu.getId(), "insert");
    }

    @Transactional
    @Override
    public void updateGoods(Spu spu) {
        if (spu.getId() == 0) {
            throw new JialegouException(ExceptionEnum.INVALID_PARAM);
        }

        //首先查询sku
        Sku sku = new Sku();
        sku.setSpuId(spu.getId());
        QueryWrapper<Sku> queryWrapper = new QueryWrapper<Sku>().setEntity(sku);
        List<Sku> skuList = skuMapper.selectList(queryWrapper);

        if (!CollectionUtils.isEmpty(skuList)) {
            //删除所有sku
            skuMapper.delete(queryWrapper);
            //删除库存
            List<Long> ids = skuList.stream()
                    .map(Sku::getId)
                    .collect(Collectors.toList());
            stockMapper.deleteBatchIds(ids);
        }

        //更新数据库  spu  spuDetail
        spu.setLastUpdateTime(new Date());
        //更新spu spuDetail
        int count = spuMapper.updateById(spu);
        if (count == 0) {
            throw new JialegouException(ExceptionEnum.GOODS_UPDATE_ERROR);
        }


        SpuDetail spuDetail = spu.getSpuDetail();
        spuDetail.setSpuId(spu.getId());
        count = spuDetailMapper.updateById(spuDetail);
        if (count == 0) {
            throw new JialegouException(ExceptionEnum.GOODS_UPDATE_ERROR);
        }

        //更新sku和stock
        saveSkuAndStock(spu);

        //发送消息
        // sendMessage(spu.getId(), "update");
    }

    @Override
    public void handleSaleable(Spu spu) {
        spu.setSaleable(!spu.getSaleable());
        int count = spuMapper.updateById(spu);
        if (count != 1) {
            throw new JialegouException(ExceptionEnum.UPDATE_SALEABLE_ERROR);
        }
    }

    @Override
    public Spu querySpuBySpuId(Long spuId) {
        //根据spuId查询spu
        Spu spu = spuMapper.selectById(spuId);

        //查询spuDetail
        SpuDetail detail = querySpuDetailBySpuId(spuId);

        //查询skus
        List<Sku> skus = querySkuBySpuId(spuId);

        spu.setSpuDetail(detail);
        spu.setSkus(skus);

        return spu;
    }

    @Override
    public List<Sku> querySkusByIds(List<Long> ids) {
        List<Sku> skus = skuMapper.selectBatchIds(ids);
        if (CollectionUtils.isEmpty(skus)) {
            throw new JialegouException(ExceptionEnum.GOODS_NOT_FOUND);
        }
        //填充库存
        fillStock(ids, skus);
        return skus;
    }

    @Transactional
    @Override
    public void decreaseStock(List<CartDto> cartDtos) {
        for (CartDto cartDto : cartDtos) {
            int count = stockMapper.decreaseStock(cartDto.getSkuId(), cartDto.getNum());
            if (count != 1) {
                throw new JialegouException(ExceptionEnum.STOCK_NOT_ENOUGH);
            }
        }
    }

    private void fillStock(List<Long> ids, List<Sku> skus) {
        //批量查询库存
        List<Stock> stocks = stockMapper.selectBatchIds(ids);
        if (CollectionUtils.isEmpty(stocks)) {
            throw new JialegouException(ExceptionEnum.STOCK_NOT_FOUND);
        }
        //首先将库存转换为map，key为sku的ID
        Map<Long, Integer> map = stocks.stream().collect(Collectors.toMap(s -> s.getSkuId(), s -> s.getStock()));

        //遍历skus，并填充库存
        for (Sku sku : skus) {
            sku.setStock(map.get(sku.getId()));
        }
    }


    /**
     * 保存sku和库存
     *
     * @param spu
     */
    private void saveSkuAndStock(Spu spu) {
        List<Sku> skuList = spu.getSkus();
        List<Stock> stocks = new ArrayList<>();

        for (Sku sku : skuList) {
            sku.setSpuId(spu.getId());
            sku.setSkuCreateTime(new Date());
            sku.setLastUpdateTime(sku.getSkuCreateTime());
            int count = skuMapper.insert(sku);
            if (count != 1) {
                throw new JialegouException(ExceptionEnum.GOODS_SAVE_ERROR);
            }

            Stock stock = new Stock();
            stock.setSkuId(sku.getId());
            stock.setStock(sku.getStock());
            stocks.add(stock);
        }
        // 批量插入库存数据
        int count = stockMapper.insertList(stocks);
        if (count == 0) {
            throw new JialegouException(ExceptionEnum.GOODS_SAVE_ERROR);
        }
    }


    /**
     * 处理商品分类名和品牌名
     *
     * @param spuList
     */
    private void handleCategoryAndBrand(List<Spu> spuList) {
        for (Spu spu : spuList) {
            //根据spu中的分类ids查询分类名
            List<String> nameList = categoryMapper.selectBatchIds(Arrays.asList(spu.getCid1(), spu.getCid2(), spu.getCid3()))
                    .stream()
                    .map(Category::getName)
                    .collect(Collectors.toList());
            //对分类名进行处理
            spu.setCname(StringUtils.join(nameList, "/"));

            //查询品牌
            spu.setBname(brandMapper.selectById(spu.getBrandId()).getName());
        }
    }

    /**
     * 封装发送到消息队列的方法
     *
     * @param id
     * @param type
     */
    // private void sendMessage(Long id, String type) {
    //     try {
    //         amqpTemplate.convertAndSend("item." + type, id);
    //     } catch (Exception e) {
    //         log.error("{}商品消息发送异常，商品ID：{}", type, id, e);
    //     }
    // }
}
