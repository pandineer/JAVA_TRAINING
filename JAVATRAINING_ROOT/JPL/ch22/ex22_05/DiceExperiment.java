/*
 * 練習問題22.5 p.565
 * 6面のサイコロの個数が指定されると、可能性のある合計値ごとの理論的確率を計算できます。
 * たとえば、2個の6面サイコロでは、合計が7になる確率は、1/6です。
 * プログラムを作成して、特定の個数の6面サイコロでの合計値の理論的分布を、
 * 1から6までの数を発生するRandomを使用して膨大な数のサイコロを「振った」結果と比較しなさい。
 * どの乱数発生メソッドを使用するかは、問題となりますか。
 */

package ch22.ex22_05;

import java.util.Random;

public class DiceExperiment
{
    public static void main(String[] args)
    {
        int diceNumber = 2;     // サイコロの数
        int trialNumber = 10000;  // （膨大な）試行回数
        int[] actualResult = new int[diceNumber * 6 + 1];   // 可能性のある合計値ごとのカウント値

        Random rnd = new Random();

        double[] probability2 = {0.0, 0.0, 1.0/36.0, 2.0/36.0, 3.0/36.0, 4.0/36.0, 5.0/36.0, 6.0/36.0, 5.0/36.0, 4.0/36.0, 3.0/36.0, 2.0/36.0, 1.0/36.0};

        int tmp = 0;

        for (int i = 0; i < trialNumber; i++)
        {
            tmp = 0;
            for (int j = 0; j < diceNumber; j++)
            {
                tmp += (rnd.nextInt(6) + 1);
            }
            actualResult[tmp]++;
        }


        for (int i = diceNumber; i <= diceNumber * 6; i++)
        {
            if (diceNumber == 2)
            {
                System.out.printf("Number: %3d, Probability: %4f, Actual result: %4f%n", i, probability2[i], (double)actualResult[i]/(double)trialNumber);
            }
            else
            {
                System.out.printf("Number: %3d, Probability: ??, Actual result: %4f", i, actualResult[i]);
            }
        }

    }
}
