package com.websocket.websocketdemo.bean;


import lombok.Data;

import java.util.concurrent.CopyOnWriteArrayList;

@Data
public class UserAndFriends {
    User user;
    private CopyOnWriteArrayList<String> friends;
}
