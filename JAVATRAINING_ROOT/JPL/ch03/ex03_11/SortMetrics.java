/*
 * 練習問題3.11 p.99
 * ソートアルゴリズムが、気づかれることなくメトリックスに関して不正を行えるSortDoubleのセキュリティホールを
 * 少なくとも１つ見つけなさい。そのセキュリティホールをなくすように修正しなさい。
 * この場合、ソートアルゴリズムの作成者はmainを書かないと想定してください
 */

package ch03.ex03_11;

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
