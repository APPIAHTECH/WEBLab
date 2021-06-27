<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false" %>

<!DOCTYPE html>
<html>
<head>
<link rel="icon" href="imgs/upf.jpg">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title> Lab 4 solution </title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://www.w3schools.com/lib/w3-theme-red.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<meta name="viewport" content="width=device-width, initial-scale=1">

<script type="text/javascript">
$(document).ready(function(){
	
	$.ajaxSetup({ cache: false }); //Avoids Internet Explorer caching!	
	$(document).on("click",".menu",function(event) {
		$('#content').load($(this).attr('id'));
		event.preventDefault();
	});
	$(document).on("submit","form", function(event) {
		$('#content').load($(this).attr('action'),$(this).serialize());
	    event.preventDefault();
	});
	/* Add tweet */
	$(document).on("click","#addTweet",function(event){
		$.post( "AddTweet", { content: $("#tweetContent").text()}, function(event) {
			$("#content").load("GetOwnTimeline");		
		});
		event.preventDefault();
	});
	/* Delete tweet */
	$(document).on("click",".delTweet",function(event){
		var tweet = $(this).parent();
		$.post( "DelTweet", { id: $(this).parent().attr("id") } , function(event) {
			$("#content").load("GetOwnTimeline");				
		});
		event.preventDefault();
	});
	
	/* Edit user profile editUser */
	$(document).on("click",".editUser",function(event){
		var tweet = $(this).parent();
		$.post( "EditProfileController", { id: $(this).parent().attr("id") } , function(event) {
			$("#content").load("EditProfileController");				
		});
		event.preventDefault();
	});
	
	/* Follow user */
	$(document).on("click",".followUser",function(event){
		var user = $(this).parent();
		$.post( "FollowUser", { id: $(this).parent().attr("id") }, function(event) { 
			$("#content").load("GetFollowedUsers");
			$("#lcolumn").load("GetNotFollowedUsers");
		});
		event.preventDefault();
	});
	/* UnFollow user */
	$(document).on("click",".unfollowUser",function(event) {
		var user = $(this).parent();
		$.post( "UnFollowUser", { id: $(this).parent().attr("id") }, function(event) {
			$("#content").load("GetFollowedUsers");
			$("#lcolumn").load("GetNotFollowedUsers");
		});
		event.preventDefault();
	});
	
	/* View page*/
	$(document).on("click",".viewPage",function(event) {
		var user = $(this).parent();
		console.log($(this).parent().attr("id") )
		$.post( "GetProfile", { userID: $(this).parent().attr("id") }, function(event) { 
			$("#content").load("GetProfile");		
		});
		event.preventDefault();
	});
	
	/* View admin, user tweets page*/
	$(document).on("click",".viewTweetPageBTN",function(event) {
		var user = $(this).parent();
		$.post( "AdminViewTweetsController", { userID: $(this).parent().attr("id") }, function(event) { 
			$("#content").load("AdminViewTweetsController");		
		});
		event.preventDefault();
	});
	
	/* View edit user page*/
	$(document).on("click",".viewEditPage",function(event) {
		var user = $(this).parent();
		console.log($(this).parent().attr("id") )
		$.post( "AdminUserEditController", { userID: $(this).parent().attr("id") }, function(event) { 
			$("#content").load("AdminUserEditController");		
		});
		event.preventDefault();
	});
	
	/* View edit user page, save btn update info*/
	$(document).on("click",".updateUserInfo",function(event) {
		var user = $(this).parent();
		console.log($(this).data("id") )
		console.log($(this).data("name") )
		console.log($(this).data("mail") )
		console.log($("input[name=inputText]").val());
		console.log($("input[name=inputEmail]").val());
		$.post( "UpdateUserController", 
			{ 
				userID: $(this).data("id") ,
				name: $("input[name=inputText]").val(),
				mail: $("input[name=inputEmail]").val()
			}, function(event) { 
			$("#content").load("UpdateUserController");		
		});
		event.preventDefault();
	});
	
	/* View delete user page*/
	$(document).on("click",".viewDeletePage",function(event) {
		var user = $(this).parent();
		console.log($(this).parent().attr("id") )
		$.post( "DelUser", { userID: $(this).parent().attr("id") }, function(event) { 
			$("#content").load("DelUser");		
		});
		event.preventDefault();
	});
	/* Delete admin, tweet */
	$(document).on("click",".adminDelTweet",function(event){
		var tweet = $(this).parent();
		$.post( "AdminDelTweet", { id: $(this).parent().attr("id") } , function(event) {
			$("#content").load("AdminDelTweet");				
		});
		event.preventDefault();
	});
	
	/* Edit, tweet */
	$(document).on("click",".viewEditTweetPage",function(event){
		var tweet = $(this).parent();
		console.log($(this).parent().attr("id"))
		$.post( "EditTweetController", { id: $(this).parent().attr("id") } , function(event) {
			$("#content").load("EditTweetController");				
		});
		event.preventDefault();
	});
	
	/* View edit tweet page, save btn update info*/
	$(document).on("click",".updateTweetInfo",function(event) {
		var user = $(this).parent();
		console.log($(this).data("id") )
		console.log( $(this).data("userid"));
		console.log($("input[name=inputTweetContent]").val());
		$.post( "AdminEditTweet", 
			{
				userID: $(this).data("userid") ,
				id: $(this).data("id") ,
				tweetContent: $("input[name=inputTweetContent]").val()
			}, function(event) { 
			$("#content").load("AdminEditTweet");		
		});
		event.preventDefault();
	});
	/*whoToFollow*/
	$(document).on("click",".whoToFollow",function(event){
		var tweet = $(this).parent();
		console.log($(this).parent().attr("id"))
		$.post( "WhoToFollowController", { id: $(this).parent().attr("id") } , function(event) {
			$("#content").load("WhoToFollowController");				
		});
		event.preventDefault();
	});
	
	/* View userInfo btn, Buddies Info*/
	$(document).on("click",".userInfo",function(event){
		var tweet = $(this).parent();
		console.log($(this).parent().attr("id"))
		$.post( "UserInfoController", { userID: $(this).parent().attr("id") } , function(event) {
			$("#content").load("UserInfoController");				
		});
		event.preventDefault();
	});
	/* Comment */
	$(document).on("click",".viewCommentTweet",function(event){
		var tweet = $(this).parent();
		console.log($(this).parent().attr("id"))
		console.log( $(this).data("userid") )
		$.post( "CommentController", { 
			userID: $(this).data("userid") ,
			id: $(this).parent().attr("id")
			} , function(event) {
			$("#content").load("CommentController");				
		});
		event.preventDefault();
	});
	/* View updateTweetComment*/
	$(document).on("click",".updateTweetComment",function(event) {
		var user = $(this).parent();
		console.log($(this).data("id") )
		console.log( $(this).data("userid"));
		console.log($("textarea[name=inputTweetComment]").val());
		$.post( "AddCommentController", 
			{
				userID: $(this).data("userid") ,
				id: $(this).data("id") ,
				comment: $("textarea[name=inputTweetComment]").val()
			}, function(event) { 
			$("#content").load("AddCommentController");		
		});
		event.preventDefault();
	});
	
	/* View deleteTweetComment*/
	$(document).on("click",".commentDelete",function(event) {
		var user = $(this).parent();
		console.log($(this).data("cid") )
		$.post( "DeleComment", { cid: $(this).data("cid") ,}, function(event) { 
			$("#content").load("DeleComment");		
		});
		event.preventDefault();
	});
	
	/* View commentEdit*/ 
	$(document).on("click",".commentEdit",function(event) {
		var user = $(this).parent();
		console.log($(this).data("cid") )
		$.post( "EditCommentController", { cid: $(this).data("cid") ,}, function(event) { 
			$("#content").load("EditCommentController");		
		});
		event.preventDefault();
	});
	
	/* View updateCommentInfo*/
	$(document).on("click",".updateCommentInfo",function(event) {
		var user = $(this).parent();
		console.log($(this).data("cid") )
		console.log( $("textarea[name=inputTweetCommentEdit]").val() )
		$.post( "UpdateCommentController", { cid: $(this).data("cid") , comment: $("textarea[name=inputTweetCommentEdit]").val() }, function(event) { 
			$("#content").load("UpdateCommentController");		
		});
		event.preventDefault();
	});
});
</script>
</head>
<body>

 	<!-- Begin Navigation -->
 	<div class="w3-theme" id="navigation">
    <jsp:include page="${menu}" />
 	</div>
 	<!-- End Navigation -->
 
 	<!-- Begin Content -->
	<div class="w3-row-padding">
 	<!-- Left Column -->
	<div class="w3-container w3-col m3 w3-hide-small">
		<div id="rcolumn">
			<p></p>
		</div>
	</div>
	<!-- Middle Column -->	
	<div class="e3-container w3-col m6">
		<div id="content">
		<jsp:include page="${content}" />
		</div>
	</div>
	<!-- Right Column -->
	<div class="w3-container w3-col m3 w3-hide-small">
		<div id="lcolumn">
			<p></p>
		</div>
	</div>
	</div>
	<!-- End Content -->
	<!-- Footer -->
	<footer class="w3-container w3-theme">
	  <p> Universitat Pompeu Fabra </p>
	</footer>
	
	<script>
		function stack() {
  			var x = document.getElementById("stack");
  			if (x.className.indexOf("w3-show") == -1) {
    			x.className += " w3-show";
  			} else { 
    		x.className = x.className.replace(" w3-show", "");
  			}
		}
	</script>

  </body>
</html>