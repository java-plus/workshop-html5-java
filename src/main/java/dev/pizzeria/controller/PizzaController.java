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

/**
 * Contrôleur responsable du traitement de la réquête : POST /clients.
 */
public class PizzaController extends HttpServlet {

	/** serialVersionUID : long */
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = LoggerFactory.getLogger(PizzaController.class);

	/**
	 * Page HTML de la réponse en cas d'insertion effectuée. Fichier présent
	 * dans le répertoire src/main/resources.
	 */
	public static final String TEMPLATE_PIZZA_INSERE = "templates/pizza_insere.html";

	List<Pizza> listeDePizzas = new ArrayList<Pizza>();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// récupération du paramètre nom
		// <input name="nom">
		String libelle = req.getParameter("libelle");
		String reference = req.getParameter("reference");
		String prix = req.getParameter("prix");
		String photo = req.getParameter("photo");

		Pizza pizza = new Pizza(libelle, reference, prix, photo);
		listeDePizzas.add(pizza);

		// TODO insérer un nouveau client en base de données

		for (int i = 0; i < listeDePizzas.size(); i++) {
			System.out.println(listeDePizzas.get(i).getLibelle() + " " + listeDePizzas.get(i).getReference() + " "
					+ listeDePizzas.get(i).getPrix() + " " + listeDePizzas.get(i).getPhoto());
		}

		try {
			// réponse au format UTF-8 pour le support des accents
			resp.setCharacterEncoding("UTF-8");

			if (libelle.equals("") || reference.equals("") || prix.equals("") || photo.equals("")
					|| Controle.nestUnEntier(prix)) {
				String templateAjout = Files
						.readAllLines(
								Paths.get(this.getClass().getClassLoader().getResource(TEMPLATE_PIZZA_INSERE).toURI()))
						.stream().collect(Collectors.joining());
				resp.setHeader("Content-Type", "text/html");
				String corpsReponseHTML = templateAjout.replaceAll("AREMPLACER",
						"Tous les champs doivent être renseignés et le prix doit être un chiffre");
				PrintWriter writer = resp.getWriter();
				writer.write(corpsReponseHTML);
			} else {

				// récupération du contenu du fichier template
				String template = Files
						.readAllLines(
								Paths.get(this.getClass().getClassLoader().getResource(TEMPLATE_PIZZA_INSERE).toURI()))
						.stream().collect(Collectors.joining());

				// écriture dans le corps de la réponse
				PrintWriter writer = resp.getWriter();
				writer.write(template);
				resp.sendRedirect("http://localhost:8080/pizzas");
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

		resp.setHeader("Content-Type", "text/html");
		// écriture dans le corps de la réponse
		PrintWriter writer = resp.getWriter();

		try {
			String template = Files
					.readAllLines(Paths
							.get(this.getClass().getClassLoader().getResource("templates/AfficherPizza.html").toURI()))
					.stream().collect(Collectors.joining());

			writer.write(listeDePizzas.toString());

			String libelle = "";
			String reference = "";
			String prix = "";
			String photo = "";
			int id;

			StringBuilder sb = new StringBuilder();

			for (int i = 0; i < listeDePizzas.size(); i++) {
				libelle = listeDePizzas.get(i).getLibelle();
				reference = listeDePizzas.get(i).getReference();
				prix = listeDePizzas.get(i).getPrix();
				photo = listeDePizzas.get(i).getPhoto();
				id = listeDePizzas.get(i).getId();
				sb.append("<tr><td>" + id + "</td><td>" + libelle + "</td><td>" + reference + "</td><td>" + prix
						+ "</td><td><img src=" + photo
						+ " height=\"150\" width=\"300\" ></td><td><a>Modifier</a></td><td><a>Supprimer</a></td></tr>");
				// writer.write("<p>" + listeDeClients.get(i).getPrenom() +
				// "</p>");
				// stations.put("nom", nom);
				// stations.put("adresse", prenom);
			}

			String partieDynamique = sb.toString();
			writer.write(template.replace("PARTIE_VARIABLE", partieDynamique));

		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
