/**
 * 
 */
package dev.pizzeria.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import dev.pizzeria.exception.TechnicalException;

/**
 * @author Eloi
 *
 */
public class ConnectionManager {

	private static ResourceBundle bundle = ResourceBundle.getBundle("database");
	private static Connection conn;

	public static Connection getInstance() {
		try {
			if (conn == null || conn.isClosed()) {
				conn = DriverManager.getConnection(bundle.getString("database.url"), bundle.getString("database.user"),
						bundle.getString("database.password"));
			}
			return conn;
		} catch (SQLException e) {
			throw new TechnicalException("Impossible de se connecter à la BDD", e);
		}
	}

	public static String getDriverName() {
		return bundle.getString("database.driver");
	}
}
