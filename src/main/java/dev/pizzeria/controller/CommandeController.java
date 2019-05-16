package dev.pizzeria.controller;

import java.io.IOException;
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

import dev.pizzeria.model.Commande;

public class CommandeController extends HttpServlet {

	/** serialVersionUID : long */
	private static final long serialVersionUID = -6537394433685526239L;

	private List<Commande> listeCommandes = new ArrayList<Commande>();

	public static final String TEMPLATE_CREER_COMMANDE = "templates/crer_commande.html";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		try {
			String template = Files
					.readAllLines(
							Paths.get(this.getClass().getClassLoader().getResource(TEMPLATE_CREER_COMMANDE).toURI()))
					.stream().collect(Collectors.joining());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
