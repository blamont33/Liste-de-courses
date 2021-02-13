package fr.perso.listeDeCourses.bo;

public class ListeCourse {
	private int id_liste;
	private String nomListe;
	
	public ListeCourse(int id_liste, String nomListe) {
		super();
		this.id_liste = id_liste;
		this.nomListe = nomListe;
	}

	
	public ListeCourse(String nomListe) {
		super();
		this.nomListe = nomListe;
	}


	public int getId_liste() {
		return id_liste;
	}

	public void setId_liste(int id_liste) {
		this.id_liste = id_liste;
	}

	public String getNomListe() {
		return nomListe;
	}

	public void setNomListe(String nomListe) {
		this.nomListe = nomListe;
	}
	
	
}
