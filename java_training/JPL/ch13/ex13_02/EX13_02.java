/*
 * 練習問題13.2 p.268
 * 文字列中に、特定文字列が出現する回数を数えるメソッドを書きなさい。
 */

package ch13.ex13_02;

public class EX13_02
{
    static int countSpecificString(String specificString, String targetString)
    {
        int result = 0;
        int index = 0;

        while(-1 != targetString.indexOf(specificString, index))
        {
            index = targetString.indexOf(specificString, index) + 1;
            result++;
        }

        return result;
    }

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        System.out.println(countSpecificString("abc", "abcabcabc"));
        System.out.println(countSpecificString("abcde", "abcabcabc"));
        System.out.println(countSpecificString("ab", "abcdefgababaabb"));
    }

}
