public class Hashing {

	public int nextPrimeOfNBuckets = -1;
	private int nBuckets = -1;
	private int betaVal = -1;
	private int alphaVal = -1;
	private int nextPrime = -1;

	public int getNextPrime() {
		return nextPrime;
	}

	public void setAlphaVal(int alphaVal) {
		this.alphaVal = alphaVal;
	}

	public void setBetaVal(int betaVal) {
		this.betaVal = betaVal;
	}

	public void setnBuckets(int nBuckets) {
		this.nBuckets = nBuckets;
	}

	public int getAlphaVal() {
		return alphaVal;
	}

	public int getBetaVal() {
		return betaVal;
	}

	public int getnBuckets() {
		return nBuckets;
	}

	public void setNextPrime(int nextPrime) {
		this.nextPrime = nextPrime;
	}

	// Provided for comparison only; feel free not to use
	long hashJava(String s) {
		return s.hashCode();
	}

	long hashPolynomial(String s, int a) {
		long hashVal = 0;
		int k = s.length();
		for (int i = 0; i < k; i++) {
			hashVal += s.charAt(i) * ((long) Math.pow(a, k - i - 1));
		}
		return hashVal;
	}

	int findNextPrime(int num) {
		int next = num + 1;
		while (true) {
			if (isPrime(next)) {
				nextPrimeOfNBuckets = next;
				return next;
			}
			next++;
		}
	}

	boolean isPrime(int numTest) {
		if (numTest <= 1) {
			return false;
		}
		for (int i = 2; i <= numTest / 2; i++) {
			if ((numTest % i) == 0) {
				return false;
			}
		}
		return true;
	}

	long compressionMAD(long i) {
		// c(i) = [(α i + β) mod p] mod N
		// Recall that N is the size of the bucket array, p is a prime number larger
		// than N, α is a positive integer, and β is an integer.
		// α and β must be in the interval [0,p-1]
		// N = 1.2 * NumberOfWords (round to nearest 1,000)
		int alpha = getAlphaVal();
		int beta = getBetaVal();
		int N = getnBuckets();
		int p = getNextPrime();
		if (alpha == -1 || beta == -1 || N == -1 || p == -1) {
			// throw no alpha beta N exception
			throw new IllegalArgumentException(
					"alpha, beta, N, and nextPrimeOfNBuckets must be set before compressionMAD is called");
		}
		N = (int) (Math.round((nBuckets * 1.2) / 1000.0) * 1000);
		return (((alpha * i) + beta) % p) % N;
	}

}
