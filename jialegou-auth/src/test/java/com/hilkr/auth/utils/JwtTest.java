package com.hilkr.auth.utils;

import com.hilkr.auth.entity.UserInfo;
import org.junit.Before;
import org.junit.Test;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;

/**
 * @Author: 98050
 * @Time: 2018-10-23 20:58
 * @Feature: JWT测试
 */
public class JwtTest {
    private static final String keyPath = "/Users/sam/Documents/Developer/swe15086/jialegou/jialegou-auth/src/main/resources/key/";

    private static final String pubKeyPath = keyPath + "rsa.pub";

    private static final String priKeyPath = keyPath + "rsa.pri";

    private PublicKey publicKey;

    private PrivateKey privateKey;

    @Test
    public void testRsa() throws Exception {
        RsaUtils.generateKey(pubKeyPath, priKeyPath, "234");
    }

    @Before
    public void testGetRsa() throws Exception {
        this.publicKey = RsaUtils.getPublicKey(pubKeyPath);
        this.privateKey = RsaUtils.getPrivateKey(priKeyPath);
    }

    @Test
    public void testGenerateToken() throws Exception {
        // 生成token
        String token = JwtUtils.generateToken(new UserInfo(20L, "jack"), privateKey, 5);
        System.out.println("token = " + token);
    }

    @Test
    public void testParseToken() throws Exception {
        String token = "eyJhbGciOiJSUzI1NiJ9.eyJpZCI6MjAsInVzZXJuYW1lIjoiamFjayIsImV4cCI6MTU0MDMwMjU4MX0.KFGDe8V8TwLl5xGqM1brPV50JXf3Z6G4cXPIeYxsqaeeol06BnXNNsyLAbUSrFxloUf-hQqO41O1OrtERllU-JfZXs6MA6rTBSfpar2MJRSZyDGKqfBpPoRED3yZv8oFuzI_94GONqsipmGyQFqWUkhTf9k0tZ5LPRvvGl9tkvc";

        // 解析token
        UserInfo user = JwtUtils.getInfoFromToken(token, publicKey);
        System.out.println("id: " + user.getId());
        System.out.println("userName: " + user.getUsername());
    }

    @Test
    public void date() {
        System.out.println(new Date());
    }
}