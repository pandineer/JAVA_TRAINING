/*
 * 練習問題22.12 p.573
 * 467頁の属性を読むメソッドをscannerを使用して書き直しなさい。
 * この練習問題に関しては、どちらのバージョンも正しくフォーマットされた入力を受け付けるだけで
 * よいです。
 */

package ch22.ex22_12;

import java.io.FileReader;
import java.io.Reader;
import java.util.Scanner;

public class EX2212
{
    public static Attributed readAttrs(Reader source)
    {
        Scanner in = new Scanner(source);
        AttributedImpl attrs = new AttributedImpl();
        Attr attr = null;

        in.useDelimiter("=|#.*\\r\\n|\\r\\n");

        while(in.hasNext())
        {
            attr = new Attr(in.next());
            attr.setValue(in.next());
            attrs.add(attr);
            attr = null;
        }
        return attrs;

    }

    public static void main(String[] args)
    {
        AttributedImpl result = null;
        try
        {
            result = (AttributedImpl)EX2212.readAttrs(new FileReader("JPL/ch22/ex22_12/test.txt"));
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        for(Attr tmp: result)
        {
            System.out.println(tmp.getName() + ": " + tmp.getValue());
        }
    }

}
