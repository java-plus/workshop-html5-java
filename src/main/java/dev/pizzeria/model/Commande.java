package dev.pizzeria.model;

public class Commande {

	private String numero;
	private String date;
	private Livreur livreur;
	private Client client;

	/**
	 * Constructeur
	 * 
	 * @param numero
	 * @param date
	 * @param livreur
	 * @param client
	 */
	public Commande(String numero, String date, Livreur livreur, Client client) {
		super();
		this.numero = numero;
		this.date = date;
		this.livreur = livreur;
		this.client = client;
	}

	/**
	 * Getter
	 * 
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * Setter
	 * 
	 * @param numero
	 *            the numero to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
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

}
