/*
 * 練習問題20.2 p.454
 * TranslateByteクラスをフィルターとして書き直しなさい。
 */

/*
 * 練習問題20.1 p.444
 * TranslateByteプログラムをメソッドに書き直して、InputStreamの内容をOutputStreamに変換するようにしなさい。
 * 変換マッピングとストリームはメソッドのパラメータとして渡します。
 * 本章で述べられる各InputStreamとOutputStreamについて、その型のストリームに対して操作を行うために、変換メソッドを
 * 使用する新たなmainメソッドを書きなさい。
 * もし入力と出力で一対となるストリームならば、1つのmainメソッドで両方を扱うことができます。
 */

package ch20.ex20_04;

import java.io.*;

public class ReturnOneLine extends FilterReader
{
    public ReturnOneLine(Reader in)
    {
        super(in);
    }

    public int read() throws IOException
    {
        return super.read();
    }

    public int read(char[] buf, int offset, int count) throws IOException
    {
        return super.read(buf, offset, count);
    }

    public static void returnOneLine() throws IOException
    {
        int b;
        while ((b = System.in.read()) != -1)
        {
            System.out.write(b);
        }
    }

    public static void main(String[] args) throws IOException
    {
        try
        {
            ReturnOneLine.returnOneLine();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

}
