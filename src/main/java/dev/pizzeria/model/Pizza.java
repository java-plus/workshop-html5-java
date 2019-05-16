/**
 * 
 */
package dev.pizzeria.model;

/**
 * @author Eloi
 *
 */
public class Pizza {

	private String libelle;
	private Integer prix;
	private Integer reference;
	private String photoUrl;
	private int id;
	private static int compteur = 0;

	/**
	 * Constructor
	 * 
	 * @param libelle
	 * @param id
	 * @param prix
	 * @param photoUrl
	 */

	public Pizza(String libelle, Integer id, Integer prix, String photoUrl) {
		this.libelle = libelle;
		this.prix = prix;
		this.reference = reference;
		this.photoUrl = photoUrl;
		compteur++;
		this.id = compteur;
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
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Getter
	 * 
	 * @return the prix
	 */
	public Integer getPrix() {
		return prix;
	}

	/**
	 * Setter
	 *
	 * @param prix
	 *            the prix to set
	 */
	public void setPrix(Integer prix) {
		this.prix = prix;
	}

	/**
	 * Getter
	 * 
	 * @return the photoUrl
	 */
	public String getPhotoUrl() {
		return photoUrl;
	}

	/**
	 * Setter
	 *
	 * @param photoUrl
	 *            the photoUrl to set
	 */
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	/**
	 * Getter
	 * 
	 * @return the reference
	 */
	public Integer getReference() {
		return reference;
	}

	/**
	 * Setter
	 *
	 * @param reference
	 *            the reference to set
	 */
	public void setReference(Integer reference) {
		this.reference = reference;
	}

}
