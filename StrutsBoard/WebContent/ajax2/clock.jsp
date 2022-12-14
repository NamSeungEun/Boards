<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript">

var XMLHttpRequest;
XMLHttpRequest = new XMLHttpRequest();

function printClientTime() {
	
	var clientTimeSpan = document.getElementById("clientTimeSpan");

	var now = new Date();
	
	var timeStr = now.getFullYear()+"년" +(now.getMonth()+1)+"월"
	+now.getDate()+"일"+now.getHours()+"시"+now.getMinutes()+"분"
	+now.getSeconds()+"초";
	
	clientTimeSpan.innerHTML = timeStr;
	
	//1초마다 자기 자신을 실행
	setTimeout("printClientTime()",1000);
}

function requestTime() {
	
	//XMLHttpRequest = new XMLHttpRequest();
	
	XMLHttpRequest.open("GET","clock_ok.jsp",true);
	XMLHttpRequest.onreadystatechange = printServerTime;
	XMLHttpRequest.send(null);
	
	setTimeout("requestTime()",1000); 
}

function printServerTime() {
	
	if(XMLHttpRequest.readyState==4){
		if(XMLHttpRequest.status==200){
			
			var serverTimeSpan = document.getElementById("serverTimeSpan");
			
			serverTimeSpan.innerHTML = XMLHttpRequest.responseText;
			
		}
	}
}


window.onload = function(){
	printClientTime();
	requestTime();
}


</script>
</head>
<body>
<h1>Clock</h1>
<br/>
<hr/>

1.현재 클라이언트 시간은 <b><span id="clientTimeSpan"></span></b>입니다<br/>
2.현재 서버 시간은 <b><span id="serverTimeSpan"></span></b>입니다<br/>
</body>
</html>