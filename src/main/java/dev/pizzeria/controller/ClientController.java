package dev.pizzeria.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.pizzeria.Client;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Contrôleur responsable du traitement de la réquête : POST /clients.
 */
public class ClientController extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientController.class);
    List<Client> arrayCli = new ArrayList<Client>();
   

    /**
     * Page HTML de la réponse en cas d'insertion effectuée.
     * Fichier présent dans le répertoire src/main/resources.
     */
    public static final String TEMPLATE_CLIENT_INSERE = "templates/client_insere.html";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
        // récupération du paramètre nom
        // <input name="nom">
        String nom = req.getParameter("nom");
        LOGGER.info("Paramètre nom reçu " + nom);
        
     // récupération du paramètre prenom
        String prenom = req.getParameter("prenom");
        LOGGER.info("Paramètre prenom reçu " + prenom);
        
     // récupération du paramètre ville
        String ville = req.getParameter("ville");
        LOGGER.info("Paramètre ville reçu " + ville);
        
     // récupération du paramètre age
        String ageStr = req.getParameter("age");
        LOGGER.info("Paramètre age reçu " + ageStr);
        int age = Integer.parseInt(ageStr);
        
        //Creation d'un client
        Client cl = new Client(nom, prenom, ville, age);
        arrayCli.add(cl);
        
        for(Client leCli : arrayCli) {
        	System.out.println(leCli);
        }
        
       

        // TODO insérer un nouveau client en base de données


        try {
            // réponse au format UTF-8 pour le support des accents
            resp.setCharacterEncoding("UTF-8");

            // récupération du contenu du fichier template
            String template = Files.readAllLines(Paths.get(this.getClass().getClassLoader().getResource(TEMPLATE_CLIENT_INSERE).toURI())).stream().collect(Collectors.joining());

            // écriture dans le corps de la réponse
            PrintWriter writer = resp.getWriter();
            writer.write(template);

        } catch (URISyntaxException e) {
           LOGGER.error("Fichier HTML non trouvé", e);
        }
    }

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter writer = resp.getWriter();
		String page = "";
		page += "<!doctype html>";
		page += "<html lang=\"fr\">";
		page += "<head>";
		page += "<meta charset=\"UTF-8\">";
		page += "<title>Titre liste des clients</title>";
		page += "</head>";
		page += "<body>";
		page += "<h1>Gestion des clients</h1>";
		page += "<h2>Liste des clients</h2>";
		page += "<table>";
		page += "<tr>";
		page += "<td><Strong>" + "ID      Nom     Prenom     Ville      Age" + "</Strong></td>";
		for (Client client : arrayCli) {
			page += "<td>" + client.getNom() + "</td>";
			page += "<td>" + client.getPrenom() + "</td>";
			page += "<td>" + client.getVille() + "</td>";
			page += "<td>" + client.getAge() + "</td>";
			page += "</tr>";
		}
		page += "<table>";
		page += "</body>";
		page += "</html>";
		writer.write(page);
	}
}
