package com.websocket.websocketdemo.service;

import com.websocket.websocketdemo.bean.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 洪辰
 * @since 2021-09-16
 */
public interface UserService extends IService<User> {
    public User login(User user);
    public boolean insertUser(User user);
    public boolean updateUser(User user);
    public boolean deleteUser(User user);
    public CopyOnWriteArrayList<String> getFriends(User user);




}
