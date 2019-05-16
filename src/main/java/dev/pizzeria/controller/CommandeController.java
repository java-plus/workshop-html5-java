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

import dev.pizzeria.model.Commande;
import dev.pizzeria.model.Livreur;
import dev.pizzeria.model.PizzeriaDaoCommande;
import dev.pizzeria.model.PizzeriaDaoLivreur;

public class CommandeController extends HttpServlet {

	private static final Logger LOGGER = LoggerFactory.getLogger(CommandeController.class);
	private PizzeriaDaoCommande dao = new PizzeriaDaoCommande();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String numero = req.getParameter("numero");
		String date = req.getParameter("date");
		String livreur = req.getParameter("livreur");
		String client = req.getParameter("client");

		LOGGER.info("Paramètre nom reçu " + numero + " " + date + " " + livreur + " " + client);

		try {
			// réponse au format UTF-8 pour le support des accents
			resp.setCharacterEncoding("UTF-8");

			if ((numero == null || "".equals(numero))) {
				String templateAjout = Files
						.readAllLines(Paths.get(
								this.getClass().getClassLoader().getResource("templates/commande_ajout.html").toURI()))
						.stream().collect(Collectors.joining());

				String corpsReponseHTML = templateAjout.replaceAll("AREMPLACER", "Le numéro est obligatoire.");
				PrintWriter writer = resp.getWriter();
				writer.write(corpsReponseHTML);
			} else if ((date == null || "".equals(date))) {
				String templateAjout = Files
						.readAllLines(Paths.get(
								this.getClass().getClassLoader().getResource("templates/commande_ajout.html").toURI()))
						.stream().collect(Collectors.joining());

				String corpsReponseHTML = templateAjout.replaceAll("AREMPLACER", "La date est obligatoire.");
				PrintWriter writer = resp.getWriter();
				writer.write(corpsReponseHTML);
			} else if ((livreur == null || "".equals(livreur))) {
				String templateAjout = Files
						.readAllLines(Paths.get(
								this.getClass().getClassLoader().getResource("templates/commande_ajout.html").toURI()))
						.stream().collect(Collectors.joining());

				String corpsReponseHTML = templateAjout.replaceAll("AREMPLACER", "Le livreur est obligatoire.");
				PrintWriter writer = resp.getWriter();
				writer.write(corpsReponseHTML);
			} else if ((client == null || "".equals(client))) {
				String templateAjout = Files
						.readAllLines(Paths.get(
								this.getClass().getClassLoader().getResource("templates/commande_ajout.html").toURI()))
						.stream().collect(Collectors.joining());

				String corpsReponseHTML = templateAjout.replaceAll("AREMPLACER", "Le client est obligatoire.");
				PrintWriter writer = resp.getWriter();
				writer.write(corpsReponseHTML);
			} else {

				// récupération du contenu du fichier template
				String template = Files
						.readAllLines(Paths.get(
								this.getClass().getClassLoader().getResource("templates/commande_insere.html").toURI()))
						.stream().collect(Collectors.joining());

				// écriture dans le corps de la réponse
				PrintWriter writer = resp.getWriter();
				writer.write(template);

				Commande commande = new Commande(numero, date, livreur, client);

				dao.add(commande);

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
							.get(this.getClass().getClassLoader().getResource("templates/liste_commande.html").toURI()))
					.stream().collect(Collectors.joining());

			// Création d'un String pour stocker le code html à afficher
			String texte = "";

			 for (Commande commande : dao.findCommande()) {
			 texte += "<tr><td>" + ++id + "</td><td>" + commande.getNumero() +
			 "</td><td>" + commande.getDate()
			 + "</td><td>" + commande.getLivreur() + "</td><td>" +
			 commande.getClient()
			 + "</td><td><a href=\"#\">Modifier</a></td><td><a
			 href=\"#\">Supprimer</a></td></tr>";
			
			 }

			PrintWriter writer = resp.getWriter();
			writer.write(template.replace("CLIENTAJOUT", texte));

		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
