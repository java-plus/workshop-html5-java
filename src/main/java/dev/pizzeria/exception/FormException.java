package dev.pizzeria.exception;

/**
 * Exception liée aux formulaires de l'application Pizzeria
 */
public class FormException extends Exception {

	/**
	 * Constructor
	 */
	public FormException() {
	}

	/**
	 * Constructor
	 * 
	 * @param message
	 */
	public FormException(String message) {
		super(message);
	}

}
