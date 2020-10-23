package com.imooc.reader.service;


import com.imooc.reader.entity.User;

public interface UserService {
    public User createUser(String username, String password);
    public User checkLogin(String username, String password);

}
