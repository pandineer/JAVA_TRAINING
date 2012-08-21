/*
 * 練習問題1.7 p.10
 * i が逆順に値が減るようにImprovedFibonacciのループを書き直しなさい
 */

package ch01.ex01_07;

public class ImprovedFibonacci
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

        System.out.println(START_INDEX + ": " + lo);
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
            System.out.println(i + ": " + hi + mark);
            hi = lo + hi;
            lo = hi - lo;
        }

    }

}
