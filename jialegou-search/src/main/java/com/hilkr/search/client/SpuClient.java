package com.hilkr.search.client;

import com.hilkr.item.api.SpuApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 描述:
 * TODO
 *
 * @author hilkr
 */
@FeignClient(value = "item-service")
public interface SpuClient extends SpuApi {
}
