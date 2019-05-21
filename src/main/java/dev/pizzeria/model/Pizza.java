/**
 * 
 */
package dev.pizzeria.model;

import java.util.UUID;

/**
 * @author Eloi
 *
 */
public class Pizza {

	private String libelle;
	private Float prix;
	private String reference;
	private String photoUrl;
	private String uuid = UUID.randomUUID().toString();

	/**
	 * Constructor
	 * 
	 * @param libelle
	 * @param id
	 * @param prix
	 * @param photoUrl
	 */

	public Pizza(String libelle, Float prix, String photoUrl, String reference) {
		this.libelle = libelle;
		this.prix = prix;
		this.reference = reference;
		this.photoUrl = photoUrl;
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
	// public Integer getId() {
	// return id;
	// }

	/**
	 * Getter
	 * 
	 * @return the prix
	 */
	public Float getPrix() {
		return prix;
	}

	/**
	 * Setter
	 *
	 * @param prix
	 *            the prix to set
	 */
	public void setPrix(Float prix) {
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
	 * @return the uuid
	 */
	public String getUuid() {
		return uuid;
	}

}
