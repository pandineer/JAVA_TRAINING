/*
 * 練習問題10.5 p.207
 * ２つのcharを引数に取り、それらの文字とそれらの文字間の文字を表示するメソッドを書きなさい。
 */

package ch10.ex10_05;

public class ShowBetweenChars
{
    static void showBetweenChars(char start, char end)
    {
        if (end < start)
        {
            char tmp = start;
            start = end;
            end = tmp;
        }

        System.out.println("start: " + start);
        for (int i = 1; (start + i) < end; i++)
        {
            System.out.println("between: " + (char) (start + i));
        }
        System.out.println("end: " + end);
    }

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        showBetweenChars('a', 'e');
        System.out.println("");
        showBetweenChars('9', '5');

    }

}
