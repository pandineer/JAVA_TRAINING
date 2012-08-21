/*
 * 練習問題14.2 p.300
 * 最初のバージョンのPrintServerを修正して、本文で述べたように、スレッドの識別により
 * コンストラクタで生成されたスレッドだけがrunを実行できるようにしなさい。
 */

/*
 * PrintQueueクラスのスタブ
 */

package ch14.ex14_02;

import java.awt.PrintJob;

public class PrintQueue
{
    public void add(PrintJob job)
    {
        System.out.println("Print queue add method. ");
    }
}
