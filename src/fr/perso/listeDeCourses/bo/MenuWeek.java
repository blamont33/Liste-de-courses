package fr.perso.listeDeCourses.bo;

import java.time.LocalDate;
import java.util.List;

public class MenuWeek {
	private List<Integer> id_recette;
	private LocalDate date;

	public MenuWeek(List<Integer> id_recette) {
		super();
		this.id_recette = id_recette;

	}

	public MenuWeek(List<Integer> id_recette, LocalDate date) {
		super();
		this.id_recette = id_recette;
		this.date = date;
	}

	public MenuWeek() {
		super();
	}

	public List<Integer> getId_recette() {
		return id_recette;
	}

	public void setId_recette(List<Integer> id_recette) {
		this.id_recette = id_recette;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

}
