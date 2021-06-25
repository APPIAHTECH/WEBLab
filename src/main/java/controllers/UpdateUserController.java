package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import managers.ManageUsers;
import models.User;

/**
 * Servlet implementation class UpdateUserController
 */
@WebServlet("/UpdateUserController")
public class UpdateUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public String paramValue;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUserController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		User user = new User();
		ManageUsers manager = new ManageUsers();
		if (session != null || user != null) {
	        if(request.getParameter("userID") != null) 
	        {
	        	paramValue = request.getParameter("userID");
		    	int userID = Integer.parseInt(paramValue);
		    	user = manager.getUser( userID );
		    	user.setName(request.getParameter("name"));
		    	user.setMail(request.getParameter("mail"));
		    	manager.updateUser(user);
	        }
			manager.finalize();
			System.out.println("User updated");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/ViewUpdateUser.jsp"); 
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
