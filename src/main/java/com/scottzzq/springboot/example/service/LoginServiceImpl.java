package com.scottzzq.springboot.example.service;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scottzzq.springboot.example.entity.UserEntity;
import com.scottzzq.springboot.example.repository.UserRepository;

@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	UserRepository userRepository;
	/**
	 * 用户登录
	 * 
	 * @author lance 2014-6-11下午11:26:05
	 * @param user
	 * @return
	 */
	public UserEntity login(UserEntity user) {
		if (StringUtils.isBlank(user.getEmail())) {
			throw new ServiceException("用户名不能为空");
		}

		if (StringUtils.isBlank(user.getPassword())) {
			throw new ServiceException("密码不能为空");
		}

		UserEntity userEntity = userRepository.findByEmail(user.getEmail());
		if (null == userEntity) {
			throw new ServiceException("用户名不存在");
		}

		String password = user.getPassword();
		if (!StringUtils.equals(password, userEntity.getPassword())) {
			throw new ServiceException("密码输入错误");
		}
		return userEntity;
	}
}
