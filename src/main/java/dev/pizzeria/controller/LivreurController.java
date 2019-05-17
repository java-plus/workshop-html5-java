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

	public static List<Livreur> listeLivreurs = new ArrayList<>();

	private static final Logger LOGGER = LoggerFactory.getLogger(LivreurController.class);

	public static final String TEMPLATE_LIVREUR_INSERE = "templates/livreur_insere.html";

	public LivreurController() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// récupération du paramètre nom
		// <input name="nom">
		String nom = req.getParameter("nom");

		LOGGER.info("Paramètre nom reçu " + nom);

		// TODO insérer une nouvelle pizza en base de données
		// Ajout à la liste des pizzas
		listeLivreurs.add(new Livreur(req.getParameter("nom"), req.getParameter("prenom")));

		try {
			// réponse au format UTF-8 pour le support des accents
			resp.setCharacterEncoding("UTF-8");

			// récupération du contenu du fichier template
			String template = Files
					.readAllLines(
							Paths.get(this.getClass().getClassLoader().getResource(TEMPLATE_LIVREUR_INSERE).toURI()))
					.stream().collect(Collectors.joining());

			// Remplacement de texte de template
			template = template.replace("codeARemplacer", "Livreur " + req.getParameter("nom") + " ajouté");

			// écriture dans le corps de la réponse
			PrintWriter writer = resp.getWriter();
			writer.write(template);

		} catch (URISyntaxException e) {
			LOGGER.error("Fichier HTML non trouvé", e);
		}
	}

}
