/*
 * 練習問題20.1 p.444
 * TranslateByteプログラムをメソッドに書き直して、InputStreamの内容をOutputStreamに変換するようにしなさい。
 * 変換マッピングとストリームはメソッドのパラメータとして渡します。
 * 本章で述べられる各InputStreamとOutputStreamについて、その型のストリームに対して操作を行うために、変換メソッドを
 * 使用する新たなmainメソッドを書きなさい。
 * もし入力と出力で一対となるストリームならば、1つのmainメソッドで両方を扱うことができます。
 */

package ch20.ex20_01;

import java.io.*;

public class TranslateByte
{
    // TODO: InputStreamの内容をOutputStreamに変換する
    public static void translateByte(String fromString, String toString) throws IOException
    {
        InputStream in;

        in = System.in;

        byte from = (byte) fromString.charAt(0);
        byte to   = (byte) toString.charAt(0);
        int b;
        // while ((b = System.in.read()) != -1)
        while((b = in.read()) != -1)
        {
            System.out.write(b == from ? to : b);
        }
    }

    public static void main(String[] args)
    {
        try
        {
            TranslateByte.translateByte("b", "B");
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

}
