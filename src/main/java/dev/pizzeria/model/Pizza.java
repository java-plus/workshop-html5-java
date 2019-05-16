package dev.pizzeria.model;

/**
 * Représente une pizza de la pizzeria
 */
public class Pizza {

	/** int compteur : compteur servant à définir les id */
	private static int compteur = 1;
	/** int id : id unique */
	private int id;
	/** String libelle : nom de la pizza */
	private String libelle;
	/** String reference : référence de la pizza */
	private String reference;
	/** double prix : prix unitaire en € de la pizza */
	private double prix;
	/** String photo : url de la photo de la pizza */
	private String photo;

	/**
	 * Constructor
	 * 
	 * @param libelle
	 *            : nom de la pizza
	 * @param reference
	 *            : référence de la pizza
	 * @param prix
	 *            : prix en € de la pizza
	 * @param photo
	 *            : url de la photo de la pizza
	 */
	public Pizza(String libelle, String reference, double prix, String photo) {
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
	public double getPrix() {
		return prix;
	}

	/**
	 * Setter
	 * 
	 * @param prix
	 *            the prix to set
	 */
	public void setPrix(double prix) {
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

}
