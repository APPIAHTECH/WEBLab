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
import managers.ManageUsers;
import models.User;

/**
 * Servlet implementation class DeleComment
 */
@WebServlet("/DeleComment")
public class DeleComment extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public String paramValue = ""; 
	int commentID;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleComment() {
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
		
		if (session != null || user != null) {
	        if(request.getParameter("cid") != null) {
	        	paramValue = request.getParameter("cid");
	        }
	        
	        System.out.println(paramValue);
	        commentID = Integer.parseInt(paramValue);
	        managerTweet.deletetUserCommentTweets(commentID);
			System.out.println("User Comment deleted");
			managerTweet.finalize();
			RequestDispatcher dispatcher = request.getRequestDispatcher("/ViewComment.jsp"); 
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
