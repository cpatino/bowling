package com.jobsity.bowling.entity;

import static org.junit.Assert.*;

import org.junit.Test;

public class GameTest {

	@Test
	public void testGameData() {
		Player player = Player.builder().name("player")
				.frame(Frame.builder().id(1).pinfall(Pinfall.builder().pinsKnockedDown(10).build()).build()).build();
		Game game = Game.builder().player(player).build();
		assertEquals(1, game.getPlayers().size());
		assertEquals(player, game.getPlayers().get(0));
	}
}