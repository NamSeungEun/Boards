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
 
 function ajaxRequest() {
	//IE(익스플로어)
	//XMLHttpRequest = new ActiveXObject("Msxml2.XMLHTTP");
	//크롬
	 XMLHttpRequest = new XMLHttpRequest();

	 XMLHttpRequest.open("GET","helloAjax_ok.jsp",true);
	 XMLHttpRequest.onreadystatechange = viewMessage;
	 XMLHttpRequest.send(null);
 }

 function viewMessage() {
	 
	 //서버에서 응답이 왔을 때 실행되는 메소드 
	 
	 var responseText = XMLHttpRequest.responseText;
	 
	 var divE = document.getElementById("printDIV");
	 
	 divE.innerHTML = responseText;
	
}

</script>

</head>
<body>

<h1>Hello Ajax</h1>

<input type="button" value="클릭" onclick="ajaxRequest();">

<br/><br/>
<div id="printDIV" style="border: 1px solid red; width: 50%"></div>
</body>
</html>