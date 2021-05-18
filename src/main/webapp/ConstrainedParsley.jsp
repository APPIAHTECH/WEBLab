<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="models.User" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="parsley/parsley.min.js"></script>
<!--  <script src="parsley/i18n/ca.js"></script> -->
<title> Form with parsley </title>
<style>
input:valid {
	border-left: 4px solid green;
}
input:invalid {
	border-left: 4px solid red;
}

input.parsley-success,
select.parsley-success,
textarea.parsley-success {
	border-left: 4px solid green;
}

input.parsley-error,
select.parsley-error,
textarea.parsley-error {
	border-left: 4px solid red;
}

.server-errors-list {
  color:red;
}

.parsley-errors-list {
  margin: 0 0 0 0;
  padding: 0 0 0 10px;
  list-style-type: none;
  font-size: 0.9em;
  line-height: 0.9em;
  opacity: 0;
  color: red;
  display:inline-block;
}
.parsley-errors-list.filled {
  opacity: 1;
  display:inline-block;
}
</style>
</head>
<body>

<ul class="server-errors-list">
<% if (((User)request.getAttribute("model")).getError()[0]) {%>
	<li> Entered user name has been already registered </li>
<% } %>
</ul>

<form data-parsley-validate action="FormController">
  <label for="user"> User name:</label>
  <br>
  <input type="text" id="user" name="user" placeholder="Name" value="<%= ((User)request.getAttribute("model")).getUser() %>" required>
  <br>
  <label for="mail"> Mail: </label>
  <br>
  <input type="email" id="mail" name="mail" placeholder="Email" value="<%= ((User)request.getAttribute("model")).getMail() %>" required>
  <br>
  <label for="pwd1"> Password: </label>
  <br>
  <input type="password" id="pwd1" name="pwd1" placeholder="Password" value="<%= ((User)request.getAttribute("model")).getPwd1() %>" required pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{6,}$">
  <br>
  <label for="pwd1"> Confirm password: </label>
  <br>
  <input type="password" id="pwd2" name="pwd2" placeholder="Confirm Password" value="<%= ((User)request.getAttribute("model")).getPwd2() %>" required data-parsley-equalto="#pwd1">
  <br><br>
  <button> Submit </button>
</form>

</body>
</html>