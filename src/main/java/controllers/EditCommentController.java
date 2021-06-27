package controllers;

import java.io.IOException;
import java.util.ArrayList;
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
 * Servlet implementation class EditCommentController
 */
@WebServlet("/EditCommentController")
public class EditCommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public String paramValue = ""; 
	int commentID;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditCommentController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		ManageTweets managerTweet = new ManageTweets();
		User user = (User) session.getAttribute("user");
		List<Tweet> tweetsComments = new ArrayList<Tweet>();
		
		if (session != null || user != null) {
	        if(request.getParameter("cid") != null) {
	        	paramValue = request.getParameter("cid");
	        }
	        commentID = Integer.parseInt(paramValue);
	        System.out.println(commentID);
	        tweetsComments.add( managerTweet.getTweetComment(commentID));
			managerTweet.finalize();
			request.setAttribute("comment",tweetsComments);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/ViewEditComment.jsp"); 
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
