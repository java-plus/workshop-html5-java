package dev.pizzeria.controller;

public class Pizza {
	String libelle;
	String reference;
	String prix;
	String photo;
	static int compteur = 0;
	int id;

	Pizza(String libelle, String reference, String prix, String photo) {
		this.libelle = libelle;
		this.reference = reference;
		this.prix = prix;
		this.photo = photo;
		this.id = compteur + 1;
		compteur++;

	}

	/**
	 * @return the libelle
	 */
	public String getLibelle() {
		return libelle;
	}

	/**
	 * Setter
	 * 
	 * @param libelle
	 *            the libelle to set
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	/**
	 * @return the reference
	 */
	public String getReference() {
		return reference;
	}

	/**
	 * Setter
	 * 
	 * @param reference
	 *            the reference to set
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}

	/**
	 * @return the prix
	 */
	public String getPrix() {
		return prix;
	}

	/**
	 * Setter
	 * 
	 * @param prix
	 *            the prix to set
	 */
	public void setPrix(String prix) {
		this.prix = prix;
	}

	/**
	 * @return the photo
	 */
	public String getPhoto() {
		return photo;
	}

	/**
	 * Setter
	 * 
	 * @param photo
	 *            the photo to set
	 */
	public void setPhoto(String photo) {
		this.photo = photo;
	}

	/**
	 * @return the compteur
	 */
	public static int getCompteur() {
		return compteur;
	}

	/**
	 * Setter
	 * 
	 * @param compteur
	 *            the compteur to set
	 */
	public static void setCompteur(int compteur) {
		Pizza.compteur = compteur;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Setter
	 * 
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
}
