package dev.pizzeria.model;

import java.util.Date;

/**
 * @author Guillaume classe modèle qui génère les commandes
 *
 */
public class Commande {

	/** id : int */
	private int id;
	/** date : Date */
	private Date date;
	/** livreur : Livreur */
	private Livreur livreur;
	/** client : Client */
	private Client client;

	/**
	 * Constructor
	 * 
	 * @param id      id de la commande
	 * @param date    date de la commande
	 * @param livreur livreur qui se charge de la commande
	 * @param client  client a qui est destinée la commande
	 */
	public Commande(int id, Date date, Livreur livreur, Client client) {
		super();
		this.id = id;
		this.date = date;
		this.livreur = livreur;
		this.client = client;
	}

	@Override
	public String toString() {
		return "Commande [id=" + id + ", date=" + date + ", livreur=" + livreur + ", client=" + client + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((client == null) ? 0 : client.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + id;
		result = prime * result + ((livreur == null) ? 0 : livreur.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Commande other = (Commande) obj;
		if (client == null) {
			if (other.client != null)
				return false;
		} else if (!client.equals(other.client))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id != other.id)
			return false;
		if (livreur == null) {
			if (other.livreur != null)
				return false;
		} else if (!livreur.equals(other.livreur))
			return false;
		return true;
	}

	/**
	 * Getter
	 * 
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Setter
	 * 
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
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
	 * @param date the date to set
	 */
	public void setDate(Date date) {
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
	 * @param livreur the livreur to set
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
	 * @param client the client to set
	 */
	public void setClient(Client client) {
		this.client = client;
	}

}
