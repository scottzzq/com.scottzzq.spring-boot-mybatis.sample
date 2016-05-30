package demo.sso.server.service;

import java.util.Set;

import org.springframework.stereotype.Component;

import demo.sso.server.model.Credential;
import demo.sso.server.model.DemoLoginUser;
import demo.sso.server.model.LoginUser;

@Component(value="demoAuthenticationHandler")
public class DemoAuthenticationHandler implements IAuthenticationHandler {
	@Override
	public LoginUser authenticate(Credential credential) throws Exception {
		if ("admin".equals(credential.getParameter("name"))
				&& "admin".equals(credential.getParameter("passwd"))) {
			DemoLoginUser user = new DemoLoginUser();
			user.setLoginName("admin");
			return user;
		} else {
			credential.setError("帐号或密码错误");
			return null;
		}
	}

	@Override
	public Set<String> authedSystemIds(LoginUser loginUser) throws Exception {
		return null;
	}
}