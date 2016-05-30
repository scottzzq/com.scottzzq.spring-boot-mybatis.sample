package spring.boot.sample.jpa.usermanage.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Page;

import spring.boot.sample.jpa.usermanage.exception.UserNotFound;
import spring.boot.sample.jpa.usermanage.model.User;
import spring.boot.sample.jpa.usermanage.service.UserService;

public class Client {
	public static void printUser(User user){
		StringBuilder sb = new StringBuilder();
		sb.append("id="+user.getId());
		sb.append("name="+user.getName());
		sb.append("address="+user.getAddress());
		sb.append("phone="+user.getPhone());
		System.out.println(sb.toString());
	}
	
	public static void testList(UserService userService){
		List<User> users = userService.getUsers();
		if (users!=null){
			for (int i=0; i<users.size();i++){
				printUser(users.get(i));
				
			}
			
			
		}
		
	}
	
	public static <T> void printPageInfo(Page<T> pageInfo){
		StringBuilder sb = new StringBuilder();
		sb.append("��ǰ�ǵڼ�ҳ="+pageInfo.getNumber()).append("\n");
		sb.append("��ǰҳ��õļ�¼��="+pageInfo.getNumberOfElements()).append("\n");
		sb.append("ÿҳ��Ҫ��ѯ������="+pageInfo.getSize()).append("\n");
		sb.append("�ܹ����������ļ�¼��="+pageInfo.getTotalElements()).append("\n");
		sb.append("�ܹ���ҳ����="+pageInfo.getTotalPages()).append("\n");
		System.out.println(sb.toString());
	}
	
	public static void testListByConditonNoPage(UserService userService){
		List<User> users = userService.getUsersByConditionNoPage("136","·");
		if (users!=null){
			for (int i=0; i<users.size();i++){
				printUser(users.get(i));
			}			
		}
	}	

	public static void testListByConditonWithPage(UserService userService){
		//���������ͷ�ҳ��Ϣ
		Page<User> userPage = userService.getUsersByConditionWithPage("136","·",0,2);
		//��ӡ��ҳ��Ϣ
		printPageInfo(userPage);
		List<User> users = userPage.getContent();
		if (users!=null){
			for (int i=0; i<users.size();i++){
				printUser(users.get(i));			
			}		
		}
	}
	
	public static void testUpate(UserService userService) throws UserNotFound{
		User user = new User();
		user.setId(1);
		user.setAddress("����·121��");
		userService.updateUser(user);
		testList(userService);
	}
	
	public static void testAdd(UserService userService){
		User user = new User();
		user.setAddress("���·120��");
		user.setName("С��");
		user.setPhone("130000000");
		userService.addUser(user);
		testList(userService);
	}	
	
	public static void testDelete(UserService userService) throws UserNotFound{
		userService.deleteUser(9);
		testList(userService);
	}
	
	public static void main(String[] arg) throws UserNotFound{
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserService userService = (UserService) ac.getBean("userService");
		//testList(userService);
		//testUpate(userService);
		//testAdd(userService);
		//testDelete(userService);
		//testListByConditonNoPage(userService);
		testListByConditonWithPage(userService);		
	}
}
