package dev.pizzeria.dao;

import java.util.List;

import dev.pizzeria.exception.TechnicalException;
import dev.pizzeria.model.Pizza;

public interface IPizzaDao {

	public void saveNewPizza(Pizza pizza) throws TechnicalException;

	public List<Pizza> findAllPizzas() throws TechnicalException;

	public Pizza findPizzaById(int id) throws TechnicalException;

	public boolean pizzaExists(int id) throws TechnicalException;

	public void deletePizzaById(int id) throws TechnicalException;

	public void updatePizzaById(int id, Pizza pizza) throws TechnicalException;

}
