package controllers;

import java.io.IOException;

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
 * Servlet implementation class UpdateCommentController
 */
@WebServlet("/UpdateCommentController")
public class UpdateCommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public String paramValue, comment;
	int userID = -1, commentID = -1;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCommentController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Tweet tweet = new Tweet();
		ManageTweets tweetManager = new ManageTweets();
		HttpSession session = request.getSession(false);
		User user = new User();
		
		if (session != null || user != null) {
		    if(request.getParameter("cid") != null && request.getParameter("comment") != null) 
		    {
		    	commentID = Integer.parseInt(request.getParameter("cid"));
		    	comment = request.getParameter("comment");
		    	tweet.setComment(comment);
		    	tweet.setCid(commentID);
				tweetManager.updateComment(tweet);
				tweetManager.finalize();
		    }
		}
		System.out.println( "Comment updated");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/ViewComment.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
