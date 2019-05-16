/**
 * 
 */
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

import dev.pizzeria.dao.PizzasDAO;
import dev.pizzeria.exception.FormException;
import dev.pizzeria.model.Pizza;

/**
 * Controller pour gérer une pizza de la pizzeria.
 */
public class PizzaController extends HttpServlet {

	/** Logger LOGGER */
	private static final Logger LOGGER = LoggerFactory.getLogger(PizzaController.class);
	/**
	 * boolean isFromACreatingError : indique s'il y a eu une précédente erreur lors
	 * de l'envoi du formulaire
	 */
	private boolean isFromACreatingError = false;
	/**
	 * String TEMPLATE_PIZZA_CREER : addresse du template HTML de base pour générer
	 * la réponse
	 */
	public static final String TEMPLATE_PIZZA_CREER = "templates/pizza.html";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			ajouterUnePizza(req);
			resp.sendRedirect("/pizzas");
		} catch (FormException e) {
			resp.setStatus(400);
			LOGGER.info("Erreur d'insertion d'une pizza : " + e);
			isFromACreatingError = true;
			resp.sendRedirect("/pizza");
		}
	}

	/**
	 * Ajoute une pizza dans la base de données de la pizzeria (List)
	 * 
	 * @param req
	 * @throws FormException
	 */
	public void ajouterUnePizza(HttpServletRequest req) throws FormException {
		try {
			String libelle = req.getParameter("libelle");
			String reference = req.getParameter("reference");
			String photo = req.getParameter("photo");
			double prix = Double.parseDouble(req.getParameter("prix"));

			if (libelle.length() > 0 && reference.length() > 0 && photo.length() > 0 && prix > 0) {
				PizzasDAO.pizzas.add(new Pizza(libelle, reference, prix, photo));
			} else {
				throw new FormException("un des champs est vide.");
			}
		} catch (Exception f) {
			throw new FormException("erreur importante dans le formulaire (de type ou nullpointer).");
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			resp.setCharacterEncoding("UTF-8");

			String template = Files
					.readAllLines(Paths.get(this.getClass().getClassLoader().getResource(TEMPLATE_PIZZA_CREER).toURI()))
					.stream().collect(Collectors.joining());
			if (isFromACreatingError) {
				template = template.replaceAll("<msgSiErreur></msgSiErreur>",
						"<p style=\"color:orange\"><b>ERREUR : MERCI DE RECOMMENCER</b></p>");
			}

			PrintWriter writer = resp.getWriter();
			writer.write(template);
			isFromACreatingError = false;

		} catch (URISyntaxException e) {
			LOGGER.error("Fichier HTML non trouvé", e);
			resp.setStatus(404);
		}
	}

}
