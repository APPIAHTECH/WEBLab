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
 * Servlet implementation class LikeController
 */
@WebServlet("/LikeController")
public class LikeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public String paramValue;
	int userID = -1, tweetID = -1;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LikeController() {
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
		

		if (session==null || session.getAttribute("user")==null) {
			System.out.println("LikeController: NO active session has been found.");
			request.setAttribute("menu","ViewMenuNotLogged.jsp");
			request.setAttribute("content","ViewLoginForm.jsp");
			RequestDispatcher dispatcher = request.getRequestDispatcher("LoginController");
			dispatcher.forward(request, response);	
		}
		else {
		    if(request.getParameter("userID") != null && request.getParameter("id") != null && request.getParameter("iscomment") != null) 
		    {
		    	userID = Integer.parseInt(request.getParameter("userID"));
		    	tweetID = Integer.parseInt(request.getParameter("id"));
			    tweet = tweetManager.getUserTweet(userID , tweetID);
			    
			    System.out.println(Boolean.parseBoolean(request.getParameter("iscomment")));
			    //Use for liking comments
			    if( Boolean.parseBoolean(request.getParameter("iscomment")) )
			    {
			    	tweet.setCliked(true);
			    	tweet.setCid(Integer.parseInt(request.getParameter("cid")));
			    	System.out.println("hola -> "+tweet.getCid());
					tweetManager.setLike(tweet, userID, true);
			    }else 
			    {
			    	System.out.println("hola -> "+tweet.getId());
			    	tweet.setLiked(true);
					tweetManager.setLike(tweet, userID, false);
			    }
				tweetManager.finalize();
				System.out.println( "Liked");
		    }
		}
		
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
