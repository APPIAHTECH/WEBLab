<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<div class="w3-container w3-card w3-round w3-white w3-section w3-center">
	  <p><img src="imgs/avatar3.png" class="w3-circle" style="height:106px;width:106px" alt="Avatar"></p>
	  <hr>
	  <p class="w3-left-align"> <i class="fa fa-id-card fa-fw w3-margin-right"></i> 
	  	<input type="text" value="${user.name}" name="inputText">	
	  </p>
	  <p class="w3-left-align"> <i class="fa fa-id-badge fa-fw w3-margin-right"></i> 
	  	<input type="text" value=" ${user.mail}" name="inputEmail">
	  </p>
	 </div>
	 <button data-id="${user.id}" data-name="${user.name}" data-mail="${user.mail}" type="button" class="w3-button w3-theme w3-margin-bottom updateUserInfo">Save</button>
	<br>
	<div id="iterator">
	</div>
	
</body>
</html>