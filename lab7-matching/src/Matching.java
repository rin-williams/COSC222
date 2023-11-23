import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

public class Matching {
	static int comparisonsKMP = 0;
	static int comparisonsBoyerMoore = 0;
	static int comparisonsBrute = 0;
	static int comparisonsRabinKarp = 0;

	// from geekforgeeks for Boyer Moore
	static int NO_OF_CHARS = 256;
	// from geekforgeeks for Rabin Karp
	static int d = 256;

	// ------------ START OF CODE FROM GEEKSFORGEEKS ------------ //
	// code from
	// https://www.geeksforgeeks.org/boyer-moore-algorithm-for-pattern-searching/
	// license: https://www.geeksforgeeks.org/legal/copyright-information/
	// this code is licensed under CC BY-SA 4.0
	// (https://creativecommons.org/licenses/by-sa/4.0/) so we can use it here, and
	// A utility function to get maximum of two integers
	static int max(int a, int b) {
		return (a > b) ? a : b;
	}

	// The preprocessing function for Boyer Moore's
	// bad character heuristic
	static void badCharHeuristic(char[] str, int size, int badchar[]) {

		// Initialize all occurrences as -1
		for (int i = 0; i < NO_OF_CHARS; i++)
			badchar[i] = -1;

		// Fill the actual value of last occurrence
		// of a character (indices of table are ascii and values are index of
		// occurrence)
		for (int i = 0; i < size; i++)
			badchar[(int) str[i]] = i;
	}
	// ------------ END OF CODE FROM GEEKSFORGEEKS ------------ //

	public static int[] findBoyerMoore(char txt[], char pat[]) {
		comparisonsBoyerMoore = 0;
		// base case
		if (pat.length == 0 || txt.length == 0 || pat.length > txt.length || pat.length == 1) {
			return new int[] {};
		}

		int[] indexes = new int[txt.length - pat.length + 1];
		// initialize indexes to -1 to indicate that the pattern was not found
		// so we don't get confused with the 0 index (the values default to 0)
		for (int i = 0; i < indexes.length; i++) {
			indexes[i] = -1;
		}

		// ------------ START OF CODE FROM GEEKSFORGEEKS ------------ //
		// code from
		// https://www.geeksforgeeks.org/boyer-moore-algorithm-for-pattern-searching/
		// license: https://www.geeksforgeeks.org/legal/copyright-information/
		// this code is licensed under CC BY-SA 4.0
		// (https://creativecommons.org/licenses/by-sa/4.0/) so we can use it here, and
		int m = pat.length;
		int n = txt.length;

		int badchar[] = new int[NO_OF_CHARS];

		/*
		 * Fill the bad character array by calling
		 * the preprocessing function badCharHeuristic()
		 * for given pattern
		 */
		badCharHeuristic(pat, m, badchar);

		int s = 0; // s is shift of the pattern with
					// respect to text
		// there are n-m+1 potential alignments
		int count = 0; // modified to count the number of matches and save the index
		while (s <= (n - m)) {
			int j = m - 1;

			/*
			 * Keep reducing index j of pattern while
			 * characters of pattern and text are
			 * matching at this shift s
			 */
			while (j >= 0 && pat[j] == txt[s + j]) {
				j--;
				comparisonsBoyerMoore++;
			}

			/*
			 * If the pattern is present at current
			 * shift, then index j will become -1 after
			 * the above loop
			 */
			if (j < 0) {
				indexes[count] = s; // modified to save the index of the match
				count++; // modified to count the number of matches and save the index
				comparisonsBoyerMoore++; // modified to count the number of comparisons

				/*
				 * Shift the pattern so that the next
				 * character in text aligns with the last
				 * occurrence of it in pattern.
				 * The condition s+m < n is necessary for
				 * the case when pattern occurs at the end
				 * of text
				 */
				// txt[s+m] is character after the pattern in text
				s += (s + m < n) ? m - badchar[txt[s + m]] : 1;

			}

			else
				/*
				 * Shift the pattern so that the bad character
				 * in text aligns with the last occurrence of
				 * it in pattern. The max function is used to
				 * make sure that we get a positive shift.
				 * We may get a negative shift if the last
				 * occurrence of bad character in pattern
				 * is on the right side of the current
				 * character.
				 */
				s += max(1, j - badchar[txt[s + j]]);
		}
		// ------------ END OF CODE FROM GEEKSFORGEEKS ------------ //

		// post processing
		// trim the array to the correct size (remove the -1 values)
		for (int k = 0; k < indexes.length; k++) {
			if (indexes[k] == -1) {
				indexes = Arrays.copyOf(indexes, k);
				break;
			}
		}
		return indexes;
	}

	public static int[] findKMP(char[] text, char[] pattern) {
		comparisonsKMP = 0;
		// base case
		if (pattern.length == 0 || text.length == 0 || pattern.length > text.length || pattern.length == 1) {
			return new int[] {};
		}
		int[] indexes = new int[text.length - pattern.length + 1];
		// initialize indexes to -1 to indicate that the pattern was not found
		// so we don't get confused with the 0 index (the values default to 0)
		for (int i = 0; i < indexes.length; i++) {
			indexes[i] = -1;
		}

		// ------------ START OF CODE FROM GEEKSFORGEEKS ------------ //
		// code from https://www.geeksforgeeks.org/kmp-algorithm-for-pattern-searching/
		// license: https://www.geeksforgeeks.org/legal/copyright-information/
		// this code is licensed under CC BY-SA 4.0
		// (https://creativecommons.org/licenses/by-sa/4.0/) so we can use it here, and
		// adapt it to our needs
		int M = pattern.length;
		int N = text.length;

		// create lps[] that will hold the longest
		// prefix suffix values for pattern
		int lps[] = new int[M];
		int j = 0; // index for pat[]

		// Preprocess the pattern (calculate lps[]
		// array)
		computeLPSArray(pattern, M, lps);

		int i = 0; // index for txt[]
		int count = 0; // modified to count the number of matches and save the index
		while ((N - i) >= (M - j)) {
			if (pattern[j] == text[i]) {
				j++;
				i++;
				comparisonsKMP++;
			}
			if (j == M) {
				indexes[count] = i - j; // modified to save the index of the match
				count++; // modified to count the number of matches and save the index
				j = lps[j - 1];
			}

			// mismatch after j matches
			else if (i < N
					&& pattern[j] != text[i]) {
				// Do not match lps[0..lps[j-1]] characters,
				// they will match anyway
				if (j != 0)
					j = lps[j - 1];
				else
					i = i + 1;
				comparisonsKMP++;
			}
		}
		// ------------ END OF CODE FROM GEEKSFORGEEKS ------------ //

		// post processing
		// trim the array to the correct size (remove the -1 values)
		for (int k = 0; k < indexes.length; k++) {
			if (indexes[k] == -1) {
				indexes = Arrays.copyOf(indexes, k);
				break;
			}
		}
		return indexes;

	}

	static void computeLPSArray(char[] pat, int M, int[] lps) {
		// ------------ START OF CODE FROM GEEKSFORGEEKS ------------ //
		// code from https://www.geeksforgeeks.org/kmp-algorithm-for-pattern-searching/
		// license: https://www.geeksforgeeks.org/legal/copyright-information/
		// this code is licensed under CC BY-SA 4.0
		// (https://creativecommons.org/licenses/by-sa/4.0/) so we can use it here, and
		// adapt it to our needs

		// length of the previous longest prefix suffix
		int len = 0;
		int i = 1;
		lps[0] = 0; // lps[0] is always 0

		// the loop calculates lps[i] for i = 1 to M-1
		while (i < M) {
			if (pat[i] == pat[len]) {
				len++;
				lps[i] = len;
				i++;
			} else // (pat[i] != pat[len])
			{
				// This is tricky. Consider the example.
				// AAACAAAA and i = 7. The idea is similar
				// to search step.
				if (len != 0) {
					len = lps[len - 1];

					// Also, note that we do not increment
					// i here
				} else // if (len == 0)
				{
					lps[i] = len;
					i++;
				}
			}
		}
		// ------------ END OF CODE FROM GEEKSFORGEEKS ------------ //
	}

	public static int[] findBrute(char[] text, char[] pattern) {
		// I coded this by myself since it was simple enough
		comparisonsBrute = 0;
		// base case
		if (pattern.length == 0 || text.length == 0 || pattern.length > text.length || pattern.length == 1) {
			return new int[] {};
		}

		int[] indexes = new int[text.length - pattern.length + 1];
		// initialize indexes to -1 to indicate that the pattern was not found
		// so we don't get confused with the 0 index (the values default to 0)
		for (int i = 0; i < indexes.length; i++) {
			indexes[i] = -1;
		}

		// save count of matches in the array
		int count = 0;
		// loop through the text array
		for (int i = 0; i <= text.length - pattern.length; i++) {
			int j = 0;
			while (j < pattern.length && text[i + j] == pattern[j]) {
				j++;
				comparisonsBrute++;
			}
			if (j == pattern.length) {
				indexes[count] = i;
				count++;
				comparisonsBrute++;
			}
		}

		// trim the array to the correct size (remove the -1 values)
		for (int i = 0; i < indexes.length; i++) {
			if (indexes[i] == -1) {
				indexes = Arrays.copyOf(indexes, i);
				break;
			}
		}
		return indexes;
	}

	public static int[] findRabinKarp(char[] text, char[] pattern) {
		comparisonsRabinKarp = 0;
		// base case
		if (pattern.length == 0 || text.length == 0 || pattern.length > text.length || pattern.length == 1) {
			return new int[] {};
		}

		int[] indexes = new int[text.length - pattern.length + 1];
		// initialize indexes to -1 to indicate that the pattern was not found
		// so we don't get confused with the 0 index (the values default to 0)
		for (int i = 0; i < indexes.length; i++) {
			indexes[i] = -1;
		}

		// ------------ START OF CODE FROM GEEKSFORGEEKS ------------ //
		// code from
		// https://www.geeksforgeeks.org/rabin-karp-algorithm-for-pattern-searching/
		// license: https://www.geeksforgeeks.org/legal/
		// this code is licensed under CC BY-SA 4.0
		// (https://creativecommons.org/licenses/by-sa/4.0/) so we can use it here, and

		int M = pattern.length;
		int N = text.length;
		int i, j;
		int p = 0; // hash value for pattern
		int t = 0; // hash value for txt
		int h = 1;
		int q = 101; // A prime number

		// The value of h would be "pow(d, M-1)%q"
		for (i = 0; i < M - 1; i++)
			h = (h * d) % q;

		// Calculate the hash value of pattern and first
		// window of text
		for (i = 0; i < M; i++) {
			p = (d * p + pattern[i]) % q;
			t = (d * t + text[i]) % q;
		}

		// Slide the pattern over text one by one
		int count = 0;
		for (i = 0; i <= N - M; i++) {

			// Check the hash values of current window of
			// text and pattern. If the hash values match
			// then only check for characters one by one
			if (p == t) {
				/* Check for characters one by one */
				for (j = 0; j < M; j++) {
					if (text[i + j] != pattern[j]) {
						comparisonsRabinKarp++; // modified to count the number of comparisons
						break;
					}
				}

				// if p == t and pat[0...M-1] = txt[i, i+1,
				// ...i+M-1]
				if (j == M) {
					indexes[count] = i; // modified to save the index of the match
					comparisonsRabinKarp++; // modified to count the number of comparisons
					count++; // modified to count the number of matches and save the index
				}
			}

			// Calculate hash value for next window of text:
			// Remove leading digit, add trailing digit
			if (i < N - M) {
				t = (d * (t - text[i] * h)
						+ text[i + M])
						% q;

				// We might get negative value of t,
				// converting it to positive
				if (t < 0)
					t = (t + q);
			}
		}
		// ------------ END OF CODE FROM GEEKSFORGEEKS ------------ //

		// post processing
		// trim the array to the correct size (remove the -1 values)
		for (int k = 0; k < indexes.length; k++) {
			if (indexes[k] == -1) {
				indexes = Arrays.copyOf(indexes, k);
				break;
			}
		}
		return indexes;
	}

	public static void main(String[] args) throws MalformedURLException {
		// read first 100 words from
		// https://docs.microsoft.com/en-us/dotnet/core/testing/unit-testing-best-practices
		System.out.println();
		String words = "";
		try {
			URL url = new URL("https://docs.microsoft.com/en-us/dotnet/core/testing/unit-testing-best-practices");
			java.util.Scanner s = new java.util.Scanner(url.openStream());
			int i = 0;
			while (s.hasNext() && i != 100) {
				words += s.next();
				i++;
			}
			s.close();
		} catch (java.io.IOException e) {
			System.out.println("Error reading from URL: " + e);
		}
		System.out.println("Words to compare: \n\n" + words + "\n");

		// preprocess the text
		String pat = "metaname";
		char[] text = words.toCharArray();
		char[] pattern = pat.toCharArray();

		// run the algorithms
		long startTime = System.nanoTime();
		int[] indexes = findBrute(text, pattern);
		int bruteComp = comparisonsBrute;
		long endTime = System.nanoTime();
		long timeElapsed = endTime - startTime;

		System.out.println(
				"Brute force for pattern \"" + pat + "\" occurs at index(es): \n" + Arrays.toString(indexes) + "\n"
						+ bruteComp
						+ " comparisons in "
						+ timeElapsed + " nano seconds");

		startTime = System.nanoTime();
		indexes = findKMP(text, pattern);
		int kmpComp = comparisonsKMP;
		endTime = System.nanoTime();
		timeElapsed = endTime - startTime;

		System.out.println("\nKMP for pattern \"" + pat + "\" occurs at index(es): \n" + Arrays.toString(indexes) + "\n"
				+ kmpComp
				+ " comparisons in "
				+ timeElapsed + " nano seconds");

		startTime = System.nanoTime();
		indexes = findBoyerMoore(text, pattern);
		int boyerComp = comparisonsBoyerMoore;
		endTime = System.nanoTime();
		timeElapsed = endTime - startTime;

		System.out.println(
				"\nBoyer-Moore for pattern \"" + pat + "\" occurs at index(es): \n" + Arrays.toString(indexes) + "\n"
						+ boyerComp
						+ " comparisons in "
						+ timeElapsed + " nano seconds");

		startTime = System.nanoTime();
		indexes = findRabinKarp(text, pattern);
		int rabinComp = comparisonsRabinKarp;
		endTime = System.nanoTime();
		timeElapsed = endTime - startTime;

		System.out.println(
				"\nRabin-Karp for pattern \"" + pat + "\" occurs at index(es): \n" + Arrays.toString(indexes) + "\n"
						+ rabinComp
						+ " comparisons in "
						+ timeElapsed + " nano seconds");

	}

}
