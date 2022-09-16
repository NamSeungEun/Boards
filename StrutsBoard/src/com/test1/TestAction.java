package com.test1;

import com.opensymphony.xwork2.ActionSupport;

public class TestAction extends ActionSupport{

	private static final long serialVersionUID = 1L;

	private UserDTO dto; //이렇게 userId, userName을 데려옴 [ Domain Object ]
	
	private String message;

	public UserDTO getDto() { //이렇게 userId, userName을 데려옴
		return dto;
	}

	public void setDto(UserDTO dto) { //이렇게 userId, userName을 데려옴
		this.dto = dto;
	}

	public String getMessage() {
		return message;
	} 
	
	public String execute() throws Exception{
		
		message = dto.getUserName() + "님 방가방가";
		
		return "ok"; //사용자 정의 문자를 써도 됨 
	}
	
}
