package demo.sso.server;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import demo.sso.server.model.ClientSystem;

@Configuration
@ConfigurationProperties(prefix="client")
public class ClientConfig {
	private List<ClientSystem> clientSystems = new ArrayList<ClientSystem>();
	/**
	 * 客户端系统列表
	 * 
	 * @return
	 */
	public List<ClientSystem> getClientSystems() {
		return clientSystems;
	}
}
