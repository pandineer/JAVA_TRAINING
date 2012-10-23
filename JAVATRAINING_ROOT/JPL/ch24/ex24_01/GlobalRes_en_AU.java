/*
 * 練習問題24.1 p.610
 * GlobalHelloを、例として挙げたロケールで動かしてみなさい。
 * ListResourceBundle、.propertiesファイル、それにResourceBundleのサブクラスを作成して、ロケールをいくつか追加しなさい。
 */

package ch24.ex24_01;

import java.util.ListResourceBundle;

public class GlobalRes_en_AU extends ListResourceBundle
{
    // 親バンドルの英語ロケールとほとんど同じ

    public Object[][] getContents()
    {
        return contents;
    }

    private static final Object[][] contents =
        {
        {GlobalRes.HELLO, "G'day"},
        };
}
