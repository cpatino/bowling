package com.jobsity.bowling.parser;

import com.jobsity.bowling.entity.Game;

/**
 * @author crodriguez
 * @since 07/04/2019
 */
public interface BowlingParser {
	
	/**
	 * Loads the game data from a particular format.
	 */
	BowlingParser load();
	
	/**
	 * Parse the game data from a particular format to Entity classes ({@link Game}).
	 * 
	 * @return Parsed {@link Game}
	 */
	Game parse();
}