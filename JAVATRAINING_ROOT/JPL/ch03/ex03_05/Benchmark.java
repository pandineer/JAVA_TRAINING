/*
 * 練習問題3.5 p.86
 * 他のベンチマークを行う新たな拡張したクラスを作成しなさい。たとえば、０からパラメータとして渡された値まで
 * ループするのに要する時間を計るとか。
 */

package ch03.ex03_05;

public abstract class Benchmark
{
    abstract void benchmark();

    public final long repeat(int count)
    {
        long start = System.nanoTime();
        for (int i = 0; i < count; i++)
        {
            benchmark();
        }
        return (System.nanoTime() - start);
    }

}
