/**
 * 
 */
package dev.pizzeria.controller.pizzas;

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

import dev.pizzeria.controller.clients.ClientController;
import dev.pizzeria.model.Pizza;

/**
 * @author Eloi
 * 
 * 
 *
 */
public class PizzaController extends HttpServlet {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClientController.class);

	public List<Pizza> listPizzas = new ArrayList<>();

	public StringBuilder afficherNouvellePizza(int id) {

		StringBuilder myStringBuilderPizza = new StringBuilder();

		int monIndex = 0;

		for (int i = 0; i < listPizzas.size(); i++) {
			if (listPizzas.get(i).getId() == id) {
				monIndex = i;
			}
		}

		Pizza pizza = listPizzas.get(monIndex);
		String monLibelle = pizza.getLibelle();
		Float monPrix = pizza.getPrix();
		String maReference = pizza.getReference();
		String maPhotoUrl = pizza.getPhotoUrl();
		int monId = pizza.getId();

		// ajout du message affichant les parametre de la pizza enregistré
		myStringBuilderPizza.append("<section><h2>Nouvelle pizza insérée :</h2>").append("<ul><li>ID : ").append(monId)
				.append("<li>LIBELLE : ").append(monLibelle).append("</li>").append("<li>REFERENCE : ")
				.append(maReference).append("</li>").append("<li>PRIX : ").append(monPrix).append("</li>")
				.append("<li>PHOTO : ").append(maPhotoUrl).append("</li></ul></section>");

		return myStringBuilderPizza;

	}

	/**
	 * Page HTML de la réponse en cas d'insertion effectuée. Fichier présent dans le répertoire src/main/resources.
	 */
	public static final String TEMPLATE_PIZZA_INSERE = "templates/pizza_infos.html";

	public StringBuilder afficherListePizzas() {

		StringBuilder myStringBuilderListe = new StringBuilder();

		myStringBuilderListe.append("<section><h2>Liste des Clients :</h2>");

		for (Pizza pizza : listPizzas) {

			String monLibelle = pizza.getLibelle();
			Float monPrix = pizza.getPrix();
			String maReference = pizza.getReference();
			String maPhotoUrl = pizza.getPhotoUrl();
			int monId = pizza.getId();

			myStringBuilderListe.append("<ul>").append("<li><span>ID : ").append(monId).append("</span>")
					.append("<span>").append(monLibelle).append("</span>").append("<span>").append(maReference)
					.append("</span>").append("<span>").append(monPrix).append("</span>").append("<span>")
					.append(maPhotoUrl).append("</span>").append("</li></ul>");
		}

		return myStringBuilderListe;

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
							Paths.get(this.getClass().getClassLoader().getResource(TEMPLATE_PIZZA_INSERE).toURI()))
					.stream().collect(Collectors.joining());

			// récupération des paramètres enregistrés par le client en fonction de son id
			if (req.getParameter("id") != null) {
				int monId = Integer.parseInt(req.getParameter("id"));

				template = template.replace("CodeNvPizza", afficherNouvellePizza(monId));
			}

			template = template.replace("CodeListePizzas", afficherListePizzas());

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
		String libelle = req.getParameter("libelle");
		String reference = req.getParameter("reference");
		String photoUrl = req.getParameter("photo");

		Float prix = Float.parseFloat(req.getParameter("prix"));

		LOGGER.info("Paramètre libelle reçu " + libelle);
		LOGGER.info("Paramètre reference reçu " + reference);
		LOGGER.info("Paramètre photo reçu " + photoUrl);
		LOGGER.info("Paramètre prix reçu " + prix);

		// création d'une nouvelle pizza
		Pizza pizza = new Pizza(libelle, prix, photoUrl, reference);

		// ajout du client dans la liste
		listPizzas.add(pizza);

		StringBuilder stringBuilderUrl = new StringBuilder();
		stringBuilderUrl.append("/pizzas?id=").append(pizza.getId());

		String newURL = resp.encodeRedirectURL(stringBuilderUrl.toString());
		resp.sendRedirect(newURL);

	}

}
