package controllers;

import java.io.IOException;
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
 * Servlet implementation class AdminViewTweetsController
 */
@WebServlet("/AdminViewTweetsController")
public class AdminViewTweetsController extends HttpServlet {
	public String paramValue = "";
	int userID = -1;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminViewTweetsController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		List<Tweet> tweets = Collections.emptyList();
		User user = (User) session.getAttribute("user");
		
		if (session != null || user != null) {
			
	        if(request.getParameter("userID") != null) {
	        	paramValue = request.getParameter("userID");
	        	userID = Integer.parseInt(paramValue);
	        	//user.setId(userID);
	        }
			ManageTweets tweetManager = new ManageTweets();
			tweets = tweetManager.getUserTweets(userID,0,4);
			for(Tweet t : tweets) t.setLkes(tweetManager.getTweetTotalLikes(t.getId()));

			tweetManager.finalize();
		}
		request.setAttribute("user",user);
		request.setAttribute("tweets",tweets);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/AdminViewTweets.jsp"); 
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
