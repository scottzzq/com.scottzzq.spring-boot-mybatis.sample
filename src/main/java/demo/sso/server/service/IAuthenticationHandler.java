package demo.sso.server.service;

import java.util.Set;

import demo.sso.server.model.Credential;
import demo.sso.server.model.LoginUser;

public interface IAuthenticationHandler {
	/**
	 * 鉴权
	 * 
	 * @param params
	 *            页面传递过来的参数
	 * @param sessionAttr
	 *            特定session属性值，这个值是在跳到login页面前，loginPreHandler通过setSessionVal()
	 *            方法写入的
	 * @param errors
	 *            授权失败时，将失败信息写入此对象
	 * @return 授权成功返回Credentail, 否则返回null
	 */
	public LoginUser authenticate(Credential credential) throws Exception;

	/**
	 * 获取当前登录用户可用系统ID列表
	 * 
	 * @param loginUser
	 * @return 返回null表示全部
	 * @throws Exception
	 */
	public Set<String> authedSystemIds(LoginUser loginUser) throws Exception;
}
