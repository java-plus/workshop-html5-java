package dev.pizzeria;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import dev.pizzeria.controller.ClientController;
import dev.pizzeria.controller.ClientsController;
import dev.pizzeria.controller.LivreurController;
import dev.pizzeria.controller.LivreursController;
import dev.pizzeria.controller.PizzaController;
import dev.pizzeria.controller.PizzasController;
import dev.pizzeria.dao.PizzasDAO;
import dev.pizzeria.model.Pizza;

/**
 * Classe principale de lancement de l'application pizzeria
 */
public class PizzeriaApp {

	public static void main(String[] args) throws Exception {

		// ajout des pizzas initiales
		PizzasDAO.pizzas.add(new Pizza("Reine", "PREI", 10, "/images/reine.jpg"));
		PizzasDAO.pizzas.add(new Pizza("Regina", "PREG", 9, "/images/regina.jpg"));
		PizzasDAO.pizzas.add(new Pizza("Napolitaine", "PNAP", 10, "/images/napolitaine.jpg"));

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
		context.addServlet(ClientController.class, "/client"); // ClientController prend la main pour les requêtes
																// /clients
		context.addServlet(ClientsController.class, "/clients");
		context.addServlet(PizzasController.class, "/pizzas");
		context.addServlet(PizzaController.class, "/pizza");
		context.addServlet(LivreurController.class, "/livreur");
		context.addServlet(LivreursController.class, "/livreurs");

		server.setHandler(context);
		server.start();
		server.join();

	}
}
