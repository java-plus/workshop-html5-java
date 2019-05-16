package dev.pizzeria.model;

import java.util.ArrayList;
import java.util.List;

public class PizzariaDaoClient extends PizzeriaDao {

	List<Client> listeClient = new ArrayList();

	public void add(Client client) {

		listeClient.add(client);

	}

	public List<Client> findClient() {
		return listeClient;
	}

}
