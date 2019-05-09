package com.hilkr.auth.client;

import com.hilkr.users.api.UsersApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 描述:
 * TODO
 *
 * @author hilkr
 */
@FeignClient(value = "users-service")
public interface UsersClient extends UsersApi {
}
