/*
 * 練習問題13.5 p.291
 * 10進数を含む文字列を、右から3桁ごとにカンマで区切られた数に変換するメソッドを書きなさい。
 * たとえば、文字列"1543729"が与えられたら、そのメソッドは、文字列"1,543,729"を返します。
 */

package ch13.ex13_05;

public class SplitStringWithComma
{
    // 右から3桁ごとにカンマで区切る
    static String splitStringWithComma(String str)
    {
        int index = 0;
        int remainder = str.length() % 3;

        StringBuilder strbrd = new StringBuilder(str);

        if (remainder != 0)
        {
            index = remainder;
        }
        else
        {
            index = 3;
        }

        // 更新条件: 3文字 + 増えた","分の1文字
        for (; index < str.length(); index = index + 3 + 1)
        {
            strbrd.insert(index, ",");
        }

        return strbrd.toString();
    }
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        System.out.println("1543729: " + splitStringWithComma("1543729"));
        System.out.println("12: " + splitStringWithComma("12"));
        System.out.println("123: " + splitStringWithComma("123"));
        System.out.println("1234: " + splitStringWithComma("1234"));
        System.out.println("123456789: " + splitStringWithComma("123456789"));
    }

}
