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

public class CommandeController extends HttpServlet {

	private static final Logger LOGGER = LoggerFactory.getLogger(PizzaController.class);

	List<Commande> listCommande = new ArrayList();
	// PizzDao listCommande = new PizzDao();

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
		String date = req.getParameter("date");
		String client = req.getParameter("client");
		String livreur = req.getParameter("livreur");

		LOGGER.info("Paramètre date reçu " + date + " client " + client + " livreur " + livreur);

		// TODO insérer une nouvelle commande en base de données
		try {
			// réponse au format UTF-8 pour le support des accents
			resp.setCharacterEncoding("UTF-8");

			if (!((date == null || date.equals("")) || (client == null || client.equals(""))
					|| (livreur == null || livreur.equals("")))) {

				// récupération du contenu du fichier template
				String template = Files
						.readAllLines(Paths.get(
								this.getClass().getClassLoader().getResource("templates/commande_insere.html").toURI()))
						.stream().collect(Collectors.joining());

				// écriture dans le corps de la réponse
				PrintWriter writer = resp.getWriter();
				writer.write(template);

				Commande commande = new Commande(date, client, livreur);
				listCommande.add(commande);

			} else {
				// récupération du contenu du fichier template
				String templateAjout = Files
						.readAllLines(Paths.get(this.getClass().getClassLoader()
								.getResource("templates/fail_ajoutCommande.html").toURI()))
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
							.get(this.getClass().getClassLoader().getResource("templates/liste_commande.html").toURI()))
					.stream().collect(Collectors.joining());

			String text = "";

			for (Commande commande : listCommande) {
				text += "<tr><td>" + ++id + "</td><td>" + commande.getDate() + "</td><td>" + commande.getClient()
						+ "</td><td>" + commande.getLivreur()
						+ "</td><td><a href=\"\">Modifier</a></td><td><a href=\"\">Supprimer</a></td></tr>";
			}

			PrintWriter writer = resp.getWriter();
			writer.write(template.replaceAll("COMMANDEAJOUT", text));

		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
