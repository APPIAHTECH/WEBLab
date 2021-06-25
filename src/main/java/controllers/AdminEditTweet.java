package controllers;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import managers.ManageTweets;
import managers.ManageUsers;
import models.Tweet;
import models.User;

/**
 * Servlet implementation class AdminEditTweet
 */
@WebServlet("/AdminEditTweet")
public class AdminEditTweet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public String paramValue;
	int userID = -1, tweetID = -1;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminEditTweet() {
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
		    if(request.getParameter("userID") != null && request.getParameter("id") != null && request.getParameter("tweetContent") != null) 
		    {
		    	paramValue = request.getParameter("userID");
		    	userID = Integer.parseInt(paramValue);
		    	paramValue = request.getParameter("id");
		    	tweetID = Integer.parseInt(paramValue);
		    	tweet = tweetManager.getUserTweet(userID , tweetID);
		    	paramValue = request.getParameter("tweetContent");
		    	tweet.setContent(paramValue);
				tweetManager.updateTweet(tweet, userID);
				tweetManager.finalize();
		    }

		}
		System.out.println( "Tweet update");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/ViewUpdateEdit.jsp");
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
