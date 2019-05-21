package dev.pizzeria.exception;

public class TechnicalException extends RuntimeException {

	/** serialVersionUID : long */
	private static final long serialVersionUID = 8414632040328352706L;

	public TechnicalException(String msg, Throwable e) {
		super(msg, e);
	}

}
