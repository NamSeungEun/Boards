package com.test2;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.test2.UserDTO;

public class TestAction extends ActionSupport implements Preparable, ModelDriven<UserDTO>{

	private static final long serialVersionUID = 1L;

	private UserDTO dto;
	
	public UserDTO getDto() {  //게터만 생성 
		return dto;           // ModelDriven 얘가 있어서 request.setAttribute("dto",dto); 대신 해줌 
	}

	@Override
	public UserDTO getModel() {
		return dto;
	}

	@Override
	public void prepare() throws Exception {  // 여기까지 구조를 항상 써줘야 함
		dto = new UserDTO();
		
	}

	public String created() throws Exception{
		
		if(dto==null||dto.getMode()==null||dto.getMode().equals("")) {
			
			return INPUT; 
		}
		
		HttpServletRequest request = ServletActionContext.getRequest();
		
		request.setAttribute("message", "스트럿츠2");
		
		//request.setAttribute("dto", dto);
		return SUCCESS;
	}
	
}
