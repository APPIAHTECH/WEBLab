<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:forEach var="t" items="${comment}">       
 <div id="${t.id}"  class="w3-container w3-card w3-section w3-white w3-round w3-animate-opacity"><br>
   <img src="imgs/avatar2.png" alt="Avatar" class="w3-left w3-circle w3-margin-right" style="width:60px">
   <hr class="w3-clear">
     <textarea rows="4" cols="50"  placeholder="Write your comment here" name="inputTweetCommentEdit">
      ${t.comment}
     </textarea>
   <button data-cid="${t.cid}" type="button" class="w3-button w3-theme w3-margin-bottom updateCommentInfo">Save</button>
 </div>
</c:forEach>
