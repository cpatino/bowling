package com.jobsity.bowling.validator;

import com.jobsity.bowling.entity.Frame;
import com.jobsity.bowling.entity.Game;
import com.jobsity.bowling.entity.Player;

/**
 * @author crodriguez
 * @since 08/04/2019
 */
public class BowlingGameValidator implements GameValidator {

	@Override
	public void validate(final Game game) {
		validateIfGameIsCompleted(game);
		validatePinfalls(game);
	}

	private void validateIfGameIsCompleted(final Game game) {
		game.getPlayers().forEach(player -> {
			if (isCompleteGame(player)) {
				throw new BowlingValidatorException(String.format(
						"Game is not completed, the frames are incompleted (or could have more than 10) for the player: %s",
						player.getName()));
			}
		});
	}

	private void validatePinfalls(final Game game) {
		game.getPlayers().forEach(player -> {
			player.getFrames().forEach(frame -> {
				if (hasValidPinfallTurns(frame) || hasValidAttempts(frame)) {
					throw new BowlingValidatorException(String.format(
							"Number of pinfall turns (%d) for the frame number %d are invalid for the player: %s",
							frame.getPinfalls().size(), frame.getId(), player.getName()));
				} else if (frame.getId() < 10 && frame.getPinsKnockedDown(true) > 10) {
					throw new BowlingValidatorException(String.format(
							"Number of pins knocked down is more than 10 for the frame number %d, for the player: %s",
							frame.getId(), player.getName()));
				} else {
					frame.getPinfalls().forEach(pinfall -> {
						if (pinfall.getPinsKnockedDown() < 0) {
							throw new BowlingValidatorException(pinfall.getPinsKnockedDown()
									+ " are not a valid score value (Valid values are from 0 to 10 numbers)");
						}
					});
				}
			});
		});
	}

	/**
	 * Returns true if the number of frames for the player is different to 10, false
	 * otherwise.
	 */
	private boolean isCompleteGame(final Player player) {
		return player.getFrames().size() != 10;
	}

	/**
	 * Returns true if the pin fall attempts for frame (1-9) are more than 2, or if
	 * the attempts are more than 2 for last frame and it was not an strike or an
	 * spare, or if the attempts are more than 3 for last frame, false otherwise.
	 */
	private boolean hasValidPinfallTurns(final Frame frame) {
		return (frame.getPinfalls().size() > 2 && frame.getId() < 10)
				|| (frame.getPinfalls().size() > 3 && frame.getId() == 10)
				|| (frame.getPinfalls().size() > 2 && frame.getId() == 10 && !frame.isStrike() && !frame.isSpare());
	}

	/**
	 * Returns true if it is not the frame number 10 and the pin fall attempts are 2
	 * but the first attempt was a strike, false otherwise.
	 */
	private boolean hasValidAttempts(final Frame frame) {
		return frame.getId() < 10 && frame.getPinfalls().size() == 2 && frame.getPinsKnockedDownFirstAttempt() == 10;
	}
}