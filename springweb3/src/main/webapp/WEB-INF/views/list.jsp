<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
결과는 ${msg}<br>
결과는 ${requestScope.msg}<br>
<%
String ss = (String)request.getAttribute("msg");
out.println("결과는 " + ss);
%>
<!-- 위 3개는 표현 방식만 다를 뿐. 다 같은 뜻! -->
</body>
</html>