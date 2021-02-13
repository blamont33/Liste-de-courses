package fr.perso.listeDeCourses.bo;

import java.util.List;

public class CompositionListe {
	private List<Ingredient> ingredient;
	private int idListe;
	
	public CompositionListe(List<Ingredient> ingredient, int idListe) {
		super();
		this.ingredient = ingredient;
		this.idListe = idListe;
	}

	public List<Ingredient> getIngredient() {
		return ingredient;
	}

	public void setIngredient(List<Ingredient> ingredient) {
		this.ingredient = ingredient;
	}

	public int getIdListe() {
		return idListe;
	}

	public void setIdListe(int idListe) {
		this.idListe = idListe;
	}
	
	
}
