package com.leyou.service;

import com.leyou.auth.entity.UserInfo;
import com.leyou.interceptors.LoginInterceptor;
import com.leyou.pojo.Cart;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    //注入spring与redis集成的对象
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    static  final String KEY_PREFIX="ly:cart:uid";

    public void addCart(Cart cart) {
        //从线程域获取登录信息
        UserInfo userInfo = LoginInterceptor.getLoginUser();
        //一般购物车信息流动比较大,所以存储在redis里面
        //根据当前用户的id,查询redis存储的数据
        //Hash结构:hash+map(key+value)
        BoundHashOperations<String, Object, Object> ops = stringRedisTemplate.boundHashOps(KEY_PREFIX + userInfo.getId());
        //根据redis里面存储的商品id查询商品信息
        Object skuObj = ops.get(cart.getSkuId().toString());
        //判断redis是否有数据
        if(skuObj!=null){


        }else{

        }

    }
}
