package com.websocket.websocketdemo.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.websocket.websocketdemo.bean.User;
import com.websocket.websocketdemo.interceptor.UserInterceptor;
import com.websocket.websocketdemo.pojo.Result;
import com.websocket.websocketdemo.service.UserService;
import com.websocket.websocketdemo.ws.ChatEndPoint;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 洪辰
 * @since 2021-09-16
 */
@RestController
@Slf4j
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/login")
    public Result login(String userName, String pwd, HttpSession httpSession) throws JsonProcessingException {
        Result result = new Result();


        if (UserInterceptor.onLineUsers.containsKey(userName)){
            result.setFlag(false);
            log.info(userName+"已登录");
            result.setMessage(userName+"已登录");
            return result;
        }

        if (UserInterceptor.sessions.contains(httpSession)){
            result.setFlag(false);
            result.setMessage("一个浏览器登陆一次");
            return result;
        }
        User user=new User(userName,pwd);
        log.info(userName+"正在验证登录");
        User loginUser = userService.login(user);
        if (loginUser==null){
            result.setFlag(false);
            result.setMessage("用户或密码错误");
        }else {
            log.info(userName+"登入成功");
            result.setFlag(true);
            UserInterceptor.sessions.add(httpSession);
            httpSession.setAttribute("user",user.getUserName());
            httpSession.setAttribute("friends",userService.getFriends(user));
        }
        return result;


    }

    @PostMapping("/register")
    public Result register(@RequestParam String userName,
                           @RequestParam String inputPwd,
                           @RequestParam String SecondPwd, HttpSession httpSession){
        System.out.println(userName);
        System.out.println(inputPwd);
        System.out.println(SecondPwd);



        Result result=new Result();
        if (inputPwd.equals("") || SecondPwd.equals("")){
            result.setFlag(false);
            result.setMessage("密码不为空");
            return result;
        }else if (inputPwd.equals(SecondPwd)){
            log.info("密码校验成功");
            boolean loginFlag = userService.insertUser(new User(userName, inputPwd));
            if (loginFlag){
                result.setFlag(true);
                result.setMessage("添加成功");
                return result;
            }else {
                result.setFlag(false);
                result.setMessage("用户存在");
                return result;
            }

        }else {
            result.setFlag(false);
            result.setMessage("两次密码输入不一致");
            return result;
        }

    }


}

