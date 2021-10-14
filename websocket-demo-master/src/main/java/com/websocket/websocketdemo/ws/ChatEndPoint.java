package com.websocket.websocketdemo.ws;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.websocket.websocketdemo.enmu.SystemCode;
import com.websocket.websocketdemo.interceptor.UserInterceptor;
import com.websocket.websocketdemo.pojo.Message;
import com.websocket.websocketdemo.utils.MessageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

@Slf4j
@ServerEndpoint(value = "/chat",configurator = GetHttpSessionConfigurator.class)
@Component
public class ChatEndPoint {
    //用线程安全的map来保存当前用户
    public static Map<String,ChatEndPoint> onLineUsers = new ConcurrentHashMap<>();
    //声明一个session对象，通过该对象可以发送消息给指定用户，不能设置为静态，每个ChatEndPoint有一个session才能区分.(websocket的session)
    private Session session;
    //保存当前登录浏览器的用户
    private HttpSession httpSession;
    //保存好友状态
    private Map<String,Boolean> friends = new ConcurrentHashMap<>();

    //建立连接时发送系统广播
    @OnOpen
    public void onOpen(Session session, EndpointConfig config) throws IOException {
        this.session = session;
        HttpSession httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        this.httpSession = httpSession;
        //获得用户名
        String username = (String) httpSession.getAttribute("user");
        //获得好友列表
        CopyOnWriteArrayList<String> friendsList = (CopyOnWriteArrayList<String>)httpSession.getAttribute("friends");
        //初始化好友状态表
        initFriendsMap(friendsList);
        //添加对象
        onLineUsers.put(username,this);
        //上线
        onlineFriends(username);
        //广播
        broadcastAllUsers(onLineUsers.keySet());
        //

    }
    //用户之间的信息发送
    @OnMessage
    public void onMessage(String message,Session session){
        try{
            ObjectMapper mapper = new ObjectMapper();
            Message mess = mapper.readValue(message,Message.class);
            String toName = mess.getToName();
            String data = mess.getMessage();
            String username = (String) httpSession.getAttribute("user");
            log.info(username + "向"+toName+"发送的消息：" + data);
            String resultMessage = MessageUtils.getMessage(SystemCode.USER_INFO.GetVal(),username,data);
            if(StringUtils.hasLength(toName)) {
                onLineUsers.get(toName).session.getBasicRemote().sendText(resultMessage);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //用户断开连接的断后操作
    @OnClose
    public void onClose(Session session) throws IOException {
        String username = (String) httpSession.getAttribute("user");
        log.info("离线用户："+ username);
        if (username != null){
            onLineUsers.remove(username);
            UserInterceptor.onLineUsers.remove(username);
        }
        logOutFriends(username);
        httpSession.removeAttribute("user");
        UserInterceptor.sessions.remove(httpSession);
        httpSession.invalidate();
        log.info(username+"session销毁");
        //广播
        broadcastAllUsers(onLineUsers);

    }
    //获取当前登录的用户
    private Set<String> getNames(){
        return onLineUsers.keySet();
    }
    //获取好友信息

    //好友上线
    private void onlineFriends(String userName) throws IOException {
        Set<String> keySet = onLineUsers.keySet();
        sendTo(userName,onLineUsers.get(userName).friends);
        for (String s : keySet) {
            if (onLineUsers.get(s).friends.containsKey(userName)){
                onLineUsers.get(s).friends.put(userName,true);
                sendTo(s, onLineUsers.get(s).friends);
            }
        }

    }
    //好友下线
    private void logOutFriends(String userName) throws IOException {
        Set<String> keySet = onLineUsers.keySet();
        for (String s : keySet) {
            if (onLineUsers.get(s).friends.containsKey(userName)){
                onLineUsers.get(s).friends.put(userName,false);
                sendTo(s, onLineUsers.get(s).friends);
            }
        }
    }
    //初始化好友集合
    private void initFriendsMap(List<String> friendList){
        for (String friend : friendList) {
            if (onLineUsers.containsKey(friend)){
                friends.put(friend,true);
            }else {
                friends.put(friend,false);
            }

        }
    }
    //像特定对象推送消息
    private void broadcastToFriends(String message,Set<String> target) throws IOException {
        for (String s : target) {
            if (onLineUsers.containsKey(s)){
                onLineUsers.get(s).session.getBasicRemote().sendText(message);
            }
        }
    }
    //向某人推送消息
    private void sendTo(String userName,Object message) throws IOException {
        String msg = MessageUtils.getMessage(SystemCode.FRIEND_INFO.GetVal(),null,message);
        onLineUsers.get(userName).session.getBasicRemote().sendText(msg);
    }
    private void sendTo(String userName,Object message,SystemCode code) throws IOException {
        String msg = MessageUtils.getMessage(code.GetVal(),null,message);
        onLineUsers.get(userName).session.getBasicRemote().sendText(msg);
    }
    //发送系统消息
    private void broadcastAllUsers(Object message){
        try{
            Set<String> names = onLineUsers.keySet();
            for(String name : names){
                sendTo(name,message,SystemCode.SYSTEM_INFO);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
