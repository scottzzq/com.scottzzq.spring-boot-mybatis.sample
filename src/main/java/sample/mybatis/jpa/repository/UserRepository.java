package sample.mybatis.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sample.mybatis.domain.User;

public interface UserRepository extends JpaRepository<User,String>{
}