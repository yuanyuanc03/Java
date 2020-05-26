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


@WebServlet(name = "Connexion", 
	urlPatterns = "/connexion")
public class Connexion extends HttpServlet {
    public static final String ATT_USER         = "utilisateur";
    public static final String ATT_FORM         = "form";
    public static final String ATT_SESSION_USER = "sessionUtilisateur";
    public static final String VUE              = "/WEB-INF/connexion.jsp";

    @Override
    public void init() throws ServletException {
        DAOContext.init( this.getServletContext() );
    }

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /* Affichage de la page de connexion */
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        /* Préparation de l'objet formulaire */
        ConnexionForm form = new ConnexionForm();

        /* Traitement de la requête et récupération du bean en résultant */
        Utilisateur utilisateur = form.connecterUtilisateur( request );

        /* Récupération de la session depuis la requête */
        HttpSession session = request.getSession();

        /**
         * Si aucune erreur de validation n'a eu lieu, alors ajout du bean
         * Utilisateur à la session, sinon suppression du bean de la session.
         */
        if ( form.getErreurs().isEmpty() ) {
            session.setAttribute( ATT_SESSION_USER, utilisateur );
        } else {
            session.setAttribute( ATT_SESSION_USER, null );
        }

        /* Stockage du formulaire et du bean dans l'objet request */
        request.setAttribute( ATT_FORM, form );
        request.setAttribute( ATT_USER, utilisateur );
        
        if(form.getErreurs().isEmpty() && utilisateur.getAdmin()!=null && utilisateur.getAdmin()) {
        	List<Utilisateur> users = UtilisateurDAO.getAllNormalUser();
        	request.setAttribute("normalUsers", users);
        	this.getServletContext().getRequestDispatcher( "/WEB-INF/UserAdmin.jsp" ).forward( request, response );
        }else if(form.getErreurs().isEmpty() && utilisateur.getAdmin()!=null && !utilisateur.getAdmin()) {
        	this.getServletContext().getRequestDispatcher( "/WEB-INF/UserNormal.jsp" ).forward( request, response );
        }else {
        	this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
        }
        
    }
}