<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="models.User"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> Form </title>
<style>
input:valid {
	border-left: 4px solid green;
}
input:invalid {
	border-left: 4px solid red;
}
</style>
</head>
<body>

<ul>
<c:if test = "${model.error[0]}">
	<li> Entered user name has been already registered </li>
</c:if>
</ul>

<form action="FormController" id="myform">
  <label for="user"> User name:</label><br>
  <input type="text" id="user" name="user" placeholder="Name" value="<%=((User)request.getAttribute("model")).getUser()%>" required><br>
  <label for="mail"> Mail  </label><br>
  <input type="email" id="mail" name="mail" placeholder="Mail" value="<%=((User)request.getAttribute("model")).getMail()%>" required><br>
  <label for="pwd1"> Password: </label><br>
  <input type="password" id="pwd1" name="pwd1" placeholder="Password" value="<%=((User)request.getAttribute("model")).getPwd1()%>" required pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}"><br>
  <label for="pwd2"> Confirm Password: </label><br>
  <input type="password" id="pwd2" name="pwd2" placeholder="Confirm Password" value="<%=((User)request.getAttribute("model")).getPwd2()%>" required><br><br>
  
  <! -- Add Gender field -->
  <label for="gender"> Gender:</label><br>
  <select id="gender" name="gender">
  	<option value="male">Male</option>
  	<option value="female">Female</option>
  </select>
  <br>
  
  <! -- Add Date of Birth -->
  <label for="date"> Date of Birth:</label><br>
  <input type="date" id="user" name="date" value="<%=((User)request.getAttribute("model")).getDate()%>"><br>
  

  
  <button> Submit </button>
</form>
<script>

const form = document.getElementById("myform");
const email = document.getElementById("mail");
const pwd1 = document.getElementById("pwd1");
const pwd2 = document.getElementById("pwd2");

var checkPasswordValidity = function() {
	 if (pwd2.value !== pwd1.value ) {
		pwd2.setCustomValidity("Passwords must match!");
	} else {
		pwd2.setCustomValidity("");
	}
}

email.addEventListener("input", function (event) {
  if (email.validity.typeMismatch) {
    email.setCustomValidity("I am expecting an e-mail address!");
  } else {
    email.setCustomValidity("");
  }
});

pwd2.addEventListener("input", checkPasswordValidity, false);

form.addEventListener("submit", function (event) {
	checkPasswordValidity();
	if (!this.checkValidity()) {
		this.reportValidity();
		event.preventDefault();
	} 
}, false);


</script>
</body>
</html>