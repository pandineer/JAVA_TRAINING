/*
 * 練習問題22.1 p.557
 * 浮動小数点値の配列と、何列使用するかを指定する数字を受け取り、配列の内容を表示するメソッドを書きなさい。
 * 各列のエントリーが綺麗に整列することを保証するようにしなさい。
 * 1行は80文字と想定してください。
 */

package ch22.ex22_01;

public class DecimalArrayAlignment
{
    public static void showDecimalArrayAlignment(double[] targetDouble, int rowNumber)
    {
        if (targetDouble.length < rowNumber)
        {
            System.out.println("Size error. ");
            return;
        }

        for (int i = 0; i < rowNumber; i++)
        {
            System.out.printf("%080.40f %n", targetDouble[i]);
        }
    }

    public static void main(String[] args)
    {
        double[] testDoubleArray = new double[6];
        testDoubleArray[0] = 0.2;
        testDoubleArray[1] = 50000.5;
        testDoubleArray[2] = 10000.0000000000005;
        testDoubleArray[3] = 0.0000000000000000000000000000000001;
        testDoubleArray[4] = 200000000000000000.0;
        testDoubleArray[5] = 1.1;
        DecimalArrayAlignment.showDecimalArrayAlignment(testDoubleArray, 5);
    }

}
