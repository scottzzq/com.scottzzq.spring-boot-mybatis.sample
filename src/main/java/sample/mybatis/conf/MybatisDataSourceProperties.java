package sample.mybatis.conf;

import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = MybatisDataSourceProperties.PREFIX)
public class MybatisDataSourceProperties implements BeanClassLoaderAware{
	public static final String PREFIX = "mybatis.datasource";
	private String url;
	private String username;
	private String password;
	private String driverClassName;
	private ClassLoader classLoader;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDriverClassName() {
		return driverClassName;
	}
	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}
	public ClassLoader getClassLoader() {
		return classLoader;
	}
	@Override
	public void setBeanClassLoader(ClassLoader classLoader) {
		this.classLoader = classLoader;
		
	}
}