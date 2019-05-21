package dev.pizzeria.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dev.pizzeria.exception.TechnicalException;
import dev.pizzeria.model.Pizza;
import dev.pizzeria.util.ConnectionManager;

public class PizzaDao implements IPizzaDao {

	@Override
	public void saveNewPizza(Pizza pizza) {
		Connection con = ConnectionManager.getInstance();
		try {
			Statement statement = con.createStatement();
			int nb = statement.executeUpdate(
					"INSERT INTO pizza (libelle, reference, prix, photo) values ('" + pizza.getLibelle() + "','"
							+ pizza.getReference() + "', " + pizza.getPrix() + ",'" + pizza.getPhoto() + "' );");

		} catch (SQLException e) {
			throw new TechnicalException(e.getMessage(), e);
		}
		try {

			if (con != null) {
				con.close();
			}

		} catch (SQLException e) {
			throw new TechnicalException("Fermeture de la connexion impossible", e);
		}

	}

	@Override
	public List<Pizza> findAllPizzas() {
		List<Pizza> listePizzas = new ArrayList<Pizza>();
		Connection con = ConnectionManager.getInstance();
		try {
			Statement statement = con.createStatement();
			ResultSet curseur = statement.executeQuery("select * from pizza");
			while (curseur.next()) {
				int id = curseur.getInt("idPIZZA");
				String libelle = curseur.getString("libelle");
				String reference = curseur.getString("reference");
				float prix = curseur.getFloat("prix");
				String photo = curseur.getString("photo");
				Pizza pizza = new Pizza(id, libelle, reference, prix, photo);
				listePizzas.add(pizza);

			}
		} catch (SQLException e) {
			throw new TechnicalException("Erreur lors de la récupération de l'objet Pizza", e);
		}

		try {
			con.close();
		} catch (SQLException e) {
			throw new TechnicalException("Fermeture de la connexion impossible", e);
		}

		return listePizzas;
	}

	@Override
	public Pizza findPizzaById(int id) {
		Connection con = ConnectionManager.getInstance();
		Pizza pizza = null;
		try {
			Statement statement = con.createStatement();
			ResultSet curseur = statement.executeQuery("SELECT * from pizza where idPIZZA = " + id);
			if (curseur.next()) {
				String libelle = curseur.getString("libelle");
				String reference = curseur.getString("reference");
				float prix = curseur.getFloat("prix");
				String photo = curseur.getString("photo");
				pizza = new Pizza(id, libelle, reference, prix, photo);
			}

			return pizza;
		} catch (SQLException e) {
			throw new TechnicalException("Erreur lors de la récupération de la pizza via le code : " + id, e);
		}
	}

	@Override
	public boolean pizzaExists(int id) {
		Connection con = ConnectionManager.getInstance();
		try {
			Statement statement = con.createStatement();
			ResultSet curseur = statement.executeQuery("select idPIZZA from pizza where idPIZZA = " + id);
			if (curseur.next()) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			throw new TechnicalException("Erreur lors de la vérification de l'existence de la pizza", e);
		}

	}

	@Override
	public void deletePizzaById(int id) {
		Connection con = ConnectionManager.getInstance();
		try {
			Statement statement = con.createStatement();
			int nb = statement.executeUpdate("DELETE from pizza where idPIZZA = " + id);
		} catch (SQLException e) {
			throw new TechnicalException("Erreur lors de la suppression de la pizza", e);
		}

	}

	@Override
	public void updatePizzaById(int id, Pizza pizza) {
		Connection con = ConnectionManager.getInstance();

		try {
			Statement statement = con.createStatement();
			int nb = statement.executeUpdate("update pizza set idPIZZA = " + pizza.getId() + ", libelle = "
					+ pizza.getLibelle() + ", reference = " + pizza.getReference() + ", prix = " + pizza.getReference()
					+ ", prix = " + pizza.getPrix() + ", photo = " + pizza.getPhoto() + "where idPIZZA=" + id);
		} catch (SQLException e) {
			throw new TechnicalException("Erreur lors de la modification de la pizza", e);
		}

	}

}
