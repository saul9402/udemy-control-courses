package mx.com.lickodev.udemy.control.commons.exceptions;

public class CourseNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CourseNotFoundException() {
		super();
	}

	public CourseNotFoundException(String message) {
		super(message);
	}

	public CourseNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

}
