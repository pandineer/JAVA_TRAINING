/*
 * 練習問題14.5 p.308
 * 練習問題14.4のコードを修正して、staticの同期されたメソッドを使用しないでスレッドが安全に
 * 値を減算できるようにしなさい。
 */

/*
 * 練習問題14.4 p.308
 * 練習問題14.3を修正してstaticデータとstaticメソッドを使用するようにしなさい。
 */

/*
 * 練習問題14.3 p.308
 * 現在の値を保持し、その値に加算して新たな値を表示するメソッドを持つオブジェクトのクラスを作成しなさい。
 * そのオブジェクトを生成し、複数のスレッドを生成して、各スレッドからその加算メソッドを繰り返し呼び出す
 * プログラムを作成しなさい。
 * 加算の結果が失われないようにそのクラスを作成しなさい。
 */

package ch14.ex14_05;

public class TestMain implements Runnable
{
    public void run()
    {
        for (int i = 0; i < 3; i++)
        {
            synchronized (LockTest.class)
            {
                LockTest.addAndShow((int) (Math.random() * 5));
            }
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        TestMain test = new TestMain();

        Thread test1 = new Thread(test);
        Thread test2 = new Thread(test);
        Thread test3 = new Thread(test);

        test1.start();
        test2.start();
        test3.start();
    }

}
