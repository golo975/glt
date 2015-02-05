<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="utf8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<title>Insert title here</title>
</head>
<body>
hello world! <br />
<a href="http://localhost:8083/test/" >返回注册页面</a>
<table>
</table>
<ol>
	<c:forEach items="${users }" var="user">
		<li>
			<a href='<c:url value="http://localhost:8083/test/deleteUser.do?userid=${user.id}"/>'>
				${user.username} -- ${user.id}
			</a>
		</li>
	</c:forEach>
</ol>
</body>
</html>