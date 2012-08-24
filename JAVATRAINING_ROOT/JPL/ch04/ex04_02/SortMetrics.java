/*
 * 練習問題4.2 p.114
 * 99頁の練習問題3.12の回答を、最初にインタフェースを使用して書いていなければ、
 * インタフェースを使用して書き直しなさい。
 */

package ch04.ex04_02;

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
