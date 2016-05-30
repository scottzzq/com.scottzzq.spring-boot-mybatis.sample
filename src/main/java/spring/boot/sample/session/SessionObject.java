package spring.boot.sample.session;

import java.io.Serializable;

public class SessionObject implements Serializable{
	private static final long serialVersionUID = 1L;
	@Override
	public String toString() {
		return "SessionObject [name=" + name + ", password=" + password + "]";
	}
	private String name;
	private String password;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
