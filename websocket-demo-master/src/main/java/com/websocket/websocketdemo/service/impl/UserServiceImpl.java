package com.websocket.websocketdemo.service.impl;

import com.websocket.websocketdemo.bean.User;
import com.websocket.websocketdemo.mapper.UserMapper;
import com.websocket.websocketdemo.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.lang.model.element.VariableElement;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 洪辰
 * @since 2021-09-16
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public User login(User user) {





        return userMapper.login(user);
    }

    @Override
    public boolean insertUser(User user) {
        if (userMapper.selectById(user.getUserName()) == null) {
            return save(user);
        } else {
            return false;
        }

    }

    @Override
    public boolean updateUser(User user) {
        if (userMapper.selectById(user.getUserName()) == null) {
            return false;
        } else {

            return updateById(user);
        }
    }

    @Override
    public boolean deleteUser(User user) {
        return removeById(user);
    }

    @Override
    public CopyOnWriteArrayList<String> getFriends(User user) {
        CopyOnWriteArrayList<String> strings = userMapper.selectFriends(user.getUserName());
        //user.setFriends(strings);
        return strings;
    }


}
