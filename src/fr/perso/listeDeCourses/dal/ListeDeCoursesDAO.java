package fr.perso.listeDeCourses.dal;

import java.time.LocalDate;
import java.util.List;

import fr.perso.listeDeCourses.bo.Composition;
import fr.perso.listeDeCourses.bo.Ingredient;
import fr.perso.listeDeCourses.bo.ListeCourse;
import fr.perso.listeDeCourses.bo.MenuWeek;
import fr.perso.listeDeCourses.bo.CompositionListe;
import fr.perso.listeDeCourses.bo.Recette;

public interface ListeDeCoursesDAO {
	
	public List<Ingredient> afficherUneRecette(int id);
	public List<Recette> afficherToutesLesRecettes();
	public void ajouterRecette(Recette nom);
	public void creerIngredient(Ingredient ingredient);
	public List<Recette> afficherListeRecette(String nom);
	public List<Ingredient> afficherIngredient();
	public void ajouterComposition(Composition composition);
	public void ajouterCompositionListe(CompositionListe liste);
	public List<Ingredient> afficherListe(int id);
	public void ajouterListe(ListeCourse nom);
	public void ajouterMenu(List<Integer> menu);
	public List<MenuWeek> afficherTousLesMenus();
	public List<Integer>afficherUnMenu(LocalDate date);
	public Recette recetteById(int id_recette);
}
