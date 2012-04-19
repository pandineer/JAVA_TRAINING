package ex03_11;

public class TestSort {

	static double[] testData = {
		0.3, 1.3e-2, 7.9, 3.17
	};
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SortDouble bsort = new SimpleSortDouble();
		SortMetrics metrics = bsort.sort(testData);
		System.out.println("Metrics: " + metrics);
		for (int i = 0; i < testData.length; i++)
		{
			System.out.println("\t" + testData[i]);
		}

	}

}
