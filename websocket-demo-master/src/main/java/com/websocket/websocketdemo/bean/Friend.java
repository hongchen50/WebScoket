package com.websocket.websocketdemo.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author 洪辰
 * @since 2021-09-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Friend implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "friend_id", type = IdType.ASSIGN_UUID)
    private String friendId;

    private String userName;

    private String friendName;


    @TableLogic
    private Boolean deleted;


}
