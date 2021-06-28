<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
<head> 
  <title>Twitter.com</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
 <link rel="stylesheet" href="css/main.css">
</head>
<body>

<div class="flex w3-container">
	<div class="content">
		<c:forEach var="t" items="${tweets}">       
		 <div id="${t.id}" class="w3-container w3-card w3-section w3-white w3-round w3-animate-opacity"><br>
		   <img src="imgs/avatar2.png" alt="Avatar" class="w3-left w3-circle w3-margin-right" style="width:60px">
		   <span class="w3-right w3-opacity"> ${t.postDateTime} </span>
		   <h4> ${t.uname} </h4><br>
		   <hr class="w3-clear">
		   <p> ${t.content} </p>
		   <button type="button" data-userid="${t.uid}" data-isliked="${t.liked}"  class="w3-button w3-theme w3-margin-bottom likeBTN"><i class="fa fa-thumbs-up"></i> &nbsp;Like</button>
		 </div>
		</c:forEach>
	</div>
	
	<div class="left ">
	<h3> Recommended Users</h3>

	<c:forEach var="u" items="${users}">       
	 <div id="${u.id}" class="w3-container w3-card w3-section w3-white w3-round w3-animate-opacity"><br>
	   <img src="imgs/avatar2.png" alt="Avatar" class="w3-left w3-circle w3-margin-right" style="width:60px">
	   <h4> ${u.name} </h4><br>
	   <hr class="w3-clear">
	   <button type="button" class="userInfo w3-button w3-theme w3-margin-bottom viewPage">
	   	<i class="fa fa-info-circle"></i> &nbsp;View page
	   </button>
	 </div>
	</c:forEach>
	
	</div>
</div>



</body>
</html>