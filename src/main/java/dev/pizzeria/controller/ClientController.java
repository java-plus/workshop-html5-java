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

import dev.pizzeria.model.Client;
import dev.pizzeria.model.PizzariaDaoClient;

/**
 * Contrôleur responsable du traitement de la réquête : POST /clients.
 */
public class ClientController extends HttpServlet {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClientController.class);
	private PizzariaDaoClient dao = new PizzariaDaoClient();

	/**
	 * Page HTML de la réponse en cas d'insertion effectuée. Fichier présent
	 * dans le répertoire src/main/resources.
	 */
	public static final String TEMPLATE_CLIENT_INSERE = "templates/client_insere.html";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// récupération du paramètre nom
		// <input name="nom">
		String nom = req.getParameter("nom");
		String prenom = req.getParameter("prenom");
		String ville = req.getParameter("ville");
		String age = req.getParameter("age");

		LOGGER.info("Paramètre nom reçu " + nom + " " + prenom + " " + ville + " " + age);

		try {
			// réponse au format UTF-8 pour le support des accents
			resp.setCharacterEncoding("UTF-8");

			if ((nom == null || "".equals(nom))) {
				String templateAjout = Files
						.readAllLines(Paths.get(
								this.getClass().getClassLoader().getResource("templates/client_ajout.html").toURI()))
						.stream().collect(Collectors.joining());

				String corpsReponseHTML = templateAjout.replaceAll("AREMPLACER", "Le nom est obligatoire.");
				PrintWriter writer = resp.getWriter();
				writer.write(corpsReponseHTML);
				resp.setStatus(400);
			} else if ((prenom == null || "".equals(prenom))) {
				String templateAjout = Files
						.readAllLines(Paths.get(
								this.getClass().getClassLoader().getResource("templates/client_ajout.html").toURI()))
						.stream().collect(Collectors.joining());

				String corpsReponseHTML = templateAjout.replaceAll("AREMPLACER", "Le prénom est obligatoire.");
				PrintWriter writer = resp.getWriter();
				writer.write(corpsReponseHTML);
				resp.setStatus(400);
			} else if ((ville == null || "".equals(ville))) {
				String templateAjout = Files
						.readAllLines(Paths.get(
								this.getClass().getClassLoader().getResource("templates/client_ajout.html").toURI()))
						.stream().collect(Collectors.joining());

				String corpsReponseHTML = templateAjout.replaceAll("AREMPLACER", "La ville est obligatoire.");
				PrintWriter writer = resp.getWriter();
				writer.write(corpsReponseHTML);
				resp.setStatus(400);
			} else if ((age == null || "".equals(age))) {
				String templateAjout = Files
						.readAllLines(Paths.get(
								this.getClass().getClassLoader().getResource("templates/client_ajout.html").toURI()))
						.stream().collect(Collectors.joining());

				String corpsReponseHTML = templateAjout.replaceAll("AREMPLACER", "L'age est obligatoire.");
				PrintWriter writer = resp.getWriter();
				writer.write(corpsReponseHTML);
				resp.setStatus(400);
			} else {

				// récupération du contenu du fichier template
				String template = Files
						.readAllLines(
								Paths.get(this.getClass().getClassLoader().getResource(TEMPLATE_CLIENT_INSERE).toURI()))
						.stream().collect(Collectors.joining());

				// écriture dans le corps de la réponse
				PrintWriter writer = resp.getWriter();
				writer.write(template);

				Client client = new Client(nom, prenom, ville, age);

				dao.add(client);

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
					.readAllLines(Paths.get(this.getClass().getClassLoader()
							.getResource("templates/gestion_client_ajoute.html").toURI()))
					.stream().collect(Collectors.joining());

			// Création d'un String pour stocker le code html à afficher
			String texte = "";

			for (Client client : dao.findClient()) {
				texte += "<tr><td>" + ++id + "</td><td>" + client.getNom() + "</td><td>" + client.getPrenom()
						+ "</td><td>" + client.getVille() + "</td><td>" + client.getAge()
						+ "</td><td><a href=\"#\">Modifier</a></td><td><a href=\"#\">Supprimer</a></td></tr>";

			}

			PrintWriter writer = resp.getWriter();
			writer.write(template.replace("CLIENTAJOUT", texte));

		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
