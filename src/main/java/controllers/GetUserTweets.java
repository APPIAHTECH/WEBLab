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
import managers.ManageUsers;
import models.Tweet;
import models.User;

/**
 * Servlet implementation class dTcontroller
 */
@WebServlet("/GetUserTweets")
public class GetUserTweets extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetUserTweets() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		List<Tweet> tweets = Collections.emptyList();
		List<Tweet> tweets_view_follow = new ArrayList<Tweet>();
		User user = (User) session.getAttribute("user");
		List<User> userFolow = Collections.emptyList();
		
		if (session != null || user != null) {
			ManageTweets tweetManager = new ManageTweets();
			ManageUsers manageUser = new ManageUsers();
			tweets = tweetManager.getUserTweets(user.getId(),0,4);
			//Personalized timeline which shows tweets only from the users they follow.
			userFolow = manageUser.getFollowedUsers(user.getId(), 0, 4);
			for(User us: userFolow)
			{
				List<Tweet> temp_tweet = tweetManager.getUserTweets(us.getId(), 0, 4);
				for(Tweet t: temp_tweet) tweets_view_follow.add(t);
			}
			for(Tweet t : tweets) t.setLkes(tweetManager.getTweetTotalLikes(t.getId()));
			for(Tweet t : tweets_view_follow) t.setLkes(tweetManager.getTweetTotalLikes(t.getId()));
			tweetManager.finalize();
		}

		request.setAttribute("tweets",tweets);
		request.setAttribute("tweets_follow", tweets_view_follow);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/ViewTweets.jsp"); 
		dispatcher.forward(request,response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

