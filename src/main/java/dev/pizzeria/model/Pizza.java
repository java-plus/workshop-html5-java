package dev.pizzeria.model;

/**
 * Classe qui gère les pizzas
 * 
 * @author Cécile Peyras
 *
 */
public class Pizza {

	/** id : int : incrémenté par la base de données */
	private int id;
	/** libelle : String : nom de la pizza */
	private String libelle;
	/** reference : String : référence de la pizza */
	private String reference;
	/** prix : String : prix de la pizza */
	private String prix;
	/** photo : String : lien url d'une pizza */
	private String photo;

	/**
	 * Constructeur
	 * 
	 * @param libelle
	 * @param reference
	 * @param prix
	 * @param photo
	 */
	public Pizza(int id, String libelle, String reference, String prix, String photo) {
		this(libelle, reference, prix, photo);
		this.id = id;
	}

	/**
	 * Constructeur
	 * 
	 * @param libelle
	 * @param reference
	 * @param prix
	 * @param photo
	 */
	public Pizza(String libelle, String reference, String prix, String photo) {
		this.libelle = libelle;
		this.reference = reference;
		this.prix = prix;
		this.photo = photo;
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
