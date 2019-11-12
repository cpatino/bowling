package com.jobsity.bowling;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BowlingAppIT {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;

	@Before
	public void setUpStreams() {
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));
	}

	@After
	public void restoreStreams() {
		System.setOut(originalOut);
		System.setErr(originalErr);
	}

	@Test
	public void scoreGame() {
		String filePath = Paths.get("").toAbsolutePath().toString() + "/src/main/resources/scores_1.txt";
		BowlingApp.main(new String[] { filePath });
		assertEquals(expectedResultForScoreFile(), outContent.toString());
	}

	@Test
	public void perfectGame() {
		String filePath = Paths.get("").toAbsolutePath().toString() + "/src/main/resources/perfect_1.txt";
		BowlingApp.main(new String[] { filePath });
		assertEquals(expectedResultForPerfectFile(), outContent.toString());
	}

	private String expectedResultForScoreFile() {
		return "Frame	 	1 	 	 2 	 	 3 	 	 4 	 	 5 	 	 6 	 	 7 	 	 8 	 	 9 	 	 10 	 	 \n"
				+ "Jeff\n"
				+ "Pinfalls		X 	 7 	 / 	 9 	 0 	 	X 	 0 	 8 	 8 	 / 	 F 	 6 	 	X 	 	X 	 X 	 8 	 1 	 \n"
				+ "Score		20 	 	 39 	 	 48 	 	 66 	 	 74 	 	 84 	 	 90 	 	 120 	 	 148 	 	 167 	 	 \n"
				+ "\n" + "John\n"
				+ "Pinfalls	3 	 / 	 6 	 3 	 	X 	 8 	 1 	 	X 	 	X 	 9 	 0 	 7 	 / 	 4 	 4 	 X 	 9 	 0 	 \n"
				+ "Score		16 	 	 25 	 	 44 	 	 53 	 	 82 	 	 101 	 	 110 	 	 124 	 	 132 	 	 151 	 	 \n"
				+ "\n" + "";
	}

	private String expectedResultForPerfectFile() {
		return "Frame	 	1 	 	 2 	 	 3 	 	 4 	 	 5 	 	 6 	 	 7 	 	 8 	 	 9 	 	 10 	 	 \n"
				+ "Carl\n"
				+ "Pinfalls		X 	 	X 	 	X 	 	X 	 	X 	 	X 	 	X 	 	X 	 	X 	 X 	 X 	 X 	 \n"
				+ "Score		30 	 	 60 	 	 90 	 	 120 	 	 150 	 	 180 	 	 210 	 	 240 	 	 270 	 	 300 	 	 \n"
				+ "\n" + "";
	}
}