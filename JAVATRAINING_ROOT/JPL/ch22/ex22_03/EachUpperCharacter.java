/*
 * 練習問題22.3 p.560
 * 入力文字列に出現した文字の上位バイト（上位8ビット）ごとにBitSetオブジェクトをHashMapに保存するようにしなさい。
 * 各BitSetオブジェクトは、特定の上位バイトを持っている文字の下位バイトを保存します。
 */

package ch22.ex22_03;

import java.util.BitSet;
import java.util.HashMap;

public class EachUpperCharacter
{
    HashMap<Integer, BitSet> map = new HashMap<Integer, BitSet>();

    public EachUpperCharacter(String str)
    {
        BitSet[] tmp = new BitSet[(int)Math.pow(2, 8)];
        for (int i = 0; i < (int)Math.pow(2, 8); i++)
        {
            tmp[i] = new BitSet();
        }
        int j = 0;
        for (int i = 0; i < str.length(); i++)
        {
            for (j = 0; j < ((int) Math.pow(2, 8) - 1); j++)
            {
                if ((str.charAt(i) & j << 8) > 0 || (j == 0 && str.charAt(i) >> 8 == 0 ))
                {
                    tmp[j].set(str.charAt(i) & 255);
                    break;
                }
            }
            map.put(j, tmp[j]);
        }
    }

    public void showSpecifiedSet(int specifiedSet)
    {
        if (map.containsKey(specifiedSet))
        {
            System.out.print(specifiedSet + ": ");
            BitSet tmp = map.get(specifiedSet);
            String desc = "[";
            for (int i = tmp.nextSetBit(0); i >= 0; i = tmp.nextSetBit(i + 1))
            {
                desc += (char) (i | specifiedSet << 8);
            }
            System.out.println(desc + "]");
        }
        else
        {
            ;
        }
    }

    public static void main(String[] args)
    {
        EachUpperCharacter test = new EachUpperCharacter("!/][\test\\ªఌฬℍ");
        for (int i = 0; i < (int) Math.pow(2, 8); i++)
        {
            test.showSpecifiedSet(i);
        }
    }

}
