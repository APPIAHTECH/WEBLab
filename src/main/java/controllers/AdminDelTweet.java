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
import models.Tweet;
import models.User;

/**
 * Servlet implementation class AdminDelTweet
 */
@WebServlet("/AdminDelTweet")
public class AdminDelTweet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public String paramValue;
	int userID = -1, tweetID = -1;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDelTweet() {
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
		User user = (User) session.getAttribute("user");
		
		if (session != null || user != null) {
			 if(request.getParameter("id") != null && request.getParameter("userID") != null) {
		        	paramValue = request.getParameter("id");
		        	tweetID = Integer.parseInt(paramValue);
		        	paramValue = request.getParameter("userID");
		        	userID = Integer.parseInt(paramValue);
					tweetManager.deleteTweet(tweetID);
					System.out.println( "Tweet deleted");
		        }

			tweetManager.finalize();
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("AdminViewTweetsController");
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
