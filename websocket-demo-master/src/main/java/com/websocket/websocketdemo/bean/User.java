package com.websocket.websocketdemo.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import java.io.Serializable;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
@NoArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "user_name", type = IdType.INPUT)
    private String userName;

    private String pwd;

    //private CopyOnWriteArrayList<String> friends;

    @TableLogic
    private Boolean deleted;

    public User(String userName,String pwd){
        this.userName=userName;
        this.pwd=pwd;
    }


}
