<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<title>Insert title here</title>
</head>
<body>
	hello world! user sign in
<!-- 	<form action="http://localhost:8080/test/signin.do?gender=male"> -->
	<form action="<c:url value='signin.do?gender=male'/>">
		username : <input name="username" /> <input type="submit" value="注册" />
	</form>
</body>
</html>