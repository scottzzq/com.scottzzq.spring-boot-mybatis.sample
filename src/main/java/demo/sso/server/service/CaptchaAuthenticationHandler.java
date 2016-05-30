package demo.sso.server.service;

import java.util.Set;

import org.springframework.stereotype.Component;

import demo.sso.server.model.Credential;
import demo.sso.server.model.DemoLoginUser;
import demo.sso.server.model.LoginUser;

@Component(value="captchaAuthenticationHandler")
public class CaptchaAuthenticationHandler implements IAuthenticationHandler {
	@Override
	public LoginUser authenticate(Credential credential) throws Exception {
		// 获取session中保存的验证码
		String sessionCode = (String) credential.getSettedSessionValue();
		String captcha = credential.getParameter("captcha");
		if (!captcha.equalsIgnoreCase(sessionCode)) {
			credential.setError("验证码错误");
			return null;
		}
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
