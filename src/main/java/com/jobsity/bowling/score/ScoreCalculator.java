package com.jobsity.bowling.score;

import com.jobsity.bowling.entity.Game;

/**
 * Calculates the score for the given {@link Game}
 * @author crodriguez
 * @since 09/04/2019
 */
public interface ScoreCalculator {

	/**
	 * Calculate the score for the {@link Game} given as parameter
	 *
	 * @param game
	 *            Current game
	 */
	void calculateScore(Game game);
}