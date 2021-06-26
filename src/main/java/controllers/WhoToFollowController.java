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

import managers.ManageUsers;
import models.User;

/**
 * Servlet implementation class WhoToFollowController
 */
@WebServlet("/WhoToFollowController")
public class WhoToFollowController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WhoToFollowController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		List<User> users = Collections.emptyList();
		User user = (User) session.getAttribute("user");
		//Gets recommended users
		ManageUsers userManager = new ManageUsers();
		users = userManager.getNotFollowedUsers(user.getId(),0,4);
		request.setAttribute("users",users);
		userManager.finalize();
		if (session==null || session.getAttribute("user")==null) {
			System.out.println("Admin: NO active session has been found.");
			request.setAttribute("menu","ViewMenuNotLogged.jsp");
			request.setAttribute("content","ViewLoginForm.jsp");
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/ViewWhoToFollow.jsp");
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
