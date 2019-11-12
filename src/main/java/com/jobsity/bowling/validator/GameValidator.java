package com.jobsity.bowling.validator;

import com.jobsity.bowling.entity.Frame;
import com.jobsity.bowling.entity.Game;
import com.jobsity.bowling.entity.Pinfall;

/**
 * @author crodriguez
 * @since 08/04/2019
 */
public interface GameValidator {

	/**
	 * Validate parsed {@link Game} to check if it is a complete one, and that the
	 * {@link Frame}s and {@link Pinfall}s are completed.
	 * 
	 * @param game
	 *            Current parsed game.
	 */
	void validate(Game game);
}