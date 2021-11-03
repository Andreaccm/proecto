package controllers;

import java.io.Serializable;

public class loginB implements Serializable {
	private static final long serialVersionUID= 1L;
	public static String email;
	public static String pwd;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
}


