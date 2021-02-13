package fr.perso.listeDeCourses.dal;

public abstract class DAOFactory {
	public static ListeDeCoursesDAO getListeDeCoursesDAO() {
		return new ListeDeCoursesDAOJdbcImpl();
	}
}
