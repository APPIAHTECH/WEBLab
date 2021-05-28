<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<ul>
	<c:if test = "${login.errors[0]}">
		<li>The username and password you entered do not match our records. Please check and try again.</li>
	</c:if>
</ul>

<form action="LoginController" method="POST" id="myform">
	<p>      
    <label class="w3-text-red"><b> User name </b></label>
    <input class="w3-input w3-border w3-light-grey" type="text" name="user" value="${login.user}" required minlength="5" ></p>
    <p>
    
    <p>      
	<label for="pass" class="w3-text-red"> Password: </label><br>
  	<input class="w3-input w3-border w3-light-grey" type="password" id="pass" name="pass" placeholder="Password" value="${login.pass}" required pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}">
    <p>
    <input class="w3-btn w3-red" type="submit" name="sumbit" value="Submit"></p>
</form>
