<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:forEach var="t" items="${tweets}">       
 <div id="${t.id}"  class="w3-container w3-card w3-section w3-white w3-round w3-animate-opacity"><br>
   <img src="imgs/avatar2.png" alt="Avatar" class="w3-left w3-circle w3-margin-right" style="width:60px">
   <span class="w3-right w3-opacity"> ${t.postDateTime} </span>
   <h4> ${t.uname} </h4><br>
   <hr class="w3-clear">
   <p> ${t.content} </p>
   <button type="button" class="viewEditTweetPage w3-button w3-red w3-margin-bottom">Edit</button> 

   <c:choose>
	    <c:when test="${t.liked}">
			<button type="button" data-userid="${t.uid}" data-isliked="${t.liked}" class="w3-button w3-theme w3-margin-bottom dislikeBTN"><i class="fa fa-thumbs-up"></i> &nbsp;Dislike</button>
	    </c:when>    
	    <c:otherwise>
	    	<button type="button"  data-userid="${t.uid}" data-isliked="${t.liked}"  class="w3-button w3-theme w3-margin-bottom likeBTN"><i class="fa fa-thumbs-up" ></i> &nbsp;Like</button>
	    </c:otherwise>
	</c:choose>
   <button type="button" class="delTweet w3-button w3-red w3-margin-bottom"><i class="fa fa-trash"></i> &nbsp;Delete</button>
   <button type="button" data-userid="${t.uid}" class="viewCommentTweet w3-button w3-red w3-margin-bottom">Comment</button> 
 </div>
</c:forEach>

<c:forEach var="tf" items="${tweets_follow}">       
 <div id="${tf.id}"  class="w3-container w3-card w3-section w3-white w3-round w3-animate-opacity"><br>
   <img src="imgs/avatar2.png" alt="Avatar" class="w3-left w3-circle w3-margin-right" style="width:60px">
   <span class="w3-right w3-opacity"> ${tf.postDateTime} </span>
   <h4> ${tf.uname} </h4><br>
   <hr class="w3-clear">
   <p> ${tf.content} </p>
   <c:choose>
	    <c:when test="${tf.liked}">
			<button type="button" data-userid="${tf.uid}" data-isliked="${tf.liked}" class="w3-button w3-theme w3-margin-bottom dislikeBTN"><i class="fa fa-thumbs-up"></i> &nbsp;Dislike</button>
	    </c:when>    
	    <c:otherwise>
	    	<button type="button" data-userid="${tf.uid}" data-isliked="${tf.liked}"  class="w3-button w3-theme w3-margin-bottom likeBTN"><i class="fa fa-thumbs-up"></i> &nbsp;Like</button>
	    </c:otherwise>
	</c:choose>
   <button type="button" class="delTweet w3-button w3-red w3-margin-bottom"><i class="fa fa-trash"></i> &nbsp;Delete</button>
   <button data-userid="${tf.uid}" type="button" class="viewCommentTweet w3-button w3-red w3-margin-bottom">Comment</button> 
 </div>
</c:forEach>
