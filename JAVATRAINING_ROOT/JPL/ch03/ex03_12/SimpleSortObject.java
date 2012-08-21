/*
 * 練習問題3.12 p.99
 * どのようなオブジェクト型もソートできる一般的なSortHarnessクラスを作成しなさい。オブジェクトの順序を比較するのに
 * <は使用できないとして、オブジェクトの順序を表す方法をどのように提供しますか。
 */

package ch03.ex03_12;

public class SimpleSortObject extends SortHarness
{
    protected void doSort()
    {

        for (int i = 0; i < getDataLength(); i++)
        {
            for (int j = i + 1; j < getDataLength(); j++)
            {
                if (compare(i, j) > 0)
                {
                    swap(i, j);
                }
            }
        }
    }
}
