package dev.pizzeria.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Guillaume Classe utilitaire pour gérer les interactions avec la base
 *         de données
 *
 */
public class ConnectionManager {

	/** LOGGER : Logger */
	private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionManager.class);

	/**
	 * dbProperties : ResourceBundle : Récupération du fichier properties contenant
	 * les infos de connexion à la base de données
	 */
	private static ResourceBundle dbProperties = ResourceBundle.getBundle("db");
	/** con : Connection */
	private static Connection con;

	/**
	 * @return retourne une instance de connexion à la base de données
	 */
	public static Connection getInstance() {
		try {
			if (con == null || con.isClosed()) {
				String url = dbProperties.getString("database.url");
				String user = dbProperties.getString("database.user");
				String password = dbProperties.getString("database.password");

				con = DriverManager.getConnection(url, user, password);

			}
		} catch (SQLException e) {
			LOGGER.error("Connexion à la base impossible", e);
		}

		return con;
	}

	/**
	 * @return renvoie les informations concernant le driver
	 */
	public static String getDriver() {
		String driverName = dbProperties.getString("database.driver");
		return driverName;
	}

}
