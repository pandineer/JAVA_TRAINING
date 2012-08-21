/*
 * 練習問題14.3 p.308
 * 現在の値を保持し、その値に加算して新たな値を表示するメソッドを持つオブジェクトのクラスを作成しなさい。
 * そのオブジェクトを生成し、複数のスレッドを生成して、各スレッドからその加算メソッドを繰り返し呼び出す
 * プログラムを作成しなさい。
 * 加算の結果が失われないようにそのクラスを作成しなさい。
 */

package ch14.ex14_03;

public class LockTest implements Runnable
{
    private int value = 0;

    public synchronized void addAndShow(int addValue)
    {
        System.out.println("     addValue is: " + addValue);
        value = value + addValue;
        System.out.println("Current value is: " + value);
    }

    public void run()
    {
        for (int i = 0; i < 3; i++)
        {
            addAndShow((int) (Math.random() * 10));
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        LockTest testObject = new LockTest();

        Thread test1 = new Thread(testObject);
        Thread test2 = new Thread(testObject);
        Thread test3 = new Thread(testObject);

        test1.start();
        test2.start();
        test3.start();
    }

}
