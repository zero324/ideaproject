package com.edu.service.impl;

import com.edu.mapper.UserMapper;
import com.edu.pojo.User;
import com.edu.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public List<User> selectList() {
        return userMapper.selectList();
    }
}
