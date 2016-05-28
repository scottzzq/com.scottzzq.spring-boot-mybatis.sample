package com.scottzzq.springboot.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scottzzq.springboot.example.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
	UserEntity findByEmail(String email);

	UserEntity findByName(String name);

	List<UserEntity> findAll();
}
