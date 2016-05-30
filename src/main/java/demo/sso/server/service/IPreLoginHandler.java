package demo.sso.server.service;

import java.util.Map;

import javax.servlet.http.HttpSession;

public interface IPreLoginHandler {
	public static final String SESSION_ATTR_NAME = "login_session_attr_name";
	/**
	 * 前置处理
	 */
	public abstract Map<?, ?> handle(HttpSession session) throws Exception;
}
