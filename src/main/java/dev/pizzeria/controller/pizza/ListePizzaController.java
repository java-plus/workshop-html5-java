package dev.pizzeria.controller.pizza;

import static dev.pizzeria.controller.utils.RecupererHtmlUtils.recupererPageHtml;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dev.pizzeria.dao.PizzaDao;
import dev.pizzeria.model.Pizza;

/**
 * Classe gérant l'affichage de la liste des Pizzas
 * 
 * @author Kevin.s
 *
 */
public class ListePizzaController extends HttpServlet {

	/** TEMPLATE_LISTE_PIZZA : String chemin vers listePizza.html */
	private static final String TEMPLATE_LISTE_PIZZA = "templates/listePizza.html";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		StringBuilder sBuilder = new StringBuilder();
		resp.setCharacterEncoding("UTF-8");
		PrintWriter writer = resp.getWriter();

		genererListePizza(sBuilder);
		String pageListePizzas = recupererPageHtml(this, TEMPLATE_LISTE_PIZZA);
		pageListePizzas = pageListePizzas.replaceAll("LISTE_DE_PIZZA_ICI", sBuilder.toString());
		writer.write(pageListePizzas);

	}

	/**
	 * Genere la liste des Pizza dans le StringBuilder passé en paramêtre
	 * 
	 * @param sBuilder
	 */
	private void genererListePizza(StringBuilder sBuilder) {

		PizzaDao pizzaDao = new PizzaDao();
		List<Pizza> listePizza = pizzaDao.getAllPizza();

		for (int i = 0; i < listePizza.size(); i++) {
			sBuilder.append("<tr><td>").append(listePizza.get(i).getId()).append("</td>")
					.append("<td>").append(listePizza.get(i).getLibelle()).append("</td>")
					.append("<td>").append(listePizza.get(i).getReference()).append("</td>")
					.append("<td>").append(listePizza.get(i).getPrix()).append("</td>")
					.append("<td><img style=\"max-width:150px\" src=\"").append(listePizza.get(i).getPhoto()).append(
							"\"></td>")
					.append("<td>").append("<a href = \"#\">Modifier</a>").append("</td>")
					.append("<td>").append("<a href = \"#\">Supprimer</a>").append("</td>")
					.append("</tr>");
		}
	}
}
