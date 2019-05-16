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
import dev.pizzeria.model.PizzDao;

/**
 * Contrôleur responsable du traitement de la réquête : POST /clients.
 */
public class ClientController extends HttpServlet {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClientController.class);

	/**
	 * Page HTML de la réponse en cas d'insertion effectuée. Fichier présent
	 * dans le répertoire src/main/resources.
	 */
	public static final String TEMPLATE_CLIENT_INSERE = "templates/client_insere.html";

	// List<Client> listCli = new ArrayList();
	PizzDao listCli = new PizzDao();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// récupération du paramètre nom
		// <input name="nom">
		String nom = req.getParameter("nom");
		String prenom = req.getParameter("prenom");
		String ville = req.getParameter("ville");
		String age = req.getParameter("age");

		LOGGER.info("Paramètre nom reçu " + nom + " prenom " + prenom + " ville " + ville + " age " + age);

		// TODO insérer un nouveau client en base de données

		try {
			// réponse au format UTF-8 pour le support des accents
			resp.setCharacterEncoding("UTF-8");

			if (!((nom == null || nom.equals("")) || (prenom == null || prenom.equals(""))
					|| (ville == null || ville.equals("")) || (age == null || age.equals("")))) {

				// récupération du contenu du fichier template
				String template = Files
						.readAllLines(
								Paths.get(this.getClass().getClassLoader().getResource(TEMPLATE_CLIENT_INSERE).toURI()))
						.stream().collect(Collectors.joining());

				// écriture dans le corps de la réponse
				PrintWriter writer = resp.getWriter();
				writer.write(template);

				Client client = new Client(nom, prenom, ville, age);
				listCli.add(client);

			} else {
				// récupération du contenu du fichier template
				String templateAjout = Files
						.readAllLines(Paths.get(
								this.getClass().getClassLoader().getResource("templates/fail_ajoutCli.html").toURI()))
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
							.get(this.getClass().getClassLoader().getResource("templates/liste_client.html").toURI()))
					.stream().collect(Collectors.joining());

			String text = "";

			for (Client client : listCli.findCli()) {
				text += "<tr><td>" + ++id + "</td><td>" + client.getNom() + "</td><td>" + client.getPrenom()
						+ "</td><td>" + client.getVille() + "</td><td>" + client.getAge()
						+ "</td><td><a href=\"\">Modifier</a></td><td><a href=\"\">Supprimer</a></td></tr>";
			}

			PrintWriter writer = resp.getWriter();
			writer.write(template.replaceAll("CLIENTAJOUT", text));

		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
