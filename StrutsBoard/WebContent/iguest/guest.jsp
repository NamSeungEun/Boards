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
<title>Ajax 방명록</title>

<link rel="stylesheet" type="text/css" href="<%=cp%>/data/css/style.css"/>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>

<script type="text/javascript">

	$(function(){
		
		listPage(1); //1페이지를 호출
				
	});

	$(document).ready(function(){
				
		$("#sendButton").click(function(){
			
			var params = "name=" + $("#name").val() + "&email=" + $("#email").val() + "&content=" + $("#content").val();
			
			$.ajax({  //데이터를 에이젝스가 넘김 
				
				type:"POST",
				//가상주소↘
				url:"<%=cp%>/guest/created.action", 
				data:params,
				success:function(args){
					
					$("#listData").html(args);
					
					//ajax는 새로고침이 안되기때문에 코딩으로 삭제해줘야된다.
					$("#name").val("");
					$("#email").val("");
					$("#content").val("");
					$("#name").focus();
				},
				beforeSend:showRequest,
				error:function(e){
					alert(e.responseText);
				}				
			});			
		});
	});
	
	function showRequest(){
		
		var name = $.trim($("#name").val()); //jquery함수
		var email = $.trim($("#email").val()); 
		var content = $.trim($("#content").val()); 
		
		if (!name) {
			alert("\n이름을 입력하세요");
			$("#name").focus();
			return false;
		}
		
		if (!email) {
			alert("\n이메일을 입력하세요");
			$("#email").focus();
			return false;
		}
		
		if (!content) {
			alert("\n내용을 작성하세요");
			$("#content").focus();
			return false;
		}
		
		if (content.length>=200) {
			alert("\n 200자 이하로 입력하세요");
			$("#content").focus();
			return false;
		}
		return true;		
	}
	
	function listPage(page){
		
		var url = "<%=cp%>/guest/list.action";
		
		$.post(url,{pageNum:page},function(args){
			$("#listData").html(args);
		});
		
		$("#listData").show();
	}
	
	function deleteData(num,page){
		
		var url = "<%=cp%>/guest/deleted.action";
		
		$.post(url,{num:num,pageNum:page},function(args){
			$("#listData").html(args);
		});
		
	}
</script>

</head>
<body>

<br/><br/>

<table width="700" border="2" cellpadding="0" cellspacing="0" bordercolor="#e6d4a6" style="margin: auto;">
	<tr height="40">
		<td style="padding-left: 20px;"><b>방 명 록</b></td>
	</tr>
</table>

<br/><br/>

<table width="700" border="0" cellpadding="0" cellspacing="0" style="margin: auto;">

<tr>
	<td width="600" colspan="4" height="3" bgcolor="#e6d4a6"></td>
</tr>

<tr>
	<td width="60" height="30" bgcolor="#eeeeee" align="center">작성자</td>
	<td width="240" height="30" style="padding-left: 10px;">
		<input type="text" id="name" size="35" maxlength="20" class="boxTF"/>
	</td>
	<td width="60" height="30" bgcolor="#eeeeee" align="center">이메일</td>
	<td width="240" height="30" style="padding-left: 10px;">
		<input type="text" id="email" size="35" maxlength="50" class="boxTF"/>
	</td>
</tr>

<tr>
	<td width="600" colspan="4" height="1" bgcolor="#e6d4a6"></td>
</tr>

<tr>
	<td width="60" height="30" bgcolor="#eeeeee" align="center">내용</td>
	<td width="540" colspan="3" style="padding-left: 10px;">
		<textarea rows="3" cols="90" class="boxTA" style="height: 50px;" id="content"></textarea>
	</td>
</tr>

<tr>
	<td width="600" colspan="4" height="1" bgcolor="#e6d4a6"></td>
</tr>

<tr>
	<td width="600" colspan="4" height="30" align="right" style="padding-right: 15px;">
		<input type="button" value="등록하기" class="btn2" id="sendButton"/>
	</td>
</tr>

</table>

<br/>

<span id="listData" style="display: none;"></span>








</body>
</html>