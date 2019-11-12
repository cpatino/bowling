package com.jobsity.bowling.writer;

import com.jobsity.bowling.entity.Game;

/**
 * Writes the game information to a particular output.
 * 
 * @author crodriguez
 * @since 09/04/2019
 */
public interface GameWriter {

	/**
	 * Write the current {@link Game} using the specified format.
	 * 
	 * @param game
	 *            Current game.
	 */
	void write(Game game);

}