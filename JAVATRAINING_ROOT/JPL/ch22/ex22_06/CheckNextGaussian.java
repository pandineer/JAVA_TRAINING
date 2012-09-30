/*
 * 練習問題22.6 p.565
 * nextGaussianを検査するプログラムを作成して、膨大な数の呼び出し結果をグラフ（*文字の棒グラフ）で表示しなさい。
 */

package ch22.ex22_06;

import java.util.HashMap;
import java.util.Random;

public class CheckNextGaussian
{
    public static void main(String[] args)
    {
        Random rnd = new Random();
        HashMap<Double, Integer> map = new HashMap<Double, Integer>();
        double tmp = 0.0;
        int trialNumber = 100000;

        for (int i = 0; i < trialNumber; i++)
        {
            tmp = rnd.nextGaussian();
            for (double j = -2.1; j < 2.0; j = j + 0.2)
            {
                if (j < tmp && tmp <= j + 0.2)
                {
                    if (map.containsKey(j))
                    {
                        map.put(j, map.get(j) + 1);
                    }
                    else
                    {
                        map.put(j, 1);
                    }
                }
            }
            tmp = 0.0;
        }

        for(double i = -2.1; i < 2.0; i = i + 0.2)
        {
            System.out.printf("%+3f - %+3f, %4d: ", i, i + 0.2, map.get(i));
            for(int j = 0; j < (int)(map.get(i) / (double)trialNumber * 100.0); j++)
            {
                System.out.print("*");
            }
            System.out.println("");
        }
    }

}
