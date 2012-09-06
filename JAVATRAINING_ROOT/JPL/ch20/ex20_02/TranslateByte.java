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

package ch20.ex20_02;

import java.io.*;

public class TranslateByte extends FilterReader
{
    private byte from;
    private byte to;

    public TranslateByte(Reader in, String fromString, String toString)
    {
        super(in);

        from = (byte)fromString.charAt(0);
        to   = (byte)toString.charAt(0);
    }

    public int read() throws IOException
    {
        int b = super.read();
        return (b == from ? to : b);
    }

    public int read(char[] buf, int offset, int count) throws IOException
    {
        int nread = super.read(buf, offset, count);
        int last = offset + nread;
        for (int i = offset; i < last; i++)
        {
            buf[i] = (buf[i] == (char)from ? (char)to : buf[i]);
        }
        return nread;
    }

    public static void translateByte(String fromString, String toString) throws IOException
    {
        InputStream in;
        FileOutputStream outFile = new FileOutputStream("JPL/ch20/ex20_01/output.txt");
        OutputStreamWriter out = new OutputStreamWriter(outFile, "UTF-8");

        in = System.in;

        byte from = (byte) fromString.charAt(0);
        byte to   = (byte) toString.charAt(0);
        int b;
        // while ((b = System.in.read()) != -1)
        while((b = in.read()) != -1)
        {
            out.write(b == from ? to : b);
            out.flush();
        }
        out.close();
    }

    public static void main(String[] args) throws IOException
    {
        StringReader src = new StringReader("abc");
        FilterReader f = new TranslateByte(src, "b", "B");
        int c;
        while((c = f.read()) != -1)
        {
            System.out.print((char)c);
        }
        System.out.println();
    }

}
