package com.hilkr.auth.utils;

import com.hilkr.auth.entity.UserInfo;
import org.junit.Before;
import org.junit.Test;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;

/**
 * 描述:
 * TODO
 *
 * @author hilkr
 */
public class JwtTest {
    private static final String keyPath = "/Users/sam/Documents/Developer/swe15086/jialegou/jialegou-auth/src/main/resources/key/";

    private static final String pubKeyPath = keyPath + "rsa.pub";

    private static final String priKeyPath = keyPath + "rsa.pri";

    private PublicKey publicKey;

    private PrivateKey privateKey;

    private final String secret = "jialegou@Login(Auth}*^31)&hilkr%f3q2";
    /**
     * 生成秘钥（注意：秘钥生成之前需要注释 testGetRsa() 方法前的 @Before 注解 ）
     *
     * @throws Exception
     */
    @Test
    public void testRsa() throws Exception {
        RsaUtils.generateKey(pubKeyPath, priKeyPath, secret);
    }

    /**
     * 获取 公钥，私钥
     *
     * @throws Exception
     */
    @Before
    public void testGetRsa() throws Exception {
        this.publicKey = RsaUtils.getPublicKey(pubKeyPath);
        this.privateKey = RsaUtils.getPrivateKey(priKeyPath);
    }

    /**
     * 生成token
     *
     * @throws Exception
     */
    @Test
    public void testGenerateToken() throws Exception {
        // 生成token
        String token = JwtUtils.generateToken(new UserInfo(20L, "hilkr"), privateKey, 5);
        System.out.println("token = " + token);
    }

    /**
     * 解析token
     *
     * @throws Exception
     */
    @Test
    public void testParseToken() throws Exception {
        String token = "eyJhbGciOiJSUzI1NiJ9.eyJpZCI6MjAsInVzZXJuYW1lIjoiaGlsa3IiLCJleHAiOjE1NTcxMjY4MjZ9.IyxbamecNB5bvaQIrEveLzrUh1gpFlLmZls5vDPg8YN6IE19zedK1k42LWH6P8IvIZu4XY2g8A9_9bgv23exDtqlbPi3mxI__aSmJhsaqFfnokjEnNNL4TL6WUD2W3akLgjHrTrnErMN0BD9tMnPiogzP3t9V-4v1BQ7B8utoDw";

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