package demo.sso.server.controller;

import demo.sso.common.StringUtil;
import demo.sso.server.Config;
import demo.sso.server.model.Credential;
import demo.sso.server.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@Controller
public class LoginController {
	@Autowired
	private Config config;
	
	/**
	 * 登录入口
	 * @param request
	 * @param backUrl
	 * @param response
	 * @param map
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/login", method=RequestMethod.GET)
	public String login(HttpServletRequest request, String backUrl,
			HttpServletResponse response, ModelMap map) throws IOException {
		return "login";
//		String vt = CookieUtil.getCookie("VT", request);
//		if (vt == null) { // VT不存在
//			String lt = CookieUtil.getCookie("LT", request);
//			if (lt == null) { // VT不存在，LT也不存在
//				return config.getLoginViewName();
//			} else { // VT不存在， LT存在
//				// TODO: 自动登录流程
//				return null;
//			}
//		} else { // VT存在
//			LoginUser loginUser = TokenManager.validate(vt);
//			if (loginUser != null) { // VT有效
//				return validateSuccess(backUrl, vt, response, map); // 验证成功后操作
//			} else { // VT 失效，转入登录页
//				return config.getLoginViewName();
//			}
//		}
	}

	// VT验证成功或登录成功后的操作
	private String validateSuccess(String backUrl, String vt,
			HttpServletResponse response, ModelMap map) throws IOException {
		if (backUrl != null) {
			response.sendRedirect(StringUtil.appendUrlParameter(backUrl, "VT",
					vt));
			return null;
		} else {
			map.put("sysList", null); // TODO 获取业务系统列表
			map.put("vt", vt);
			return config.getLoginViewName();
		}

	}
	
	/**
	 * 登录验证
	 * @param backUrl
	 * @param rememberMe
	 * @param request
	 * @param session
	 * @param response
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/login")
	public String login(String backUrl, Boolean rememberMe, HttpServletRequest request,
			HttpSession session, HttpServletResponse response, ModelMap map)
			throws Exception {

		final Map<String, String[]> params = request.getParameterMap();
		final Object sessionVal = session
				.getAttribute(Config.SESSION_ATTR_NAME);

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

		LoginUser loginUser = config.getAuthenticationHandler().authenticate(
				credential);

		if (loginUser == null) {
			map.put("errorMsg", credential.getError());
			return config.getLoginViewName();
		} else {
			String vt = authSuccess(response, rememberMe);
			return validateSuccess(backUrl, vt, response, map);
		}
	}

	// 授权成功后的操作
	private String  authSuccess(HttpServletResponse response, Boolean rememberMe) {
		// 生成VT
		// 生成LT？
		// 存入Map
		// Cookie
		return "null";
	}
}
