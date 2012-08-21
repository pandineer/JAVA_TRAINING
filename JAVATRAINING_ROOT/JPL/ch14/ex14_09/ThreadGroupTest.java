/*
 * スレッドグループを引数に取り、そのグループ内のスレッドとスレッドグループの階層を定期的に表示するスレッドを
 * 開始するメソッドを書きなさい。
 * そのメソッドを、様々なグループ内でいくつかの短命なスレッドを生成するプログラムでテストしなさい。
 */

package ch14.ex14_09;

public class ThreadGroupTest extends Thread
{
    int wait = 0;
    int times = 0;

    public ThreadGroupTest(ThreadGroup parent, String name, int a_wait, int a_times)
    {
        super(parent, name);

        wait = a_wait;
        times = a_times;
    }

    public void run()
    {
        for (int i = 0; i < times; i++)
        {
            try
            {
                Thread.sleep(wait);
                System.out.println("test message per " + wait + " ms. ");
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }
    }
    /**
     * @param args
     */

    /*
    // パターン1
    public static void main(String[] args)
    {
        ThreadGroup testGroup1 = new ThreadGroup("testGroup1");
        ThreadGroup testGroup1a = new ThreadGroup(testGroup1, "test1a");

        ThreadGroupTest test1 = new ThreadGroupTest(testGroup1a, "test1", 500, 20);
        ThreadGroupTest test2 = new ThreadGroupTest(testGroup1a, "test2", 600, 20);

        ThreadGroupInformation testInformation = new ThreadGroupInformation(testGroup1a);

        Thread test = new Thread(testInformation);

        test1.start();
        test2.start();
        test.start();
    }
    */

    // パターン2
    public static void main(String[] args)
    {
        ThreadGroup testGroup1 = new ThreadGroup("testGroup1");
        ThreadGroup testGroup2 = new ThreadGroup(testGroup1, "testGroup2");
        ThreadGroup testGroup3 = new ThreadGroup(testGroup2, "testGroup3");
        ThreadGroup testGroup4 = new ThreadGroup(testGroup3, "testGroup4");
        ThreadGroup testGroup5 = new ThreadGroup(testGroup4, "testGroup5");
        ThreadGroup testGroup6 = new ThreadGroup(testGroup5, "testGroup6");

        ThreadGroupTest test1_1 = new ThreadGroupTest(testGroup1, "test1_1", 500, 10);
        ThreadGroupTest test1_2 = new ThreadGroupTest(testGroup1, "test1_2", 600, 10);

        ThreadGroupTest test6_1 = new ThreadGroupTest(testGroup6, "test6_1", 550, 30);
        ThreadGroupTest test6_2 = new ThreadGroupTest(testGroup6, "test6_2", 530, 30);
        ThreadGroupTest test6_3 = new ThreadGroupTest(testGroup6, "test6_3", 510, 30);
        ThreadGroupTest test6_4 = new ThreadGroupTest(testGroup6, "test6_4", 490, 10);
        ThreadGroupTest test6_5 = new ThreadGroupTest(testGroup6, "test6_5", 470, 10);
        ThreadGroupTest test6_6 = new ThreadGroupTest(testGroup6, "test6_6", 450, 10);
        ThreadGroupTest test6_7 = new ThreadGroupTest(testGroup6, "test6_7", 430, 10);

        ThreadGroupInformation testInformation = new ThreadGroupInformation(testGroup6);

        Thread test = new Thread(testInformation);

        test1_1.start();
        test1_2.start();
        test6_1.start();
        test6_2.start();
        test6_3.start();
        test6_4.start();
        test6_5.start();
        test6_6.start();
        test6_7.start();
        test.start();
    }
}
