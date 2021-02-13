package fr.perso.listeDeCourses.bo;

import java.util.List;

public class Recette {
	private int id;
	private String nomRecette;
	private List<Ingredient> listeIngredient;
	private String lien;

	public Recette(int id, String nomRecette, List<Ingredient> listeIngredient) {
		super();
		this.id = id;
		this.nomRecette = nomRecette;
		this.listeIngredient = listeIngredient;
	}

	public Recette() {
		super();
	}
	
	

	public Recette(int id, String nomRecette, List<Ingredient> listeIngredient, String lien) {
		super();
		this.id = id;
		this.nomRecette = nomRecette;
		this.listeIngredient = listeIngredient;
		this.lien = lien;
	}

	public Recette(int id, String nomRecette, String lien, List<Ingredient> listeIngredient) {
		super();
		this.id = id;
		this.nomRecette = nomRecette;
		this.lien = lien;
		this.listeIngredient = listeIngredient;

	}

	public Recette(int id, String nomRecette, String lien) {
		super();
		this.id = id;
		this.nomRecette = nomRecette;
		this.lien = lien;
	}

	public Recette(String nomRecette, String lien) {
		super();
		this.nomRecette = nomRecette;
		this.lien = lien;
	}

	public Recette(int id, String nomRecette) {
		super();
		this.id = id;
		this.nomRecette = nomRecette;
	}

	public Recette(String nomRecette) {
		super();
		this.nomRecette = nomRecette;
	}

	public String getLien() {
		return lien;
	}

	public void setLien(String lien) {
		this.lien = lien;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNomRecette() {
		return nomRecette;
	}

	public void setNomRecette(String nomRecette) {
		this.nomRecette = nomRecette;
	}

	public List<Ingredient> getListeIngredient() {
		return listeIngredient;
	}

	public void setListeIngredient(List<Ingredient> listeIngredient) {
		this.listeIngredient = listeIngredient;
	}

	@Override
	public String toString() {
		return "Recette [id=" + id + ", nomRecette=" + nomRecette + ", listeIngredient=" + listeIngredient + "]";
	}

}
