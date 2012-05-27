/*
 * 練習問題4.1 p.113
 * 86頁のれんしゅう問題3.6の回答を、抽象クラスではなく、EnergySourceのためのインタフェースを使用して書き直しなさい。
 */

package ch04.ex04_01;

public class GasTank implements EnergySource
{
    public void empty()
    {
        System.out.println("GasTank's empty method");
    }

}
