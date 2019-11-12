package com.jobsity.bowling.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.nio.file.Paths;

import org.junit.Test;

import com.jobsity.bowling.BowlingAppTest;
import com.jobsity.bowling.entity.Frame;
import com.jobsity.bowling.entity.Game;
import com.jobsity.bowling.entity.Pinfall;
import com.jobsity.bowling.entity.Player;

/**
 * @author crodriguez
 * @since 07/04/2019
 */
public class BowlingFileParserTest {

	@Test(expected = BowlingParseException.class)
	public void loadNoExistingFile() throws IOException {
		BowlingParser parser = new BowlingFileParser("not-found.txt");
		parser.load();
		fail("Expecting bowling parse exception");
	}

	@Test(expected = BowlingParseException.class)
	public void parseLinesFromFileWhenEmpty() {
		expectingExceptionAfterParse(Paths.get("").toAbsolutePath().toString() + "/src/main/resources/empty.txt");
	}

	@Test(expected = BowlingParseException.class)
	public void validateLineFormatNoTabs() {
		expectingExceptionAfterParse(Paths.get("").toAbsolutePath().toString() + "/src/main/resources/no_tabs.txt");
	}

	@Test(expected = BowlingParseException.class)
	public void validateLineFormatInvalidScoresWithLetterDifferentToF() {
		expectingExceptionAfterParse(Paths.get("").toAbsolutePath().toString() + "/src/main/resources/invalid_scores_1.txt");
	}

	@Test(expected = BowlingParseException.class)
	public void validateLineFormatInvalidScoresNegative() {
		expectingExceptionAfterParse(Paths.get("").toAbsolutePath().toString() + "/src/main/resources/invalid_scores_2.txt");
	}

	@Test(expected = BowlingParseException.class)
	public void validateLineFormatInvalidScoresOverTen() {
		expectingExceptionAfterParse(Paths.get("").toAbsolutePath().toString() + "/src/main/resources/invalid_scores_3.txt");
	}

	@Test
	public void parse() {
		Game expectingGame = expectingGameFromFile();
		Game game = callParse(Paths.get("").toAbsolutePath().toString() + "/src/main/resources/scores_1.txt");
		assertEquals(expectingGame, game);
	}

	private Game callParse(String filePath) {
		BowlingParser parser = new BowlingFileParser(filePath).load();
		return parser.parse();
	}

	private void expectingExceptionAfterParse(String filePath) {
		callParse(filePath);
		fail("Expecting bowling parse exception");
	}

	private Game expectingGameFromFile() {
		Player jeffPlayer = Player.builder().name("Jeff").build();
		jeffPlayer.addFrame(Frame.builder().id(1).pinfall(Pinfall.builder().pinsKnockedDown(10).build()).build());
		jeffPlayer.addFrame(Frame.builder().id(2).pinfall(Pinfall.builder().pinsKnockedDown(7).build())
				.pinfall(Pinfall.builder().pinsKnockedDown(3).build()).build());
		jeffPlayer.addFrame(Frame.builder().id(3).pinfall(Pinfall.builder().pinsKnockedDown(9).build())
				.pinfall(Pinfall.builder().pinsKnockedDown(0).build()).build());
		jeffPlayer.addFrame(Frame.builder().id(4).pinfall(Pinfall.builder().pinsKnockedDown(10).build()).build());
		jeffPlayer.addFrame(Frame.builder().id(5).pinfall(Pinfall.builder().pinsKnockedDown(0).build())
				.pinfall(Pinfall.builder().pinsKnockedDown(8).build()).build());
		jeffPlayer.addFrame(Frame.builder().id(6).pinfall(Pinfall.builder().pinsKnockedDown(8).build())
				.pinfall(Pinfall.builder().pinsKnockedDown(2).build()).build());
		jeffPlayer.addFrame(Frame.builder().id(7).pinfall(Pinfall.builder().pinsKnockedDown(0).build())
				.pinfall(Pinfall.builder().pinsKnockedDown(6).build()).build());
		jeffPlayer.addFrame(Frame.builder().id(8).pinfall(Pinfall.builder().pinsKnockedDown(10).build()).build());
		jeffPlayer.addFrame(Frame.builder().id(9).pinfall(Pinfall.builder().pinsKnockedDown(10).build()).build());
		jeffPlayer.addFrame(Frame.builder().id(10).pinfall(Pinfall.builder().pinsKnockedDown(10).build())
				.pinfall(Pinfall.builder().pinsKnockedDown(8).build())
				.pinfall(Pinfall.builder().pinsKnockedDown(1).build()).build());

		Player johnPlayer = Player.builder().name("John").build();
		johnPlayer.addFrame(Frame.builder().id(1).pinfall(Pinfall.builder().pinsKnockedDown(3).build())
				.pinfall(Pinfall.builder().pinsKnockedDown(7).build()).build());
		johnPlayer.addFrame(Frame.builder().id(2).pinfall(Pinfall.builder().pinsKnockedDown(6).build())
				.pinfall(Pinfall.builder().pinsKnockedDown(3).build()).build());
		johnPlayer.addFrame(Frame.builder().id(3).pinfall(Pinfall.builder().pinsKnockedDown(10).build()).build());
		johnPlayer.addFrame(Frame.builder().id(4).pinfall(Pinfall.builder().pinsKnockedDown(8).build())
				.pinfall(Pinfall.builder().pinsKnockedDown(1).build()).build());
		johnPlayer.addFrame(Frame.builder().id(5).pinfall(Pinfall.builder().pinsKnockedDown(10).build()).build());
		johnPlayer.addFrame(Frame.builder().id(6).pinfall(Pinfall.builder().pinsKnockedDown(10).build()).build());
		johnPlayer.addFrame(Frame.builder().id(7).pinfall(Pinfall.builder().pinsKnockedDown(9).build())
				.pinfall(Pinfall.builder().pinsKnockedDown(0).build()).build());
		johnPlayer.addFrame(Frame.builder().id(8).pinfall(Pinfall.builder().pinsKnockedDown(7).build())
				.pinfall(Pinfall.builder().pinsKnockedDown(3).build()).build());
		johnPlayer.addFrame(Frame.builder().id(9).pinfall(Pinfall.builder().pinsKnockedDown(4).build())
				.pinfall(Pinfall.builder().pinsKnockedDown(4).build()).build());
		johnPlayer.addFrame(Frame.builder().id(10).pinfall(Pinfall.builder().pinsKnockedDown(10).build())
				.pinfall(Pinfall.builder().pinsKnockedDown(9).build())
				.pinfall(Pinfall.builder().pinsKnockedDown(0).build()).build());

		return Game.builder().player(jeffPlayer).player(johnPlayer).build();
	}
}