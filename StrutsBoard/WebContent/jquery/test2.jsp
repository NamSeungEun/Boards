<%@ page contentType="text/html; charset=UTF-8"%>
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

<script type="text/javascript" src="<%=cp%>/data/js/jquery-3.1.1.min.js"></script>

<script type="text/javascript">

	$(function(){
		
		$("#btnOK").click(function(){
			
			var msg = $("#userName").val();
			
			msg += "\r\n" + $("input:radio[name=gender]:checked").val();
			
			msg += "\r\n" + $("#hobby").val().join("|");
			
			alert(msg);
			
		});
	
	});


	function result(){
		
		var msg = document.getElementById("userName").value;
		
		alert(msg);
		
	}


</script>

</head>
<body>

이름: <input type="text" id="userName"/><br/>
성별: <input type="radio" id="gender1" value="M" name="gender">남자
<input type="radio" id="gender2" value="F" name="gender">여자<br/><br/>

취미: 
<select id="hobby" multiple="multiple">
	<option value="여행">여행</option>
	<option value="영화">영화</option>
	<option value="운동">운동</option>
	<option value="게임">게임</option>
</select>

<br/><br/>

jQuery: <input type="button" value="확인" id="btnOK"/><br/>
Javascript: <input type="button" value="확인" onclick="result();"/><br/>




</body>
</html>



















