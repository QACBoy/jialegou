package com.hilkr.auth.service;

/**
 * 描述:
 * TODO
 *
 * @author hilkr
 */
public interface IAuthService {
    /**
     * 用户授权
     * @param username
     * @param password
     * @return
     */
    String authentication(String username, String password);
}
