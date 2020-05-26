package com.exemple.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exemple.bdd.DAOContext;

/**
 * Servlet implementation class Deconnexion
 */
@WebServlet(name = "Deconnexion", 
		urlPatterns = "/deconnexion")
public class Deconnexion extends HttpServlet {
	public static final String ATT_SESSION_USER = "sessionUtilisateur";
	public static final String VUE              = "/WEB-INF/connexion.jsp";   
	
	@Override
    public void init() throws ServletException {
        DAOContext.init( this.getServletContext() );
    }
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute( ATT_SESSION_USER, null );
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
	}

}
