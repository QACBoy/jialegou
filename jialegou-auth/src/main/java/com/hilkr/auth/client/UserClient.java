package com.hilkr.auth.client;

import com.hilkr.users.api.UserApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 描述:
 * TODO
 *
 * @author sam
 * @create 2019-04-21
 */
@FeignClient(value = "user-service")
public interface UserClient extends UserApi {
}
