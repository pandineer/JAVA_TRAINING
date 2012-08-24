/*
 * 練習問題9.4 p.192
 * この章で学んだ事柄を使用して、実際にコードを書かないで、次の式のどれが正しくないかを判断しなさい。
 * そして、正しい式であれば、その型と値が何であるか考えなさい。
 */

package ch09.ex09_04;

public class Confirm
{
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        System.out.println(3 << 2L - 1);

        System.out.println((3L << 2) - 1);

        System.out.println(10 < 12 == 6 > 17);

        System.out.println(10 << 12 == 6 >> 17);

        System.out.println(13.5e-1 % Float.POSITIVE_INFINITY);

        System.out.println(Float.POSITIVE_INFINITY + Double.NEGATIVE_INFINITY);

        System.out.println(Double.POSITIVE_INFINITY - Float.NEGATIVE_INFINITY);

        System.out.println(0.0 / -0.0 == -0.0 / 0.0);

        System.out.println(Integer.MAX_VALUE + Integer.MIN_VALUE);

        System.out.println(Long.MAX_VALUE + 5);

        System.out.println((short) 5 * (byte) 10);

        int i = 5;
        System.out.println((i < 15 ? 1.72e3f : 0));
        i = 20;
        System.out.println((i < 15 ? 1.72e3f : 0));

        i = 3;
        System.out.println(i++ + i++ + --i);
    }

}
