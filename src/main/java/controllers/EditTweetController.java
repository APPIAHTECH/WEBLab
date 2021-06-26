package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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
 * Servlet implementation class EditTweetController
 */
@WebServlet("/EditTweetController")
public class EditTweetController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public String paramValue;
	int userID = -1, tweetID = -1;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditTweetController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		List<Tweet> tweets = new ArrayList<Tweet>();
		User user = (User) session.getAttribute("user");
		
		if (session != null || user != null) {
			
	        if(request.getParameter("id") != null) {
	        	paramValue = request.getParameter("id");
	        	tweetID = Integer.parseInt(paramValue);
	        	System.out.println(tweetID);
	        }
			ManageTweets tweetManager = new ManageTweets();
			tweets.add(tweetManager.getUserTweet(user.getId(), tweetID));
			System.out.println(tweets);
			tweetManager.finalize();
		}
		request.setAttribute("user",user);
		request.setAttribute("tweets",tweets);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/ViewEditTweet.jsp"); 
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
