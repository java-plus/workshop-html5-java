package dev.pizzeria.controller;

public class Controle {
	public static boolean nestUnEntier(String chaine) {
		try {
			Integer.parseInt(chaine);
		} catch (NumberFormatException e) {
			return true;
		}

		return false;
	}
}
