package com.mms.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mms.beans.Movie;
import com.mms.dao.MovieDAO;

/**
 * Servlet implementation class MovieController
 */
@WebServlet("/MovieController")
public class MovieController extends HttpServlet {
	private static final long serialVersionUID = 1L;
		
	RequestDispatcher dispatcher = null;
	MovieDAO movieDAO = null;
       
    public MovieController() {
        super();
        movieDAO = new MovieDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		if(action == null) {
			action = "LIST";
		}
		
		switch(action) {
			
			case "LIST":
				listEmployee(request, response);
				break;
				
			case "EDIT":
				getSingleEmployee(request, response);
				break;
				
			case "DELETE":
				deleteEmployee(request, response);
				break;
				
			default:
				listEmployee(request, response);
				break;
				
		}
		
	}

    private void deleteEmployee(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
    	String id = request.getParameter("id");
    	
		if(movieDAO.delete(Integer.parseInt(id))) {
			request.setAttribute("NOTIFICATION", "Movie Deleted Successfully!");
		}
		
		listEmployee(request, response);
	}

	private void getSingleEmployee(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
    	
    	String id = request.getParameter("id");
		
		Movie theMovie = movieDAO.get(Integer.parseInt(id));
		
		request.setAttribute("movie", theMovie);
		
		dispatcher = request.getRequestDispatcher("/views/movie-form.jsp");
		
		dispatcher.forward(request, response);
		
	}

	private void listEmployee(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		HttpSession session = request.getSession();
    	List<Movie> theList = movieDAO.get();
		
		request.setAttribute("list", theList);
		
		dispatcher = request.getRequestDispatcher("/views/movie-list.jsp");
		
		dispatcher.forward(request, response);
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("mid");
		
		Movie e = new Movie();
		//e.setMovieId(Integer.parseInt(request.getParameter("mid")));
		e.setMovieName(request.getParameter("mname"));
		e.setMovieType(request.getParameter("mtype"));
		e.setMovieLanguage(request.getParameter("mlanguage"));
		e.setMovieDuration(Integer.parseInt(request.getParameter("mduration")));
		
		if(id.isEmpty() || id == null) {
			if(movieDAO.save(e)) {
				request.setAttribute("NOTIFICATION", "Movie Saved Successfully!");
			}
		}else{
			e.setMovieId(Integer.parseInt(id));
			if(movieDAO.update(e)) {
				request.setAttribute("NOTIFICATION", "Movie Updated Successfully!");
			}
		}
		listEmployee(request, response);
	}
}
