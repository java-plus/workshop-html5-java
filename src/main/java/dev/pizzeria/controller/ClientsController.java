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

import dev.pizzeria.dao.ClientsDAO;
import dev.pizzeria.model.Client;

/**
 * Gère la page de gestion de tous les clients de la pizzeria
 */
public class ClientsController extends HttpServlet {

	/** Logger LOGGER */
	private static final Logger LOGGER = LoggerFactory.getLogger(ClientsController.class);
	/**
	 * String TEMPLATE_CLIENTS : adresse du template HTML de base pour la réponse
	 */
	public static final String TEMPLATE_CLIENTS = "templates/clients.html";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			resp.setCharacterEncoding("UTF-8");

			String template = Files
					.readAllLines(Paths.get(this.getClass().getClassLoader().getResource(TEMPLATE_CLIENTS).toURI()))
					.stream().collect(Collectors.joining());

			String lignesClients = "";
			for (Client client : ClientsDAO.clients) {
				lignesClients += " <tr>\r\n" + "              <td>" + client.getId() + "</td>\r\n"
						+ "              <td>" + client.getNom() + "</td>\r\n" + "              <td>"
						+ client.getPrenom() + "</td>\r\n" + "              <td>" + client.getVille() + "</td>\r\n"
						+ "              <td>" + client.getAge() + "</td>\r\n"
						+ "              <td><a href=\"#\">Modifier</a></td>\r\n"
						+ "              <td><a href=\"#\">Supprimer</a></td>\r\n" + "            </tr>";
			}

			String listeDesClients = template.replaceAll("LIGNES_DES_CLIENTS_ICI", lignesClients);

			// écriture dans le corps de la réponse
			PrintWriter writer = resp.getWriter();
			writer.write(listeDesClients);

		} catch (URISyntaxException e) {
			LOGGER.error("Fichier HTML non trouvé", e);
		}
	}

}
