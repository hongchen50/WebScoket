package com.websocket.websocketdemo.pojo;

import com.websocket.websocketdemo.enmu.SystemCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//用户间传送的消息
public class ResultMessage {
    private int code;
    private String fromName;
    //private String toName;
    private Object message;


}
