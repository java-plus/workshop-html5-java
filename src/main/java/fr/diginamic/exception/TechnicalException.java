package fr.diginamic.exception;

/**
 * Classe qui gère les exceptions
 * 
 * @author Cécile Peyras
 *
 */
public class TechnicalException extends RuntimeException {

	/**
	 * Constructeur
	 * 
	 * @param message
	 */
	public TechnicalException(String message) {
		super(message);

	}

	/**
	 * Constructeur
	 * 
	 * @param message
	 * @param cause
	 */
	public TechnicalException(String message, Throwable cause) {
		super(message, cause);

	}

}
