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
<script type="text/javascript" src="<%=cp%>/data/js/log.js"></script>
<script type="text/javascript" src="<%=cp%>/data/js/member.js"></script>
<script type="text/javascript">

	function memberClass() {
		
	}

	window.onload = function () {
		memberClass();
	}

</script>
</head>
<body>

<h1>javascript  클래스 사용 </h1>
<hr/>

<div id="console"></div>
</body>
</html>