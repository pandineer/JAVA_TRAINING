/*
 * 練習問題7.1 p.142
 * 遊びで、Unicodeエスケープシーケンスだけを使用して"Hello, World"プログラムを書きなさい。
 */

package ch07.ex07_01;

public class HelloWorld
{
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        // Unicodeエスケープシーケンスだけを使用して"Hello, World"プログラムを書く
        System.out
                .println("\u0048\u0065\u006c\u006c\u006f\u002c\u0020\u0057\u006f\u0072\u006c\u0064");
    }

}
