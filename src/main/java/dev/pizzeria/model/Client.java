/**
 * 
 */
package dev.pizzeria.model;

/**
 * @author Eloi
 *
 *         Class représentant un Client de la pizzeria
 *
 */
public class Client {

	private String nom;
	private String prenom;
	private String ville;
	private int age;
	private int id;
	private static int compteur = 0;

	/**
	 * Constructor
	 * 
	 * @param nom
	 * @param prénom
	 * @param ville
	 * @param age
	 */
	public Client(String nom, String prenom, String ville, int age) {
		this.nom = nom;
		this.prenom = prenom;
		this.ville = ville;
		this.age = age;
		compteur++;
		this.id = compteur;
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
	 * @return the prénom
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * Setter
	 *
	 * @param prénom
	 *            the prénom to set
	 */
	public void setPrenom(String prénom) {
		this.prenom = prénom;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + id;
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((prenom == null) ? 0 : prenom.hashCode());
		result = prime * result + ((ville == null) ? 0 : ville.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		if (age != other.age)
			return false;
		if (id != other.id)
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (prenom == null) {
			if (other.prenom != null)
				return false;
		} else if (!prenom.equals(other.prenom))
			return false;
		if (ville == null) {
			if (other.ville != null)
				return false;
		} else if (!ville.equals(other.ville))
			return false;
		return true;
	}

}
