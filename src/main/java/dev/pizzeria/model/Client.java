/**
 * 
 */
package dev.pizzeria.model;

/**
 * Un client de la base de données
 */
public class Client {

	private String nom;
	private String prenom;
	private String ville;
	private int age;

	/**
	 * Constructor
	 * 
	 */
	public Client(String nom, String prenom, String ville, int age) {
		this.nom = nom;
		this.prenom = prenom;
		this.ville = ville;
		this.age = age;
	}

}
