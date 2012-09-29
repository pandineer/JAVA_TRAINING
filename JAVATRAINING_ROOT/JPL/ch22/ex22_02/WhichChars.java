/*
 * 練習問題22.2 p.560
 * WhichCharsクラスは、Unicode範囲の上位の文字を記録するのに問題を抱えています。
 * それは、上位のもm時は、下位の範囲に関して多くの使用されないビットを残してしまうことです。
 * 出現した文字ごとにCharacterオブジェクトをHashSetに保存することで、この問題を解決しなさい。
 */

package ch22.ex22_02;

// import java.util.BitSet;
import java.util.HashSet;
import java.util.Iterator;

public class WhichChars
{
    // private BitSet used = new BitSet();
    private HashSet<Character> used = new HashSet<Character>();

    public WhichChars(String str)
    {
        for (int i = 0; i < str.length(); i++)
        {
            // used.set(str.charAt(i)); // 文字に対応したビットを設定
            used.add(str.charAt(i));
        }
    }

    public String toString()
    {
        String desc = "[";
        // for(int i = used.nextSetBit(0); i >= 0; i = used.nextSetBit(i+1))
        Iterator<Character> it = used.iterator();
        while(it.hasNext())
        {
            // desc += (char) i;
            desc += it.next();
        }
        return desc + "]";
    }

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        WhichChars test = new WhichChars("test");
        System.out.println(test.toString());
    }

}
