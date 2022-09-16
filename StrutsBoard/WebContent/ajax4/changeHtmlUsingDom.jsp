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

	var count = 0;
	
	function addItem() {
		
		count++;
		
		var newItemE = document.createElement("div");
		
		newItemE.setAttribute("id","item_"+count);
		
		var html = "새로 추가된 아이템[" + count + "]&nbsp;";
		html += "<input type=\"button\"value=\"삭제\" " +
				"onclick=\"removeItem('item_" + count + "');\"/>";
		
		newItemE.innerHTML = html;
		
		var itemListDIV = document.getElementById("itemList");
		itemListDIV.appendChild(newItemE);  //자식 노드 추가 
	}
	
	function removeItem(itemId) {
		
		var removeItem = document.getElementById(itemId);
		removeItem.parentNode.removeChild(removeItem);
	}


</script>
</head>
<body>

<h1>HTML DOM을 이용한 화면 변경</h1>
<hr/>

<input type="button" value="추가" onclick="addItem();"/>

<div id="itemList"></div>

</body>
</html>