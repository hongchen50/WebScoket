package com.websocket.websocketdemo.mapper;

import com.websocket.websocketdemo.bean.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 洪辰
 * @since 2021-09-16
 */
@Component
public interface UserMapper extends BaseMapper<User> {
    public User login(@Param("user") User user);
    public CopyOnWriteArrayList<String> selectFriends(String userName);

}
