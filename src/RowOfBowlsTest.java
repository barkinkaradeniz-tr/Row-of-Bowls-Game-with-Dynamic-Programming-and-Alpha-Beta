import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.Random;

class RowOfBowlsTest {

	@Test
	void testRowOfBowls() {
		fail("Not yet implemented");
	}

	@Test
	void testMaxGain() {
		fail("Not yet implemented");
	}

	@Test
	void testMaxGainRecursiveIntArray() {
		fail("Not yet implemented");
	}

	@Test
	void testMaxGainRecursiveIntInt() {
		fail("Not yet implemented");
	}

	@Test
	void testOptimalSequence() {
		fail("Not yet implemented");
	}

	@Test
	void testRandomSequence() {
		fail("Not yet implemented");
	}

	@Test
	void testTime() {

		RowOfBowls tmp = new RowOfBowls();
		Random rng = new Random();
		int[] values = new int[100];

		for (int i = 0; i < values.length; i++) {
			values[i] = rng.nextInt(10);
		}

		tmp.maxGainRecursive(values);
	}

}

