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
import managers.ManageUsers;
import models.Tweet;
import models.User;

/**
 * Servlet implementation class UserInfoController
 */
@WebServlet("/UserInfoController")
public class UserInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public String paramValue = "";
	int userID = -1;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserInfoController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		User user = new User();
		List<Tweet> tweets = Collections.emptyList();
		ManageUsers manager = new ManageUsers();
		ManageTweets managerTweet = new ManageTweets();
		
		if (session != null || user != null) {
	        if(request.getParameter("userID") != null) {
	        	paramValue = request.getParameter("userID");
	        }
	    	userID = Integer.parseInt(paramValue);
	    	user = manager.getUser(userID);
	    	tweets = managerTweet.getUserTweets(userID, 0, 4);
	    	for(Tweet t : tweets) t.setLkes(managerTweet.getTweetTotalLikes(t.getId()));
			manager.finalize();
			request.setAttribute("user",user);
			request.setAttribute("tweets",tweets);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/ViewUserInfoFollow.jsp"); 
			dispatcher.forward(request,response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
