<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:forEach var="t" items="${tweets}">       
 <div id="${t.id}"  class="w3-container w3-card w3-section w3-white w3-round w3-animate-opacity"><br>
   <img src="imgs/avatar2.png" alt="Avatar" class="w3-left w3-circle w3-margin-right" style="width:60px">
   <span class="w3-right w3-opacity"> ${t.postDateTime} </span><br>
   <span class="w3-right w3-opacity"> Likes ${t.likes} </span>
   <h4> ${t.uname} </h4><br>
   <hr class="w3-clear">
   <input type="text" value=" ${t.content}" name="inputTweetContent">
   <button data-id="${t.id}" data-userid="${user.id}" data-content="${t.content}" type="button" class="w3-button w3-theme w3-margin-bottom updateTweetInfo">Save</button>
 </div>
</c:forEach>
