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

	private static final Logger LOGGER = LoggerFactory.getLogger(PizzaController.class);
	private List<Pizza> listePizzas = new ArrayList();

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

			} else if ((reference == null || "".equals(reference))) {
				String templateAjout;

				templateAjout = Files
						.readAllLines(Paths.get(
								this.getClass().getClassLoader().getResource("templates/pizza_ajout.html").toURI()))
						.stream().collect(Collectors.joining());

				String corpsReponseHTML = templateAjout.replaceAll("AREMPLACER", "La référence est obligatoire.");
				PrintWriter writer = resp.getWriter();
				writer.write(corpsReponseHTML);

			} else if ((prix == null || "".equals(prix))) {
				String templateAjout;

				templateAjout = Files
						.readAllLines(Paths.get(
								this.getClass().getClassLoader().getResource("templates/pizza_ajout.html").toURI()))
						.stream().collect(Collectors.joining());

				String corpsReponseHTML = templateAjout.replaceAll("AREMPLACER", "Le prix est obligatoire.");
				PrintWriter writer = resp.getWriter();
				writer.write(corpsReponseHTML);

			} else if ((photo == null || "".equals(photo))) {
				String templateAjout;

				templateAjout = Files
						.readAllLines(Paths.get(
								this.getClass().getClassLoader().getResource("templates/pizza_ajout.html").toURI()))
						.stream().collect(Collectors.joining());

				String corpsReponseHTML = templateAjout.replaceAll("AREMPLACER", "La photo est obligatoire.");
				PrintWriter writer = resp.getWriter();
				writer.write(corpsReponseHTML);

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

				listePizzas.add(pizza);
			}

		} catch (URISyntaxException e) {
			LOGGER.error("Fichier HTML non trouvé", e);
		}

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int id = 0;
		try {

			// réponse au format UTF-8 pour le support des accents
			resp.setCharacterEncoding("UTF-8");

			String template = Files
					.readAllLines(Paths
							.get(this.getClass().getClassLoader().getResource("templates/liste_pizza.html").toURI()))
					.stream().collect(Collectors.joining());
			// Création d'un String pour stocker le code html à afficher
			String texte = "";

			for (Pizza pizza : listePizzas) {
				texte += "<tr><td>" + ++id + "</td><td>" + pizza.getLibelle() + "</td><td>" + pizza.getReference()
						+ "</td><td>" + pizza.getPrix() + "</td><td>" + pizza.getPhoto()
						+ "</td><td><a href=\"#\">Modifier</a></td><td><a href=\"#\">Supprimer</a></td></tr>";

			}

			PrintWriter writer = resp.getWriter();
			writer.write(template.replace("PIZZAAJOUT", texte));

		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
