package dev.pizzeria.model;

public class Livreur {
	private int id;
	private String nom;
	private String prenom;
	private static int compteur = 1;

	/**
	 * Constructeur
	 * 
	 * @param nom
	 * @param prenom
	 */
	public Livreur(String nom, String prenom) {
		this.id = compteur++;
		this.nom = nom;
		this.prenom = prenom;
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
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Setter
	 * 
	 * @param nom
	 *            the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Getter
	 * 
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * Setter
	 * 
	 * @param prenom
	 *            the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
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
		Livreur.compteur = compteur;
	}

}
