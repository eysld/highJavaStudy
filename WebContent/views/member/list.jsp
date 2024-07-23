<%@page import="kr.or.ddit.member.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%--
	1. 디렉티브(Directive)
	
	JSP 페이지에 대한 설정정보를 지정할 때 사용된다. (page, taglib, include 등)
	 ex) <%@ 디렉티브이름 속성명="속성값" ...%>
	 
	2. 스크립트 요소
	
	JSP에서 문서의 내용을 동적으로 생성하기 위해 사용된다.
	
	 - 표현식(expression)		: 값을 출력결과에 포함시키고자 할 때 사용한다. ex) <%=값%>
	 - 스크립트릿(scriptlet)	: 자바코드 블럭을 작성할 때 사용한다. ex) <% 자바코드들 ... %>
	 - 선언부(declaration)	: 스크립트릿이나 표현식에서 사용할 수 있는 메서드  작성시 사용한다. ex) <%! ~~~ %>
	 
	3. JSP 기본객체와 영역(SCOPE)
	
	 - PAGE    	   : 하나의 JSP페이지를 처리할 때 사용되는 영역 => pageContext
	 - REQUEST 	   : 하나의 HTTP요청을 처리할 때 사용되는 영역 => request
	 
	 - SESSION 	   : 하나의 브라우저(사용자)와 관련된 영역 => session
	 - APPLICATION : 하나의 웹애플리케이션과 관련된 영역 => application
	 
 --%>
 
 <%
 	List<MemberVO> memList = (List<MemberVO>) request.getAttribute("memList");
 
 	String msg = session.getAttribute("msg") == null ? "" : (String)session.getAttribute("msg");
 	session.removeAttribute("msg");
 %>
 
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원목록</title>
</head>
<body>
	<table border="1">
		<tr>
			<th>ID</th>
			<th>이름</th>
			<th>전화번호</th>
			<th>주소</th>
			<th>첨부파일</th>
		</tr>
		
<%
	if(memList.size() > 0){
		for(MemberVO mv : memList){
%>
		<tr>
			<td><% out.print(mv.getMemId()); %></td>
			<td><a href="./detail.do?memId=<%=mv.getMemId() %>"><%=mv.getMemName() %></a></td>
			<td><%=mv.getMemTel() %></td>
			<td><%=mv.getMemAddr() %></td>
			<td><%=mv.getAtchFileId() %></td>
		</tr>
<%
		}
	}else{
%>

	<tr>
		<td colspan="5">회원정보가 존재하지 않습니다.</td>
	</tr>

<%
	}
%>

	<tr align="center">
		<td colspan="5"><a href="<%=request.getContextPath() %>/member/insert.do">[회원 등록]</a></td>
	</tr>

	</table>
<% if(!msg.equals("")) { %>

<script >
	alert('정상적으로 처리되었습니다');
</script>

<% } %>
</body>
</html>