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
        ResourceBundle res = ResourceBundle.getBundle("GlobalRes");
        String msg;
        if (args.length > 0)
        {
            msg = res.getString(GlobalRes.GoodBYE);
        }
        else
        {
            msg = res.getString(GlobalRes.HELLO);
        }
        System.out.println(msg);
    }

}
