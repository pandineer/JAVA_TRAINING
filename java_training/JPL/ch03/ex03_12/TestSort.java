/*
 * 練習問題3.12 p.99
 * どのようなオブジェクト型もソートできる一般的なSortHarnessクラスを作成しなさい。オブジェクトの順序を比較するのに
 * <は使用できないとして、オブジェクトの順序を表す方法をどのように提供しますか。
 */

package ch03.ex03_12;

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
