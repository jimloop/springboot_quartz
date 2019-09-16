package com.example.quartz.service.beanservice;

import com.example.quartz.domain.User;

public interface UserService {
    User getUser(Long userId);
    String getIdByName(String name);
    User gerUserByName(String name);
}
