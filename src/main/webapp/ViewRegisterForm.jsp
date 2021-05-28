<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="models.User" session="false"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<style>
input:valid {
	border-left: 4px solid green;
}
input:invalid {
	border-left: 4px solid red;
}
</style>

<ul>
	<c:if test = "${user.error[0]}">
		<li> This user name has been already registered" </li>
	</c:if>
</ul>

<ul>
	<c:if test = "${user.error[1]}">
		<li> This email address has been already registered" </li>
	</c:if>
</ul>


<form action="RegisterController" method="POST" id="myform">
	<p>      
    <label class="w3-text-red"><b>Username</b></label>
    <input class="w3-input w3-border w3-light-grey" type="text" id="user" name="user" value="${user.user}" required minlength="5" ></p>
    <p>      
    <label class="w3-text-red"><b> Mail address </b></label>
    <input class="w3-input w3-border w3-light-grey" type="email" id="mail" name="mail" value = "${user.mail}" required></p>
    <p>
    
    <p>      
	<label for="pwd1" class="w3-text-red"> Password: </label><br>
  	<input class="w3-input w3-border w3-light-grey" type="password" id="pwd1" name="pwd1" placeholder="Password" value="${user.pwd1}" required pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}">
    <p>
    
    <p>      
	  <label for="pwd2" class="w3-text-red"> Confirm Password: </label><br>
	  <input class="w3-input w3-border w3-light-grey" type="password" id="pwd2" name="pwd2" placeholder="Confirm Password" value="${user.pwd2}" required>
	<p>
    
    <p>
      <! -- Add Gender field -->
	  <label class="w3-text-red" for="gender"> Gender:</label><br>
	  <select class="w3-input w3-border w3-light-grey"  id="gender" name="gender">
	  	<option value="male">Male</option>
	  	<option value="female">Female</option>
	  </select>
    <p>
  	<! -- Add Date of Birth -->
  	<label class="w3-text-red" for="date"> Date of Birth:</label><br>
  	<input class="w3-input w3-border w3-light-grey" type="date" id="user" name="date" value="${user.date}%>"><br>
    <input class="w3-btn w3-red" type="submit" name="sumbit" value="Submit">
    <p>
</form>

<script>

const form = document.getElementById("myform");
const email = document.getElementById("mail");
const pwd1 = document.getElementById("pwd1");
const pwd2 = document.getElementById("pwd2");

var checkPasswordValidity = function() {
	console.log("hey there")
	 if (pwd2.value !== pwd1.value ) {
		 console.log("Passwords must match!")
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
