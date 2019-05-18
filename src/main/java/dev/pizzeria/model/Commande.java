package dev.pizzeria.model;

public class Commande {

	private String numero;
	private String date;
	
	/**
	 * Constructeur
	 * 
	 * @param numero
	 * @param date
	 * @param livreur
	 * @param client
	 */
	public Commande(String numero, String date) {
		super();
		this.numero = numero;
		this.date = date;
	
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



}
