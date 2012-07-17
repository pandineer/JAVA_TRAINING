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
    public static void main(String[] args)
    {
        ThreadGroup testGroup1 = new ThreadGroup("testGroup1");
        ThreadGroup testGroup1a = new ThreadGroup(testGroup1, "test1a");

        ThreadGroupTest test1 = new ThreadGroupTest(testGroup1a, "test1", 500, 20);

        ThreadGroupInformation testInformation = new ThreadGroupInformation(testGroup1a);
    }
}
