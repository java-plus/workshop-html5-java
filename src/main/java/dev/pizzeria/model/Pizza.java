package dev.pizzeria.model;

public class Pizza {
	private int id;
	private String libelle;
	private String reference;
	private Double prix;
	private String photo;
	private static int compteur = 1;

	/**
	 * Constructeur
	 * 
	 * @param libelle
	 * @param reference
	 * @param prix
	 * @param photo
	 */
	public Pizza(String libelle, String reference, Double prix, String photo) {
		this.id = compteur++;
		this.libelle = libelle;
		this.reference = reference;
		this.prix = prix;
		this.photo = photo;
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
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Getter
	 * 
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
	 * Getter
	 * 
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
	 * Getter
	 * 
	 * @return the prix
	 */
	public Double getPrix() {
		return prix;
	}

	/**
	 * Setter
	 * 
	 * @param prix
	 *            the prix to set
	 */
	public void setPrix(Double prix) {
		this.prix = prix;
	}

	/**
	 * Getter
	 * 
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
	 * Getter
	 * 
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

}
