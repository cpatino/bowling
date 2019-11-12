package com.jobsity.bowling.parser;

/**
 * Exception to be thrown when file could not be parsed following the rules.
 * 
 * @author crodriguez
 * @since 06/04/2019
 */
public class BowlingParseException extends IllegalArgumentException {
	
	private static final long serialVersionUID = -3388516876069250551L;

	public BowlingParseException(String message) {
		super(message);
	}
}