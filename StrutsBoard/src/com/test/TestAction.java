package com.test;

import com.opensymphony.xwork2.ActionSupport;

public class TestAction extends ActionSupport{

	private static final long serialVersionUID = 1L;

	private String userId;
	private String userName;
	private String message;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMessage() {
		return message;
	}
	
	
	public String execute() throws Exception { //무조건 실행되는 execute() 메서드 
		
		message = userName + "님 방가방가~";
		
		return SUCCESS;  //여까지 잘 실행되면 이 문자를 써~ 
	}
	
}
