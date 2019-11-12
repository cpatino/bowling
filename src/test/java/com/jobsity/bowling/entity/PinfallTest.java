package com.jobsity.bowling.entity;

import static org.junit.Assert.*;

import org.junit.Test;

public class PinfallTest {

	@Test
	public void testPinfallValues() {
		Pinfall pinfall = Pinfall.builder().pinsKnockedDown(10).build();
		assertEquals(10, pinfall.getPinsKnockedDown());
		assertEquals(false, pinfall.isFoul());
	}
	
	@Test
	public void testFaultPinfallValues() {
		Pinfall pinfall = Pinfall.builder().pinsKnockedDown(0).foul(true).build();
		assertEquals(0, pinfall.getPinsKnockedDown());
		assertEquals(true, pinfall.isFoul());
	}
}