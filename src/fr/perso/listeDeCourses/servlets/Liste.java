package fr.perso.listeDeCourses.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.perso.listeDeCourses.bll.ListeDeCoursesManager;
import fr.perso.listeDeCourses.bo.CompositionListe;
import fr.perso.listeDeCourses.bo.Ingredient;
import fr.perso.listeDeCourses.bo.ListeCourse;
import fr.perso.listeDeCourses.bo.MenuWeek;
import fr.perso.listeDeCourses.bo.Recette;

/**
 * Servlet implementation class listeDeCourses
 */
@WebServlet("/Liste")
public class Liste extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ListeDeCoursesManager listeDeCoursesManager = new ListeDeCoursesManager();
	private static List<Ingredient> listeIngredient = new ArrayList<Ingredient>();
	private static int id_liste;
	private static List<Ingredient> ListeFinale;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setAttribute("listeFinale", ListeFinale);

		List<Ingredient> ListeComplete = listeDeCoursesManager.afficherIngredient();
		request.setAttribute("ListeComplete", ListeComplete);

		request.getServletContext().getRequestDispatcher("/WEB-INF/liste.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//On récupère la liste des recettes composant le menu
		List<Recette> listeRecette = (List<Recette>) request.getSession().getAttribute("listeRecette");
		
	

		///////////////////////////////////////GENERATION LISTE DE COURSE////////////////////////////////////////////
		
		// Après avoir cliqué sur "générer liste de courses"
		String generer = request.getParameter("choix");
		if(generer!=null && generer.equals("generer")) {
		//On récupère tous les ingrédients présent dans la liste de recettes
		for (Recette r : listeRecette) {
			for (Ingredient i : r.getListeIngredient()) {
				listeIngredient.add(i);
			}
		}
		
		//On crée une liste en BDD pour récupérer son ID
		ListeCourse listeCourse = new ListeCourse("");
		listeDeCoursesManager.ajouterListe(listeCourse);
		id_liste = listeCourse.getId_liste();

		// On crée une instance de compositionListe qu'on ajoute en BDD pour pouvoir la traiter et additionner les mêmes ingrédients.
		CompositionListe compositionListe = new CompositionListe(listeIngredient, id_liste);
		listeDeCoursesManager.ajouterCompositionListe(compositionListe);

		//On récupère la liste de course additionnée en BDD
		ListeFinale = listeDeCoursesManager.afficherListe(id_liste);

		request.setAttribute("listeFinale", ListeFinale);
		}
		///////////////////////////////////////GENERATION LISTE DE COURSE -FIN //////////////////////////////////////////////
		
		// ajouter un ingrédient
		String ajouter = request.getParameter("ajouter");
		if (ajouter != null) {
			if (ajouter.equals("Ajouter Ã  la liste")) {
				String nom = request.getParameter("nom");
				int quantite = Integer.parseInt(request.getParameter("quantite"));
				String unite = request.getParameter("unite");
				Ingredient ingredient = new Ingredient(nom, quantite, unite);
				ListeFinale.add(ingredient);
			}
		}

		// Supprimer un ingredient de la liste
		String supprimer = request.getParameter("choix");
		if (supprimer != null) {
			if (supprimer.equals("X")) {
				int id = Integer.parseInt(request.getParameter("supprimer"));
				int position = 0;
				for (Ingredient i : ListeFinale) {
					if (i.getId() == id) {
						position = ListeFinale.indexOf(i);
					}
				}
				ListeFinale.remove(position);
			}
		}

		doGet(request, response);
	}

}
