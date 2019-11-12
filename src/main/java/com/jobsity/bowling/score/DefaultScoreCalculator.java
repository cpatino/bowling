package com.jobsity.bowling.score;

import java.util.List;

import com.jobsity.bowling.entity.Frame;
import com.jobsity.bowling.entity.Game;

/**
 * Calculates the score for each player using the bowling's default rules
 * 
 * @author crodriguez
 * @since 09/04/2019
 */
public class DefaultScoreCalculator implements ScoreCalculator {

	@Override
	public void calculateScore(Game game) {
		game.getPlayers().forEach(player -> calculateScore(player.getFrames()));

	}

	/**
	 * Set score values for the given {@link Frame}s
	 * 
	 * @param frames
	 *            to be used for score calculation
	 */
	private void calculateScore(List<Frame> frames) {
		for (int i = 0; i < frames.size(); i++) {
			if (frames.get(i).isSpare() && i < 9) {
				calculateForSpare(frames.get(i), frames.get(i + 1), (i > 0) ? frames.get(i - 1) : null);
			} else if (frames.get(i).isStrike() && i < 9) {
				calculateForStrike(frames, i);
			} else {
				calculateForOpenFrame(frames.get(i), (i > 0) ? frames.get(i - 1) : null);
			}
		}
	}

	/**
	 * For an open frame, sum the pins knocked down in current frame with the score
	 * from the previous frame.
	 * 
	 * @param currentFrame
	 * @param previousFrame
	 */
	private void calculateForOpenFrame(final Frame currentFrame, final Frame previousFrame) {
		int previousScore = (previousFrame != null) ? previousFrame.getScore() : 0;
		currentFrame.setScore(currentFrame.getPinsKnockedDown(true) + previousScore);
	}

	/**
	 * For a spare, sum the score from the previous frame plus the pins knocked down
	 * in the current frame plus the pins knocked down in the first attempt for the
	 * next frame.
	 * 
	 * @param currentFrame
	 * @param nextFrame
	 * @param previousFrame
	 */
	private void calculateForSpare(final Frame currentFrame, final Frame nextFrame, final Frame previousFrame) {
		int previousScore = (previousFrame != null) ? previousFrame.getScore() : 0;
		currentFrame.setScore(
				previousScore + currentFrame.getPinsKnockedDown(false) + nextFrame.getPinsKnockedDownFirstAttempt());
	}

	/**
	 * For a strike, two rules will have to be accomplished:
	 * 
	 * 1. If the next frame is not a strike, the score calculation will be sum of
	 * the score from the previous frame plus the pins knocked down for the current
	 * frame (10) plus the pins knocked down in the first attempt for the next
	 * frame.
	 * 
	 * 2. If the next frame is also a strike, the score calculation will be sum of
	 * the score from the previous frame plus the pins knocked down for the current
	 * frame (10) plus the pins knocked down for the next frame (10) plus the pins
	 * knocked down for the third frame (10).
	 * 
	 * @param frames
	 * @param currentIndex
	 */
	private void calculateForStrike(final List<Frame> frames, final int currentIndex) {
		Frame currentFrame = frames.get(currentIndex);
		Frame previousFrame = (currentIndex > 0) ? frames.get(currentIndex - 1) : null;
		int previousScore = (previousFrame != null) ? previousFrame.getScore() : 0;
		if (currentIndex < 9) {
			Frame nextFrame = frames.get(currentIndex + 1);
			if (!nextFrame.isStrike() || currentIndex == 8) {
				currentFrame.setScore(
						previousScore + currentFrame.getPinsKnockedDown(false) + nextFrame.getPinsKnockedDown(false));
			} else {
				Frame thirdFrame = frames.get(currentIndex + 2);
				if (currentIndex < 7) {
					currentFrame.setScore(previousScore + currentFrame.getPinsKnockedDown(false)
							+ nextFrame.getPinsKnockedDown(false) + thirdFrame.getPinsKnockedDown(false));
				} else {
					currentFrame.setScore(previousScore + currentFrame.getPinsKnockedDown(false)
							+ nextFrame.getPinsKnockedDown(false) + thirdFrame.getPinsKnockedDownFirstAttempt());
				}
			}
		}
	}
}