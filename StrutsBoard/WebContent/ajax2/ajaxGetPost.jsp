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

	function getXMLHttpRequest() {

		if (window.ActiveXOBject) { //IE

			try { //IE 5.0 이후
				XMLHttpRequest = new ActiveXOBject("Msxml2.XMLHTTP");
			} catch (e) { //IE 5.0 이전
				XMLHttpRequest = new ActiveXOBject("Microsoft.XMLHTTP");
			}

		} else { //NON-IE

			XMLHttpRequest = new XMLHttpRequest();

		}

	}

	function ajaxRequestGet() {

		var data = document.myForm.greeting.value;

		if (data == "") {

			alert("데이터 입력!");
			document.myForm.greeting.focus();
			return;
		}

		//get방식 한글이 깨짐
		XMLHttpRequest.open("GET", "ajaxGetPost_ok.jsp?greeting=" + data, true);
		XMLHttpRequest.onreadystatechange = viewMessage;
		XMLHttpRequest.send(null);

	}
	
	function ajaxRequestPost() {
		
		var data = document.myForm.greeting.value;

		if (data == "") {

			alert("데이터 입력!");
			document.myForm.greeting.focus();
			return;
		}
		
		XMLHttpRequest.open("POST", "ajaxGetPost_ok.jsp",true);
		
		XMLHttpRequest.setRequestHeader("Content-type","application/x-www-form-urlencoded")
		
		XMLHttpRequest.onreadystatechange = viewMessage;
		XMLHttpRequest.send("greeting=" + data);
		
		
	}
	
	
	function viewMessage() {

		var divE = document.getElementById("printDiv");

		if (XMLHttpRequest.readyState == 1 || XMLHttpRequest.readyState == 2
				|| XMLHttpRequest.readyState == 3) {

			divE.innerHTML = "<img src='./image/processing.gif' width='50' height='50'/>";

		} else if (XMLHttpRequest.readyState == 4) {

			divE.innerHTML = XMLHttpRequest.responseText;
		}

	}
	
	window.onload = function(){
		getXMLHttpRequest();
	}
</script>



</head>
<body>

	<h1>AjaxGetPost</h1>
	<hr />
	<form action="" name="myForm">
		<input type="text" name="greeting" /> 
		<input type="button"value="Get전송" onclick="ajaxRequestGet();" />
		<input type="button"value="Post전송" onclick="ajaxRequestPost();"/>
	</form>

	<div id="printDiv" style="border: 1px solid blue; width: 50%"></div>


</body>
</html>