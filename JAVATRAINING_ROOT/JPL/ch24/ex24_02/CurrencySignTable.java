/*
 * 練習問題24.2 p.611
 * ６つの異なるロケールと６つの異なる通貨を選択して、各ロケールで各通貨に対する通貨記号を示す表を表示しなさい。
 */

package ch24.ex24_02;

import java.util.Currency;
import java.util.Locale;

public class CurrencySignTable
{
    public static void showCurrencySymbol()
    {
        System.out.println("JAPAN\t: " + Currency.getInstance(Locale.JAPAN).getSymbol());
        System.out.println("CHINE\t: " + Currency.getInstance(Locale.CHINA).getSymbol());
        System.out.println("GERMANY\t: " + Currency.getInstance(Locale.GERMANY).getSymbol());
        System.out.println("KOREA\t: " + Currency.getInstance(Locale.KOREA).getSymbol());
        System.out.println("UK\t: " + Currency.getInstance(Locale.UK).getSymbol());
        System.out.println("US\t: " + Currency.getInstance(Locale.US).getSymbol());
        System.out.println("");
    }

    public static void main(String[] args)
    {
        Locale.setDefault(Locale.JAPAN);
        System.out.println("Locale is JAPAN");
        showCurrencySymbol();
        Locale.setDefault(Locale.CHINA);
        System.out.println("Locale is CHINA");
        showCurrencySymbol();
        Locale.setDefault(Locale.GERMANY);
        System.out.println("Locale is GERMANY");
        showCurrencySymbol();
        Locale.setDefault(Locale.KOREA);
        System.out.println("Locale is KOREA");
        showCurrencySymbol();
        Locale.setDefault(Locale.UK);
        System.out.println("Locale is UK");
        showCurrencySymbol();
        Locale.setDefault(Locale.US);
        System.out.println("Locale is US");
        showCurrencySymbol();
    }

}
