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

import dev.pizzeria.dao.PizzaDao;
import dev.pizzeria.model.Pizza;

public class PizzaController extends HttpServlet {

	/** serialVersionUID : long */
	private static final long serialVersionUID = -5979757111982430295L;

	private static final Logger LOGGER = LoggerFactory.getLogger(PizzaController.class);

	public static final String TEMPLATE_AJOUTER_PIZZA = "templates/ajouter_pizza.html";

	private PizzaDao dao = new PizzaDao();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setCharacterEncoding("UTF-8");
		String template;

		try {
			template = Files
					.readAllLines(
							Paths.get(this.getClass().getClassLoader().getResource(TEMPLATE_AJOUTER_PIZZA).toURI()))
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
		resp.setCharacterEncoding("UTF-8");

		String libelle = req.getParameter("libelle");
		String reference = req.getParameter("reference");
		String prixString = req.getParameter("prix");
		String photo = req.getParameter("photo");

		LOGGER.info("libelle reçu :" + libelle);
		LOGGER.info("reference reçu :" + reference);
		LOGGER.info("prix reçu :" + prixString);
		LOGGER.info("photo reçue :" + photo);

		if (libelle.isEmpty() || reference.isEmpty() || prixString.isEmpty() || photo.isEmpty()) {

//			resp.sendRedirect("/ajouter_pizza");

			try {
				String template = Files
						.readAllLines(
								Paths.get(this.getClass().getClassLoader().getResource(TEMPLATE_AJOUTER_PIZZA).toURI()))
						.stream().collect(Collectors.joining());
				PrintWriter writter = resp.getWriter();
				writter.write(template.replaceAll("<input type =\"hidden\">", "<p>Tous les champs sont requis</p>"));
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		else {
			float prix = Float.parseFloat(prixString);
			Pizza pizza = new Pizza(libelle, reference, prix, photo);
			dao.saveNewPizza(pizza);

			resp.sendRedirect("/liste_pizzas");

		}
	}

}
