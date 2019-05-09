package com.jialegou.seckill.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hilkr.common.utils.JsonUtils;
import com.hilkr.dal.dao.SeckillOrderMapper;
import com.hilkr.dal.dao.SeckillSkuMapper;
import com.hilkr.dal.dao.SkuMapper;
import com.hilkr.dal.dao.StockMapper;
import com.hilkr.dal.model.*;
import com.hilkr.seckill.vo.SeckillMessage;
import com.jialegou.seckill.client.GoodsClient;
import com.jialegou.seckill.client.OrderClient;
import com.jialegou.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 描述:
 * TODO
 *
 * @author hilkr
 */
@Service
public class SeckillServiceImpl implements SeckillService {

    @Autowired
    private SeckillSkuMapper seckillMapper;

    @Autowired
    private GoodsClient goodsClient;

    @Autowired
    private StockMapper stockMapper;

    @Autowired
    private SkuMapper skuMapper;

    @Autowired
    private OrderClient orderClient;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private SeckillOrderMapper seckillOrderMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    private static final Logger LOGGER = LoggerFactory.getLogger(SeckillServiceImpl.class);

    private static final String KEY_PREFIX_PATH = "jialegou:seckill:path";


    /**
     * 创建订单
     *
     * @param seckillGoods
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createOrder(SeckillSku seckillGoods) {

        Order order = new Order();
        order.setPaymentType(1);
        order.setTotalPay(seckillGoods.getSeckillPrice());
        order.setActualPay(seckillGoods.getSeckillPrice());
        // TODO order.setPostFee(0 + "");
        order.setPostFee(0L);
        order.setReceiver("张三");
        order.setReceiverMobile("13012312312");
        order.setReceiverCity("厦门");
        order.setReceiverDistrict("湖里区");
        order.setReceiverState("福建");
        order.setReceiverZip("000000000");
        order.setInvoiceType(0);
        order.setSourceType(2);

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setSkuId(seckillGoods.getSkuId());
        orderDetail.setNum(1);
        orderDetail.setTitle(seckillGoods.getTitle());
        orderDetail.setImage(seckillGoods.getImage());
        orderDetail.setPrice(seckillGoods.getSeckillPrice());
        orderDetail.setOwnSpec(this.skuMapper.selectByPrimaryKey(seckillGoods.getSkuId()).getOwnSpec());

        order.setOrderDetails(Arrays.asList(orderDetail));


        String seck = "seckill";
        ResponseEntity<List<Long>> responseEntity = this.orderClient.createOrder(seck, order);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            //库存不足，返回null
            return null;
        }
        return responseEntity.getBody().get(0);
    }

    /**
     * 检查秒杀库存
     *
     * @param skuId
     * @return
     */
    @Override
    public boolean queryStock(Long skuId) {
        Stock stock = this.stockMapper.selectByPrimaryKey(skuId);
        if (stock.getSeckillStock() - 1 < 0) {
            return false;
        }
        return true;
    }

    /**
     * 发送消息到秒杀队列当中
     *
     * @param seckillMessage
     */
    @Override
    public void sendMessage(SeckillMessage seckillMessage) {
        String json = JsonUtils.serialize(seckillMessage);
        try {
            this.amqpTemplate.convertAndSend("order.seckill", json);
        } catch (Exception e) {
            LOGGER.error("秒杀商品消息发送异常，商品id：{}", seckillMessage.getSeckillSku().getSkuId(), e);
        }
    }

    /**
     * 根据用户id查询秒杀订单
     *
     * @param userId
     * @return
     */
    @Override
    public Long checkSeckillOrder(Long userId) {
        List<SeckillOrder> seckillOrders = this.seckillOrderMapper.selectList(new QueryWrapper<SeckillOrder>().eq("user_id", userId));
        if (seckillOrders == null || seckillOrders.size() == 0) {
            return null;
        }
        return seckillOrders.get(0).getOrderId();
    }

    /**
     * 创建秒杀地址
     *
     * @param goodsId
     * @param id
     * @return
     */
    @Override
    public String createPath(Long goodsId, Long id) {
        String str = new BCryptPasswordEncoder().encode(goodsId.toString() + id);
        BoundHashOperations<String, Object, Object> hashOperations = this.stringRedisTemplate.boundHashOps(KEY_PREFIX_PATH);
        String key = id.toString() + "_" + goodsId;
        hashOperations.put(key, str);
        hashOperations.expire(60, TimeUnit.SECONDS);
        return str;
    }

    /**
     * 验证秒杀地址
     *
     * @param goodsId
     * @param id
     * @param path
     * @return
     */
    @Override
    public boolean checkSeckillPath(Long goodsId, Long id, String path) {
        String key = id.toString() + "_" + goodsId;
        BoundHashOperations<String, Object, Object> hashOperations = this.stringRedisTemplate.boundHashOps(KEY_PREFIX_PATH);
        String encodePath = (String) hashOperations.get(key);
        return new BCryptPasswordEncoder().matches(path, encodePath);
    }


}
