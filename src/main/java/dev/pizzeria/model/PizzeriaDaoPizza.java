package dev.pizzeria.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.diginamic.exception.TechnicalException;

/**
 * Classe qui gère les opération CRUD pour les pizzas
 * 
 * @author Cécile Peyras
 *
 */
public class PizzeriaDaoPizza {

	/**
	 * méthode qui permet d'ajouter une pizza à la base de données
	 * 
	 * @param pizza
	 */
	public void add(Pizza pizza) {
		Connection conn = ConnectionMgr.getInstance();
		Statement statement = null;

		try {
			conn.setAutoCommit(false);
			statement = conn.createStatement();
			statement
					.executeUpdate("INSERT INTO PIZZAS (LIBELLE, REFERENCE, PRIX, PHOTO) VALUES ('" + pizza.getLibelle()
							+ "', '" + pizza.getReference() + "', " + pizza.getPrix() + ",'" + pizza.getPhoto() + "')");

			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				throw new TechnicalException("Le rollback n'a pas fonctionné", e);
			}
			throw new TechnicalException("L'ajout ne s'est pas fait", e);
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {

				throw new TechnicalException("La fermeture ne s'est pas faite", e);
			}
		}

	}

	/**
	 * méthode qui permet de retourner la liste des pizzas de la base de données
	 * 
	 * @return
	 */
	public List<Pizza> findPizza() {

		Connection conn = ConnectionMgr.getInstance();
		Statement statement = null;
		String texte = null;
		List<Pizza> listePizza = new ArrayList<>();

		try {
			conn.setAutoCommit(false);
			statement = conn.createStatement();

			ResultSet curseur = statement.executeQuery("SELECT * FROM PIZZAS");

			while (curseur.next()) {
				Integer id = curseur.getInt("idpizzas");
				String libelle = curseur.getString("libelle");
				String reference = curseur.getString("reference");
				String prix = curseur.getString("prix");
				String photo = curseur.getString("photo");

				Pizza pizza = new Pizza(id, libelle, reference, prix, photo);

				listePizza.add(pizza);

			}
			conn.commit();

		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				throw new TechnicalException("Le rollback n'a pas fonctionné", e);
			}
			throw new TechnicalException("L'ajout ne s'est pas fait", e);
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {

				throw new TechnicalException("La fermeture ne s'est pas faite", e);
			}
		}

		return listePizza;
	}

}
