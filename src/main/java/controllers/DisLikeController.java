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
 * Servlet implementation class DisLikeController
 */
@WebServlet("/DisLikeController")
public class DisLikeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public String paramValue, isLiked;
	int userID = -1, tweetID = -1;    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisLikeController() {
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
		    if(request.getParameter("userID") != null && request.getParameter("id") != null && request.getParameter("iscomment") != null) 
		    {
		    	userID = Integer.parseInt(request.getParameter("userID"));
		    	tweetID = Integer.parseInt(request.getParameter("id"));
			    tweet = tweetManager.getUserTweet(userID , tweetID);
			    
			    System.out.println(Boolean.parseBoolean(request.getParameter("iscomment")));
			    //Use for liking comments
			    if( Boolean.parseBoolean(request.getParameter("iscomment")) )
			    {
			    	tweet.setCliked(false);
			    	tweet.setCid(Integer.parseInt(request.getParameter("cid")));
					tweetManager.setDisLike(tweet, userID, true);
			    }else 
			    {
			    	tweet.setLiked(false);
					tweetManager.setDisLike(tweet, userID, false);
			    }
				tweetManager.finalize();
		    }
		}
		System.out.println( "DisLiked");
		RequestDispatcher dispatcher = request.getRequestDispatcher("GetUserTweets");
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
