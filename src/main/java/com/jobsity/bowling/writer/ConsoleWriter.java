package com.jobsity.bowling.writer;

import java.util.List;

import com.jobsity.bowling.entity.Frame;
import com.jobsity.bowling.entity.Game;
import com.jobsity.bowling.entity.Pinfall;
import com.jobsity.bowling.entity.Player;

/**
 * Writes the game information to the console.
 * 
 * @author crodriguez
 * @since 08/04/2019
 */
public class ConsoleWriter implements GameWriter {

	@Override
	public void write(final Game game) {
		printFrameInformation();
		game.getPlayers().forEach(player -> printPlayerInformation(player));
	}

	private void printFrameInformation() {
		System.out.print("Frame\t \t");
		for (int i = 1; i <= 10; i++) {
			System.out.print(i + " \t \t ");
		}
		System.out.print("\n");
	}

	private void printPlayerInformation(final Player player) {
		System.out.print(player.getName() + "\n");
		printPintfalls(player.getFrames());
		printScore(player.getFrames());
		System.out.print("\n");
	}

	private void printPintfalls(final List<Frame> frames) {
		System.out.print("Pinfalls\t");
		for (Frame frame : frames) {
			if (frame.isStrike()) {
				if (frame.getId() < 10) {
					System.out.print("\tX \t ");
				} else {
					frame.getPinfalls().forEach(pinfall -> System.out.print(getPinfallToPrint(pinfall) + " \t "));
				}
			} else if (frame.isSpare() && frame.getId() < 10) {
				System.out.print(frame.getPinsKnockedDownFirstAttempt() + " \t / \t ");
			} else {
				frame.getPinfalls().forEach(pinfall -> System.out.print(getPinfallToPrint(pinfall) + " \t "));
			}
		}
		System.out.print("\n");
	}
	
	private void printScore(final List<Frame> frames) {
		System.out.print("Score\t\t");
		frames.forEach(frame -> System.out.print(frame.getScore() + " \t \t "));
		System.out.print("\n");
	}

	private String getPinfallToPrint(Pinfall pinfall) {
		return pinfall.isFoul() ? "F" : pinfall.getPinsKnockedDown() == 10 ? "X" : pinfall.getPinsKnockedDown() + "";
	}
}