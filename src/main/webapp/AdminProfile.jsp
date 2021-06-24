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

<div class="adminPage">

<c:forEach var="u" items="${users}">       
	<div  class="w3-container w3-card w3-round w3-white w3-center w3-section">
	<table class="table table-hover tablefixed">
		<thead>
			<tr>
				<th style="text-align: center"><div> <img src="imgs/avatar6.png" alt="Avatar" style="width:50%"><br> ${u.name}</div> </th>
			</tr>
		</thead>
		<tbody>
			<td id="${u.id}"> <button  class="viewTweetPageBTN"> <a>View Tweets</a> </button></td>
			<td id="${u.id}"> <button  class="viewEditPage"> <a>Edit</a> </button></td>
			<td id="${u.id}"> <button  class="viewDeletePage"> <a>Delete</a> </button></td>
		</tbody>
	</table>
	</div>
</c:forEach>

</div>




