package demo.sso.server;

import demo.sso.server.service.IAuthenticationHandler;
import org.springframework.stereotype.Component;

/**
 * 应用配置信息
 * 
 * @author Administrator
 *
 */
@Component
public class Config {
	public static final String SESSION_ATTR_NAME = "login_session_attr_name";
	private IAuthenticationHandler authenticationHandler; // 鉴权处理器
	private String loginViewName = "login_sso.jsp"; // 登录页面视图名称

	/**
	 * 获取当前鉴权处理器
	 * 
	 * @return
	 */
	public IAuthenticationHandler getAuthenticationHandler() {
		return authenticationHandler;
	}

	public void setAuthenticationHandler(
			IAuthenticationHandler authenticationHandler) {
		this.authenticationHandler = authenticationHandler;
	}

	/**
	 * 获取登录页面视图名称
	 * 
	 * @return
	 */
	public String getLoginViewName() {
		return loginViewName;
	}

}
