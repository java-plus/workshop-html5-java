/**
 * 
 */
package dev.pizzeria;

/**
 * @author birdman
 *
 */
public class Client {
	private String nom = "nomVide";
	private String prenom = "prenomVide";
	private String ville = "villeVide";
	private int age = 0;
	
	public Client(){
		System.out.println("Constructeur par défaut utilisé");
	}
	
	public Client(String leNom, String lePrenom, String laVille, int lAge){
		this.nom = leNom;
		this.prenom = lePrenom;
		this.ville = laVille;
		this.age = lAge;
	}
	
	@Override
	public String toString() {
		return "CLIENT\nNom: " + this.nom + "\nPrenom: " + this.prenom + "\nVille: " + this.ville + "\nAge: " + this.age;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
