package com.mms.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mms.beans.Login;
import com.mms.dao.LoginDAO;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	LoginDAO loginDAO = null;
	
	public LoginController() {
		loginDAO = new LoginDAO();
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Login login = new Login();
		login.setEmail(request.getParameter("email"));
		login.setPassword(request.getParameter("password"));
		
		String result=loginDAO.loginCheck(login);
		
		if(result.equals("true")){
			session.setAttribute("email",login.getEmail());
			response.sendRedirect("MovieController?action=LIST");
		}
		 
		if(result.equals("false")){
			response.sendRedirect("index.jsp?status=false");
		}
		 
		if(result.equals("error")){
		    response.sendRedirect("index.jsp?status=error");
		}
		
	}
}
