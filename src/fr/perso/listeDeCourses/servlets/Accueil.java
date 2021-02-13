package fr.perso.listeDeCourses.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.perso.listeDeCourses.bll.ListeDeCoursesManager;
import fr.perso.listeDeCourses.bo.Recette;

/**
 * Servlet implementation class Accueil
 */
@WebServlet("/Accueil")
public class Accueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ListeDeCoursesManager listeDeCoursesManager = new ListeDeCoursesManager();


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		//On récupère les recettes en BDD
		List<Recette> listeAllRecette = listeDeCoursesManager.afficherToutesLesRecettes();
		request.setAttribute("AllRecettes", listeAllRecette);

		request.getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		

		doGet(request, response);
	}

}
