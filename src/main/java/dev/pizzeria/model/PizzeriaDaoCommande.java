package dev.pizzeria.model;

import java.util.ArrayList;
import java.util.List;

public class PizzeriaDaoCommande extends PizzeriaDao {

	List<Commande> listeCommande = new ArrayList();

	public void add(Commande commande) {

		listeCommande.add(commande);

	}

	public List<Commande> findCommande() {
		return listeCommande;
	}

}
