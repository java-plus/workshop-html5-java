/**
 * 
 */
package dev.pizzeria.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServlet;

/**
 * @author Eloi
 *
 */
public class PizzaController extends HttpServlet {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClientController.class);

	public List<Pizza> listPizza = new ArrayList<>();

	/**
	 * Constructor
	 * 
	 */
	public PizzaController() {
		// TODO Auto-generated constructor stub
	}

}
