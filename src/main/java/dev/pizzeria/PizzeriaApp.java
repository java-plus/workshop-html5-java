package dev.pizzeria;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import dev.pizzeria.controller.ClientController;
import dev.pizzeria.controller.GestionClientController;
import dev.pizzeria.controller.GestionLivreurController;
import dev.pizzeria.controller.GestionPizzaController;
import dev.pizzeria.controller.LivreurController;
import dev.pizzeria.controller.PizzaController;

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
		context.addServlet(holderPwd, "/*");

		// Les contrôleurs de l'application

		// ClientController prend la main pour les requêtes /clients
		context.addServlet(ClientController.class, "/clients");
		// GestionClientsController prend la main pour les requêtes de Gestion
		// des clients
		context.addServlet(GestionClientController.class, "/gestionclients");

		// PizzaController prend la main pour les requêtes /clients
		context.addServlet(PizzaController.class, "/pizzas");
		// GestionPizzasController prend la main pour les requêtes de Gestion
		// des pizzas
		context.addServlet(GestionPizzaController.class, "/gestionpizzas");

		// GestionLivreursController prend la main pour les requêtes de Gestion
		// des livreurs
		context.addServlet(GestionLivreurController.class, "/gestionlivreurs");
		// LivreurController prend la main pour les requêtes /clients
		context.addServlet(LivreurController.class, "/livreurs");

		server.setHandler(context);
		server.start();
		server.join();
	}
}
