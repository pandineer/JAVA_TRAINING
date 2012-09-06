/*
 * 練習問題20.3 p.454
 * バイトを何らかの値とXORするなど、どのようなアルゴリズムでも良いので、
 * バイトを暗号化する一組のFilterストリームクラスであるDecryptInputStreamとEncryptOutputStreamを
 * 作成しなさい。
 * DecryptInputStreamは、EncryptOutputStreamクラスが生成したバイトを復号化します。
 */

package ch20.ex20_03;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

public class DecryptInputStream extends FilterReader
{

    protected DecryptInputStream(Reader in)
    {
        super(in);
    }

    public int read() throws IOException
    {
        int c = super.read();
        if (c != -1)
        {
            return c^7;
        }
        else
        {
            return -1;
        }
    }

    public static void main(String[] args) throws IOException
    {
        StringReader src = new StringReader("test");
        FilterReader f = new DecryptInputStream(src);
        int c;
        while ((c = f.read()) != -1)
        {
            System.out.print((char)c);
        }
        System.out.println();
    }
}
