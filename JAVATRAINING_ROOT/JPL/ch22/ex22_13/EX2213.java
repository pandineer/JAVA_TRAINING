/*
 * 練習問題22.13 p.573
 * StreamTokenizerのバージョンと同様に、誤って置かれた=文字を検出するように
 * 練習問題22.12の回答を拡張しなさい。
 * （ヒント：ある種のトークン間でデリミタパターンを動的に変更してみると良いかも知れません。）
 */

/*
 * 練習問題22.12 p.573
 * 467頁の属性を読むメソッドをscannerを使用して書き直しなさい。
 * この練習問題に関しては、どちらのバージョンも正しくフォーマットされた入力を受け付けるだけで
 * よいです。
 */

package ch22.ex22_13;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;

public class EX2213
{
    public static Attributed readAttrs(Reader source) throws IOException
    {
        Scanner in = new Scanner(source);
        AttributedImpl attrs = new AttributedImpl();
        Attr attr = null;
        String tmp = null;

        in.useDelimiter("#.*\\r\\n|\\r\\n");

        while(in.hasNext())
        {
            tmp = in.next();

            Scanner inner = new Scanner(tmp);
            inner.useDelimiter("=");
            attr = new Attr(inner.next());

            inner.useDelimiter("=|#.*\\r\\n|\\r\\n");
            if (inner.hasNext())
            {
                attr.setValue(inner.next());
                attrs.add(attr);
            }
            else
            {
                throw new IOException("maybe '=' is misplaced");
            }

            attr = null;
            tmp = null;
        }
        return attrs;

    }

    public static void main(String[] args)
    {
        AttributedImpl result = null;

        System.out.println("OK pattern");
        try
        {
            result = (AttributedImpl)EX2213.readAttrs(new FileReader("JPL/ch22/ex22_13/test.txt"));
            for(Attr tmp: result)
            {
                System.out.println(tmp.getName() + ": " + tmp.getValue());
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }



        result = null;
        System.out.println("NG pattern");
        try
        {
            result = (AttributedImpl)EX2213.readAttrs(new FileReader("JPL/ch22/ex22_13/bad.txt"));
            for(Attr tmp: result)
            {
                System.out.println(tmp.getName() + ": " + tmp.getValue());
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }


    }

}
