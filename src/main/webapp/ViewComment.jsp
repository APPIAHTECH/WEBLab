<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:forEach var="t" items="${tweets}">       
 <div id="${t.id}"  class="w3-container w3-card w3-section w3-white w3-round w3-animate-opacity"><br>
   <img src="imgs/avatar2.png" alt="Avatar" class="w3-left w3-circle w3-margin-right" style="width:60px">
   <span class="w3-right w3-opacity"> ${t.postDateTime} </span>
   <h4> ${t.uname} </h4><br>
   <hr class="w3-clear">
   <p> ${t.content} </p>
    <textarea rows="4" cols="50"  placeholder="Write your comment here" name="inputTweetComment"></textarea>
 </div>
  <button data-id="${t.id}" data-userid="${t.uid}"  type="button" class="w3-button w3-theme w3-margin-bottom updateTweetComment">Save</button>
</c:forEach>


<h4> Comments </h4>
<c:forEach var="t" items="${comment}">       
 <div id="${t.id}"  class="w3-container w3-card w3-section w3-white w3-round w3-animate-opacity"><br>
   <p>${t.comment}</p>
 </div>
</c:forEach>