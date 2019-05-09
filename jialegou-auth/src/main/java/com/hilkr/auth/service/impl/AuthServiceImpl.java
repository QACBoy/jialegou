package com.hilkr.auth.service.impl;

import com.hilkr.auth.client.UsersClient;
import com.hilkr.auth.entity.UserInfo;
import com.hilkr.auth.config.JwtProperties;
import com.hilkr.auth.service.IAuthService;
import com.hilkr.auth.utils.JwtUtils;
import com.hilkr.dal.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 描述:
 * TODO
 *
 * @author hilkr
 */
@Service
public class AuthServiceImpl implements IAuthService {

    @Autowired
    private UsersClient userClient;

    @Autowired
    private JwtProperties properties;

    /**
     * 用户授权
     * @param username
     * @param password
     * @return
     */
    @Override
    public String authentication(String username, String password) {

        try{
            //1.调用微服务查询用户信息
            User user = this.userClient.queryUser(username,password);
            //2.查询结果为空，则直接返回null
            if (user == null){
                return null;
            }
            //3.查询结果不为空，则生成token
            String token = JwtUtils.generateToken(new UserInfo(user.getId(), user.getUsername()),
                    properties.getPrivateKey(), properties.getExpire());
            System.out.println("=========== token ==========\n" + token);
            return token;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
