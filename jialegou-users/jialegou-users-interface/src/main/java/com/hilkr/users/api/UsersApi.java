package com.hilkr.users.api;

import com.hilkr.dal.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

/**
 * 描述:
 * TODO
 *
 * @author sam
 * @create 2019-04-21
 */
public interface UsersApi {
    /**
     * 用户验证
     *
     * @param username
     * @param password
     * @return
     */
    @GetMapping("query")
    User queryUser(@RequestParam("username") String username, @RequestParam("password") String password);

    @GetMapping("check/{data}/{type}")
    ResponseEntity<Boolean> checkUserData(@PathVariable("data") String data, @PathVariable(value = "type") Integer type);

    @PostMapping("register")
    ResponseEntity<Void> register(@Valid User user, @RequestParam("code") String code);

    @PostMapping("code")
    ResponseEntity senVerifyCode(@RequestParam("phone") String phone);
}