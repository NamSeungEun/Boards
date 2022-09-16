<%@page import="java.util.Date"%>
<%@ page contentType="text/xml; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
	
%>
<%!
	
	String[] newsTitle = {
			"윤석열 부동산 정책, 집값 '대세 하락' 가속화하나",
			"물가는 뛰고, 경기는 힘 빠지고…韓 경제 스태그플레이션 진입하나",
			"조국사태 교훈? 尹측 靑 인사 추천만, 법무 경찰이 검증",
			"'친문 전력' 김오수 어떡하나…권성동 스스로 거취 정해야",
			"[강준만의 직설] '충성 경쟁'이 대통령을 망친다",
			"코로나 신규확진 40만명대…정부는 감염병 등급 하향",
	};
	

%>

<result>
	<count><%=newsTitle.length %></count>
	<data>
		<%for(int i=0;i<newsTitle.length;i++){ %>
			<title><%=newsTitle[i]+"|"+new Date() %></title>
		<%} %>
	</data>
</result>	