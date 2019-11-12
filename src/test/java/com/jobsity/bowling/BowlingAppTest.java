package com.jobsity.bowling;

import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * @author crodriguez
 * @since 06/04/2019
 */
public class BowlingAppTest {

	@Test(expected = IllegalArgumentException.class)
	public void mainNoGameData() {
		BowlingApp.main(new String[] {});
		fail("Expecting illegal argument exception");
	}

	@Test(expected = IllegalArgumentException.class)
	public void mainNoTxtFileAsArgument() {
		BowlingApp.main(new String[] { "bowling-game" });
		fail("Expecting illegal argument exception");
	}

	@Test(expected = IllegalArgumentException.class)
	public void mainNoExistingTxtFile() {
		BowlingApp.main(new String[] { "not-found.txt" });
		fail("Expecting illegal argument exception");
	}
}