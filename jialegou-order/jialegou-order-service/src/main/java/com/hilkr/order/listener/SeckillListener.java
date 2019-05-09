package com.hilkr.order.listener;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hilkr.auth.entity.UserInfo;
import com.hilkr.common.utils.IdWorker;
import com.hilkr.common.utils.JsonUtils;
import com.hilkr.dal.dao.*;
import com.hilkr.dal.model.*;
import com.hilkr.order.service.OrderService;
import com.hilkr.seckill.vo.SeckillMessage;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 描述:
 * 秒杀消息队列监听器
 *
 * @author hilkr
 */
@Component
public class SeckillListener {

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private OrderService orderService;

    @Autowired
    private SkuMapper skuMapper;

    @Autowired
    private StockMapper stockMapper;

    @Autowired
    private SeckillOrderMapper seckillOrderMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderStatusMapper orderStatusMapper;

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Autowired
    private SeckillSkuMapper seckillMapper;

    /**
     * 接收秒杀信息
     *
     * @param seck
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "jialegou.order.seckill.queue", durable = "true"), //队列持久化
            exchange = @Exchange(
                    value = "jialegou.order.exchange",
                    ignoreDeclarationExceptions = "true",
                    type = ExchangeTypes.TOPIC
            ),
            key = {"order.seckill"}
    ))
    @Transactional(rollbackFor = Exception.class)
    public void listenSeckill(String seck) {

        SeckillMessage seckillMessage = JsonUtils.parse(seck, SeckillMessage.class);
        UserInfo userInfo = seckillMessage.getUserInfo();
        SeckillSku seckillGoods = seckillMessage.getSeckillSku();


        //1.首先判断库存是否充足
        Stock stock = stockMapper.selectByPrimaryKey(seckillGoods.getSkuId());
        if (stock.getSeckillStock() <= 0 || stock.getStock() <= 0) {
            //如果库存不足的话修改秒杀商品的enable字段
            List<SeckillSku> list = this.seckillMapper.selectList(new QueryWrapper<SeckillSku>().eq("sku_id", seckillGoods.getSkuId()));
            for (SeckillSku temp : list) {
                if (temp.getEnable()) {
                    temp.setEnable(false);
                    this.seckillMapper.updateByPrimaryKeySelective(temp);
                }
            }
            return;
        }
        //2.判断此用户是否已经秒杀到了
        QueryWrapper<SeckillOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userInfo.getId())
                .eq("sku_id", seckillGoods.getSkuId());
        List<SeckillOrder> list = this.seckillOrderMapper.selectList(queryWrapper);
        if (list.size() > 0) {
            return;
        }
        //3.下订单
        //构造order对象
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

        //3.1 生成orderId
        long orderId = idWorker.nextId();
        //3.2 初始化数据
        order.setBuyerNick(userInfo.getUsername());
        order.setBuyerRate(false);
        order.setCreateTime(new Date());
        order.setOrderId(orderId);
        order.setUserId(userInfo.getId());
        //3.3 保存数据
        this.orderMapper.insertSelective(order);

        //3.4 保存订单状态
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderId(orderId);
        orderStatus.setCreateTime(order.getCreateTime());
        //初始状态未未付款：1
        orderStatus.setStatus(1);
        //3.5 保存数据
        this.orderStatusMapper.insertSelective(orderStatus);

        //3.6 在订单详情中添加orderId
        order.getOrderDetails().forEach(od -> {
            //添加订单
            od.setOrderId(orderId);
        });

        //3.7 保存订单详情，使用批量插入功能
        this.orderDetailMapper.insertList(order.getOrderDetails());

        //3.8 修改库存
        order.getOrderDetails().forEach(ord -> {
            Stock stock1 = this.stockMapper.selectByPrimaryKey(ord.getSkuId());
            stock1.setStock(stock1.getStock() - ord.getNum());
            stock1.setSeckillStock(stock1.getSeckillStock() - ord.getNum());
            this.stockMapper.updateByPrimaryKeySelective(stock1);

            //新建秒杀订单
            SeckillOrder seckillOrder = new SeckillOrder();
            seckillOrder.setOrderId(orderId);
            seckillOrder.setSkuId(ord.getSkuId());
            seckillOrder.setUserId(userInfo.getId());
            this.seckillOrderMapper.insert(seckillOrder);

        });


    }
}
