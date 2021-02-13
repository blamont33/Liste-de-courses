package fr.perso.listeDeCourses.bll;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.perso.listeDeCourses.bo.Composition;
import fr.perso.listeDeCourses.bo.Ingredient;
import fr.perso.listeDeCourses.bo.ListeCourse;
import fr.perso.listeDeCourses.bo.MenuWeek;
import fr.perso.listeDeCourses.bo.CompositionListe;
import fr.perso.listeDeCourses.bo.Recette;
import fr.perso.listeDeCourses.dal.DAOFactory;

public class ListeDeCoursesManager {
	public List<Ingredient> afficherUneRecette(int id) {
		return DAOFactory.getListeDeCoursesDAO().afficherUneRecette(id);
	}

	public List<Recette> afficherListeRecette(String nom) {
		return DAOFactory.getListeDeCoursesDAO().afficherListeRecette(nom);
	}

	public List<Recette> afficherToutesLesRecettes() {
		return DAOFactory.getListeDeCoursesDAO().afficherToutesLesRecettes();
	}

	public List<Recette> afficherRecetteAvecIngredient() {
		List<Recette> listeRecette = afficherToutesLesRecettes();
		List<Recette> listeRecette2 = new ArrayList<Recette>();
		for (Recette r : listeRecette) {
			int id = r.getId();
			List<Ingredient> listeIngredient = afficherUneRecette(id);
			Recette recette = new Recette(id, r.getNomRecette(),r.getLien(), listeIngredient);
			listeRecette2.add(recette);
		}
		return listeRecette2;
	}
	
	public List<MenuWeek> afficherListeMenus(){
		List<MenuWeek> listeMenus = afficherTousLesMenus();
		List<MenuWeek> listeMenus2 = new ArrayList<MenuWeek>();
		for(MenuWeek i : listeMenus) {
			LocalDate date = i.getDate();
			List<Integer>Liste_id = afficherUnMenu(date);
			MenuWeek menu = new MenuWeek(Liste_id,date);
			listeMenus2.add(menu);
		}
		return listeMenus2;
	}

	public List<Ingredient> afficherIngredient() {
		// TODO Auto-generated method stub
		return DAOFactory.getListeDeCoursesDAO().afficherIngredient();
	}
	
	public void creerIngredient(Ingredient ingredient) {
		DAOFactory.getListeDeCoursesDAO().creerIngredient(ingredient);
	}
	
	public void ajouterRecette(Recette nom) {
		DAOFactory.getListeDeCoursesDAO().ajouterRecette(nom);
	}
	
	public void ajouterComposition(Composition composition) {
		DAOFactory.getListeDeCoursesDAO().ajouterComposition(composition);
	}
	public void ajouterCompositionListe(CompositionListe liste) {
		DAOFactory.getListeDeCoursesDAO().ajouterCompositionListe(liste);
	}
	public List<Ingredient> afficherListe(int id){
		return DAOFactory.getListeDeCoursesDAO().afficherListe(id);
	}

	public void ajouterListe(ListeCourse nom) {
		DAOFactory.getListeDeCoursesDAO().ajouterListe(nom);
	}
	public void ajouterMenu(List<Integer> menu) {
		DAOFactory.getListeDeCoursesDAO().ajouterMenu(menu);
	}
	public List<MenuWeek> afficherTousLesMenus(){
		return DAOFactory.getListeDeCoursesDAO().afficherTousLesMenus();
	}
	public List<Integer>afficherUnMenu(LocalDate date){
		return DAOFactory.getListeDeCoursesDAO().afficherUnMenu(date);
	}
	public Recette recetteById(int id_recette) {
		return DAOFactory.getListeDeCoursesDAO().recetteById(id_recette);
	}
}
