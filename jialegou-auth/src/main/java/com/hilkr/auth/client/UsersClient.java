package com.hilkr.auth.client;

import com.hilkr.users.api.UsersApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 描述:
 * TODO
 *
 * @author sam
 * @create 2019-04-21
 */
@FeignClient(value = "users-service")
public interface UsersClient extends UsersApi {
}
