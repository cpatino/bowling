package com.jobsity.bowling.score;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.jobsity.bowling.entity.Frame;
import com.jobsity.bowling.entity.Game;
import com.jobsity.bowling.entity.Pinfall;
import com.jobsity.bowling.entity.Player;

public class DefaultScoreCalculatorTest {

	private ScoreCalculator scoreCalculator;

	@Before
	public void setUp() {
		scoreCalculator = new DefaultScoreCalculator();
	}

	@Test
	public void calculateNotPerfectGame() {
		Game game = buildCompleteGame();
		scoreCalculator.calculateScore(game);
		assertEquals(20, game.getPlayers().get(0).getFrames().get(0).getScore());
		assertEquals(39, game.getPlayers().get(0).getFrames().get(1).getScore());
		assertEquals(48, game.getPlayers().get(0).getFrames().get(2).getScore());
		assertEquals(66, game.getPlayers().get(0).getFrames().get(3).getScore());
		assertEquals(74, game.getPlayers().get(0).getFrames().get(4).getScore());
		assertEquals(84, game.getPlayers().get(0).getFrames().get(5).getScore());
		assertEquals(90, game.getPlayers().get(0).getFrames().get(6).getScore());
		assertEquals(120, game.getPlayers().get(0).getFrames().get(7).getScore());
		assertEquals(148, game.getPlayers().get(0).getFrames().get(8).getScore());
		assertEquals(167, game.getPlayers().get(0).getFrames().get(9).getScore());

		assertEquals(16, game.getPlayers().get(1).getFrames().get(0).getScore());
		assertEquals(25, game.getPlayers().get(1).getFrames().get(1).getScore());
		assertEquals(44, game.getPlayers().get(1).getFrames().get(2).getScore());
		assertEquals(53, game.getPlayers().get(1).getFrames().get(3).getScore());
		assertEquals(82, game.getPlayers().get(1).getFrames().get(4).getScore());
		assertEquals(101, game.getPlayers().get(1).getFrames().get(5).getScore());
		assertEquals(110, game.getPlayers().get(1).getFrames().get(6).getScore());
		assertEquals(124, game.getPlayers().get(1).getFrames().get(7).getScore());
		assertEquals(132, game.getPlayers().get(1).getFrames().get(8).getScore());
		assertEquals(151, game.getPlayers().get(1).getFrames().get(9).getScore());
	}

	@Test
	public void calculatePerfectGame() {
		Game game = buildPerfectGame();
		scoreCalculator.calculateScore(game);
		assertEquals(30, game.getPlayers().get(0).getFrames().get(0).getScore());
		assertEquals(60, game.getPlayers().get(0).getFrames().get(1).getScore());
		assertEquals(90, game.getPlayers().get(0).getFrames().get(2).getScore());
		assertEquals(120, game.getPlayers().get(0).getFrames().get(3).getScore());
		assertEquals(150, game.getPlayers().get(0).getFrames().get(4).getScore());
		assertEquals(180, game.getPlayers().get(0).getFrames().get(5).getScore());
		assertEquals(210, game.getPlayers().get(0).getFrames().get(6).getScore());
		assertEquals(240, game.getPlayers().get(0).getFrames().get(7).getScore());
		assertEquals(270, game.getPlayers().get(0).getFrames().get(8).getScore());
		assertEquals(300, game.getPlayers().get(0).getFrames().get(9).getScore());
	}

	@Test
	public void calculateFaultGame() {
		Game game = buildFaultGame();
		scoreCalculator.calculateScore(game);
		assertEquals(0, game.getPlayers().get(0).getFrames().get(0).getScore());
		assertEquals(0, game.getPlayers().get(0).getFrames().get(1).getScore());
		assertEquals(0, game.getPlayers().get(0).getFrames().get(2).getScore());
		assertEquals(0, game.getPlayers().get(0).getFrames().get(3).getScore());
		assertEquals(0, game.getPlayers().get(0).getFrames().get(4).getScore());
		assertEquals(0, game.getPlayers().get(0).getFrames().get(5).getScore());
		assertEquals(0, game.getPlayers().get(0).getFrames().get(6).getScore());
		assertEquals(0, game.getPlayers().get(0).getFrames().get(7).getScore());
		assertEquals(0, game.getPlayers().get(0).getFrames().get(8).getScore());
		assertEquals(0, game.getPlayers().get(0).getFrames().get(9).getScore());
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

	private Game buildPerfectGame() {
		Player player = Player.builder().name("player").build();
		player.addFrame(Frame.builder().id(1).pinfall(Pinfall.builder().pinsKnockedDown(10).build()).build());
		player.addFrame(Frame.builder().id(2).pinfall(Pinfall.builder().pinsKnockedDown(10).build()).build());
		player.addFrame(Frame.builder().id(3).pinfall(Pinfall.builder().pinsKnockedDown(10).build()).build());
		player.addFrame(Frame.builder().id(4).pinfall(Pinfall.builder().pinsKnockedDown(10).build()).build());
		player.addFrame(Frame.builder().id(5).pinfall(Pinfall.builder().pinsKnockedDown(10).build()).build());
		player.addFrame(Frame.builder().id(6).pinfall(Pinfall.builder().pinsKnockedDown(10).build()).build());
		player.addFrame(Frame.builder().id(7).pinfall(Pinfall.builder().pinsKnockedDown(10).build()).build());
		player.addFrame(Frame.builder().id(8).pinfall(Pinfall.builder().pinsKnockedDown(10).build()).build());
		player.addFrame(Frame.builder().id(9).pinfall(Pinfall.builder().pinsKnockedDown(10).build()).build());
		player.addFrame(Frame.builder().id(10).pinfall(Pinfall.builder().pinsKnockedDown(10).build())
				.pinfall(Pinfall.builder().pinsKnockedDown(10).build())
				.pinfall(Pinfall.builder().pinsKnockedDown(10).build()).build());

		return Game.builder().player(player).build();
	}

	private Game buildFaultGame() {
		Player player = Player.builder().name("player").build();
		player.addFrame(Frame.builder().id(1).pinfall(Pinfall.builder().pinsKnockedDown(0).build())
				.pinfall(Pinfall.builder().pinsKnockedDown(0).build()).build());
		player.addFrame(Frame.builder().id(2).pinfall(Pinfall.builder().pinsKnockedDown(0).build())
				.pinfall(Pinfall.builder().pinsKnockedDown(0).build()).build());
		player.addFrame(Frame.builder().id(3).pinfall(Pinfall.builder().pinsKnockedDown(0).build())
				.pinfall(Pinfall.builder().pinsKnockedDown(0).build()).build());
		player.addFrame(Frame.builder().id(4).pinfall(Pinfall.builder().pinsKnockedDown(0).build())
				.pinfall(Pinfall.builder().pinsKnockedDown(0).build()).build());
		player.addFrame(Frame.builder().id(5).pinfall(Pinfall.builder().pinsKnockedDown(0).build())
				.pinfall(Pinfall.builder().pinsKnockedDown(0).build()).build());
		player.addFrame(Frame.builder().id(6).pinfall(Pinfall.builder().pinsKnockedDown(0).build())
				.pinfall(Pinfall.builder().pinsKnockedDown(0).build()).build());
		player.addFrame(Frame.builder().id(7).pinfall(Pinfall.builder().pinsKnockedDown(0).build())
				.pinfall(Pinfall.builder().pinsKnockedDown(0).build()).build());
		player.addFrame(Frame.builder().id(8).pinfall(Pinfall.builder().pinsKnockedDown(0).build())
				.pinfall(Pinfall.builder().pinsKnockedDown(0).build()).build());
		player.addFrame(Frame.builder().id(9).pinfall(Pinfall.builder().pinsKnockedDown(0).build())
				.pinfall(Pinfall.builder().pinsKnockedDown(0).build()).build());
		player.addFrame(Frame.builder().id(10).pinfall(Pinfall.builder().pinsKnockedDown(0).build())
				.pinfall(Pinfall.builder().pinsKnockedDown(0).build()).build());

		return Game.builder().player(player).build();
	}
}