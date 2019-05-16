package dev.pizzeria.model;

import java.util.ArrayList;
import java.util.List;

public class PizzeriaDaoPizza extends PizzeriaDao {

	List<Pizza> listePizza = new ArrayList();

	public void add(Pizza pizza) {

		listePizza.add(pizza);

	}

	public List<Pizza> findPizza() {
		return listePizza;
	}

}
