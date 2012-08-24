/*
 * 練習問題9.1 p.177
 * ２つの無限大のオペランドに対して演算子+, -, *, /を使用するプログラムを作成して、結果を表示しなさい。
 * どちらも同じ符号の値と、異なる符号の値の両方を試しなさい。
 */

package ch09.ex09_01;

public class Infinity
{
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        double plus = Double.POSITIVE_INFINITY;
        double minus = Double.NEGATIVE_INFINITY;

        System.out.println("+inf + +inf: " + (plus + plus)); // Infinity
        System.out.println("+inf + -inf: " + (plus + minus)); // NaN

        System.out.println("+inf - +inf: " + (plus - plus)); // NaN
        System.out.println("+inf - -inf: " + (plus - minus)); // Infinity

        System.out.println("+inf * +inf: " + (plus * plus)); // Infinity
        System.out.println("+inf * -inf: " + (plus * minus)); // -Infinity

        System.out.println("+inf / +inf: " + (plus / plus)); // NaN
        System.out.println("+inf / -inf: " + (plus / minus)); // NaN
    }

}
