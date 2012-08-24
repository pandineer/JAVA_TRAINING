/*
 * 練習問題14.6 p.311
 * 15秒間隔でメッセージを表示する別のスレッドを持ち、実行開始からの経過時間を表示するプログラムを作成しなさい。
 * メッセージ表示スレッドは、時間表示スレッドから1秒経過する毎に通知されるようにしなさい。
 * 時間表示スレッドを修正することなく、7秒間隔で異なるメッセージを表示する別のスレッドを追加しなさい。
 */

package ch14.ex14_06;

import java.util.Date;

public class ShowTime implements Runnable
{
    long currentTime = 0;
    long showedTime = 0;
    long elapsedTime = 0;

    public ShowTime()
    {
        currentTime = new Date().getTime();
    }

    public void run()
    {
        while (true)
        {
            showTime();
        }
    }

    public synchronized void showTime()
    {
        // synchronized (this)
        {
            currentTime = new Date().getTime();
            if (currentTime - showedTime > 1000)
            {
                showedTime = currentTime;
                elapsedTime = elapsedTime + 1000;
                System.out.println(elapsedTime / 1000
                        + " seconds have elapsed. ");
                notifyAll();
            }
        }
    }

    public synchronized long showMessage(long messageInterval, String message, long showedTime) throws InterruptedException
    {
        while ((new Date().getTime() - showedTime) < messageInterval)
        {
            wait();
        }
        System.out.println(message);
        return (new Date().getTime());
    }

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        ShowTime testShowTime = new ShowTime();
        Thread testThreadShowTime = new Thread(testShowTime);
        testThreadShowTime.start();

        ShowMessage testShowMessage15 = new ShowMessage(testShowTime, 15 * 1000, "message per 15 seconds");
        Thread testThreadShowMessage15 = new Thread(testShowMessage15);
        testThreadShowMessage15.start();

        ShowMessage testShowMessage7 = new ShowMessage(testShowTime, 7 * 1000, "message per 7 seconds");
        Thread testThreadShowMessage7 = new Thread(testShowMessage7);
        testThreadShowMessage7.start();
    }

}
