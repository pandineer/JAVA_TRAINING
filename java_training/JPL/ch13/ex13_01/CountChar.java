/*
 * 練習問題13.1 p.267
 * 文字列中に、指定された文字が出現する回数を数えるメソッドを書きなさい。
 */

package ch13.ex13_01;

public class CountChar
{

    static int countSpecificChar(char ch, String str)
    {
        int result = 0;
        for (int i = 0; i < str.length(); i++)
        {
            if (ch == str.charAt(i))
            {
                result++;
            }
        }
        return result;
    }

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        System.out.println(countSpecificChar('a', "aaabbbccc"));
        System.out.println(countSpecificChar('a', "abbabcccadddaeeeeeea"));
        System.out.println(countSpecificChar('a', "bcdefg"));
    }

}
