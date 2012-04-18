package ex03_12;

public class TestSort {

	static Object[] testData = {
		"b", "a", "d", "c"
	};
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SortHarness bsort = new SimpleSortObject();
		SortMetrics metrics = bsort.sort(testData);
		System.out.println("Metrics: " + metrics);
		for (int i = 0; i < testData.length; i++)
		{
			System.out.println("\t" + testData[i]);
		}

	}

}
