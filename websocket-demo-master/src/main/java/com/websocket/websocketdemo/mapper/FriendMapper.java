package com.websocket.websocketdemo.mapper;

import com.websocket.websocketdemo.bean.Friend;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.websocket.websocketdemo.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 洪辰
 * @since 2021-09-16
 */

@Component
public interface FriendMapper extends BaseMapper<Friend> {


}
