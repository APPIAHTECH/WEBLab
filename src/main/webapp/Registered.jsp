<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="models.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> Registered view </title>
</head>
<body>
User <%= ((User)request.getAttribute("model")).getUser() %> has been registered correctly.
</body>
</html>