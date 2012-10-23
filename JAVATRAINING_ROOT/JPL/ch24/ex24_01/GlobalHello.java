/*
 * 練習問題24.1 p.610
 * GlobalHelloを、例として挙げたロケールで動かしてみなさい。
 * ListResourceBundle、.propertiesファイル、それにResourceBundleのサブクラスを作成して、ロケールをいくつか追加しなさい。
 */

package ch24.ex24_01;

import java.util.*;

public class GlobalHello
{
    public static void main(String[] args)
    {
        // Locale.setDefault(new Locale("en", "AU"));
        // Locale.setDefault(Locale.CHINA);
        // Locale.setDefault(new Locale("ho", "GE"));
        Locale.setDefault(new Locale("te", "XT"));
        ResourceBundle res = ResourceBundle.getBundle("ch24.ex24_01.GlobalRes");
        String msg;
        if (args.length > 0)
        {
            msg = res.getString(GlobalRes.GOODBYE);
        }
        else
        {
            msg = res.getString(GlobalRes.HELLO);
        }
        System.out.println(msg);
    }

}
