/*
 * 練習問題14.1 p.297
 * mainを実行しているスレッドの名前を表示するプログラムを作成しなさい。
 */

package ch14.ex14_01;

public class NameOfMainThread
{

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        System.out.println("Current thread name is: " + Thread.currentThread().getName());
    }

}
