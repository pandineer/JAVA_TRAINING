/*
 * 練習問題20.7 p.473
 * 第3章のAttrクラスへ、DataOutputStreamにオブジェクトの内容を書き込むメソッドを追加しなさい。
 * また、DataInputStreamから状態を読み込むコンストラクタを追加しなさい。
 */

/*
 * 練習問題3.7 p.87
 * ColorAttrに対してequalsとhashCodeをオーバーライドしなさい。
 */

package ch20.ex20_07;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class Attr
{
    private final String name;
    private Object value = null;

    public Attr(String name)
    {
        this.name = name;
    }

    public Attr(String name, Object value)
    {
        this.name = name;
        this.value = value;
    }

    public Attr(String name, DataInputStream in)
    {
        this.name = name;
        try
        {
            this.value = in.readUTF();
        }
        catch(Exception e)
        {
            System.out.println("input data constructor: " + e);
        }
    }

    public String getName()
    {
        return name;
    }

    public Object getValue()
    {
        return value;
    }

    public Object setValue(Object newValue)
    {
        Object oldVal = value;
        value = newValue;
        return oldVal;
    }

    public String toString()
    {
        return name + "='" + value + "'";
    }

    public void outputData(String file)
    {
        try
        {
            OutputStream fout = new FileOutputStream(file);
            DataOutputStream out = new DataOutputStream(fout);
            out.writeUTF(value.toString());
        }
        catch(Exception e)
        {
            System.out.println("outputData: " + e);
        }
    }

    public static void main(String[] args)
    {
        Attr test1 = new Attr("testName", "testValue");
        System.out.println(test1);
        test1.outputData("JPL/ch20/ex20_07/test.dat");

        try
        {
            InputStream fin = new FileInputStream("JPL/ch20/ex20_07/test.dat");
            DataInputStream in = new DataInputStream(fin);
            Attr test2 = new Attr("testName2", in);
            System.out.println(test2);
        }
        catch(Exception e)
        {
            System.out.println("input data: " + e);
        }
    }
}
