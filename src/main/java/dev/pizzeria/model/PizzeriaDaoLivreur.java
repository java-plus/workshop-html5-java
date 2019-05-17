package dev.pizzeria.model;

import java.util.ArrayList;
import java.util.List;

public class PizzeriaDaoLivreur extends PizzeriaDao {

	List<Livreur> listeLivreur = new ArrayList();

	public void add(Livreur livreur) {

		listeLivreur.add(livreur);

	}

	public List<Livreur> findLivreur() {
		return listeLivreur;
	}

}
