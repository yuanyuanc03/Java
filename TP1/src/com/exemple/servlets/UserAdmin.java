package com.exemple.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exemple.bdd.DAOContext;
import com.exemple.bdd.UtilisateurDAO;
import com.exemple.beans.Utilisateur;
import com.exemple.forms.ConnexionForm;

@WebServlet(name = "UserAdmin", 
		urlPatterns = "/userAdmin")
public class UserAdmin extends HttpServlet {
	
	private static final String ID  = "supprimer";
    public static final String VUE              = "/WEB-INF/UserAdmin.jsp";   
    
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
		
		Long id =  Long.parseLong(request.getParameter(ID));
		UtilisateurDAO.deleteUser(id);
		List<Utilisateur> users = UtilisateurDAO.getAllNormalUser();
    	request.setAttribute("normalUsers", users);
    	this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
        
	}



}
