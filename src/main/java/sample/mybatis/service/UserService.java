package sample.mybatis.service;

import sample.mybatis.domain.User;
import sample.mybatis.mapper.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Autowired
	private UserMapper userMapper;

	public User readUserByUsername(String username){
		User selectUser = userMapper.selectUser(username);
		return selectUser;
	}
	public Integer readUserCount(){
		Integer count = userMapper.selectUserCount();
		return count;
	}
}
