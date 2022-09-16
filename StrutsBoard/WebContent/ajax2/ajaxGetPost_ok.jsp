<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();

	/* String protocol = request.getProtocol();
	System.out.print(protocol); */
	
	//클라이언트의 ajax 캐쉬를 삭제
	if(request.getProtocol().equals("HTTP/1.1")){
		response.setHeader("Cache-Control", "no-cache");
	}
	
%>
<%

	String greeting = request.getParameter("greeting");

	for(int i=0;i<3500;i++){
		System.out.print("처리중....");
	}

%>

<%="Server: " + greeting %>