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

import dev.pizzeria.model.Livreur;

public class LivreurController extends HttpServlet {

	private static final Logger LOGGER = LoggerFactory.getLogger(PizzaController.class);

	List<Livreur> listLivreur = new ArrayList();

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// récupération du paramètre nom
		// <input name="nom">
		String nom = req.getParameter("nom");
		String prenom = req.getParameter("prénom");

		LOGGER.info("Paramètre nom reçu " + nom + " prénom " + prenom);

		// TODO insérer un nouveau livreur en base de données
		try {
			// réponse au format UTF-8 pour le support des accents
			resp.setCharacterEncoding("UTF-8");

			if (!((nom == null || nom.equals("")) || (prenom == null || prenom.equals("")))) {

				// récupération du contenu du fichier template
				String template = Files
						.readAllLines(Paths.get(
								this.getClass().getClassLoader().getResource("templates/livreur_insere.html").toURI()))
						.stream().collect(Collectors.joining());

				// écriture dans le corps de la réponse
				PrintWriter writer = resp.getWriter();
				writer.write(template);

				Livreur livreur = new Livreur(nom, prenom);
				listLivreur.add(livreur);

			} else {
				// récupération du contenu du fichier template
				String templateAjout = Files
						.readAllLines(Paths.get(this.getClass().getClassLoader()
								.getResource("templates/fail_ajoutLivreur.html").toURI()))
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
							.get(this.getClass().getClassLoader().getResource("templates/liste_livreur.html").toURI()))
					.stream().collect(Collectors.joining());

			String text = "";

			for (Livreur livreur : listLivreur) {
				text += "<tr><td>" + ++id + "</td><td>" + livreur.getNom() + "</td><td>" + livreur.getPrenom() + "</td>"
						+ "</td><td><a href=\"\">Modifier</a></td><td><a href=\"\">Supprimer</a></td></tr>";
			}

			PrintWriter writer = resp.getWriter();
			writer.write(template.replaceAll("LIVREURAJOUT", text));

		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
