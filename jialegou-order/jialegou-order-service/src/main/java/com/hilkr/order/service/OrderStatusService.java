package com.hilkr.order.service;

import com.hilkr.order.vo.CommentsParameter;
import com.hilkr.order.vo.OrderStatusMessage;

/**
 * 描述:
 * 发送延时消息和评论消息
 *
 * @author hilkr
 */
public interface OrderStatusService {


    /**
     * 发送消息到延时队列
     *
     * @param orderStatusMessage
     */
    void sendMessage(OrderStatusMessage orderStatusMessage);

    /**
     * 发送评论信息
     *
     * @param commentsParameter
     */
    void sendComments(CommentsParameter commentsParameter);
}
