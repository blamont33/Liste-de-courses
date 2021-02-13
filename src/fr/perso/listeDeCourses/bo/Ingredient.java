package fr.perso.listeDeCourses.bo;

public class Ingredient {
	
	private int id;
	private String nomIngredient;
	private int quantite;
	private String unite;
	
	public Ingredient(int id, String nomIngredient, int quantite, String unite) {
		super();
		this.id = id;
		this.nomIngredient = nomIngredient;
		this.quantite = quantite;
		this.unite = unite;
	}

	public Ingredient() {
		// TODO Auto-generated constructor stub
	}



	public Ingredient(String nomIngredient, int quantite, String unite) {
		super();
		this.nomIngredient = nomIngredient;
		this.quantite = quantite;
		this.unite = unite;
	}

	public Ingredient(int id, int quantite, String unite) {
		super();
		this.id = id;
		this.quantite = quantite;
		this.unite = unite;
	}

	public Ingredient(String nomIngredient) {
		super();
		this.nomIngredient = nomIngredient;
	}

	public Ingredient(int id, String nomIngredient) {
		super();
		this.id = id;
		this.nomIngredient = nomIngredient;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNomIngredient() {
		return nomIngredient;
	}

	public void setNomIngredient(String nomIngredient) {
		this.nomIngredient = nomIngredient;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public String getUnite() {
		return unite;
	}

	public void setUnite(String unite) {
		this.unite = unite;
	}

	@Override
	public String toString() {
		return "Ingredient [id=" + id + ", nomIngredient=" + nomIngredient + ", quantite=" + quantite + ", unite="
				+ unite + "]";
	}


	
	
	
	

}
