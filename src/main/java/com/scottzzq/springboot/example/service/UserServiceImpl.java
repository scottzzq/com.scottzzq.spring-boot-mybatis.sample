package com.scottzzq.springboot.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scottzzq.springboot.example.entity.UserEntity;
import com.scottzzq.springboot.example.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	
	public List<UserEntity> findAll() {
		return userRepository.findAll();
	}
	public UserEntity save(UserEntity user) {
		return userRepository.save(user);
	}
}
