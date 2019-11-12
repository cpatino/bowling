package com.jobsity.bowling.entity;

import static org.junit.Assert.*;

import org.junit.Test;

public class PlayerTest {

	@Test
	public void testPlayerData() {
		Frame frame = Frame.builder().id(1).pinfall(Pinfall.builder().pinsKnockedDown(10).build()).build();
		Player player = Player.builder().name("player").frame(frame).build();
		assertEquals("player", player.getName());
		assertEquals(1, player.getFrames().size());
		assertEquals(frame, player.getFrames().get(0));
	}
}