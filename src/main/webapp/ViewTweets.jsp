<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:forEach var="t" items="${tweets}">       
 <div id="${t.id}" class="w3-container w3-card w3-section w3-white w3-round w3-animate-opacity"><br>
   <img src="imgs/avatar2.png" alt="Avatar" class="w3-left w3-circle w3-margin-right" style="width:60px">
   <span class="w3-right w3-opacity"> ${t.postDateTime} </span>
   <h4> ${t.uname} </h4><br>
   <hr class="w3-clear">
   <p> ${t.content} </p>
   <button type="button" class="viewEditTweetPage w3-button w3-red w3-margin-bottom">Edit</button> 
   <button type="button" class="likeTweet w3-button w3-theme w3-margin-bottom"><i class="fa fa-thumbs-up"></i> &nbsp;Like</button>
   <button type="button" class="delTweet w3-button w3-red w3-margin-bottom"><i class="fa fa-trash"></i> &nbsp;Delete</button>
   <button type="button" data-userid="${t.uid}" class="viewCommentTweet w3-button w3-red w3-margin-bottom">Comment</button> 
 </div>
</c:forEach>

<c:forEach var="tf" items="${tweets_follow}">       
 <div id="${tf.id}" class="w3-container w3-card w3-section w3-white w3-round w3-animate-opacity"><br>
   <img src="imgs/avatar2.png" alt="Avatar" class="w3-left w3-circle w3-margin-right" style="width:60px">
   <span class="w3-right w3-opacity"> ${tf.postDateTime} </span>
   <h4> ${tf.uname} </h4><br>
   <hr class="w3-clear">
   <p> ${tf.content} </p>
   <button type="button" class="likeTweet w3-button w3-theme w3-margin-bottom"><i class="fa fa-thumbs-up"></i> &nbsp;Like</button>
   <button type="button" class="delTweet w3-button w3-red w3-margin-bottom"><i class="fa fa-trash"></i> &nbsp;Delete</button>
   <button data-userid="${tf.uid}" type="button" class="viewCommentTweet w3-button w3-red w3-margin-bottom">Comment</button> 
 </div>
</c:forEach>
