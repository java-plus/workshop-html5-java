package dev.pizzeria;

public class Client {

	private String nom = "leGrosNull";
	private String prenom = "lePrenomGrosNull";
	private String ville = "GrosNullCity";
	private int age = 0;
	private static int id = 0;

	public Client() {

	}

	public Client(String leNom, String lePrenom, String laVille, int lage) {
		this.nom = leNom;
		this.prenom = lePrenom;
		this.ville = laVille;
		this.age = lage;
		this.id = this.id + 1;
	}

	@Override
	public String toString() {
		return "CLIENT\nNom: " + nom + "\nPrenom: " + prenom + "\nVille: " + ville + "\nAge: " + age;
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

	public void setPrenom(String prennom) {
		this.prenom = prennom;
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

	public static int getId() {
		return id;
	}

}
