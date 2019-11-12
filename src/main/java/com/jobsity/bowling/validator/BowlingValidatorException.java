package com.jobsity.bowling.validator;

/**
 * Exception to be thrown when file could not be parsed following the rules.
 * 
 * @author crodriguez
 * @since 06/04/2019
 */
public class BowlingValidatorException extends IllegalArgumentException {

	private static final long serialVersionUID = 8372704531748472634L;

	public BowlingValidatorException(String message) {
		super(message);
	}
}