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
	public Livreur(String nom, String prenom, int id) {
		this.nom = nom;
		this.prenom = prenom;
		this.id = compteur++;
	}

}
