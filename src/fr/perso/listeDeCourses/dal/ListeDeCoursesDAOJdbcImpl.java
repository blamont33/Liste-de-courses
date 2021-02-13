package fr.perso.listeDeCourses.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.perso.listeDeCourses.bo.Composition;
import fr.perso.listeDeCourses.bo.Ingredient;
import fr.perso.listeDeCourses.bo.ListeCourse;
import fr.perso.listeDeCourses.bo.MenuWeek;
import fr.perso.listeDeCourses.bo.CompositionListe;
import fr.perso.listeDeCourses.bo.Recette;

public class ListeDeCoursesDAOJdbcImpl implements ListeDeCoursesDAO {

	private static final String SELECT_BY_ID = "SELECT i.id_ingredient, i.nom_ingredient, c.quantite, c.unite FROM composition c INNER JOIN ingredients i ON c.id_ingredient = i.id_ingredient WHERE c.id_recette = ?;";
	private static final String SELECT_ALL = "SELECT nom_recette, id_recette, lien FROM recettes GROUP BY nom_recette,id_recette,lien ORDER BY nom_recette";
	private static final String SELECT_BY_NAME = "SELECT nom_recette,id_recette FROM recettes WHERE nom_recette LIKE ? OR nom_recette LIKE ? OR nom_recette LIKE ?;";
	private static final String INSERT_RECETTE = "INSERT INTO recettes VALUES (?,?);";
	private static final String INSERT_COMPOSITION = "INSERT INTO composition VALUES (?,?,?,?);";
	private static final String INSERT_INGREDIENT = "INSERT INTO ingredients VALUES (?);";
	private static final String SELECT_ALL_INGREDIENT = "SELECT * FROM ingredients ORDER BY nom_ingredient ASC";
	private static final String INSERT_LISTE_COMPOSITION = "INSERT INTO compositionlistes VALUES (?,?,?,?,?);";
	private static final String SELECT_LISTE = "SELECT id_ingredient, id_liste, nom_ingredient, unite, SUM(quantite) AS quantite FROM compositionlistes GROUP BY id_ingredient, id_liste, nom_ingredient, unite having id_liste=?";
	private static final String INSERT_LISTE = "INSERT INTO listes VALUES (?);";
	private static final String INSERT_MENU = "INSERT INTO menus VALUES (?,?)";
	private static final String SELECT_ALL_MENUS = "select date_menu from menus GROUP BY date_menu";
	private static final String SELECT_MENU = "select * from menus WHERE date_menu=?";
	private static final String SELECT_RECETTE_BY_ID = "SELECT * FROM recettes WHERE id_recette = ?";

	@Override
	public List<Ingredient> afficherUneRecette(int id) {
		List<Ingredient> listeIngredient = new ArrayList<Ingredient>();

		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ID);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Ingredient ingredient = new Ingredient(rs.getInt("id_ingredient"), rs.getString("nom_ingredient"),
						rs.getInt("quantite"), rs.getString("unite"));

				listeIngredient.add(ingredient);
			}

		} catch (SQLException e) {
			System.out.println("afficherUneRecette");
		}
		return listeIngredient;
	}

	@Override
	public List<Recette> afficherToutesLesRecettes() {
		List<Recette> listeRecette = new ArrayList<Recette>();

		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Recette recette = new Recette(rs.getInt("id_recette"), rs.getString("nom_recette"),
						rs.getString("lien"));
				listeRecette.add(recette);

			}

		} catch (SQLException e) {
			System.out.println("erreur afficherToutesLesRecettes2");
		}
		return listeRecette;
	}

	@Override
	public void ajouterRecette(Recette nom) {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(INSERT_RECETTE, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, nom.getNomRecette());
			pstmt.setString(2, nom.getLien());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				nom.setId(rs.getInt(1));
			}
		} catch (SQLException e) {
			System.out.println("erreur ajouterRecette");
		}
	}

	@Override
	public void creerIngredient(Ingredient ingredient) {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(INSERT_INGREDIENT, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, ingredient.getNomIngredient());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				ingredient.setId(rs.getInt(1));
			}
		} catch (SQLException e) {
			System.out.println("erreur creerIngredient");
		}
	}

	@Override
	public List<Recette> afficherListeRecette(String nom) {
		List<Recette> listeRecette = new ArrayList<Recette>();

		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_NAME);
			pstmt.setString(1, "%" + nom);
			pstmt.setString(2, nom + "%");
			pstmt.setString(3, "%" + nom + "%");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Recette recette = new Recette(rs.getInt("id_recette"), rs.getString("nom_recette"));
				listeRecette.add(recette);
			}

		} catch (SQLException e) {
			System.out.println("erreur afficherListeRecette");
		}
		return listeRecette;
	}

	@Override
	public List<Ingredient> afficherIngredient() {
		List<Ingredient> listeIngredient = new ArrayList<Ingredient>();

		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL_INGREDIENT);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Ingredient ingredient = new Ingredient(rs.getInt("id_ingredient"), rs.getString("nom_ingredient"));

				listeIngredient.add(ingredient);
			}

		} catch (SQLException e) {
			System.out.println("erreur afficherIngredient");
		}
		return listeIngredient;
	}

	@Override
	public void ajouterComposition(Composition composition) {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(INSERT_COMPOSITION);
			for (Ingredient i : composition.getIngredient()) {
				pstmt.setInt(1, composition.getIdRecette());
				pstmt.setInt(2, i.getId());
				pstmt.setInt(3, i.getQuantite());
				if (!i.getUnite().equals("qte")) {
					pstmt.setString(4, i.getUnite());
				} else {
					pstmt.setString(4, "");
				}
				pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println("erreur ajouterComposition");
		}

	}

	@Override
	public void ajouterCompositionListe(CompositionListe liste) {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(INSERT_LISTE_COMPOSITION);
			for (Ingredient i : liste.getIngredient()) {
				pstmt.setInt(1, liste.getIdListe());
				pstmt.setInt(2, i.getId());
				pstmt.setString(3, i.getNomIngredient());
				pstmt.setInt(4, i.getQuantite());
				if (!i.getUnite().equals("qte")) {
					pstmt.setString(5, i.getUnite());
				} else {
					pstmt.setString(5, "");
				}
				pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println("erreur INSERT_LISTE_COMPOSITION");
		}

	}

	@Override
	public List<Ingredient> afficherListe(int id) {
		List<Ingredient> listeIngredient = new ArrayList<Ingredient>();

		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_LISTE);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Ingredient ingredient = new Ingredient(rs.getInt("id_ingredient"), rs.getString("nom_ingredient"),
						rs.getInt("quantite"), rs.getString("unite"));
				listeIngredient.add(ingredient);
			}

		} catch (SQLException e) {
			System.out.println("erreur SELECT_LISTE");
		}
		return listeIngredient;
	}

	@Override
	public void ajouterListe(ListeCourse nom) {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(INSERT_LISTE, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, nom.getNomListe());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				nom.setId_liste(rs.getInt(1));
			}
		} catch (SQLException e) {
			System.out.println("erreur INSERT_LISTE");
		}
	}

	@Override
	public void ajouterMenu(List<Integer> menu) {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(INSERT_MENU);
			for (int m : menu) {
				pstmt.setInt(1, m);
				LocalDate today = LocalDate.now();
				Date date = Date.valueOf(today);
				pstmt.setDate(2, date);
				pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println("erreur INSERT_MENU");
		}
	}

	@Override
	public List<MenuWeek> afficherTousLesMenus() {
		List<MenuWeek> menuListe = new ArrayList<MenuWeek>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL_MENUS);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				MenuWeek menu = new MenuWeek();
				menu.setDate(rs.getDate("date_menu").toLocalDate());
				menuListe.add(menu);
			}
		} catch (SQLException e) {
			System.out.println("erreur SELECT_ALL_MENUS");
		}
		return menuListe;
	}

	@Override
	public List<Integer> afficherUnMenu(LocalDate date) {
		List<Integer> menuListe = new ArrayList<Integer>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_MENU);
			Date dateSql = Date.valueOf(date);
			pstmt.setDate(1, dateSql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				menuListe.add(rs.getInt("id_recette"));
			}
		} catch (SQLException e) {
			System.out.println("erreur SELECT_MENU");
		}
		return menuListe;
	}

	@Override
	public Recette recetteById(int id_recette) {
		Recette recette = new Recette();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_RECETTE_BY_ID);
			pstmt.setInt(1, id_recette);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				recette.setNomRecette(rs.getString("nom_recette"));
				recette.setLien(rs.getString("lien"));
			}

		} catch (SQLException e) {
			System.out.println("erreur SELECT_RECETTE_BY_ID");
		}
		return recette;
	}

}
