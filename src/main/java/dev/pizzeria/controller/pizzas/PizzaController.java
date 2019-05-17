/**
 * 
 */
package dev.pizzeria.controller.pizzas;

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
public class PizzaController extends HttpServlet {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClientController.class);

	public List<Pizza> listPizza = new ArrayList<>();

	/**
	 * Page HTML de la réponse en cas d'insertion effectuée. Fichier présent dans le répertoire src/main/resources.
	 */
	public static final String TEMPLATE_CLIENT_INSERE = "templates/client_infos.html";
}
