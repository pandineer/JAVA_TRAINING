/*
 * 練習問題13.6 p.291
 * 区切り文字と、区切り文字間の桁数を指定するパラメータを受け付けるようにメソッドを修正しなさい。
 */

/*
 * 練習問題13.5 p.291
 * 10進数を含む文字列を、右から3桁ごとにカンマで区切られた数に変換するメソッドを書きなさい。
 * たとえば、文字列"1543729"が与えられたら、そのメソッドは、文字列"1,543,729"を返します。
 */

package ch13.ex13_06;

public class SplitStringWithComma
{
    // 右から3桁ごとにカンマで区切る
    static String splitStringWithComma(String str, String delimiter, int delimitLength)
    {
        int index = 0;
        int remainder = str.length() % delimitLength;

        StringBuilder strbrd = new StringBuilder(str);

        if (remainder != 0)
        {
            index = remainder;
        }
        else
        {
            index = delimitLength;
        }

        // 更新条件: 区切り文字間の長さ + 増えた区切り文字分の文字
        for (; index < strbrd.length(); index = index + delimitLength + delimiter.length())
        {
            strbrd.insert(index, delimiter);
        }

        return strbrd.toString();
    }
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        System.out.println("123456789: " + splitStringWithComma("123456789", ",", 3));
        System.out.println("123456789: " + splitStringWithComma("123456789", "delimiter", 4));
        System.out.println("123456789: " + splitStringWithComma("123456789", "<kugiri>", 2));
    }

}
