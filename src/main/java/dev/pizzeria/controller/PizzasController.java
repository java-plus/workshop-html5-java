package dev.pizzeria.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.pizzeria.dao.PizzasDAO;
import dev.pizzeria.model.Pizza;

/**
 * Controller de gestion de l'ensemble des pizzas de la pizzeria
 */
public class PizzasController extends HttpServlet {

	/** Logger LOGGER */
	private static final Logger LOGGER = LoggerFactory.getLogger(PizzasController.class);

	/**
	 * String TEMPLATE_PIZZAS : adresse du template HTML servant de base à la
	 * génération de la réponse
	 */
	public static final String TEMPLATE_PIZZAS = "templates/pizzas.html";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			resp.setCharacterEncoding("UTF-8");

			String template = Files
					.readAllLines(Paths.get(this.getClass().getClassLoader().getResource(TEMPLATE_PIZZAS).toURI()))
					.stream().collect(Collectors.joining());

			String lignesPizzas = "";
			for (Pizza pizza : PizzasDAO.pizzas) {
				lignesPizzas += "<tr>\r\n" + "              <td>" + pizza.getId() + "</td>\r\n" + "              <td>"
						+ pizza.getLibelle() + "</td>\r\n" + "              <td>" + pizza.getReference() + "</td>\r\n"
						+ "              <td>" + pizza.getPrix() + "</td>\r\n"
						+ "              <td><img style=\"max-width:200px\" src=\"" + pizza.getPhoto() + "\"</td>\r\n"
						+ "              <td><a href=\"#\">Modifier</a></td>\r\n"
						+ "              <td><a href=\"#\">Supprimer</a></td>\r\n" + "            </tr>";
			}

			String listeDesPizzas = template.replaceAll("LIGNES_DES_PIZZAS_ICI", lignesPizzas);

			PrintWriter writer = resp.getWriter();
			writer.write(listeDesPizzas);

		} catch (URISyntaxException e) {
			LOGGER.error("Fichier HTML non trouvé", e);
			resp.setStatus(404);
		}
	}

}
