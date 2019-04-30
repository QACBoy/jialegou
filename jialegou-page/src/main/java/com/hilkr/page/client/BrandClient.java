package com.hilkr.page.client;

import com.hilkr.item.api.BrandApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 描述: <br>
 * TODO
 *
 * @author sam
 * @create 2019-04-10
 */
@FeignClient(value = "item-service")
public interface BrandClient extends BrandApi {
}
