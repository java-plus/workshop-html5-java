package dev.pizzeria.model;

import java.util.ArrayList;
import java.util.List;

public class PizzDao {

	List<Client> listCli = new ArrayList<>();
	List<Livreur> listLiv = new ArrayList<>();

	public void add(Client client) {
		listCli.add(client);
	}

	public void add(Livreur livreur) {
		listLiv.add(livreur);
	}

	public List<Client> findCli() {
		return listCli;
	}

	public List<Livreur> findLiv() {
		return listLiv;
	}

}
