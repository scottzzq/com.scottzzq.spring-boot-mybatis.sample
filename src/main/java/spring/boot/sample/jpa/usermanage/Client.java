package spring.boot.sample.jpa.usermanage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import spring.boot.sample.jpa.usermanage.exception.UserNotFound;
import spring.boot.sample.jpa.usermanage.model.User;
import spring.boot.sample.jpa.usermanage.service.UserService;

@Component
public class Client {
	@Autowired
	@Qualifier("userServiceImpl") 
	UserService userService;
	
	public void printUser(User user) {
		StringBuilder sb = new StringBuilder();
		sb.append("id=" + user.getId());
		sb.append("name=" + user.getUsername());
		sb.append("address=" + user.getAddress());
		sb.append("phone=" + user.getPhone());
		System.out.println(sb.toString());
	}

	public void testList(UserService userService) {
		List<User> users = userService.getUsers();
		if (users != null) {
			for (int i = 0; i < users.size(); i++) {
				printUser(users.get(i));
			}
		}
	}

	public static <T> void printPageInfo(Page<T> pageInfo) {
		StringBuilder sb = new StringBuilder();
		sb.append("当前是第几页=" + pageInfo.getNumber()).append("\n");
		sb.append("当前页查得的记录数=" + pageInfo.getNumberOfElements()).append("\n");
		sb.append("每页需要查询的条数=" + pageInfo.getSize()).append("\n");
		sb.append("总共符合条件的记录数=" + pageInfo.getTotalElements()).append("\n");
		sb.append("总共的页数是=" + pageInfo.getTotalPages()).append("\n");
		System.out.println(sb.toString());

	}

	public void testListByConditonNoPage(UserService userService) {
		List<User> users = userService.getUsersByConditionNoPage("136", "路");
		if (users != null) {
			for (int i = 0; i < users.size(); i++) {
				printUser(users.get(i));
			}
		}
	}

	public void testListByConditonWithPage(UserService userService) {
		// 传入条件和分页信息
		Page<User> userPage = userService.getUsersByConditionWithPage("136", "路", 0, 2);
		// 打印分页信息
		printPageInfo(userPage);
		List<User> users = userPage.getContent();
		if (users != null) {
			for (int i = 0; i < users.size(); i++) {
				printUser(users.get(i));
			}
		}
	}

	public void testUpate(UserService userService) throws UserNotFound {
		User user = new User();
		user.setId(1);
		user.setAddress("北京路121号");
		userService.updateUser(user);
		testList(userService);

	}

	public void testAdd(UserService userService) {
		User user = new User();
		user.setAddress("天河路120号");
		user.setUsername("小徐");
		user.setPhone("130000000");
		userService.addUser(user);
		testList(userService);
	}

	public void testDelete(UserService userService) throws UserNotFound {
		userService.deleteUser(9);
		testList(userService);
	}

	public void  test() throws UserNotFound {
		//UserService userService = (UserService) ac.getBean("userService");
		testList(userService);
		// testUpate(userService);
		// testAdd(userService);
		// testDelete(userService);
		// testListByConditonNoPage(userService);
		//testListByConditonWithPage(userService);
	}
}
