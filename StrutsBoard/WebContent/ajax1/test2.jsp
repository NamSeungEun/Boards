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

<!-- 데이터를 보내는 곳 -->
<script type="text/javascript">

	$(function(){
		
		$("#sendButton").click(function () {
			
			var params = "subject=" + $("#subject").val() + "&content=" + $("#content").val();
			
			$.ajax({
				
				type:"POST",
				url:"test2_ok.jsp",
				data:params,
				dateType:"xml", //돌아오는 데이터 타입은 xml
				success:function(args) {
					
					$(args).find("status").each(function() { //each는 반복작업명령어 -status가 있는만큼 반복한다
						
						alert($(this).text());
					});
					
					var str = "";
					
					$(args).find("record").each(function() {
						
						//id는 속성이라 attr로 씀
						var id = $(this).attr("id");
						var subject = $(this).find("subject").text(); 
						var content = $(this).find("content").text();
						
						str += "id=" + id + 
									",subject=" + subject +
									",content=" + content + "<br/>";
						
					});
					
					$("#resultDIV").html(str);
					
					
				},			
				beforeSend:showRequest, 
				
				error:function(e) {
					alert(e.responseText);
				}
				
			});
			

			
		});
		
	});
	
	
	function showRequest() {
		
		var flag = true;
		
		if(!$("#subject").val()) {//subject에 value가 없으면
			alert("제목을 입력하세요!");
			$("#subject").focus();
			return false; //false값을 반환하면 실행하지 않는다. true가 와야 실행	
		}
		
		if(!$("#content").val()) {//subject에 value가 없으면
			alert("내용을 입력하세요!");
			$("#content").focus();
			return false; //false값을 반환하면 실행하지 않는다. true가 와야 실행	
		}
		
		return flag; //flag 안에 true값이 들어가있다 그래서 flag를 반환하면서 실행
		
	}
	
	

</script>

</head>
<body>

제목: <input type="text" id="subject"/><br/>
내용: <input type="text" id="content"/><br/>
<input type="button" id="sendButton" value="보내기"/><br/>

<div id="resultDIV"></div> <!-- 여기에 데이터 뿌림 -->

</body>
</html>