package ex09_01;

public class Infinity {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		double plus = Double.POSITIVE_INFINITY;
		double minus = Double.NEGATIVE_INFINITY;

		System.out.println("+inf + +inf: " + (plus + plus));	// Infinity
		System.out.println("+inf + -inf: " + (plus + minus));	// NaN

		System.out.println("+inf - +inf: " + (plus - plus));	// NaN
		System.out.println("+inf - -inf: " + (plus - minus));	// Infinity

		System.out.println("+inf * +inf: " + (plus * plus));	// Infinity
		System.out.println("+inf * -inf: " + (plus * minus));	// -Infinity

		System.out.println("+inf / +inf: " + (plus / plus));	// NaN
		System.out.println("+inf / -inf: " + (plus / minus));	// NaN
	}

}
