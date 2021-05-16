package project_04;

/**
 * This class allows programmer to create their own exception called "IllegalCheckerboardArgumentException"
 * @author jaize John Naizer
 * CSE 271
 */
@SuppressWarnings("serial")
public class IllegalCheckerboardArgumentException extends Exception {

	/**
	 * A constructor that has a preset message displayed if the exception is thrown.
	 */
	public IllegalCheckerboardArgumentException() {
		super("The checker piece cannot be placed there!");
	}
	
	/**
	 * A constructor that allows programmer to input their own message
	 * that they want displayed when the exception is thrown.
	 * @param message a message
	 */
	public IllegalCheckerboardArgumentException(String message) {
		super(message);
	}
}
