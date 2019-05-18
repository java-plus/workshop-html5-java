/**
 * 
 */
package dev.pizzeria.model;

/**
 * @author Eloi
 *
 */
public class Livreur {

	private String nom;
	private String prenom;

	/**
	 * Constructor
	 * 
	 * @param nom
	 * @param prenom
	 */
	public Livreur(String nom, String prenom) {
		this.nom = nom;
		this.prenom = prenom;
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
	 * Constructor
	 * 
	 */

}
