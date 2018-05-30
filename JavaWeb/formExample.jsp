<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form name="formPost" action="/company_management/FirstServlet" method="post">
	Name:<input type="text" value="" name="name" />
	<input type="submit" value="POST SUBMIT" />
</form>
<form name="formGet" action="/company_management/FirstServlet" method="get">
	Id Employee:<input type="text" value="" name="idemployee" />
	<input type="submit" value="GET SUBMIT" />
</form>
</body>
</html>