<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
	
	int su1 = Integer.parseInt(request.getParameter("su1"));
	int su2 = Integer.parseInt(request.getParameter("su2"));
	String oper = request.getParameter("oper");
	
	
	int sum = 0;
	
	if(oper.equals("hap"))
		sum = su1 + su2;
	else if(oper.equals("sub"))
		sum = su1 - su2;
	else if(oper.equals("mul"))
		sum = su1 * su2;
	else if(oper.equals("div"))
		sum = su1 / su2;
	
	StringBuffer sb = new StringBuffer();
	
	sb.append("<?xml version='1.0' encoding='utf-8'?>\n");
	sb.append("<root>" + sum + "</root>");
	
	response.setContentType("text/xml;charset=utf-8");
	response.getWriter().write(sb.toString());
		
	
%>
