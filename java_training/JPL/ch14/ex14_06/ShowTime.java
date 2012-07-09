package ch14.ex14_06;

import java.util.Date;

public class ShowTime implements Runnable
{
    long currentTime = 0;
    long showedTime = 0;
    long elapsedTime = 0;

    public void run()
    {
        while (true)
        {
            currentTime = new Date().getTime();
            if (currentTime - showedTime > 1000)
            {
                showedTime = currentTime;
                elapsedTime = elapsedTime + 1000;
                System.out.println(elapsedTime / 1000
                        + " seconds have elapsed. ");
            }
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        ShowTime testShowTime = new ShowTime();
        Thread testThread = new Thread(testShowTime);
        testThread.start();

        // TODO: ShowMessageオブジェクトをコンストラクタに引数渡しながら生成して、スレッドを生成する
    }

}
