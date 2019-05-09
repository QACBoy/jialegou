package com.hilkr.comments.dao;


import com.jialegou.comments.pojo.Spit;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * 描述:
 * TODO
 *
 * @author hilkr
 */
public interface SpitDao extends MongoRepository<Spit, String> {
}
