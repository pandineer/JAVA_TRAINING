/*
 * 練習問題14.6 p.311
 * 15秒間隔でメッセージを表示する別のスレッドを持ち、実行開始からの経過時間を表示するプログラムを作成しなさい。
 * メッセージ表示スレッドは、時間表示スレッドから1秒経過する毎に通知されるようにしなさい。
 * 時間表示スレッドを修正することなく、7秒間隔で異なるメッセージを表示する別のスレッドを追加しなさい。
 */

package ch14.ex14_06;

import java.util.Date;

public class ShowMessage implements Runnable
{
    String message;
    long messageInterval;
    long showedTime = 0;
    ShowTime showTimeObj;

    public ShowMessage(ShowTime obj, long messageInterval, String message)
    {
        this.showTimeObj = obj;
        this.messageInterval = messageInterval;
        this.message = message;
        showedTime = new Date().getTime();
    }

    public void run()
    {
        while (true)
        {
            try
            {
                showedTime = showTimeObj.showMessage(messageInterval, message, showedTime);
            }
            catch (Exception e)
            {
                System.out.println(e);
            }
        }
    }


}
