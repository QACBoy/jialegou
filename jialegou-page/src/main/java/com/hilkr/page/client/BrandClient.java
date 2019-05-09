package com.hilkr.page.client;

import com.hilkr.item.api.BrandApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 描述:
 * TODO
 *
 * @author hilkr
 */
@FeignClient(value = "item-service")
public interface BrandClient extends BrandApi {
}
