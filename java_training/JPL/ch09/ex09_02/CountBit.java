/*
 * 練習問題9.2 p.183
 * ビット操作演算子だけを使用し（すなわち、Integer.bitCountを使用しないで）、渡されたintで、1となっているビット数を
 * 調べるメソッドを作成しなさい。作成した解答と公開されているアルゴリズムを比較しなさい。
 * 公開されているアルゴリズムの１つに関しては、670頁の「一般的なプログラミング技法」の関連する書籍を参照してください。
 */

package ch09.ex09_02;

public class CountBit
{
    static int countBit(int target)
    {
        int count = 0;
        for(; target != 0; target = target >> 1)
        {
            if ((target & 1) == 1)
            {
                count++;
            }
        }
        return count;
    }

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        System.out.println("countBit(1): " + countBit(1));
        System.out.println("countBit(2): " + countBit(2));
        System.out.println("countBit(4): " + countBit(4));
        System.out.println("countBit(3): " + countBit(3));
        System.out.println("countBit(5): " + countBit(5));
        System.out.println("countBit(7): " + countBit(7));
        System.out.println("countBit(15): " + countBit(15));

    }

}
