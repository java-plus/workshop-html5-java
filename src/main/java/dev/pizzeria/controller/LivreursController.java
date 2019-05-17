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

import dev.pizzeria.dao.LivreursDAO;
import dev.pizzeria.model.Livreur;

/**
 *
 */
public class LivreursController extends HttpServlet {

	/** Logger LOGGER */
	private static final Logger LOGGER = LoggerFactory.getLogger(LivreursController.class);
	/**
	 * String TEMPLATE_LIVREURS : adresse du template HTML de base pour la réponse
	 */
	public static final String TEMPLATE_LIVREURS = "templates/livreurs.html";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			resp.setCharacterEncoding("UTF-8");

			String template = Files
					.readAllLines(Paths.get(this.getClass().getClassLoader().getResource(TEMPLATE_LIVREURS).toURI()))
					.stream().collect(Collectors.joining());

			String lignesLivreurs = "";
			for (Livreur livreur : LivreursDAO.livreurs) {
				lignesLivreurs += " <tr><td>" + livreur.getNom() + "</td><td>" + livreur.getPrenom()
						+ "</td><td><a href=\"#\">Modifier</a></td><td><a href=\"#\">Supprimer</a></td></tr>";
			}

			String listeDesLivreurs = template.replaceAll("LIGNES_DES_LIVREURS_ICI", lignesLivreurs);

			PrintWriter writer = resp.getWriter();
			writer.write(listeDesLivreurs);

		} catch (URISyntaxException e) {
			LOGGER.error("Fichier HTML non trouvé", e);
			resp.setStatus(404);
		}
	}

}
