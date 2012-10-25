/*
 * 練習問題24.3 p.621
 * 文字列引数を取り、その文字列を解析して日付に変換して、その日付を可能なすべてのスタイルで
 * 表示するプログラムを書きなさい。
 * 日付の解析はどの程度寛大ですか。
 */

package ch24.ex24_03;


import java.text.DateFormat;
import java.text.Format;
import java.util.Date;

public class DateParseShow
{
    public static void showDateAllFormat(String source)
    {
        System.out.println("Source String: " + source);
        try
        {
            System.out.println("Parse sucess!");
            Date date = DateFormat.getDateInstance().parse(source);
            System.out.println("SHORT");
            Format fmt = DateFormat.getDateInstance(DateFormat.SHORT);
            System.out.println(fmt.format(date));
            System.out.println("MEDIUM");
            fmt = DateFormat.getDateInstance(DateFormat.MEDIUM);
            System.out.println(fmt.format(date));
            System.out.println("LONG");
            fmt = DateFormat.getDateInstance(DateFormat.LONG);
            System.out.println(fmt.format(date));
            System.out.println("FULL");
            fmt = DateFormat.getDateInstance(DateFormat.FULL);
            System.out.println(fmt.format(date));
        }
        catch(Exception e)
        {
            System.out.println("This String can not be parsed!");
            System.out.println(e);
        }
        System.out.println("");
    }
    public static void main(String[] args)
    {
        DateParseShow.showDateAllFormat("2012/10/8");
        DateParseShow.showDateAllFormat("12/10/8");
        DateParseShow.showDateAllFormat("10/8");
        DateParseShow.showDateAllFormat("2012.10.8");
        DateParseShow.showDateAllFormat("2012.Oct.8");
        DateParseShow.showDateAllFormat("2012.October.8");
    }

}
