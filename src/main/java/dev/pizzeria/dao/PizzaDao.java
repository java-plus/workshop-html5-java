/**
 * 
 */
package dev.pizzeria.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import dev.pizzeria.connection.ConnectionManager;
import dev.pizzeria.exception.TechnicalException;
import dev.pizzeria.model.Pizza;

/**
 * @author Eloi
 *
 */
public class PizzaDao {

	/**
	 * Constructor
	 * 
	 */
	public PizzaDao() {
		super();
	}

	public void addPizza(Pizza maPizza) {

		Connection conn = ConnectionManager.getInstance();
		Statement monStatement = null;

		try {
			conn.setAutoCommit(false);
			monStatement = conn.createStatement();
			monStatement.executeUpdate("INSERT INTO PIZZA (LIBELLE, REFERENCE, PHOTO, PRIX, UUID) VALUES ('"
					+ maPizza.getLibelle() + "', '" + maPizza.getReference() + "', '" + maPizza.getPhotoUrl() + "', "
					+ maPizza.getPrix() + ", '" + maPizza.getUuid() + "')");
			conn.commit();
		} catch (SQLException e) {
			throw new TechnicalException("Impossible d'ajouter la pizza", e);
		} finally {

			try {
				if (monStatement != null) {
					monStatement.close();
				}
				if (conn != null || conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				throw new TechnicalException("Impossible de fermer les connections à la BDD", e);
			}
		}

	}

	public Pizza findPizzaByUuid(String uuid) {

		Connection conn = ConnectionManager.getInstance();
		Statement monStatement = null;

		try {
			conn.setAutoCommit(false);
			monStatement = conn.createStatement();
			ResultSet curseur = monStatement.executeQuery("SELECT * FROM PIZZA WHERE UUID='" + uuid + "'");

			conn.commit();

			Pizza pizzaTrouvee = new Pizza(curseur.getS("PRIX"), curseur.getDouble("PRIX"), curseur.getString("PRIX"),
					uuid);

			curseur.close();

			return null;

		} catch (SQLException e) {
			throw new TechnicalException("Impossible de récupérer la liste des pizzas", e);
		} finally {

			try {
				if (monStatement != null) {
					monStatement.close();
				}
				if (conn != null || conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				throw new TechnicalException("Impossible de fermer les connections à la BDD", e);
			}
		}

	}

	public List<Pizza> findAllPizza() {

		return null;
	}

}
