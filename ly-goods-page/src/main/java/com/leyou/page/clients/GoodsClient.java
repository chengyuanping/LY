package com.leyou.page.clients;


import com.leyou.item.api.GoodsApi;
import com.leyou.item.pojo.Spu;
import org.springframework.cloud.openfeign.FeignClient;


@FeignClient("item-service")
public interface GoodsClient   extends GoodsApi {



}
