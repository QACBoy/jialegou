package com.hilkr.comments.dao;


import com.jialegou.comments.pojo.Spit;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @Author: 98050
 * @Time: 2018-11-26 20:47
 * @Feature:
 */
public interface SpitDao extends MongoRepository<Spit, String> {
}
