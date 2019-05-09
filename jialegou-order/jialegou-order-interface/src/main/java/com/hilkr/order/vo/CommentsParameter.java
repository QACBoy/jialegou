package com.hilkr.order.vo;

import com.jialegou.comments.pojo.Review;

/**
 * 描述:
 * 评论消息对象
 *
 * @author hilkr
 */
public class CommentsParameter {

    private Long orderId;

    private Review review;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }
}
