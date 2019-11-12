package com.jobsity.bowling.parser;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import com.jobsity.bowling.entity.Frame;
import com.jobsity.bowling.entity.Game;
import com.jobsity.bowling.entity.Pinfall;
import com.jobsity.bowling.entity.Player;

/**
 * Bowling parser implementation that loads game data from a txt file
 * 
 * @author crodriguez
 * @since 07/04/2019
 */
public class BowlingFileParser implements BowlingParser {

	private final String file;
	private final List<String> linesFromFile = new ArrayList<>();
	private final Game game = Game.builder().build();

	public BowlingFileParser(final String file) {
		this.file = file;
	}

	@Override
	public BowlingParser load() {
		try (Stream<String> stream = Files.lines(Paths.get(file), StandardCharsets.UTF_8)) {
			stream.forEach(s -> linesFromFile.add(s));
		} catch (IOException ex) {
			throw new BowlingParseException(ex.getMessage());
		}
		return this;
	}

	@Override
	public Game parse() {
		if (linesFromFile.isEmpty()) {
			throw new BowlingParseException("The txt file is empty");
		} else {
			for (String line : linesFromFile) {
				String[] lineSplitted = line.split("\t");
				validateLineFormat(lineSplitted);
				parse(lineSplitted[0], getPinsKnockedDown(lineSplitted[1]), isFoul(lineSplitted[1]));
			}
		}
		return game;
	}

	/**
	 * Parse a pin fall turn to the entities.
	 * 
	 * @param name
	 *            Player's name
	 * @param pinsKnockedDown
	 *            Pins knocked down for the turn
	 * @param foul
	 *            True is F was sent for the pins knocked down, false otherwise
	 */
	private void parse(String name, int pinsKnockedDown, boolean foul) {
		Optional<Player> optionalPlayer = game.getPlayers().stream().filter(player -> player.getName().equals(name))
				.findFirst();
		if (optionalPlayer.isPresent()) {
			Player player = optionalPlayer.get();
			Frame frame = player.getFrames().get(player.getFrames().size() - 1);
			if (mustAddNewPinfall(frame)) {
				frame.addPinfall(buildNewPinfall(pinsKnockedDown, foul));
			} else {
				player.addFrame(buildNewFrame(player.getFrames().size() + 1, pinsKnockedDown, foul));
			}
		} else {
			game.addPlayer(buildNewPlayer(name, pinsKnockedDown, foul));
		}
	}

	/**
	 * Validates the rules that each line should accomplish in order to be parsed
	 * correctly
	 * 
	 * @param line
	 *            String array with the content to be validated.
	 */
	private void validateLineFormat(String[] line) {
		if (line.length != 2) {
			throw new BowlingParseException(
					"Each line must represent a player and a chance with the subsequent number of pins knocked down (tab-separated)");
		}
		if (!line[1].equals("F")) {
			try {
				int pinsKnockedDown = Integer.parseInt(line[1]);
				if (pinsKnockedDown < 0 || pinsKnockedDown > 10) {
					throw new BowlingParseException(
							pinsKnockedDown + " are not a valid score value (Valid values are F or 0 to 10 numbers)");
				}
			} catch (NumberFormatException ex) {
				throw new BowlingParseException(
						line[1] + " are not a valid score value (Valid values are F or 0 to 10 numbers)");
			}
		}
	}

	private int getPinsKnockedDown(String pinsKnockedDownStr) {
		return (pinsKnockedDownStr.equals("F")) ? 0 : Integer.parseInt(pinsKnockedDownStr);
	}

	private boolean isFoul(String pinsKnockedDownStr) {
		return pinsKnockedDownStr.equals("F");
	}

	private Player buildNewPlayer(String name, int pinsKnockedDown, boolean foul) {
		return Player.builder().name(name).frame(buildNewFrame(1, pinsKnockedDown, foul)).build();
	}

	private Frame buildNewFrame(int id, int pinsKnockedDown, boolean foul) {
		return Frame.builder().id(id).pinfall(buildNewPinfall(pinsKnockedDown, foul)).build();
	}

	private Pinfall buildNewPinfall(int pinsKnockedDown, boolean foul) {
		return Pinfall.builder().pinsKnockedDown(pinsKnockedDown).foul(foul).build();
	}

	/**
	 * Returns true if the given frame must add a new pin fall chance, false if a
	 * new frame should be created.
	 * 
	 * @param frame
	 *            Current frame
	 * @return true if the given frame must add a new pin fall chance, false if a
	 *         new frame should be created
	 */
	private boolean mustAddNewPinfall(Frame frame) {
		return (frame.getPinsKnockedDown(true) < 10 && frame.getPinfalls().size() < 2) || frame.getId() == 10;
	}
}