/*
 * 練習問題1.12 p.19
 * ImprovedFibonacciを修正してprintlnで文字列を直接表示するのではなく、
 * Stringオブジェクトを作成して配列に入れるようにしてみてください。
 */

package ch01.ex01_12;

public class StringArrayImprovedFibonacci
{
    static final int START_INDEX = 9;
    static final int FINAL_INDEX = 1;

    /**
     * 偶数要素に'*'を付けて、フィボナッチ数列の最初の方の要素を表示する
     *
     * @param args
     */
    public static void main(String[] args)
    {
        int lo = 1;
        int hi = 1;
        String mark;
        String[] result = new String[100];

        result[START_INDEX] = START_INDEX + ": " + lo;
        for (int i = START_INDEX - 1; i >= FINAL_INDEX; i--)
        {
            if (hi % 2 == 0)
            {
                mark = " *";
            }
            else
            {
                mark = "";
            }
            result[i] = i + ": " + hi + mark;
            hi = lo + hi;
            lo = hi - lo;
        }

        for (int i = START_INDEX; i >= FINAL_INDEX; i--)
        {
            System.out.println(result[i]);
        }

    }

}
