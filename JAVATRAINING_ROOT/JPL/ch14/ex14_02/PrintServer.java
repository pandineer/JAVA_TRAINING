/*
 * 練習問題14.2 p.300
 * 最初のバージョンのPrintServerを修正して、本文で述べたように、スレッドの識別により
 * コンストラクタで生成されたスレッドだけがrunを実行できるようにしなさい。
 */

package ch14.ex14_02;

import java.awt.PrintJob;

public class PrintServer implements Runnable
{
    private final PrintQueue requests = new PrintQueue();
    private String threadName = Thread.currentThread().getName();

    public PrintServer()
    {
        Thread tmp = new Thread(this);
        threadName = tmp.getName();
        tmp.start();
    }

    public void print(PrintJob job)
    {
        requests.add(job);
    }

    public void run()
    {
        if (Thread.currentThread().getName().equals(threadName))
        {
            for (int i = 0; i < 3; i++)
                realPrint();
            // realPrint(requests.remove());
        }
        else
        {
            System.out.println("Thread is incorrect");
            System.out.println("Expected thread name is: " + threadName);
            System.out.println("Current thread name is: " + Thread.currentThread().getName());
        }
    }

    /*
     * private void realPrint(PrintJob job) { // 印刷の実際の処理を行う }
     */
    private void realPrint()
    {
        // テスト実施のための偽物のrealPrintメソッド
        System.out.println("realPrint");
    }

    public static void main(String[] args)
    {
        PrintServer test = new PrintServer();
        test.run();
    }

}
