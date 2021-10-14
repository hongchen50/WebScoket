package com.websocket.websocketdemo;

import com.websocket.websocketdemo.bean.User;
import com.websocket.websocketdemo.service.FriendService;
import com.websocket.websocketdemo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WebsocketDemoApplicationTests {

    @Autowired
    UserService userService;



    @Test
    void contextLoads() {
    }
    @Test
    void testService(){
        User user =new User();
        user.setUserName("洪辰");
        user.setPwd("456");

        //userService.insertUser(user);
        //userService.updateUser(user);
        //userService.deleteUser(user);
        userService.getFriends(user).forEach(System.out::println);
    }
    @Test
    void ArraysTest(){
        ArrayList<Integer> arr=new ArrayList<>();
        Collections.sort(arr);
        arr.get(arr.size()-1);

    }

}
