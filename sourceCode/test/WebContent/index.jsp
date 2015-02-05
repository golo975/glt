<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="utf8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<title>Insert title here</title>
</head>
<body>
hello world!<br/>
user sign in 
<form action="/test/signin.do">
	username : <input name="username" />
	<input type="submit" value="注册" />
</form>

<form action="/test/searchUser.do">
	username : <input name="username" />
	<input type="submit" value="查找" />
</form>

</body>
</html>