<%@page import="kr.or.ddit.member.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	MemberVO mv = (MemberVO) session.getAttribute("LOGIN_USER");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인페이지</title>
</head>
<body>
<%
	if(mv != null){
%>
<p> 반갑습니다. <%=mv.getMemName() %>님  &nbsp; <a href="<%=request.getContextPath() %>/logout.do">로그아웃</a></p>
<%
	}else{
%>
<p> 아직 로그인을 하지 않았습니다. 로그인 페이지로 이동 &nbsp; <a href="<%=request.getContextPath() %>/login.do">로그인</a></p>
<%
	}
%>
</body>
</html>