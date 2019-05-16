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

	public Livreur findByCodeLivreur(String code) {
		Livreur livreurTrouve = null;

		for (Livreur liste : listeLivreur) {
			if (liste.getNom().equals(code)) {

				livreurTrouve = liste;
			}
		}
		return livreurTrouve;
	}
}
