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

import dev.pizzeria.model.Pizza;
import dev.pizzeria.model.PizzeriaDaoPizza;
import fr.diginamic.exception.TechnicalException;

/**
 * Classe qui controlle les pages web
 * 
 * @author Cécile Peyras
 *
 */
public class PizzaController extends HttpServlet {

	/** LOGGER : Logger */
	private static final Logger LOGGER = LoggerFactory.getLogger(PizzaController.class);
	/**
	 * dao : PizzeriaDaoPizza - qui permet de gérer les informations sur la page
	 */
	private PizzeriaDaoPizza dao = new PizzeriaDaoPizza();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String libelle = req.getParameter("libelle");
		String reference = req.getParameter("reference");
		String prix = req.getParameter("prix");
		String photo = req.getParameter("photo");

		LOGGER.info("Paramètre nom reçu " + libelle + " " + reference + " " + prix + " " + photo);

		try {

			// réponse au format UTF-8 pour le support des accents
			resp.setCharacterEncoding("UTF-8");

			if ((libelle == null || "".equals(libelle))) {
				String templateAjout;

				templateAjout = Files
						.readAllLines(Paths.get(
								this.getClass().getClassLoader().getResource("templates/pizza_ajout.html").toURI()))
						.stream().collect(Collectors.joining());

				String corpsReponseHTML = templateAjout.replaceAll("AREMPLACER", "Le libellé est obligatoire.");
				PrintWriter writer = resp.getWriter();
				writer.write(corpsReponseHTML);
				resp.setStatus(400);

			} else if ((reference == null || "".equals(reference))) {
				String templateAjout;

				templateAjout = Files
						.readAllLines(Paths.get(
								this.getClass().getClassLoader().getResource("templates/pizza_ajout.html").toURI()))
						.stream().collect(Collectors.joining());

				String corpsReponseHTML = templateAjout.replaceAll("AREMPLACER", "La référence est obligatoire.");
				PrintWriter writer = resp.getWriter();
				writer.write(corpsReponseHTML);
				resp.setStatus(400);
			} else if ((prix == null || "".equals(prix))) {
				String templateAjout;

				templateAjout = Files
						.readAllLines(Paths.get(
								this.getClass().getClassLoader().getResource("templates/pizza_ajout.html").toURI()))
						.stream().collect(Collectors.joining());

				String corpsReponseHTML = templateAjout.replaceAll("AREMPLACER", "Le prix est obligatoire.");
				PrintWriter writer = resp.getWriter();
				writer.write(corpsReponseHTML);
				resp.setStatus(400);

			} else if ((photo == null || "".equals(photo))) {
				String templateAjout;

				templateAjout = Files
						.readAllLines(Paths.get(
								this.getClass().getClassLoader().getResource("templates/pizza_ajout.html").toURI()))
						.stream().collect(Collectors.joining());

				String corpsReponseHTML = templateAjout.replaceAll("AREMPLACER", "La photo est obligatoire.");
				PrintWriter writer = resp.getWriter();
				writer.write(corpsReponseHTML);
				resp.setStatus(400);

			} else {

				// récupération du contenu du fichier template
				String template = Files
						.readAllLines(Paths.get(
								this.getClass().getClassLoader().getResource("templates/pizza_insere.html").toURI()))
						.stream().collect(Collectors.joining());

				// écriture dans le corps de la réponse
				PrintWriter writer = resp.getWriter();
				writer.write(template);

				Pizza pizza = new Pizza(libelle, reference, prix, photo);

				dao.add(pizza);
			}

		} catch (URISyntaxException e) {
			LOGGER.error("Fichier HTML non trouvé", e);
			throw new TechnicalException("Fichier HTML non trouvé", e);
		}

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {

			// réponse au format UTF-8 pour le support des accents
			resp.setCharacterEncoding("UTF-8");

			String template = Files
					.readAllLines(Paths
							.get(this.getClass().getClassLoader().getResource("templates/liste_pizza.html").toURI()))
					.stream().collect(Collectors.joining());
			// Création d'un StringBuilder pour stocker le code html à afficher
			StringBuilder sb = new StringBuilder();

			for (Pizza pizza : dao.findPizza()) {
				sb.append("<tr><td>").append(pizza.getId()).append("</td><td>").append(pizza.getLibelle())
						.append("</td><td>").append(pizza.getReference()).append("</td><td>").append(pizza.getPrix())
						.append("</td><td><img style=\"max-width: 150px\" src=\"").append(pizza.getPhoto())
						.append("\"></td><td><a href=\"#\">Modifier</a></td><td><a href=\"#\">Supprimer</a></td></tr>");

			}

			PrintWriter writer = resp.getWriter();
			writer.write(template.replace("PIZZAAJOUT", sb));

		} catch (URISyntaxException e) {
			LOGGER.error("Fichier HTML non trouvé", e);
			throw new TechnicalException("Fichier HTML non trouvé", e);
		}

	}

}
