/**
 * 
 */
package dev.pizzeria.model;

/**
 * Représente un client de la base de données
 */
public class Client {

	/** int compteur : pour définir l'id de chaque client */
	private static int compteur = 1;
	/** int id : id unique */
	private int id;
	/** String nom : nom du client */
	private String nom;
	/** String prenom : prénom du client */
	private String prenom;
	/** String ville : ville du client */
	private String ville;
	/** int age : âge du client */
	private int age;

	/**
	 * Constructor
	 * 
	 * @param nom
	 *            : nom du client
	 * @param prenom
	 *            : prénom du client
	 * @param ville
	 *            : ville d'habitation du client
	 * @param age
	 *            : âge du client
	 */
	public Client(String nom, String prenom, String ville, int age) {
		this.nom = nom;
		this.prenom = prenom;
		this.ville = ville;
		this.age = age;
		this.id += Client.compteur++;

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
	 * @return the ville
	 */
	public String getVille() {
		return ville;
	}

	/**
	 * Setter
	 * 
	 * @param ville
	 *            the ville to set
	 */
	public void setVille(String ville) {
		this.ville = ville;
	}

	/**
	 * Getter
	 * 
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * Setter
	 * 
	 * @param age
	 *            the age to set
	 */
	public void setAge(int age) {
		this.age = age;
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
		Client.compteur = compteur;
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
