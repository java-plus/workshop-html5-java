package dev.pizzeria.model;

public class Commande {

	String date;
	Client client;
	Livreur livreur;

	public Commande(String date, Client client, Livreur livreur) {
		super();
		this.date = date;
		this.client = client;
		this.livreur = livreur;
	}

	/**
	 * Getter
	 * 
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * Setter
	 * 
	 * @param date
	 *            the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * Getter
	 * 
	 * @return the client
	 */
	public Client getClient() {
		return client;
	}

	/**
	 * Setter
	 * 
	 * @param client
	 *            the client to set
	 */
	public void setClient(Client client) {
		this.client = client;
	}

	/**
	 * Getter
	 * 
	 * @return the livreur
	 */
	public Livreur getLivreur() {
		return livreur;
	}

	/**
	 * Setter
	 * 
	 * @param livreur
	 *            the livreur to set
	 */
	public void setLivreur(Livreur livreur) {
		this.livreur = livreur;
	}

}
