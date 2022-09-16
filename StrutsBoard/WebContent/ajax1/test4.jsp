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
		
		var params = "userId=" + $("#userId").val() + "&userPwd=" + $("#userPwd").val();
		
		$.ajax({
			
			type:"POST",
			url:"test4_ok.jsp",
			data:params,
			success:function(args){
				
				$("#resultDIV").html(args);
			},
			beforeSend:showRequest,
			error:function(e){
				alert(e.responseText);
			}
			
		});
	});
	
	function showRequest(){
		var flag = true;
		
		if (!$("#userId").val()) {
			alert("아이디 입력~");
			$("#userId").focus();
			return false;
		}
		
		if (!$("#userPwd").val()) {
			alert("패스워드 입력~");
			$("#userPwd").focus();
			return false;
		}
		
		return flag;
	}
	
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