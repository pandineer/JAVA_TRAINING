package ex03_05;

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
        System.out.println("5 println(test) time: " + test.repeat(5));

    }

}
