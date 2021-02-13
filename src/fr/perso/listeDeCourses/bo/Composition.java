package fr.perso.listeDeCourses.bo;

import java.util.List;

public class Composition {
	private List<Ingredient> ingredient;
	private int idRecette;
	
	public Composition(List<Ingredient> ingredient, int idRecette) {
		super();
		this.ingredient = ingredient;
		this.idRecette = idRecette;
	}

	public List<Ingredient> getIngredient() {
		return ingredient;
	}

	public void setIngredient(List<Ingredient> ingredient) {
		this.ingredient = ingredient;
	}

	public int getIdRecette() {
		return idRecette;
	}

	public void setIdRecette(int idRecette) {
		this.idRecette = idRecette;
	}
	
	
	
}

	
	