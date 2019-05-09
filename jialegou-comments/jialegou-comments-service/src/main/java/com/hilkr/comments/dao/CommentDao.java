package com.hilkr.comments.dao;


import com.jialegou.comments.pojo.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;


/**
 * 描述:
 * TODO
 *
 * @author hilkr
 */
public interface CommentDao extends MongoRepository<Review, String> {

    /**
     * 分页查询
     *
     * @param spuId
     * @param pageable
     * @return
     */
    Page<Review> findReviewBySpuid(String spuId, Pageable pageable);
}
