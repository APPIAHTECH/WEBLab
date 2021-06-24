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
 * Servlet implementation class AdminProfileController
 */
@WebServlet("/AdminProfile")
public class AdminProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminProfileController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		List<User> users = Collections.emptyList();
		
		//Gets recommended users
		ManageUsers userManager = new ManageUsers();
		users = userManager.getUsers(0,4);
		
		request.setAttribute("users",users);
		userManager.finalize();
		
		if (session==null || session.getAttribute("user")==null) {
			System.out.println("Admin: NO active session has been found.");
			request.setAttribute("menu","ViewMenuNotLogged.jsp");
			request.setAttribute("content","ViewLoginForm.jsp");
		}
		else {
			User u = new User();
			u = (User) session.getAttribute("user");
			ManageUsers manager = new ManageUsers();
			System.out.println("hey" +u.getName());
			if(manager.isAdmin(u))
			{
				System.out.println("Admin Controller: active session has been found.");
				request.setAttribute("menu","ViewMenuLogged.jsp");
				request.setAttribute("content","AdminProfile.jsp");
			}else
			{
				System.out.println("Admin: Not valid user.");
				request.setAttribute("menu","ViewMenuNotLogged.jsp");
				request.setAttribute("content","ViewLoginForm.jsp");
			}
			manager.finalize();
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
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
