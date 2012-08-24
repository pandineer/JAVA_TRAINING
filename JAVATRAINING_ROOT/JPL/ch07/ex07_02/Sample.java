/*
 * 練習問題7.2 p.148
 * 数値である各基本データ型のフィールドを宣言しているクラスを作成して、異なるリテラル形式を使用して
 * 値を代入してみなさい。たとえば、intフィールドに3.5fを代入してみるとか。どのリテラルがどの
 * フィールド型と一緒に使用できますか。値の大きさを変えて、どのような影響があるかを調べてみなさい。
 */

package ch07.ex07_02;

public class Sample
{

    /**
     * @param args
     */
    public static void main(String[] args)
    {

        int int_temp = (int) 3.5f;
        System.out.println(int_temp); // 3 が表示される

        byte byte_temp;
        byte_temp = (byte) Short.MAX_VALUE;
        System.out.println(byte_temp); // -1 が表示される

        byte_temp = (byte) Integer.MAX_VALUE;
        System.out.println(byte_temp); // -1 が表示される

    }

}
