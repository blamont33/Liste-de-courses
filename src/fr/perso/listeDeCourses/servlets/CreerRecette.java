package fr.perso.listeDeCourses.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.microsoft.sqlserver.jdbc.StringUtils;

import fr.perso.listeDeCourses.bll.ListeDeCoursesManager;
import fr.perso.listeDeCourses.bo.Composition;
import fr.perso.listeDeCourses.bo.Ingredient;
import fr.perso.listeDeCourses.bo.Recette;

/**
 * Servlet implementation class CreerRecette
 */
@WebServlet("/CreerRecette")
public class CreerRecette extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ListeDeCoursesManager listeDeCoursesManager = new ListeDeCoursesManager();
	private static List<Ingredient> listeIngredients = new ArrayList<Ingredient>();
	private static int idRecette;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Ingredient> ListeIngredient = listeDeCoursesManager.afficherIngredient();
		request.setAttribute("listeIngredient", ListeIngredient);

		request.getSession().setAttribute("listeIngredient", listeIngredients);

		request.getServletContext().getRequestDispatcher("/WEB-INF/creerRecette.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		request.setAttribute("listeIngredients", listeIngredients);

		// Créer un ingrédient
		String creerIngredient = request.getParameter("choix");
		String nomIngredient = request.getParameter("nomIngredient");
		if (creerIngredient.equals("Créer un ingrédient") && !StringUtils.isEmpty(nomIngredient)) {
			Ingredient ingredient = new Ingredient(nomIngredient);
			listeDeCoursesManager.creerIngredient(ingredient);
			System.out.println("J'ai crée l'ingrédient : " + nomIngredient + " id : " + ingredient.getId());
		}

		// Créer une liste d'aliments
		String ajouterIngredient = request.getParameter("choix");
		if (ajouterIngredient.equals("ajouterIngredient")) {
			String nom = request.getParameter("nom");
			int quantite = Integer.parseInt(request.getParameter("quantite"));
			String unite = request.getParameter("unite");
			int id = Integer.parseInt(request.getParameter("id"));
			Ingredient ingredient = new Ingredient(id, nom, quantite, unite);
			listeIngredients.add(ingredient);
			System.out.println("J'ai ajoutée un aliment : id= " + id + "nom = " + nom + " quantité = " + quantite
					+ " unité = " + unite);
		}

		// Valider la recette
		String validerRecette = request.getParameter("choix");
		if (validerRecette.equals("Valider la recette")) {
			String nomRecette = request.getParameter("nomRecette");
			String lien = request.getParameter("lien");
			Recette recette = new Recette(nomRecette, lien);
			listeDeCoursesManager.ajouterRecette(recette);
			idRecette = recette.getId();
			System.out.println("J'ai ajouté la recette : " + nomRecette + " id : " + idRecette);
			// On crée une instance de compostition
			Composition composition = new Composition(listeIngredients, idRecette);
			listeDeCoursesManager.ajouterComposition(composition);
			listeIngredients.clear();
		}

		// Supprimer un ingredient de la liste
		String supprimer = request.getParameter("choix");
		if (supprimer.equals("X")) {
			int id = Integer.parseInt(request.getParameter("supprimer"));
			int position = 0;
			for (Ingredient i : listeIngredients) {
				if (i.getId() == id) {
					position = listeIngredients.indexOf(i);
				}
			}
			System.out.println(position);
			listeIngredients.remove(position);

		}

		doGet(request, response);
	}

	protected void doAutre(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
