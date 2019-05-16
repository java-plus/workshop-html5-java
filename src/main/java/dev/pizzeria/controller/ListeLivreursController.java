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

import dev.pizzeria.model.Livreur;

public class ListeLivreursController extends HttpServlet {

	/** serialVersionUID : long */
	private static final long serialVersionUID = 750344744768127464L;

	List<Livreur> listeLivreurs = LivreurController.getListeLivreurs();

	public static final String TEMPLATE_LISTE_LIVREURS = "templates/liste_livreurs.html";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setCharacterEncoding("UTF-8");
		try {
			String template = Files
					.readAllLines(
							Paths.get(this.getClass().getClassLoader().getResource(TEMPLATE_LISTE_LIVREURS).toURI()))
					.stream().collect(Collectors.joining());

			StringBuilder str = new StringBuilder();

			for (int i = 0; i < listeLivreurs.size(); i++) {
				str.append(("<tr><td>" + listeLivreurs.get(i).getId() + "</td><td>" + listeLivreurs.get(i).getNom()
						+ "</td><td>" + listeLivreurs.get(i).getPrenom() + "</td></tr>").toString());
			}

			String remplacement = str.toString();
			PrintWriter writter = resp.getWriter();
			writter.write(template.replace("ListeDeslivreurs", remplacement));
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
