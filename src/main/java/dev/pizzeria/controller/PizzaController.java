package dev.pizzeria.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.pizzeria.model.Pizza;

public class PizzaController extends HttpServlet {

	public static List<Pizza> listePizzas = new ArrayList<>();

	private static final Logger LOGGER = LoggerFactory.getLogger(PizzaController.class);

	public static final String TEMPLATE_PIZZA_INSEREE = "templates/pizza_inseree.html";

	public PizzaController() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// récupération du paramètre nom
		// <input name="nom">
		String nom = req.getParameter("libelle");

		LOGGER.info("Paramètre nom reçu " + nom);

		// TODO insérer une nouvelle pizza en base de données
		// Ajout à la liste des pizzas
		listePizzas.add(new Pizza(req.getParameter("libelle"), req.getParameter("reference"),
				Double.parseDouble(req.getParameter("prix")), req.getParameter("image")));

		try {
			// réponse au format UTF-8 pour le support des accents
			resp.setCharacterEncoding("UTF-8");

			// récupération du contenu du fichier template
			String template = Files
					.readAllLines(
							Paths.get(this.getClass().getClassLoader().getResource(TEMPLATE_PIZZA_INSEREE).toURI()))
					.stream().collect(Collectors.joining());

			// Remplacement de texte de template
			template = template.replace("codeARemplacer", "Pizza " + req.getParameter("libelle") + " ajoutée");

			// écriture dans le corps de la réponse
			PrintWriter writer = resp.getWriter();
			writer.write(template);

		} catch (URISyntaxException e) {
			LOGGER.error("Fichier HTML non trouvé", e);
		}
	}

}
