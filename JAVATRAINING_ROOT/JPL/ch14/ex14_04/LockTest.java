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

package ch14.ex14_04;

public class LockTest implements Runnable
{
    private static int value = 0;

    public static synchronized void addAndShow(int addValue)
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
}
