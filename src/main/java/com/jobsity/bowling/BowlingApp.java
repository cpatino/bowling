package com.jobsity.bowling;

import java.nio.file.Files;
import java.nio.file.Paths;

import com.jobsity.bowling.entity.Game;
import com.jobsity.bowling.parser.BowlingFileParser;
import com.jobsity.bowling.parser.BowlingParser;
import com.jobsity.bowling.score.DefaultScoreCalculator;
import com.jobsity.bowling.score.ScoreCalculator;
import com.jobsity.bowling.validator.BowlingGameValidator;
import com.jobsity.bowling.validator.GameValidator;
import com.jobsity.bowling.writer.ConsoleWriter;
import com.jobsity.bowling.writer.GameWriter;

/**
 * Bowling game application. Emulate the rules of a regular bowling game reading
 * the data from a text file and writing to the console.
 * 
 * @author crodriguez
 * @since 06/04/2019
 *
 */
public class BowlingApp {
	
	private final BowlingParser parser;
	private final GameValidator validator;
	private final ScoreCalculator scoreCalculator;
	private final GameWriter writer;
	
	public BowlingApp(final String filePath) {
		parser = new BowlingFileParser(filePath);
		validator = new BowlingGameValidator();
		scoreCalculator = new DefaultScoreCalculator();
		writer = new ConsoleWriter();
	}

	public static void main(String[] args) {
		if (args.length == 0) {
			throw new IllegalArgumentException("No game data (Text File) was passed as parameter");
		} else if (args[0].lastIndexOf(".txt") == -1) {
			throw new IllegalArgumentException("The game data should be passed as a .txt file");
		} else if (!Files.exists(Paths.get(args[0]))) {
			throw new IllegalArgumentException(args[0] + " could not be found");
		} else {
			new BowlingApp(args[0]).start();
		}
	}
	
	public void start() {
		Game game = parser.load().parse();
		validator.validate(game); 
		scoreCalculator.calculateScore(game);
		writer.write(game);
	}
}