package dev.pizzeria.model;

import java.util.Date;

/**
 * Représente une commande de l'application pizzeria
 */
public class Commande {

	private static int compteur;
	private int id;
	private int numero;
	private Date date;
	private Livreur livreur;
	private Client client;

	public Commande(int numero, Date date, Livreur livreur, Client client) {
		this.client = client;
		this.numero = numero;
		this.date = date;
		this.livreur = livreur;
	}

}
