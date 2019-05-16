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

import dev.pizzeria.model.Livreur;

public class LivreurController extends HttpServlet {

	/** serialVersionUID : long */
	private static final long serialVersionUID = -98547143755298130L;

	public static final String TEMPLATE_AJOUTER_LIVREUR = "templates/ajouter_livreur.html";

	private AtomicInteger incr = new AtomicInteger();

	private static List<Livreur> listeLivreurs = new ArrayList<Livreur>();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		try {
			String template = Files
					.readAllLines(
							Paths.get(this.getClass().getClassLoader().getResource(TEMPLATE_AJOUTER_LIVREUR).toURI()))
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
		String nom = req.getParameter("nom");
		String prenom = req.getParameter("prenom");

		if (nom.isEmpty() || prenom.isEmpty()) {
			try {
				String template = Files
						.readAllLines(Paths
								.get(this.getClass().getClassLoader().getResource(TEMPLATE_AJOUTER_LIVREUR).toURI()))
						.stream().collect(Collectors.joining());
				PrintWriter writter = resp.getWriter();
				writter.write(template.replace("<input type =\"hidden\">", "<p>Tous les champs sont requis</p>"));
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		else {
			int id = incr.getAndIncrement();
			Livreur livreur = new Livreur(id, nom, prenom);
			listeLivreurs.add(livreur);
			resp.sendRedirect("/liste_livreurs");
		}
	}

	/**
	 * Getter
	 * 
	 * @return the listeLivreurs
	 */
	public static List<Livreur> getListeLivreurs() {
		return listeLivreurs;
	}

	/**
	 * Setter
	 * 
	 * @param listeLivreurs the listeLivreurs to set
	 */
	public static void setListeLivreurs(List<Livreur> listeLivreurs) {
		LivreurController.listeLivreurs = listeLivreurs;
	}

}
