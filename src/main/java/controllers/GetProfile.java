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
 * Servlet implementation class GetUserInfo
 */
@WebServlet("/GetProfile")
public class GetProfile extends HttpServlet {
	
	public String paramValue = "";
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetProfile() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		
		User user = new User();
		user.setId(0);
		ManageUsers userManager = new ManageUsers();

		List<Tweet> tweets = Collections.emptyList();
		ManageTweets tweetManager = new ManageTweets();
		
        
        if(request.getParameter("userID") != null) {
        	paramValue = request.getParameter("userID");
        }
        
        System.out.println(paramValue);
    	int userID = Integer.parseInt(paramValue);
    	user.setId(userID);

    	System.out.println("integer value"+userID);
		user = userManager.getUser(user.getId());
    	
		tweets = tweetManager.getUserTweets(user.getId(),0,4);
    	for(Tweet tweet: tweets) System.out.println(tweet.getContent());
    	
		request.setAttribute("tweets",tweets);
   		request.setAttribute("user",user);
		request.setAttribute("menu","ViewMenuNotLogged.jsp");
		request.setAttribute("content","ViewProfile.jsp");
		userManager.finalize();
		RequestDispatcher dispatcher = request.getRequestDispatcher("ViewProfile.jsp"); 
		dispatcher.include(request,response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
