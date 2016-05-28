package sample.mybatis.mapper;

import sample.mybatis.domain.User;

public interface UserMapper {
	User selectUser(String username);
	Integer selectUserCount();
}
