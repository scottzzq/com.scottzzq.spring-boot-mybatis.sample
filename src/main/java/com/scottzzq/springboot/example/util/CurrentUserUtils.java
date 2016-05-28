package com.scottzzq.springboot.example.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.scottzzq.springboot.example.entity.UserEntity;

/**
 * 管理当前当前登录对象
 * 
 * @author lance 2014-06-14
 */
public class CurrentUserUtils {
	private final String CUR_USER = "cur_user";
	public static CurrentUserUtils INSTANCE = null;

	static {
		INSTANCE = new CurrentUserUtils();
	}

	private CurrentUserUtils() {
	}

	/**
	 * 获取实例
	 * 
	 * @return
	 */
	public static CurrentUserUtils getInstance() {
		return INSTANCE;
	}

	/**
	 * 获取当前Request
	 * 
	 * @return
	 */
	private HttpServletRequest getRequest() {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		return requestAttributes.getRequest();
	}

	/**
	 * 获取当前Session
	 * 
	 * @return
	 */
	private HttpSession getSession() {
		return getRequest().getSession(true);
	}

	/**
	 * 获取当前session里面放置的User对象
	 * 
	 * @return
	 */
	public UserEntity getUser() {
		return (UserEntity) getSession().getAttribute(CUR_USER);
	}

	/**
	 * 把当前User对象放置到session里面
	 * 
	 * @param user
	 */
	public void setUser(UserEntity user) {
		getSession().setAttribute(CUR_USER, user);
	}
}
