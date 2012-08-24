/*
 * 練習問題4.2 p.114
 * 99頁の練習問題3.12の回答を、最初にインタフェースを使用して書いていなければ、
 * インタフェースを使用して書き直しなさい。
 */

package ch04.ex04_02;

public class TestSort
{
    static Object[] testData =
    { "b", "a", "d", "c" };

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        SortHarness bsort = new SimpleSortObject();
        SortMetrics metrics = bsort.sort(testData);
        System.out.println("Metrics: " + metrics);
        for (int i = 0; i < testData.length; i++)
        {
            System.out.println("\t" + testData[i]);
        }

    }

}
