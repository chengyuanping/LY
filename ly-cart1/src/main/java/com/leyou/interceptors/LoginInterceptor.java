package com.leyou.interceptors;

import com.leyou.auth.entity.UserInfo;
import com.leyou.auth.utils.JwtUtils;
import com.leyou.common.utils.CookieUtils;
import com.leyou.config.JwtProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//作用:获取用户信息返回给控制层
//编写一个拦截器,用来拦截用户登录信息,然后解密登录信息并存放到线程域

public class LoginInterceptor extends HandlerInterceptorAdapter {

    private JwtProperties jwtProperties;
    //定义一个线程域，存放登录用户
    //同一线程的应用可以获取这个登录用户
    private  static  final  ThreadLocal<UserInfo> t1=new ThreadLocal<>();

    //使拦截器生效并指定要拦截的对象:导入配置文件MvcConfig
    public  LoginInterceptor(JwtProperties jwtProperties){

        this.jwtProperties=jwtProperties;
    }

    //当用户使用添加到购物车时,拦截器会拦截到request携带的cookie,而cookie中含有登录信息token
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //查询token
        String token = CookieUtils.getCookieValue(request, "LY_TOKEN");
        //判断cookie是否有token,就是说判断下是否登录
        if(StringUtils.isBlank(token)){
            //如果没登录,就返回异常
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return  false;
        }
        //如果登录,就用公钥进行解密
        //config文件夹导入公钥配置文件:产生一个公钥
       try{
           UserInfo userInfo = JwtUtils.getInfoFromToken(token, jwtProperties.getPublicKey());
           //userinfo放到线程对象
           t1.set(userInfo);
           return true;

       }catch (Exception e){
           response.setStatus(HttpStatus.UNAUTHORIZED.value());
           return  false;

       }

    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }
    //定义一个从线程域获取这个登录信息的方法
    public  static  UserInfo getLoginUser(){
         return  t1.get();

    }
}