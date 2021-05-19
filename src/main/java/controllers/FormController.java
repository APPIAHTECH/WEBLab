package controllers;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import managers.ManageUsers;
import managers.model;
import models.User;

/**
 * Servlet implementation class FormController
 * TO solve time zone issue use SET GLOBAL time_zone = '-3:00'; on Msql server
 */
@WebServlet("/FormController")
public class FormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FormController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User model = new User();
		ManageUsers manager = new ManageUsers();

		String view = "ConstrainedValidationSimple.jsp";
		
		try {
			BeanUtils.populate(model,request.getParameterMap());
			model.setGender(request.getParameter("gender"));
			System.out.print(model.toString());
			if (manager.isComplete(model)) {
				manager.addUser(
						model.getUser(), 
						model.getMail(), 
						model.getPwd1(),
						model.getDate(),
						model.getGender()
						);
				manager.finalize();
				view = "Registered.jsp";
			}
				
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}

		request.setAttribute("model", model);
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
