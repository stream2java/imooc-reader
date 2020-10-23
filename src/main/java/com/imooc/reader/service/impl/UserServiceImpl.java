package com.imooc.reader.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.imooc.reader.entity.Member;
import com.imooc.reader.entity.User;
import com.imooc.reader.mapper.UserMapper;
import com.imooc.reader.service.UserService;
import com.imooc.reader.service.exception.BussinessException;
import com.imooc.reader.util.MD5Utils;
import org.apache.commons.codec.digest.Md5Crypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    public User createUser(String username, String password) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        List<User> users = userMapper.selectList(queryWrapper);
        if (users.size() > 0) {
            throw new BussinessException("U01", "管理員名稱已存在");
        }
        User user = new User();
        user.setUsername(username);
        int salt = new Random().nextInt(1000) + 1000;
        String md5 = MD5Utils.md5Digest(password, salt);
        user.setPassword(md5);
        user.setSalt(salt);
        userMapper.insert(user);
        return user;
    }

    public User checkLogin(String username, String password) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        User user = userMapper.selectOne(queryWrapper);
        if (user == null){
            throw new BussinessException("U01","管理員不存在");
        }
        String md5 = MD5Utils.md5Digest(password, user.getSalt());
        if (!md5.equals(user.getPassword())){
            throw new BussinessException("U02","輸入密碼有誤");
        }
        return user;
    }
}
