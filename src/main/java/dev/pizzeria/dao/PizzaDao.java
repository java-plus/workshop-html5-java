package dev.pizzeria.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.pizzeria.controller.utils.ConnectionUtils;
import dev.pizzeria.controller.utils.UpdateQueryUtils;
import dev.pizzeria.exception.TechnicalException;
import dev.pizzeria.model.Pizza;

/**
 * Classe contenant la liste des pizzas
 * 
 * @author Kevin.s
 *
 */
public class PizzaDao {

	/** SERVICE_LOG : Logger */
	private static final Logger SERVICE_LOG = LoggerFactory.getLogger(UpdateQueryUtils.class);

	/** listeClient : List<Client> */
	public static List<Pizza> listePizza = new ArrayList<>();

	/**
	 * Constructeur
	 * 
	 */
	public PizzaDao() {
		super();
	}

	/**
	 * ajoute une pizza dans la base de données
	 * 
	 * @param pizza
	 */
	public void ajouterPizza(Pizza pizza) {
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("insert into pizza (PIZ_LIBELLE,PIZ_REF,PIZ_PRIX,PIZ_PHOTO) values ('");
		sBuilder.append(pizza.getLibelle()).append("','");
		sBuilder.append(pizza.getReference()).append("',");
		sBuilder.append(pizza.getPrix()).append(",'");
		sBuilder.append(pizza.getPhoto()).append("')");

		UpdateQueryUtils.updateQuery(sBuilder.toString());
		pizza.setId(recupereIdPizza(pizza));

	}

	/**
	 * recupere l'identifiant d'une pizza
	 * 
	 * @param pizza
	 *            pizza dont on veut récuperer l'identifiant
	 * @return l'identifiant de la pizza
	 */
	private Integer recupereIdPizza(Pizza pizza) {

		Statement myStatement = null;
		StringBuilder sBuild = new StringBuilder();
		ResultSet resultSet = null;
		Integer id = null;

		try {
			myStatement = ConnectionUtils.getInstance().createStatement();
			resultSet = myStatement.executeQuery("select PIZ_ID from pizza where PIZ_REF='" + pizza.getReference()
					+ "';");
			if (resultSet.next()) {
				id = resultSet.getInt("PIZ_ID");
			}
			return id;
		} catch (SQLException e) {
			SERVICE_LOG.error("probleme de selection en base", e);
			throw new TechnicalException("probleme de selection en base", e);

		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					SERVICE_LOG.error("impossible de fermer le resultSet", e);
					throw new TechnicalException("impossible de fermer le resultSet", e);
				}
			}
			if (myStatement != null) {
				try {
					myStatement.close();
				} catch (SQLException e) {
					SERVICE_LOG.error("impossible de fermer le statement", e);
					throw new TechnicalException("impossible de fermer le statement", e);
				}
			}
			ConnectionUtils.doClose();
		}
	}

	/**
	 * retourne la liste de toutes les pizzas en base de données
	 * 
	 * @return List<Pizza>
	 */
	public List<Pizza> getAllPizza() {

		List<Pizza> listePizza = new ArrayList<>();
		Statement myStatement = null;
		StringBuilder sBuild = new StringBuilder();
		ResultSet resultSet = null;

		try {
			myStatement = ConnectionUtils.getInstance().createStatement();
			resultSet = myStatement.executeQuery("select * from pizza");
			while (resultSet.next()) {
				Integer id = resultSet.getInt("PIZ_ID");
				String libelle = resultSet.getString("PIZ_LIBELLE");
				String ref = resultSet.getString("PIZ_REF");
				double prix = resultSet.getDouble("PIZ_PRIX");
				String url = resultSet.getString("PIZ_PHOTO");

				Pizza pizza = new Pizza(libelle, ref, prix, url);
				pizza.setId(id);
				listePizza.add(pizza);
			}
			return listePizza;
		} catch (SQLException e) {
			SERVICE_LOG.error("probleme de selection en base", e);
			throw new TechnicalException("probleme de selection en base", e);

		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					SERVICE_LOG.error("impossible de fermer le resultSet", e);
					throw new TechnicalException("impossible de fermer le resultSet", e);
				}
			}
			if (myStatement != null) {
				try {
					myStatement.close();
				} catch (SQLException e) {
					SERVICE_LOG.error("impossible de fermer le statement", e);
					throw new TechnicalException("impossible de fermer le statement", e);
				}
			}
			ConnectionUtils.doClose();
		}

	}

}
