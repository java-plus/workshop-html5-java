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

import dev.pizzeria.Client;

/**
 * Contrôleur responsable du traitement de la réquête : POST /clients.
 */
public class ClientController extends HttpServlet {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClientController.class);

	// TODO créer liste de client
	List<Client> maListeClient = new ArrayList<Client>();

	/**
	 * Page HTML de la réponse en cas d'insertion effectuée. Fichier présent
	 * dans le répertoire src/main/resources.
	 */
	public static final String TEMPLATE_CLIENT_INSERE = "templates/client_insere.html";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// récupération des paramètres nom, prenom, ville, age depuis le
		// navigateur
		// <input name="nom">
		String nom = req.getParameter("nom");
		LOGGER.info("Paramètre nom reçu " + nom);

		String prenom = req.getParameter("prenom");
		LOGGER.info("Paramètre prenom reçu " + prenom);

		String ville = req.getParameter("ville");
		LOGGER.info("Paramètre ville reçu " + ville);

		String ageString = req.getParameter("age");
		int age = Integer.parseInt(ageString);
		LOGGER.info("Paramètre age reçu " + age);

		// Création d'un client
		Client leClient = new Client(nom, prenom, ville, age);

		// Introduction du client dans une liste de client
		maListeClient.add(leClient);

		// Afficher la liste de client
		for (Client lesClients : maListeClient) {
			System.out.println(lesClients);
		}

		// TODO insérer un nouveau client en base de données

		try {
			// réponse au format UTF-8 pour le support des accents
			resp.setCharacterEncoding("UTF-8");

			// récupération du contenu du fichier template
			String template = Files
					.readAllLines(
							Paths.get(this.getClass().getClassLoader().getResource(TEMPLATE_CLIENT_INSERE).toURI()))
					.stream().collect(Collectors.joining());

			// écriture dans le corps de la réponse
			PrintWriter writer = resp.getWriter();
			writer.write(template);

		} catch (URISyntaxException e) {
			LOGGER.error("Fichier HTML non trouvé", e);
		}

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

		PrintWriter writer = resp.getWriter();

		String page = "";

		page += "<!doctype html>";
		page += "<html lang=\"fr\">";
		page += "<head>";
		page += "<meta charset=\"UTF-8\">";
		page += "<title>Titre liste des clients</title>";
		page += "</head>";
		page += "<body>";

		page += "<h1>Gestion des clients</h1>";
		page += "<h2>Liste des clients</h2>";
		page += "<table>";
		page += "<tr>";
		page += "<td><Strong>" + "ID      Nom     Prenom     Ville      Age" + "</Strong></td>";
		for (Client client : maListeClient) {

			page += "<td>" + client.getId() + "</td>";
			page += "<td>" + client.getNom() + "</td>";
			page += "<td>" + client.getPrenom() + "</td>";
			page += "<td>" + client.getVille() + "</td>";
			page += "<td>" + client.getAge() + "</td>";
			page += "</tr>";
		}
		page += "<table>";
		page += "</body>";
		page += "</html>";

		writer.write(page);
	}
}
