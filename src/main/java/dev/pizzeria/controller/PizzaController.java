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

import dev.pizzeria.model.Pizza;

public class PizzaController extends HttpServlet {

	/** serialVersionUID : long */
	private static final long serialVersionUID = -5979757111982430295L;

	private static final Logger LOGGER = LoggerFactory.getLogger(PizzaController.class);

	public static final String TEMPLATE_AJOUTER_PIZZA = "templates/ajouter_pizza.html";

	private static List<Pizza> listePizzas = new ArrayList<Pizza>();

	private AtomicInteger incr = new AtomicInteger();

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
			resp.sendRedirect("/ajouter_pizza");
		}

		else {
			int prix = Integer.parseInt(prixString);
			int id = incr.incrementAndGet();
			Pizza pizza = new Pizza(id, libelle, reference, prix, photo);
			listePizzas.add(pizza);

			resp.sendRedirect("/liste_pizzas");

		}
	}

	/**
	 * Getter
	 * 
	 * @return the listePizzas
	 */
	public static List<Pizza> getListePizzas() {
		return listePizzas;
	}

	/**
	 * Setter
	 * 
	 * @param listePizzas the listePizzas to set
	 */
	public static void setListePizzas(List<Pizza> listePizzas) {
		PizzaController.listePizzas = listePizzas;
	}

}
