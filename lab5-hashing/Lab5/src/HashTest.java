import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HashTest {
	Hashing h = new Hashing();

	@Test
	public void testHashPolynomial() {
		String s = "hello";
		int a = 31;
		assertEquals(s.hashCode(), h.hashPolynomial(s, a));
		a = 30;
		assertNotEquals(s.hashCode(), h.hashPolynomial(s, a));
	}

	@Test
	public void testCompressionMAD() {
		Hashing hashing = new Hashing();
		hashing.setAlphaVal(3);
		hashing.setBetaVal(7);
		hashing.setnBuckets(1000);
		hashing.setNextPrime(1009);

		assertEquals(10, hashing.compressionMAD(1));
		assertEquals(13, hashing.compressionMAD(2));
		assertEquals(16, hashing.compressionMAD(3));
		assertEquals(19, hashing.compressionMAD(4));
	}

	@Test

	public void testIsPrime() {
		assertTrue(h.isPrime(1009));
		h.setnBuckets(1000);
		assertEquals(1009, h.findNextPrime(h.getnBuckets()));
	}

}
