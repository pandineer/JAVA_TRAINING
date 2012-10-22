/*
 * 練習問題24.1 p.610
 * GlobalHelloを、例として挙げたロケールで動かしてみなさい。
 * ListResourceBundle、.propertiesファイル、それにResourceBundleのサブクラスを作成して、ロケールをいくつか追加しなさい。
 */

package ch24.ex24_01;

import java.util.ListResourceBundle;

public class GlobalRes extends ListResourceBundle
{
    public static final String HELLO = "hello";
    public static final String GOODBYE = "goodbye";

    public Object[][] getContesnts()
    {
        return contents;
    }

    private static final Object[][] contents =
        {
        {GlobalRes.HELLO, "Ciao"},
        {GlobalRes.GOODBYE, "CIao"},
        };
}
