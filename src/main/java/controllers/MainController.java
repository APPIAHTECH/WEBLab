package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
 * Servlet implementation class MainController
 */
@WebServlet("/MainController")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		List<User> users = Collections.emptyList();
		List<Tweet> tweets = Collections.emptyList();
		List<Tweet> finalTweets = new ArrayList<>();
		
		//Gets recommended users
		ManageUsers userManager = new ManageUsers();
		users = userManager.getUsers(0,4);
		
		//Gets latest tweets
		ManageTweets tweetManager = new ManageTweets();
		for (User user: users) {
			tweets = tweetManager.getUserTweets(user.getId(),0,4);
			for (Tweet tweet: tweets) {
				finalTweets.add(tweet);
			}
		}
		
		request.setAttribute("users",users);
		request.setAttribute("tweets",finalTweets);
		userManager.finalize();
		tweetManager.finalize();
		
		if (session==null || session.getAttribute("user")==null) {
			System.out.println("MainController: NO active session has been found.");
			request.setAttribute("menu","ViewMenuNotLogged.jsp");
			//request.setAttribute("content","ViewLoginForm.jsp");
			request.setAttribute("content","ViewHome.jsp");
			
		}
		else {
			System.out.println("Main Controller: active session has been found.");
			request.setAttribute("menu","ViewMenuLogged.jsp");
			request.setAttribute("content","ViewOwnTimeline.jsp");
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

