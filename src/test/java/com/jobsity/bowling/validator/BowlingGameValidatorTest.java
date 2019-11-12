package com.jobsity.bowling.validator;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.jobsity.bowling.entity.Frame;
import com.jobsity.bowling.entity.Game;
import com.jobsity.bowling.entity.Pinfall;
import com.jobsity.bowling.entity.Player;

/**
 * @author crodriguez
 * @since 07/04/2019
 */
public class BowlingGameValidatorTest {

	private GameValidator validator;

	@Before
	public void setUp() {
		validator = new BowlingGameValidator();
	}

	@Test(expected = BowlingValidatorException.class)
	public void validateImcompleteGame() {
		Player player = Player.builder().name("player").build();
		Game game = Game.builder().player(player).build();
		validator.validate(game);
		fail("Expecting bowling validator exception");
	}

	@Test(expected = BowlingValidatorException.class)
	public void validateWrongPinfallAtTheEnd() {
		validator.validate(buildWithWrongPinfallTurns(true));
		fail("Expecting bowling validator exception");
	}

	@Test(expected = BowlingValidatorException.class)
	public void validateWrongPinfallOtherPosition() {
		validator.validate(buildWithWrongPinfallTurns(false));
		fail("Expecting bowling validator exception");
	}

	@Test(expected = BowlingValidatorException.class)
	public void validateWrongPinfallValuesStrikeAndOtherTurn() {
		validator.validate(buildWithPinfallValues(10, 10));
		fail("Expecting bowling validator exception");
	}

	@Test(expected = BowlingValidatorException.class)
	public void validateWrongPinfallValues() {
		validator.validate(buildWithPinfallValues(8, 3));
		fail("Expecting bowling validator exception");
	}

	@Test(expected = BowlingValidatorException.class)
	public void validateNegativePinfallValues() {
		validator.validate(buildWithPinfallValues(8, -1));
		fail("Expecting bowling validator exception");
	}

	@Test
	public void validateSuccessfullPath() {
		try {
			validator.validate(buildCompleteGame());
		} catch (BowlingValidatorException ex) {
			fail("Not expecting bowling validator exception");
		}
	}

	private Game buildWithWrongPinfallTurns(boolean wrongNumOfPinfallAtTheEnd) {
		Player player = Player.builder().name("player").build();
		player.addFrame(Frame.builder().id(1).pinfall(Pinfall.builder().pinsKnockedDown(10).build()).build());
		player.addFrame(Frame.builder().id(2).pinfall(Pinfall.builder().pinsKnockedDown(10).build()).build());
		player.addFrame(Frame.builder().id(3).pinfall(Pinfall.builder().pinsKnockedDown(10).build()).build());
		player.addFrame(Frame.builder().id(4).pinfall(Pinfall.builder().pinsKnockedDown(10).build()).build());
		player.addFrame(Frame.builder().id(5).pinfall(Pinfall.builder().pinsKnockedDown(10).build()).build());
		player.addFrame(Frame.builder().id(6).pinfall(Pinfall.builder().pinsKnockedDown(10).build()).build());
		player.addFrame(Frame.builder().id(7).pinfall(Pinfall.builder().pinsKnockedDown(10).build()).build());
		player.addFrame(Frame.builder().id(8).pinfall(Pinfall.builder().pinsKnockedDown(10).build()).build());
		if (wrongNumOfPinfallAtTheEnd) {
			player.addFrame(Frame.builder().id(9).pinfall(Pinfall.builder().pinsKnockedDown(10).build()).build());
			player.addFrame(Frame.builder().id(10).pinfall(Pinfall.builder().pinsKnockedDown(10).build())
					.pinfall(Pinfall.builder().pinsKnockedDown(10).build())
					.pinfall(Pinfall.builder().pinsKnockedDown(10).build())
					.pinfall(Pinfall.builder().pinsKnockedDown(10).build()).build());
		} else {
			player.addFrame(Frame.builder().id(9).pinfall(Pinfall.builder().pinsKnockedDown(10).build())
					.pinfall(Pinfall.builder().pinsKnockedDown(10).build())
					.pinfall(Pinfall.builder().pinsKnockedDown(10).build()).build());
			player.addFrame(Frame.builder().id(10).pinfall(Pinfall.builder().pinsKnockedDown(10).build()).build());
		}

		return Game.builder().player(player).build();
	}

	private Game buildWithPinfallValues(int pinsKnockedDownFirstAttempt, int pinsKnockedDownSecondAttempt) {
		Player player = Player.builder().name("player").build();
		player.addFrame(Frame.builder().id(1).pinfall(Pinfall.builder().pinsKnockedDown(10).build()).build());
		player.addFrame(Frame.builder().id(2).pinfall(Pinfall.builder().pinsKnockedDown(10).build()).build());
		player.addFrame(Frame.builder().id(3).pinfall(Pinfall.builder().pinsKnockedDown(10).build()).build());
		player.addFrame(Frame.builder().id(4).pinfall(Pinfall.builder().pinsKnockedDown(10).build()).build());
		player.addFrame(Frame.builder().id(5).pinfall(Pinfall.builder().pinsKnockedDown(10).build()).build());
		player.addFrame(Frame.builder().id(6).pinfall(Pinfall.builder().pinsKnockedDown(10).build()).build());
		player.addFrame(Frame.builder().id(7).pinfall(Pinfall.builder().pinsKnockedDown(10).build()).build());
		player.addFrame(Frame.builder().id(8).pinfall(Pinfall.builder().pinsKnockedDown(10).build()).build());
		player.addFrame(
				Frame.builder().id(9).pinfall(Pinfall.builder().pinsKnockedDown(pinsKnockedDownFirstAttempt).build())
						.pinfall(Pinfall.builder().pinsKnockedDown(pinsKnockedDownSecondAttempt).build()).build());
		player.addFrame(Frame.builder().id(10).pinfall(Pinfall.builder().pinsKnockedDown(10).build()).build());

		return Game.builder().player(player).build();
	}

	private Game buildCompleteGame() {
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