package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import managers.ManageTweets;
import models.Tweet;
import models.User;

/**
 * Servlet implementation class CommentController
 */
@WebServlet("/CommentController")
public class CommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public String paramValue;
	int userID = -1, tweetID = -1;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		List<Tweet> tweets = new ArrayList<Tweet>();
		List<Tweet> tweetsComments = new ArrayList<Tweet>();
		User user = (User) session.getAttribute("user");
		
		if (session != null || user != null) {
			
	        if(request.getParameter("id") != null && request.getParameter("userID") != null) {
	        	paramValue = request.getParameter("id");
	        	tweetID = Integer.parseInt(paramValue);
	        	paramValue = request.getParameter("userID");
	        	userID = Integer.parseInt(paramValue);
	        }
			ManageTweets tweetManager = new ManageTweets();
			tweets.add(tweetManager.getUserTweet(userID, tweetID));
			for(Tweet t : tweets) t.setLkes(tweetManager.getTweetTotalLikes(t.getId()));

			tweetsComments = tweetManager.getUserCommentTweets(tweetID, 0,4);
			for(Tweet t: tweetsComments){
				t.setLiked(false);
				t = tweetManager.getUserCommentTweets(t, user.getId());
				t.setComments(tweetManager.getTweetTotalComments(t.getId()));
				t.setClks(tweetManager.getTweetTotalCommentLikes(t.getCid()));
			}
			tweetManager.finalize();
		}
		
		request.setAttribute("user",user);
		request.setAttribute("tweets",tweets);
		request.setAttribute("comment",tweetsComments);
		request.setAttribute("totalComment", tweetsComments.get(0).getComments());
		RequestDispatcher dispatcher = request.getRequestDispatcher("/ViewComment.jsp"); 
		dispatcher.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
