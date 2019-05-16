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

public class GestionClientController extends HttpServlet {

	public static final String TEMPLATE_GESTION_CLIENTS = "templates/templ_gestion_clients.html";

	private static final Logger LOGGER = LoggerFactory.getLogger(GestionClientController.class);

	public GestionClientController() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			// réponse au format UTF-8 pour le support des accents
			resp.setCharacterEncoding("UTF-8");

			// récupération du contenu du fichier template
			String template = Files
					.readAllLines(
							Paths.get(this.getClass().getClassLoader().getResource(TEMPLATE_GESTION_CLIENTS).toURI()))
					.stream().collect(Collectors.joining());

			// Remplacement de texte de template
			String texteHTMLDeRemplacement = "";
			for (int i = 0; i < ClientController.listeClients.size(); i++) {
				texteHTMLDeRemplacement += "<tr><td>" + ClientController.listeClients.get(i).getId() + "</td><td>"
						+ ClientController.listeClients.get(i).getNom() + "</td><td>"
						+ ClientController.listeClients.get(i).getPrenom() + "</td><td>"
						+ ClientController.listeClients.get(i).getVille() + "</td><td>"
						+ ClientController.listeClients.get(i).getAge() + "</td></tr>";
			}
			template = template.replace("ListeClientsARemplacer", texteHTMLDeRemplacement);

			// écriture dans le corps de la réponse
			PrintWriter writer = resp.getWriter();
			writer.write(template);

		} catch (URISyntaxException e) {
			LOGGER.error("Fichier HTML non trouvé", e);
		}

	}

}
