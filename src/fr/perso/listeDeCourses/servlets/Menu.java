package fr.perso.listeDeCourses.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.perso.listeDeCourses.bll.ListeDeCoursesManager;
import fr.perso.listeDeCourses.bo.Ingredient;
import fr.perso.listeDeCourses.bo.MenuWeek;
import fr.perso.listeDeCourses.bo.Recette;

/**
 * Servlet implementation class Menu
 */
@WebServlet("/Menu")
public class Menu extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ListeDeCoursesManager listeDeCoursesManager = new ListeDeCoursesManager();
	private static List<Recette> listeRecette = new ArrayList<Recette>();
	private static int idRecette;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Créer la liste des recettes existantes
		List<MenuWeek> ListeMenus = listeDeCoursesManager.afficherListeMenus();
		request.setAttribute("listeMenus", ListeMenus);

		request.setAttribute("listeRecette", listeRecette);

		request.getServletContext().getRequestDispatcher("/WEB-INF/menu.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		//Enregistrer le menu
		String enregistrer = request.getParameter("choix");
		if(enregistrer!=null && enregistrer.equals("enregistrer")) {
		List<Integer> menuListe = new ArrayList<Integer>();
		for (Recette r : listeRecette) {
			int id_recette = r.getId();
			menuListe.add(id_recette);
		}
		listeDeCoursesManager.ajouterMenu(menuListe);
		}

		// Après avoir cliqué sur "ajouter" dans l'accueil :
		// on récupère l'id de la recette
		String idRecetteTemp = request.getParameter("id");
		if (request.getParameter("id") != null) {
			idRecette = Integer.parseInt(idRecetteTemp);
			// On récupère le nom de la recette
			String nomRecette = request.getParameter("nomRecette");
			request.setAttribute("nomRecette", nomRecette);
			// On récupère le lien vers la recette
			String lien = request.getParameter("lien");
			// On crée une liste des ingrédients de la recette
			List<Ingredient> listeIngredient = listeDeCoursesManager.afficherUneRecette(idRecette);
			// On crée une nouvelle instance de la recette
			Recette recette = new Recette(idRecette, nomRecette, lien, listeIngredient);
			// On ajoute cette recette à une liste
			listeRecette.add(recette);
			// On met cette liste de recette en attribut de session pour la récupérer dans
			// la Servlet liste.
			request.getSession().setAttribute("listeRecette", listeRecette);
		}

		// Supprimer un ingredient de la liste
		String supprimer = request.getParameter("choix");
		if (supprimer != null) {
			if (supprimer.equals("X")) {
				int id = Integer.parseInt(request.getParameter("supprimer"));
				int position = 0;
				for (Recette r : listeRecette) {
					if (r.getId() == id) {
						position = listeRecette.indexOf(r);
					}
				}
				listeRecette.remove(position);
			}
		}

		// Afficher la liste des recettes sélectionnée
		String afficher = request.getParameter("afficher");
		if (afficher != null) {
			// On supprime la liste déjà en mémoire
			listeRecette.clear();
			// On récupère la date du menu à afficher
			LocalDate date = LocalDate.parse(request.getParameter("date"));
			// On récupère la liste des id_recette enregistrés à ette date
			List<Integer> Liste_id = listeDeCoursesManager.afficherUnMenu(date);
			// Pour chaque id_recette on crée la liste des ingrédient de la recette, on récupère le
			// nom, lien et on crée une instance qu'on ajoute à la liste des recettes.
			for (Integer i : Liste_id) {
				List<Ingredient> listeIngredient = listeDeCoursesManager.afficherUneRecette(i);
				Recette recette = listeDeCoursesManager.recetteById(i);
				Recette recetteFinale = new Recette(i, recette.getNomRecette(), recette.getLien(), listeIngredient);
				listeRecette.add(recetteFinale);
			}
		}
		request.getSession().setAttribute("listeRecette", listeRecette);

		response.sendRedirect("Menu");

	}

}
