/**
 * 
 */
package dev.pizzeria.controller.livreurs;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.pizzeria.controller.clients.ClientController;
import dev.pizzeria.model.Pizza;

/**
 * @author Eloi
 *
 */
public class LivreurController extends HttpServlet {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClientController.class);

	public List<Pizza> listLivreurs = new ArrayList<>();

	/**
	 * Constructor
	 * 
	 */
	public LivreurController() {
		// TODO Auto-generated constructor stub
	}

}
