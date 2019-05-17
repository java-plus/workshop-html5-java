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

public class GestionPizzaController extends HttpServlet {

	public static final String TEMPLATE_GESTION_PIZZAS = "templates/templ_gestion_pizzas.html";

	private static final Logger LOGGER = LoggerFactory.getLogger(GestionPizzaController.class);

	public GestionPizzaController() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			// réponse au format UTF-8 pour le support des accents
			resp.setCharacterEncoding("UTF-8");

			// récupération du contenu du fichier template
			String template = Files
					.readAllLines(
							Paths.get(this.getClass().getClassLoader().getResource(TEMPLATE_GESTION_PIZZAS).toURI()))
					.stream().collect(Collectors.joining());

			// Remplacement de texte de template
			String texteHTMLDeRemplacement = "";
			for (int i = 0; i < PizzaController.listePizzas.size(); i++) {
				texteHTMLDeRemplacement += "<tr><td>" + PizzaController.listePizzas.get(i).getId() + "</td><td>"
						+ PizzaController.listePizzas.get(i).getLibelle() + "</td><td>"
						+ PizzaController.listePizzas.get(i).getReference() + "</td><td>"
						+ PizzaController.listePizzas.get(i).getPrix() + "</td><td><img src="
						+ PizzaController.listePizzas.get(i).getPhoto() + " height=\"352\" width=\"470\" ></td></tr>";
			}
			template = template.replace("ListePizzasARemplacer", texteHTMLDeRemplacement);

			// écriture dans le corps de la réponse
			PrintWriter writer = resp.getWriter();
			writer.write(template);

		} catch (URISyntaxException e) {
			LOGGER.error("Fichier HTML non trouvé", e);
		}

	}

}
