package com.example.service;

import com.example.entity.User;

public interface UserService {

    public User loginCheck(String id, String pass);
    
    public int CreateUser(String name, String loginId, String pass);
}
