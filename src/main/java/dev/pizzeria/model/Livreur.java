package dev.pizzeria.model;

/**
 * Représente un livreur de la pizzeria.
 */
public class Livreur {

	/** int compteur : compteur pour définir l'id de chaque livreur */
	private static int compteur = 1;
	/** int id : id unique */
	private int id;
	/** String nom : nom du livreur */
	private String nom;
	/** String prenom : prénom du livreur */
	private String prenom;

	/**
	 * Constructor
	 * 
	 * @param nom
	 *            : nom du livreur
	 * @param prenom
	 *            : prénom du livreur
	 * @param id
	 *            : id unique du livreur
	 */
	public Livreur(String nom, String prenom) {
		this.nom = nom;
		this.prenom = prenom;
		this.id = compteur++;
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

}
