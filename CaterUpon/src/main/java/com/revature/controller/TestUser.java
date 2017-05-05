package com.revature.controller;

public class TestUser {
	
	private String user_UserName;
	private String user_Password;
	@Override
	public String toString() {
		return "TestUser [name=" + user_UserName + ", pw=" + user_Password + "]";
	}
	public String getUser_UserName() {
		return user_UserName;
	}
	public void setUser_UserName(String user_UserName) {
		this.user_UserName = user_UserName;
	}
	public String getUser_Password() {
		return user_Password;
	}
	public void setUser_Password(String user_Password) {
		this.user_Password = user_Password;
	}
	
}
