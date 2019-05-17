package dev.pizzeria;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import dev.pizzeria.controller.ClientController;

public class PizzeriaApp {

	public static void main(String[] args) throws Exception {

		Server server = new Server();

		ServerConnector connector = new ServerConnector(server);
		connector.setPort(8080);
		server.setConnectors(new Connector[] { connector });
		ServletContextHandler context = new ServletContextHandler();
		context.setContextPath("/");

		DefaultServlet defaultServlet = new DefaultServlet();
		ServletHolder holderPwd = new ServletHolder("default", defaultServlet);
		holderPwd.setInitParameter("resourceBase", "./src/main/webapp/static");
		context.addServlet(holderPwd, "/*"); // a demarer en 1er

		// Les contrôleurs de l'application

		// context.addServlet(ClientController.class, "/accueil");
		// context.addServlet(AccueilController.class, "/accueil");

		// ClientController prend la main pour les requêtes /clients
		context.addServlet(ClientController.class, "/clients");

		// ClientController prend la main pour les requêtes /commandes
		// context.addServlet(CommandesController.class, "/commandes");

		// ClientController prend la main pour les requêtes /pizzas
		// context.addServlet(PizzasController.class, "/pizzas");

		// ClientController prend la main pour les requêtes /contact
		// context.addServlet(ContactController.class, "/contact");
		server.setHandler(context);
		server.start();
		server.join();
	}
}
