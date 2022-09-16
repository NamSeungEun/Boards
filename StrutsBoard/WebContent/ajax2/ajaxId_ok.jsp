<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
	
	String userId = request.getParameter("userId");
	
	String str = "";
	
	if(!userId.equals("")){
		
		if(userId.startsWith("suzi")){
			str = userId + "는 유효한 아이디입니다";
		}else{
			str = userId + "는 유효한 아이디가 아닙니다.";
			
		}
	}
%>



<%=str%>