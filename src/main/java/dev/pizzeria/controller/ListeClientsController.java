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

public class ListeClientsController extends HttpServlet {

	/** serialVersionUID : long */
	private static final long serialVersionUID = 8919819158244222740L;

	/** TEMPLATE_LISTE_CLIENTS : String */
	public static final String TEMPLATE_LISTE_CLIENTS = "templates/liste_clients.html";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		String template;

		try {
			template = Files
					.readAllLines(
							Paths.get(this.getClass().getClassLoader().getResource(TEMPLATE_LISTE_CLIENTS).toURI()))
					.stream().collect(Collectors.joining());

			StringBuilder str = new StringBuilder();
			for (int i = 0; i < ClientController.getListeClients().size(); i++) {
				str.append(("<tr><td>" + ClientController.getListeClients().get(i).getId() + "</td><td>"
						+ ClientController.getListeClients().get(i).getNom() + "</td><td>"
						+ ClientController.getListeClients().get(i).getPrenom() + "</td><td>"
						+ ClientController.getListeClients().get(i).getVille() + "</td><td>"
						+ ClientController.getListeClients().get(i).getAge()
						+ "</td><td><a href = \"\">Modifier</a><a href = \"\">Supprimer</a></td></tr>").toString());
			}
			String ajoutTemplate = str.toString();
			String resultatDuReplace = template.replace("ListeDesclients", ajoutTemplate);

			PrintWriter writter = resp.getWriter();
			writter.write(resultatDuReplace);

		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
