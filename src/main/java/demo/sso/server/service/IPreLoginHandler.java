package demo.sso.server.service;

/**
 * 登录页前置处理器
 * @author Administrator
 *
 */
public abstract class IPreLoginHandler {

	protected  void setSessionValue(Object value) {
		
	}
	
	/**
	 * 前置处理
	 */
	public abstract void handle();
}
