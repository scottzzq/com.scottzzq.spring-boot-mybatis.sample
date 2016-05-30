package demo.sso.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import demo.sso.server.model.ClientSystem;
import demo.sso.server.model.LoginUser;
import demo.sso.server.service.IAuthenticationHandler;
import demo.sso.server.service.IPreLoginHandler;

@Component
public class Config{
	@Autowired
	@Qualifier("captchaAuthenticationHandler") 
	IAuthenticationHandler authenticationHandler; // 鉴权处理器
	
	@Autowired
	@Qualifier("captchaPreLoginHandler")
	IPreLoginHandler preLoginHandler; // 登录前预处理器
	private String loginViewName = "login_sso2.jsp"; // 登录页面视图名称

	@Value("${tokenTimeout}")
	private int tokenTimeout; // 令牌有效期，单位为分钟，默认30分钟
	
	@Autowired 
	private ClientConfig clientConfig;

	public IAuthenticationHandler getAuthenticationHandler(){
		return authenticationHandler;
	}
	
	public IPreLoginHandler getPreLoginHandler(){
		return preLoginHandler;
	}
	/**
	 * 应用停止时执行，做清理性工作，如通知客户端logout
	 */
	public void destroy() {
		for (ClientSystem clientSystem : clientConfig.getClientSystems()) {
			clientSystem.noticeShutdown();
		}
	}
	/**
	 * 获取登录页面视图名称
	 * 
	 * @return
	 */
	public String getLoginViewName() {
		return loginViewName;
	}

	public void setLoginViewName(String loginViewName) {
		this.loginViewName = loginViewName;
	}

	/**
	 * 获取令牌有效期，单位为分钟
	 * 
	 * @return
	 */
	public int getTokenTimeout() {
		return tokenTimeout;
	}
	/**
	 * 获取指定用户的可用系统列表
	 * 
	 * @param loginUser
	 * @return
	 * @throws Exception
	 */
	public List<ClientSystem> getClientSystems(LoginUser loginUser)
			throws Exception {
		Set<String> authedSysIds = authenticationHandler.authedSystemIds(loginUser);
		// null表示允许全部
		if (authedSysIds == null) {
			return clientConfig.getClientSystems();
		}
		List<ClientSystem> auhtedSystems = new ArrayList<ClientSystem>();
		for (ClientSystem clientSystem : clientConfig.getClientSystems()) {
			if (authedSysIds.contains(clientSystem.getId())) {
				auhtedSystems.add(clientSystem);
			}
		}
		return auhtedSystems;
	}

	public List<ClientSystem> getClientSystems() {
		return clientConfig.getClientSystems();
	}
}
