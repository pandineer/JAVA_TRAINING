/*
 * 練習問題20.3 p.454
 * バイトを何らかの値とXORするなど、どのようなアルゴリズムでも良いので、
 * バイトを暗号化する一組のFilterストリームクラスであるDecryptInputStreamとEncryptOutputStreamを
 * 作成しなさい。
 * DecryptInputStreamは、EncryptOutputStreamクラスが生成したバイトを復号化します。
 */

package ch20.ex20_03;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilterReader;
import java.io.FilterWriter;
import java.io.Writer;

public class EncryptOutputStream extends FilterWriter
{
    protected EncryptOutputStream(Writer out)
    {
        super(out);
    }

    public void write(int c)
    {
        try
        {
            super.write(c^7);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    public static void main(String[] args)
    {
        FileWriter filewriter = null;
        try
        {
            filewriter = new FileWriter("JPL/ch20/ex20_03/test.txt");
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        EncryptOutputStream test = new EncryptOutputStream(filewriter);
        test.write('a');
        test.write('b');
        test.write('c');
        try
        {
            test.flush();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

        FileReader src = null;
        try
        {
            src = new FileReader("JPL/ch20/ex20_03/test.txt");
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        FilterReader f = new DecryptInputStream(src);
        int c;
        try
        {
            while ((c = f.read()) != -1)
            {
                System.out.print((char)c);
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        System.out.println();
    }
}
