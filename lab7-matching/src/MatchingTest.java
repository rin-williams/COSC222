import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MatchingTest {
	char[] text, pattern;
	int[] expected, actual;

	@Test
	void testFindKMP() {
		// test for worst case
		text = "aababcabcdabcdef".toCharArray();
		pattern = "abcdef".toCharArray();
		expected = new int[] { 10 };
		actual = Matching.findKMP(text, pattern);
		assertArrayEquals(expected, actual);

		// test for match
		text = "abcdefg".toCharArray();
		pattern = "efg".toCharArray();
		expected = new int[] { 4 };
		actual = Matching.findKMP(text, pattern);
		assertArrayEquals(expected, actual);

		// test for one match
		text = "abc".toCharArray();
		pattern = "abc".toCharArray();
		expected = new int[] { 0 };
		actual = Matching.findKMP(text, pattern);
		assertArrayEquals(expected, actual);

		// test for multiple matches
		text = "eeeeee".toCharArray();
		pattern = "eee".toCharArray();
		expected = new int[] { 0, 1, 2, 3 };
		actual = Matching.findKMP(text, pattern);
		assertArrayEquals(expected, actual);

		// test for single character (which is not a pattern)
		text = "eeeeee".toCharArray();
		pattern = "e".toCharArray();
		expected = new int[] {};
		actual = Matching.findKMP(text, pattern);
		assertArrayEquals(expected, actual);

		// test for empty text and pattern
		text = "".toCharArray();
		pattern = "".toCharArray();
		expected = new int[] {};
		actual = Matching.findKMP(text, pattern);
		assertArrayEquals(expected, actual);

		// test for empty pattern
		text = "abc".toCharArray();
		pattern = "".toCharArray();
		expected = new int[] {};
		actual = Matching.findKMP(text, pattern);
		assertArrayEquals(expected, actual);

		// test for empty text
		text = "a".toCharArray();
		pattern = "abc".toCharArray();
		expected = new int[] {};
		actual = Matching.findKMP(text, pattern);
		assertArrayEquals(expected, actual);

	}

	@Test
	void testFindBoyerMoore() {
		// test for worst case
		text = "aababcabcdabcdef".toCharArray();
		pattern = "abcdef".toCharArray();
		expected = new int[] { 10 };
		actual = Matching.findBoyerMoore(text, pattern);
		assertArrayEquals(expected, actual);

		// test for match
		text = "abcdefg".toCharArray();
		pattern = "efg".toCharArray();
		expected = new int[] { 4 };
		actual = Matching.findBoyerMoore(text, pattern);
		assertArrayEquals(expected, actual);

		// test for one match
		text = "abc".toCharArray();
		pattern = "abc".toCharArray();
		expected = new int[] { 0 };
		actual = Matching.findBoyerMoore(text, pattern);
		assertArrayEquals(expected, actual);

		// test for multiple matches
		text = "eeeeee".toCharArray();
		pattern = "eee".toCharArray();
		expected = new int[] { 0, 1, 2, 3 };
		actual = Matching.findBoyerMoore(text, pattern);
		assertArrayEquals(expected, actual);

		// test for single character (which is not a pattern)
		text = "eeeeee".toCharArray();
		pattern = "e".toCharArray();
		expected = new int[] {};
		actual = Matching.findBoyerMoore(text, pattern);
		assertArrayEquals(expected, actual);

		// test for empty text and pattern
		text = "".toCharArray();
		pattern = "".toCharArray();
		expected = new int[] {};
		actual = Matching.findBoyerMoore(text, pattern);
		assertArrayEquals(expected, actual);

		// test for empty pattern
		text = "abc".toCharArray();
		pattern = "".toCharArray();
		expected = new int[] {};
		actual = Matching.findBoyerMoore(text, pattern);
		assertArrayEquals(expected, actual);

		// test for empty text
		text = "a".toCharArray();
		pattern = "abc".toCharArray();
		expected = new int[] {};
		actual = Matching.findBoyerMoore(text, pattern);
		assertArrayEquals(expected, actual);
	}

	@Test
	void testFindBrute() {
		// test for match
		text = "abcdefg".toCharArray();
		pattern = "efg".toCharArray();
		expected = new int[] { 4 };
		actual = Matching.findBrute(text, pattern);
		assertArrayEquals(expected, actual);

		// test for one match
		text = "abc".toCharArray();
		pattern = "abc".toCharArray();
		expected = new int[] { 0 };
		actual = Matching.findBrute(text, pattern);
		assertArrayEquals(expected, actual);

		// test for multiple matches
		text = "eeeeee".toCharArray();
		pattern = "eee".toCharArray();
		expected = new int[] { 0, 1, 2, 3 };
		actual = Matching.findBrute(text, pattern);
		assertArrayEquals(expected, actual);

		// test for single character (which is not a pattern)
		text = "eeeeee".toCharArray();
		pattern = "e".toCharArray();
		expected = new int[] {};
		actual = Matching.findBrute(text, pattern);
		assertArrayEquals(expected, actual);

		// test for empty text and pattern
		text = "".toCharArray();
		pattern = "".toCharArray();
		expected = new int[] {};
		actual = Matching.findBrute(text, pattern);
		assertArrayEquals(expected, actual);

		// test for empty pattern
		text = "abc".toCharArray();
		pattern = "".toCharArray();
		expected = new int[] {};
		actual = Matching.findBrute(text, pattern);
		assertArrayEquals(expected, actual);

		// test for empty text
		text = "a".toCharArray();
		pattern = "abc".toCharArray();
		expected = new int[] {};
		actual = Matching.findBrute(text, pattern);
		assertArrayEquals(expected, actual);

	}

	@Test
	void testFindRabinKarp() {
		// test for worst case
		text = "aababcabcdabcdef".toCharArray();
		pattern = "abcdef".toCharArray();
		expected = new int[] { 10 };
		actual = Matching.findRabinKarp(text, pattern);
		assertArrayEquals(expected, actual);

		// test for match
		text = "abcdefg".toCharArray();
		pattern = "efg".toCharArray();
		expected = new int[] { 4 };
		actual = Matching.findRabinKarp(text, pattern);
		assertArrayEquals(expected, actual);

		// test for one match
		text = "abc".toCharArray();
		pattern = "abc".toCharArray();
		expected = new int[] { 0 };
		actual = Matching.findRabinKarp(text, pattern);
		assertArrayEquals(expected, actual);

		// test for multiple matches
		text = "eeeeee".toCharArray();
		pattern = "eee".toCharArray();
		expected = new int[] { 0, 1, 2, 3 };
		actual = Matching.findRabinKarp(text, pattern);
		assertArrayEquals(expected, actual);

		// test for single character (which is not a pattern)
		text = "eeeeee".toCharArray();
		pattern = "e".toCharArray();
		expected = new int[] {};
		actual = Matching.findRabinKarp(text, pattern);
		assertArrayEquals(expected, actual);

		// test for empty text and pattern
		text = "".toCharArray();
		pattern = "".toCharArray();
		expected = new int[] {};
		actual = Matching.findRabinKarp(text, pattern);
		assertArrayEquals(expected, actual);

		// test for empty pattern
		text = "abc".toCharArray();
		pattern = "".toCharArray();
		expected = new int[] {};
		actual = Matching.findRabinKarp(text, pattern);
		assertArrayEquals(expected, actual);

		// test for empty text
		text = "a".toCharArray();
		pattern = "abc".toCharArray();
		expected = new int[] {};
		actual = Matching.findRabinKarp(text, pattern);
		assertArrayEquals(expected, actual);
	}

}
