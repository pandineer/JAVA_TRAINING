/*
 * 練習問題3.11 p.99
 * ソートアルゴリズムが、気づかれることなくメトリックスに関して不正を行えるSortDoubleのセキュリティホールを
 * 少なくとも１つ見つけなさい。そのセキュリティホールをなくすように修正しなさい。
 * この場合、ソートアルゴリズムの作成者はmainを書かないと想定してください
 */

package ch03.ex03_11;

public class SimpleSortDouble extends SortDouble
{
    static int count = 0;
    double tmp[] =
    { 0.0, 0.1 };

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
        // sortを終えた後に関係ないデータを用いてもう一度ソートを行うと、
        // カウンタが上書きされてしまう
        if (count == 0)
        {
            count = 1;
            sort(tmp);
        }
    }
}
