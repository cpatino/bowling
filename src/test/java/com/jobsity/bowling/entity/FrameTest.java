package com.jobsity.bowling.entity;

import static org.junit.Assert.*;

import org.junit.Test;

public class FrameTest {

	@Test
	public void testFrameData() {
		Frame frame = Frame.builder().id(10).pinfall(Pinfall.builder().pinsKnockedDown(10).build())
				.pinfall(Pinfall.builder().pinsKnockedDown(9).build())
				.pinfall(Pinfall.builder().pinsKnockedDown(1).build()).build();
		assertEquals(10, frame.getId());
		assertEquals(3, frame.getPinfalls().size());
		assertEquals(10, frame.getPinsKnockedDownFirstAttempt());
		assertEquals(19, frame.getPinsKnockedDown(false));
		assertEquals(20, frame.getPinsKnockedDown(true));
	}

	@Test
	public void testFrameIsStrike() {
		Frame frame = Frame.builder().id(1).pinfall(Pinfall.builder().pinsKnockedDown(10).build()).build();
		assertEquals(10, frame.getPinsKnockedDownFirstAttempt());
		assertEquals(true, frame.isStrike());
	}

	@Test
	public void testFrameIsSpare() {
		Frame frame = Frame.builder().id(1).pinfall(Pinfall.builder().pinsKnockedDown(9).build())
				.pinfall(Pinfall.builder().pinsKnockedDown(1).build()).build();
		assertEquals(10, frame.getPinsKnockedDown(true));
		assertEquals(false, frame.isStrike());
		assertEquals(true, frame.isSpare());
	}
	
	@Test
	public void testFrameIsOpenFrame() {
		Frame frame = Frame.builder().id(1).pinfall(Pinfall.builder().pinsKnockedDown(8).build())
				.pinfall(Pinfall.builder().pinsKnockedDown(1).build()).build();
		assertEquals(9, frame.getPinsKnockedDown(true));
		assertEquals(false, frame.isStrike());
		assertEquals(false, frame.isSpare());
	}
}