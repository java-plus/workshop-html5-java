package dev.pizzeria.controller.clients;

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

import dev.pizzeria.model.Client;

/**
 * Contrôleur responsable du traitement de la réquête : POST /clients.
 */
public class ClientController extends HttpServlet {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClientController.class);

	public List<Client> listClients = new ArrayList<>();

	/**
	 * Page HTML de la réponse en cas d'insertion effectuée. Fichier présent dans le répertoire src/main/resources.
	 */
	public static final String TEMPLATE_CLIENT_INSERE = "templates/client_infos.html";

	/**
	 * méthode permettant de construire le message html qui sera affiché dans le navigateur, et qui correspond à la liste de tous les clients existant dans la liste de client
	 * 
	 * @return StringBuilder contenant le message html
	 */
	public StringBuilder afficherListeClients() {

		StringBuilder myStringBuilderListe = new StringBuilder();

		myStringBuilderListe.append("<section><div class='list'><h2>Liste des Clients :</h2>");

		for (Client client : listClients) {
			String monNom = client.getNom();
			String monPrenom = client.getPrenom();
			String maVille = client.getVille();
			int monAge = client.getAge();
			int monId = client.getId();

			myStringBuilderListe.append("<ul><li><span>").append(monId).append("</span>").append("<span>")
					.append(monNom).append("</span>").append("<span>").append(monPrenom).append("</span>")
					.append("<span>").append(maVille).append("</span>").append("<span>").append(monAge)
					.append("</span>").append("</li></ul>");
		}
		myStringBuilderListe.append("</div></section>");
		return myStringBuilderListe;
	}

	/**
	 * 
	 * méthode permmettant de construire le message html contenant l'intégralité des paramètres du client correspondant à l'id indiqué
	 * 
	 * @param id
	 *            l'identifiant unique du client
	 * @return StringBuilder
	 */
	public StringBuilder afficherNouveauClient(int id) {

		StringBuilder myStringBuilderClient = new StringBuilder();

		int monIndex = 0;

		for (int i = 0; i < listClients.size(); i++) {
			if (listClients.get(i).getId() == id) {
				monIndex = i;
			}
		}

		Client client = listClients.get(monIndex);
		String monNom = client.getNom();
		String monPrenom = client.getPrenom();
		String maVille = client.getVille();
		int monAge = client.getAge();
		int monId = client.getId();

		// ajout du message affichant les parametre du nouveau client enregistré
		myStringBuilderClient.append("<section><div class='list'><h2>Nouveau client inséré :</h2>")
				.append("<ul><li>ID : ").append(monId).append("<li>NOM : ").append(monNom).append("</li>")
				.append("<li>PRENOM : ").append(monPrenom).append("</li>").append("<li>VILLE : ").append(maVille)
				.append("</li>").append("<li>AGE : ").append(monAge).append("</li></ul></div></section>");

		return myStringBuilderClient;

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {

			// on indique le code de statut de réponse
			resp.setStatus(200);

			// réponse au format UTF-8 pour le support des accents
			resp.setCharacterEncoding("UTF-8");

			// récupération du contenu du fichier template
			String template = Files
					.readAllLines(
							Paths.get(this.getClass().getClassLoader().getResource(TEMPLATE_CLIENT_INSERE).toURI()))
					.stream().collect(Collectors.joining());

			// récupération des paramètres enregistrés par le client en fonction de son id
			if (req.getParameter("id") != null) {
				int monId = Integer.parseInt(req.getParameter("id"));

				template = template.replace("CodeNvClients", afficherNouveauClient(monId));
			} else {
				template = template.replace("CodeNvClients", "");
			}

			template = template.replace("CodeListeClients", afficherListeClients());

			// écriture dans le corps de la réponse
			PrintWriter writer = resp.getWriter();
			writer.write(template);

		} catch (URISyntaxException e) {
			LOGGER.error("Fichier HTML non trouvé", e);
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// TODO : gerer les erreurs et récupérer ce qui a deja ete entre dans les input afin de ne pas renvoyer une page avec des champs vides

		// récupération des paramètres enregistrés par le client
		String nom = req.getParameter("nom");
		String prenom = req.getParameter("prenom");
		String ville = req.getParameter("ville");
		int age = Integer.parseInt(req.getParameter("age"));

		LOGGER.info("Paramètre nom reçu " + nom);
		LOGGER.info("Paramètre prenom reçu " + prenom);
		LOGGER.info("Paramètre ville reçu " + ville);
		LOGGER.info("Paramètre age reçu " + age);

		// création d'un nouveau client
		Client client = new Client(nom, prenom, ville, age);

		// ajout du client dans la liste
		listClients.add(client);

		StringBuilder stringBuilderUrl = new StringBuilder();
		stringBuilderUrl.append("/clients?id=").append(client.getId());

		String newURL = resp.encodeRedirectURL(stringBuilderUrl.toString());
		resp.sendRedirect(newURL);

	}

}
