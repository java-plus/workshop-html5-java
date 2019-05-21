/**
 * 
 */
package dev.pizzeria.controller.pizzas;

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

import dev.pizzeria.affichage.AffichagePizza;
import dev.pizzeria.controller.clients.ClientController;
import dev.pizzeria.dao.PizzaDao;
import dev.pizzeria.model.Pizza;

/**
 * @author Eloi
 * 
 *         Class controleur de
 *
 */
public class PizzaController extends HttpServlet {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClientController.class);

	/** TEMPLATE_PIZZA_INSERE : String Page HTML de la réponse en cas d'insertion effectuée. Fichier présent dans le répertoire src/main/resources. */
	private static final String TEMPLATE_PIZZA_INSERE = "templates/pizza_infos.html";

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
			// uuid généré dans la class pizza automatiquement
			String monUuid;

			// récupération des paramètres enregistrés par le client en fonction de son id
			if (req.getParameter("uuid") != null) {
				monUuid = req.getParameter("id");
				template = template.replace("CodeNvPizza", AffichagePizza.afficherNouvellePizza(monUuid));
			}

			template = template.replace("CodeListePizzas", AffichagePizza.afficherListePizzas());

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

		PizzaDao dao = new PizzaDao();
		dao.addPizza(pizza);

		StringBuilder stringBuilderUrl = new StringBuilder();
		stringBuilderUrl.append("/pizzas?uuid=").append(pizza.getUuid());

		String newURL = resp.encodeRedirectURL(stringBuilderUrl.toString());
		resp.sendRedirect(newURL);

	}

}
