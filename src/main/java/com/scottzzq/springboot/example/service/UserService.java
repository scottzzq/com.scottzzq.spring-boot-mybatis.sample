package com.scottzzq.springboot.example.service;

import java.util.List;

import com.scottzzq.springboot.example.entity.UserEntity;

public interface UserService {
	List<UserEntity> findAll();
	UserEntity save(UserEntity user);
}
