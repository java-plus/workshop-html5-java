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

import dev.pizzeria.model.Livreur;
import dev.pizzeria.model.PizzeriaDaoLivreur;

public class LivreurController extends HttpServlet {

	private static final Logger LOGGER = LoggerFactory.getLogger(LivreurController.class);
	private PizzeriaDaoLivreur dao = new PizzeriaDaoLivreur();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String nom = req.getParameter("nom");
		String prenom = req.getParameter("prenom");

		LOGGER.info("Paramètre nom reçu " + nom + " " + prenom);

		try {
			// réponse au format UTF-8 pour le support des accents
			resp.setCharacterEncoding("UTF-8");

			if ((nom == null || "".equals(nom))) {
				String templateAjout = Files
						.readAllLines(Paths.get(
								this.getClass().getClassLoader().getResource("templates/livreur_ajout.html").toURI()))
						.stream().collect(Collectors.joining());

				String corpsReponseHTML = templateAjout.replaceAll("AREMPLACER", "Le nom est obligatoire.");
				PrintWriter writer = resp.getWriter();
				writer.write(corpsReponseHTML);
			} else if ((prenom == null || "".equals(prenom))) {
				String templateAjout = Files
						.readAllLines(Paths.get(
								this.getClass().getClassLoader().getResource("templates/livreur_ajout.html").toURI()))
						.stream().collect(Collectors.joining());

				String corpsReponseHTML = templateAjout.replaceAll("AREMPLACER", "Le prénom est obligatoire.");
				PrintWriter writer = resp.getWriter();
				writer.write(corpsReponseHTML);
			} else {

				// récupération du contenu du fichier template
				String template = Files
						.readAllLines(Paths.get(
								this.getClass().getClassLoader().getResource("templates/livreur_insere.html").toURI()))
						.stream().collect(Collectors.joining());

				// écriture dans le corps de la réponse
				PrintWriter writer = resp.getWriter();
				writer.write(template);

				Livreur livreur = new Livreur(nom, prenom);

				dao.add(livreur);

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
							.get(this.getClass().getClassLoader().getResource("templates/liste_livreur.html").toURI()))
					.stream().collect(Collectors.joining());

			// Création d'un String pour stocker le code html à afficher
			String texte = "";

			for (Livreur livreur : dao.findLivreur()) {
				texte += "<tr><td>" + ++id + "</td><td>" + livreur.getNom() + "</td><td>" + livreur.getPrenom()
						+ "</td><td><a href=\"#\">Modifier</a></td><td><a href=\"#\">Supprimer</a></td></tr>";

			}

			PrintWriter writer = resp.getWriter();
			writer.write(template.replace("LIVREURAJOUT", texte));

		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
