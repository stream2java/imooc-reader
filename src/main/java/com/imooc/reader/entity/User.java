package com.imooc.reader.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;


@TableName("user")
public class User {
    public Long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {

        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    public Integer getSalt() {

        return salt;
    }

    public void setSalt(Integer salt) {

        this.salt = salt;
    }

    public void setUserId(Long userId) {

        this.userId = userId;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username +
                "password=" + password +
                "salt=" + salt +
                '}';
    }

    @TableId(type = IdType.AUTO)
    private Long userId;


    private String username;
    private String password;
    private Integer salt;

}
