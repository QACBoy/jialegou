package com.hilkr.users.api;

import com.hilkr.dal.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 描述:
 * TODO
 *
 * @author sam
 * @create 2019-04-21
 */
public interface UserApi {
    /**
     * 用户验证
     *
     * @param username
     * @param password
     * @return
     */
    @GetMapping("query")
    User queryUser(@RequestParam("username") String username, @RequestParam("password") String password);
}