package dev.pizzeria.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import fr.diginamic.exception.TechnicalException;

/**
 * Classe qui gère la connexion avec la base de données
 * 
 * @author Cécile Peyras
 *
 */
public class ConnectionMgr {

	/** bundle : ResourceBundle : fichier avec les identifiant de connexion */
	private static ResourceBundle bundle = ResourceBundle.getBundle("database");
	/** conn : Connection */
	private static Connection conn;

	/**
	 * méthode qui permet d'établir la connexion avec la base de données.
	 * 
	 * @return
	 */
	public static Connection getInstance() {

		try {
			if (conn == null || conn.isClosed()) {

				String urlName = bundle.getString("database.url");
				String userName = bundle.getString("database.user");
				String password = bundle.getString("database.password");
				conn = DriverManager.getConnection(urlName, userName, password);

			}

		} catch (SQLException e) {

			throw new TechnicalException("La connexion n'a pas pu s'établir", e);

		}
		return conn;
	}
}
