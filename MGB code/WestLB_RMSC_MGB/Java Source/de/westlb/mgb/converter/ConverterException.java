package de.westlb.mgb.converter;

/**
 * @author D055625
 *
 * This exception wraps all exceptions thrown while running a converter.
 */
public class ConverterException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2141176561377290825L;

	/**
	 * Constructor for ConverterException.
	 */
	public ConverterException() {
		super();
	}

	/**
	 * Constructor for ConverterException.
	 * @param message
	 */
	public ConverterException(String message) {
		super(message);
	}

	/**
	 * Constructor for ConverterException.
	 * @param message
	 * @param cause
	 */
	public ConverterException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructor for ConverterException.
	 * @param cause
	 */
	public ConverterException(Throwable cause) {
		super(cause);
	}

}
