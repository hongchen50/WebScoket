package com.websocket.websocketdemo.enmu;

import lombok.Data;


public enum SystemCode {
    SYSTEM_INFO(0),
    USER_INFO(1),
    FRIEND_INFO(2);
    int x;

    private SystemCode(int x){
        this.x=x;
    }
    public int GetVal(){
        return x;
    }
}
