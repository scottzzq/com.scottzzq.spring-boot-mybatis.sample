package com.scottzzq.springboot.example.service;

import com.scottzzq.springboot.example.entity.UserEntity;

public interface LoginService {
	UserEntity login(UserEntity user);
}
