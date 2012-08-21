/*
 * 練習問題3.12 p.99
 * どのようなオブジェクト型もソートできる一般的なSortHarnessクラスを作成しなさい。オブジェクトの順序を比較するのに
 * <は使用できないとして、オブジェクトの順序を表す方法をどのように提供しますか。
 */

package ch03.ex03_12;

final class SortMetrics implements Cloneable
{
    public long probeCnt, // 単純なデータの値調査
            compareCnt, // 2つの要素の比較
            swapCnt; // 2つの要素の交換

    public void init()
    {
        probeCnt = swapCnt = compareCnt = 0;
    }

    public String toString()
    {
        return probeCnt + " probes " + compareCnt + " compares " + swapCnt
                + " swaps";
    }

    /** このクラスは、cloneをサポートしている */
    public SortMetrics clone()
    {
        try
        {
            // デフォルトの仕組みで十分
            return (SortMetrics) super.clone();
        }
        catch (CloneNotSupportedException e)
        {
            // 起こり得ない。このクラスとObjectは複製できる
            throw new InternalError(e.toString());
        }
    }

}
