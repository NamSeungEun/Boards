<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script src="https://code.jquery.com/jquery-3.6.0.js"></script>

<script type="text/javascript">

$(document).ready(function(){
	
	$("#saveButton").click(function(){
		
		var value1 = $("#userId").val();
		var value2 = $("#userPwd").val();
		
		$.post("test3_ok.jsp",{userId:value1,userPwd:value2},function(args){
			$("#resultDIV").html(args);			
		});		
	});
	
	$("#clearButton").click(function(){
		$("#resultDIV").empty();
		
	});
	
});


</script>




</head>
<body>

아이디: <input type="text" id="userId"/><br/>
패스워드: <input type="text" id="userPwd"/><br/>

<button id="saveButton">전송</button>
<button id="clearButton">지움</button>

<br/><br/>

<div id="resultDIV"></div>


</body>
</html>