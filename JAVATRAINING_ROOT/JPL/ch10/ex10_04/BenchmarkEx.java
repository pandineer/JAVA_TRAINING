/*
 * 練習問題10.4 p.205
 * 今までの練習問題からforループを使用した問題を数題選んで、whileループを使用して書き直しなさい。
 * do-whileループを使用しても書きなおすことができますか。そのように書きなおしたりしますか。しないとしたら、なぜですか。
 */

/*
 * 練習問題3.5 p.86
 * 他のベンチマークを行う新たな拡張したクラスを作成しなさい。たとえば、０からパラメータとして渡された値まで
 * ループするのに要する時間を計るとか。
 */

package ch10.ex10_04;

public class BenchmarkEx extends Benchmark
{
    void benchmark()
    {
        System.out.println("test");
    }

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        BenchmarkEx test = new BenchmarkEx();
        System.out.println("5 println(test) time: " + test.repeat(0));

    }

}
