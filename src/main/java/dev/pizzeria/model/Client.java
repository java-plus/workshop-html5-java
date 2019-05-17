package dev.pizzeria.model;

public class Client {

	private String nom;
	private String prenom;
	private String ville;
	private String age;

	public Client(String nom, String prenom, String ville, String age) {
		super();
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
	public String getAge() {
		return age;
	}

	/**
	 * Setter
	 * 
	 * @param age
	 *            the age to set
	 */
	public void setAge(String age) {
		this.age = age;
	}

}
