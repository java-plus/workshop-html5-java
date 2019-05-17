package dev.pizzeria.model;

import java.util.Date;

public class Commande {
	private int id;
	private int numero;
	private Date date;
	private String client;
	private String livreur;
	private static int compteur = 1;

	/**
	 * Constructeur
	 * 
	 * @param id
	 * @param numero
	 * @param date
	 * @param client
	 * @param livreur
	 */
	public Commande(int numero, Date date, String client, String livreur) {
		super();
		this.id = compteur++;
		this.numero = numero;
		this.date = date;
		this.client = client;
		this.livreur = livreur;
	}

	public Commande() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Getter
	 * 
	 * @return the numero
	 */
	public int getNumero() {
		return numero;
	}

	/**
	 * Setter
	 * 
	 * @param numero
	 *            the numero to set
	 */
	public void setNumero(int numero) {
		this.numero = numero;
	}

	/**
	 * Getter
	 * 
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Setter
	 * 
	 * @param date
	 *            the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * Getter
	 * 
	 * @return the client
	 */
	public String getClient() {
		return client;
	}

	/**
	 * Setter
	 * 
	 * @param client
	 *            the client to set
	 */
	public void setClient(String client) {
		this.client = client;
	}

	/**
	 * Getter
	 * 
	 * @return the livreur
	 */
	public String getLivreur() {
		return livreur;
	}

	/**
	 * Setter
	 * 
	 * @param livreur
	 *            the livreur to set
	 */
	public void setLivreur(String livreur) {
		this.livreur = livreur;
	}

}
