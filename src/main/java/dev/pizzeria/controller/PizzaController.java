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

	List<Pizza> listPizza = new ArrayList();

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// récupération du paramètre libellé
		// <input name="libelle">
		String libelle = req.getParameter("libellé");
		String reference = req.getParameter("référence");
		String prix = req.getParameter("prix");
		String photo = req.getParameter("photo");

		LOGGER.info(
				"Paramètre libellé reçu " + libelle + " référence " + reference + " prix " + prix + " photo " + photo);

		// TODO insérer une nouvelle pizza en base de données
		try {
			// réponse au format UTF-8 pour le support des accents
			resp.setCharacterEncoding("UTF-8");

			if (!((libelle == null || libelle.equals("")) || (reference == null || reference.equals(""))
					|| (prix == null || prix.equals("")) || (photo == null || photo.equals("")))) {

				// récupération du contenu du fichier template
				String template = Files
						.readAllLines(Paths.get(
								this.getClass().getClassLoader().getResource("templates/pizza_insere.html").toURI()))
						.stream().collect(Collectors.joining());

				// écriture dans le corps de la réponse
				PrintWriter writer = resp.getWriter();
				writer.write(template);

				Pizza pizza = new Pizza(libelle, reference, prix, photo);
				listPizza.add(pizza);

			} else {
				// récupération du contenu du fichier template
				String templateAjout = Files
						.readAllLines(Paths.get(
								this.getClass().getClassLoader().getResource("templates/fail_ajoutPizza.html").toURI()))
						.stream().collect(Collectors.joining());

				// écriture dans le corps de la réponse
				String corpsReponseHTML = templateAjout.replaceAll("AREMPLACER", "Veuillez remplir tous les champs");
				PrintWriter writer = resp.getWriter();
				writer.write(corpsReponseHTML);
			}

		} catch (URISyntaxException e) {
			LOGGER.error("Fichier HTML non trouvé", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
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

			String text = "";

			for (Pizza pizza : listPizza) {
				text += "<tr><td>" + ++id + "</td><td>" + pizza.getLibelle() + "</td><td>" + pizza.getReference()
						+ "</td><td>" + pizza.getPrix() + "</td><td>" + pizza.getPhoto()
						+ "</td><td><a href=\"\">Modifier</a></td><td><a href=\"\">Supprimer</a></td></tr>";
			}

			PrintWriter writer = resp.getWriter();
			writer.write(template.replaceAll("PIZZAAJOUT", text));

		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
