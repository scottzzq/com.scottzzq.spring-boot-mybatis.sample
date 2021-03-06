package demo.sso.server.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import demo.sso.common.CookieUtil;
import demo.sso.common.StringUtil;
import demo.sso.server.Config;
import demo.sso.server.TokenManager;
import demo.sso.server.model.ClientSystem;
import demo.sso.server.model.Credential;
import demo.sso.server.model.LoginUser;
import demo.sso.server.service.IPreLoginHandler;

@Controller
public class LoginController {
	@Autowired
	private Config config;
	@Autowired
	private TokenManager tokenManager;
	/**
	 * 登录入口
	 * 
	 * @param request
	 * @param backUrl
	 * @param response
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(HttpServletRequest request, String backUrl,
			HttpServletResponse response, ModelMap map) throws Exception {
				String vt = CookieUtil.getCookie("VT", request);
		if (vt == null) { // VT不存在
			String lt = CookieUtil.getCookie("LT", request);
			if (lt == null) { // VT不存在，LT也不存在
				return config.getLoginViewName();
			} else { // VT不存在， LT存在
				// TODO: 自动登录流程
				return null;
			}
		} else { // VT存在
			LoginUser loginUser = tokenManager.validate(vt);
			if (loginUser != null) { // VT有效
				return validateSuccess(backUrl, vt, loginUser, response, map); // 验证成功后操作
			} else { // VT 失效，转入登录页
				return config.getLoginViewName();
			}
		}
	}

	@RequestMapping("/preLogin")
	@ResponseBody
	public Object preLogin(HttpSession session) throws Exception {
		IPreLoginHandler preLoginHandler = config.getPreLoginHandler();
		if (preLoginHandler == null) {
			throw new Exception("没有配置preLoginHandler,无法执行预处理");
		}
		return preLoginHandler.handle(session);
	}

	// VT验证成功或登录成功后的操作
	private String validateSuccess(String backUrl, String vt,
			LoginUser loginUser, HttpServletResponse response, ModelMap map)
			throws Exception {
		if (backUrl != null) {
			response.sendRedirect(StringUtil.appendUrlParameter(backUrl, "VT",vt));
			return null;
		} else {
			map.put("sysList", config.getClientSystems(loginUser));
			map.put("vt", vt);
			map.put("loginUser", loginUser);
			return config.getLoginViewName();
		}
	}

	/**
	 * 登录验证
	 * 
	 * @param backUrl
	 * @param rememberMe
	 * @param request
	 * @param session
	 * @param response
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST )
	public String login(String backUrl, Boolean rememberMe,
			HttpServletRequest request, HttpSession session,
			HttpServletResponse response, ModelMap map) throws Exception {
		final Map<String, String[]> params = request.getParameterMap();
		final Object sessionVal = session.getAttribute(IPreLoginHandler.SESSION_ATTR_NAME);
		Credential credential = new Credential() {
			@Override
			public String getParameter(String name) {
				String[] tmp = params.get(name);
				return tmp != null && tmp.length > 0 ? tmp[0] : null;
			}
			@Override
			public String[] getParameterValue(String name) {
				return params.get(name);
			}

			@Override
			public Object getSettedSessionValue() {
				return sessionVal;
			}
		};
		LoginUser loginUser = config.getAuthenticationHandler().authenticate(credential);
		if (loginUser == null) {
			map.put("errorMsg", credential.getError());
			return config.getLoginViewName();
		} else {
			String vt = authSuccess(response, loginUser, rememberMe);
			return validateSuccess(backUrl, vt, loginUser, response, map);
		}
	}

	/**
	 * 用户退出
	 * 
	 * @param backUrl
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/logout")
	public String logout(String backUrl, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String vt = CookieUtil.getCookie("VT", request);
		// 移除token
		tokenManager.invalid(vt);
		// 移除server端vt cookie
		Cookie cookie = new Cookie("VT", null);
		cookie.setMaxAge(0);
		response.addCookie(cookie);

		// 通知各客户端logout
		for (ClientSystem clientSystem : config.getClientSystems()) {
			clientSystem.noticeLogout(vt);
		}

		if (backUrl == null) {
			return "/logout";
		} else {
			response.sendRedirect(backUrl);
			return null;
		}
	}

	// 授权成功后的操作
	private String authSuccess(HttpServletResponse response,
			LoginUser loginUser, Boolean rememberMe) {
		// 生成VT
		String vt = StringUtil.uniqueKey();
		// 生成LT？
		// TODO: 自动登录标识生成
		// 存入Map
		tokenManager.addToken(vt, loginUser);
		// 写 Cookie
		Cookie cookie = new Cookie("VT", vt);
		response.addCookie(cookie);
		return vt;
	}
}
