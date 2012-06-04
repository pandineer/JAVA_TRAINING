/*
 * 練習問題1.13 p.20
 * printlnではなく、printfを使用して、ImprovedFibonacciを書き直しなさい。
 */

package ch01.ex01_13;

public class PrintfImprovedFibonacci
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

        System.out.printf("%d: %2d%n", START_INDEX, lo);
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
            System.out.printf("%d: %2d%s%n", i, hi, mark);
            hi = lo + hi;
            lo = hi - lo;
        }

    }

}
