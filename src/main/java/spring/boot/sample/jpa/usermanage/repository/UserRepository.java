package spring.boot.sample.jpa.usermanage.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import spring.boot.sample.jpa.usermanage.model.User;

public interface UserRepository extends JpaRepository<User,Integer> {	
	//select * from user where phone like '136%' and address like '%·%' order by phone desc limit 0,2
	List<User> findTop2ByPhoneStartingWithAndAddressContainingOrderByPhoneDesc(String phone,String address);
	List<User> findTop2ByPhoneStartingWithAndAddressContaining(String phone,String address,Sort sort);
	Page<User> findByPhoneStartingWithAndAddressContaining(String phone,String address,Pageable pageable);
}
