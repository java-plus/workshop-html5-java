/**
 * 
 */
package dev.pizzeria.affichage;

import dev.pizzeria.dao.PizzaDao;
import dev.pizzeria.model.Pizza;

/**
 * @author Eloi
 *
 */
public class AffichagePizza {

	/** myStringBuilder : StringBuilder */
	private static StringBuilder myStringBuilder = new StringBuilder();

	/**
	 * @param id
	 *            l'id de la pizza
	 * @return StringBuiler contenant l'html permettant l'affichage de la <section> nouvelle pizza
	 */
	public static StringBuilder afficherNouvellePizza(String uuid) {

		myStringBuilder = null;

		int monIndex = 0;

		// for (int i = 0; i < dao.listPizzas.size(); i++) {
		// if (dao.listPizzas.get(i).getId() == id) {
		// monIndex = i;
		// }
		// }

		Pizza pizza = PizzaDao.listPizzas.get(monIndex);
		String monLibelle = pizza.getLibelle();
		Float monPrix = pizza.getPrix();
		String maReference = pizza.getReference();
		String maPhotoUrl = pizza.getPhotoUrl();
		// int monId = pizza.getId();

		int monId = 0;

		// ajout du message affichant les parametre de la pizza enregistré
		myStringBuilder.append("<section><h2>Nouvelle pizza insérée :</h2>").append("<ul><li>ID : ").append(monId)
				.append("<li>LIBELLE : ").append(monLibelle).append("</li>").append("<li>REFERENCE : ")
				.append(maReference).append("</li>").append("<li>PRIX : ").append(monPrix).append("</li>")
				.append("<li>PHOTO : ").append(maPhotoUrl).append("</li></ul></section>");

		return myStringBuilder;

	}

	public static StringBuilder afficherListePizzas() {

		myStringBuilder = null;

		myStringBuilder.append("<section><h2>Liste des Clients :</h2>");

		for (Pizza pizza : PizzaDao.listPizzas) {
			String monLibelle = pizza.getLibelle();
			Float monPrix = pizza.getPrix();
			String maReference = pizza.getReference();
			String maPhotoUrl = pizza.getPhotoUrl();
			// int monId = pizza.getId();
			int monId = 0;

			myStringBuilder.append("<ul>").append("<li><span>ID : ").append(monId).append("</span>").append("<span>")
					.append(monLibelle).append("</span>").append("<span>").append(maReference).append("</span>")
					.append("<span>").append(monPrix).append("</span>").append("<span>").append(maPhotoUrl)
					.append("</span>").append("</li></ul>");
		}

		return myStringBuilder;

	}

}
