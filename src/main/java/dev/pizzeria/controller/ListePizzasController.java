package dev.pizzeria.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dev.pizzeria.model.Pizza;

public class ListePizzasController extends HttpServlet {

	/** serialVersionUID : long */
	private static final long serialVersionUID = -5009145139792091095L;

	public static final String TEMPLATE_LISTE_PIZZAS = "templates/liste_pizzas.html";

	List<Pizza> listePizzas = PizzaController.getListePizzas();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");

		try {
			String template = Files
					.readAllLines(
							Paths.get(this.getClass().getClassLoader().getResource(TEMPLATE_LISTE_PIZZAS).toURI()))
					.stream().collect(Collectors.joining());

			StringBuilder str = new StringBuilder();

			for (int i = 0; i < listePizzas.size(); i++) {
				str.append("<tr><td>" + listePizzas.get(i).getId() + "</td><td>" + listePizzas.get(i).getLibelle()
						+ "</td><td>" + listePizzas.get(i).getReference() + "</td><td>" + listePizzas.get(i).getPrix()
						+ "</td><td><img src =\"" + listePizzas.get(i).getPhoto() + "\"/></td></tr>").toString();
			}

			String remplacement = str.toString();
			PrintWriter writter = resp.getWriter();
			writter.write(template.replace("ListeDesPizzas", remplacement));
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
