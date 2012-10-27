/*
 * 練習問題22.10 p.573
 * スキャナーのデリミタの一部としてコメントパターンを使用して、
 * コメントを無視して入力をトークンに分解するメソッドを書きなさい。
 */

package ch22.ex22_10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.util.Scanner;

public class ScannerWithCommentDelimiter
{
    // #コメントを無視して、半角スペースか改行で分解する
    public static void splitSourceWithDelimiter(Reader source)
    {
        Scanner in = new Scanner(source);
        in.useDelimiter(" |#.*\\r\\n|\\r\\n");
        while(in.hasNext())
        {
            System.out.println(in.next());
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        try
        {
            splitSourceWithDelimiter(new BufferedReader(new FileReader("JPL/ch22/ex22_10/test.txt")));
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

}
