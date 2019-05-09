package com.hilkr.common.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 描述:
 * 密码加密
 *
 * @author hilkr
 */
public class CodecUtils {

    public static String passwordBcryptEncode(String username,String password){

        return new BCryptPasswordEncoder().encode(username + password);
    }

    public static Boolean passwordConfirm(String rawPassword,String encodePassword){
        return new BCryptPasswordEncoder().matches(rawPassword,encodePassword);
    }
}
