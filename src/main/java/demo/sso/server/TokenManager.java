package demo.sso.server;

import demo.sso.server.model.LoginUser;

import java.util.HashMap;
import java.util.Map;

/**
 * 存储VT_USER信息，并提供操作方法
 * 
 * @author Administrator
 *
 */
public class TokenManager {

	private static final Map<String, LoginUser> DATA_MAP = new HashMap<String, LoginUser>();

	private TokenManager() {
	}

	public static LoginUser validate(String vt) {
		// TODO Auto-generated method stub
		System.out.println(DATA_MAP.get(vt));
		return null;
	}

}
