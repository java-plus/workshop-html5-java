package dev.pizzeria.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.pizzeria.model.Client;

/**
 * Contrôleur responsable du traitement de la réquête : POST /clients.
 */
public class ClientController extends HttpServlet {

	/** serialVersionUID : long */
	private static final long serialVersionUID = -6376570603895061109L;

	private static final Logger LOGGER = LoggerFactory.getLogger(ClientController.class);

	/**
	 * Page HTML de la réponse en cas d'insertion effectuée. Fichier présent dans le
	 * répertoire src/main/resources.
	 */
	public static final String TEMPLATE_CLIENT_INSERE = "templates/liste_clients.html";

	/**
	 * TEMPLATE_CREER_CLIENT : page HTML en cas d'échec de l'ajout, fichier présent
	 * dans src/main/webapp
	 */

	public static final String TEMPLATE_CREER_CLIENT = "templates/creer_client.html";

	private static List<Client> listeClients = new ArrayList<Client>();

	private AtomicInteger incr = new AtomicInteger();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		String template;
		try {
			template = Files
					.readAllLines(
							Paths.get(this.getClass().getClassLoader().getResource(TEMPLATE_CREER_CLIENT).toURI()))
					.stream().collect(Collectors.joining());

			PrintWriter writter = resp.getWriter();
			writter.write(template);

		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// récupération du paramètre nom
		// <input name="nom">
		resp.setCharacterEncoding("UTF-8");
		String template;
		String nom = req.getParameter("nom");
		String prenom = req.getParameter("prenom");
		String ville = req.getParameter("ville");
		String ageString = req.getParameter("age");
		if (nom.isEmpty() || prenom.isEmpty() || ville.isEmpty() || ageString.isEmpty()) {
			try {
				template = Files
						.readAllLines(
								Paths.get(this.getClass().getClassLoader().getResource(TEMPLATE_CREER_CLIENT).toURI()))
						.stream().collect(Collectors.joining());

				PrintWriter writter = resp.getWriter();
				writter.write(template.replace("<input type =\"hidden\">", "<p>Tous les champs sont requis</p>"));
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		else {
			int age = Integer.parseInt(ageString);

			LOGGER.info("Paramètre nom reçu " + nom);
			LOGGER.info("Paramètre prénom reçu " + prenom);
			LOGGER.info("Paramètre ville reçu " + ville);
			LOGGER.info("Paramètre age reçu " + age);

			// TODO insérer un nouveau client en base de données
			int id = incr.incrementAndGet();
			Client client = new Client(id, nom, prenom, ville, age);
			listeClients.add(client);

			// réponse au format UTF-8 pour le support des accents
			resp.setCharacterEncoding("UTF-8");

			// récupération du contenu du fichier template

			resp.sendRedirect("/liste_clients");

		}

	}

	/**
	 * Getter
	 * 
	 * @return the listeClients
	 */
	public static List<Client> getListeClients() {
		return listeClients;
	}

	/**
	 * Setter
	 * 
	 * @param listeClients the listeClients to set
	 */
	public static void setListeClients(List<Client> listeClients) {
		ClientController.listeClients = listeClients;
	}

}
