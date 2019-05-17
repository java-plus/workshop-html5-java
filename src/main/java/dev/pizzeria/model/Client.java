package dev.pizzeria.model;

public class Client {
	private int id;
	private String nom;
	private String prenom;
	private String ville;
	private int age;
	private static int compteur = 1;

	/**
	 * Constructeur
	 * 
	 * @param nom
	 * @param prenom
	 * @param ville
	 * @param age
	 */
	public Client(String nom, String prenom, String ville, int age) {
		this.id = compteur++;
		this.nom = nom;
		this.prenom = prenom;
		this.ville = ville;
		this.age = age;
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
