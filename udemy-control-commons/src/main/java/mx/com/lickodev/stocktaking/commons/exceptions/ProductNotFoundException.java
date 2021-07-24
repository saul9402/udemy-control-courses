package mx.com.lickodev.stocktaking.commons.exceptions;

public class ProductNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProductNotFoundException() {
		super();
	}

	public ProductNotFoundException(String message) {
		super(message);
	}

	public ProductNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

}
