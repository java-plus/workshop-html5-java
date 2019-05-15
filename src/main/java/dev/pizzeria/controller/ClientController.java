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

//import org.eclipse.jetty.io.ssl.ALPNProcessor.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.pizzeria.dao.ClientsDAO;
import dev.pizzeria.model.Client;

/**
 * 
 */
public class ClientController extends HttpServlet {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClientController.class);

	/**
	 * Page HTML de la réponse en cas d'insertion effectuée. Fichier présent dans le
	 * répertoire src/main/resources.
	 */
	public static final String TEMPLATE_CLIENT_CREER = "templates/client.html";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			// récupération des paramètres du formulaire
			String nom = req.getParameter("nom");
			String prenom = req.getParameter("prenom");
			String ville = req.getParameter("ville");
			int age = Integer.parseInt(req.getParameter("age"));

			// insérer un nouveau client en base de données
			ClientsDAO.clients.add(new Client(nom, prenom, ville, age));

			resp.sendRedirect("/clients");

		} catch (Exception e) {

			LOGGER.info("Erreur d'insertion du client");
			resp.sendRedirect("/client");

			// try {
			// // resp.setCharacterEncoding("UTF-8");
			// // String template = Files
			// // .readAllLines(
			// //
			// Paths.get(this.getClass().getClassLoader().getResource(TEMPLATE_CLIENT_CREER).toURI()))
			// // .stream().collect(Collectors.joining());
			// // PrintWriter writer = resp.getWriter();
			// // writer.write(template);
			//
			// } catch (URISyntaxException f) {
			// LOGGER.error("Fichier HTML non trouvé", f);
			// }
		}

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			// réponse au format UTF-8 pour le support des accents
			resp.setCharacterEncoding("UTF-8");

			// récupération du contenu du fichier template
			String template = Files
					.readAllLines(
							Paths.get(this.getClass().getClassLoader().getResource(TEMPLATE_CLIENT_CREER).toURI()))
					.stream().collect(Collectors.joining());

			// écriture dans le corps de la réponse
			PrintWriter writer = resp.getWriter();
			writer.write(template);

		} catch (URISyntaxException e) {
			LOGGER.error("Fichier HTML non trouvé", e);
		}
	}

}
