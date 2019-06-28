package com.leyou.user.controller;

import com.leyou.user.pojo.User;
import com.leyou.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    //校验前台发来的数据  用户名 手机号
    @GetMapping("/check/{data}/{type}")//data 数据 type类型
    public ResponseEntity<Boolean> check(@PathVariable("data") String data,@PathVariable("type") Integer type){
        //判断数据是否唯一
        Boolean boo=userService.checkData(data,type);
        if(boo==null){

            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return   ResponseEntity.ok(boo);

    }
    //发送验证码
    @PostMapping("code")
    public ResponseEntity<Void> sendVerfyCode(@RequestParam("phone") String phone){
        Boolean b=userService.sendVerfyCode(phone);
        if(b==null){

            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return  new  ResponseEntity<>(HttpStatus.CREATED);
    }
    //注册用户
    @PostMapping("register")
    public ResponseEntity<Void> createUser(@Valid User user, @RequestParam("code") String code){
        Boolean b= userService.register(user,code);
        if(b==null){

            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return  new  ResponseEntity<>(HttpStatus.CREATED);
    }

    //登录
    @PostMapping("login")
    public  ResponseEntity<User> queryUser(@RequestParam("username") String username,@RequestParam("password") String password){
        User user= userService.queryUser(username,password);
        if(user!=null){

            return   ResponseEntity.ok(user);
        }

        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}
