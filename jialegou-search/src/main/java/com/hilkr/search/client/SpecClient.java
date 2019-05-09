package com.hilkr.search.client;

import com.hilkr.item.api.SpecApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 描述:
 * TODO
 *
 * @author hilkr
 */
@FeignClient(value = "item-service")
public interface SpecClient extends SpecApi {
}
