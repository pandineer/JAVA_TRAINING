/*
 * スレッドグループを引数に取り、そのグループ内のスレッドとスレッドグループの階層を定期的に表示するスレッドを
 * 開始するメソッドを書きなさい。
 * そのメソッドを、様々なグループ内でいくつかの短命なスレッドを生成するプログラムでテストしなさい。
 */

package ch14.ex14_09;

public class ThreadGroupInformation implements Runnable
{
    ThreadGroup targetThreadGroup;

    public ThreadGroupInformation(ThreadGroup argument)
    {
        targetThreadGroup = argument;
    }

    public void run()
    {
        while(true)
        {
            try
            {
                Thread.sleep(3000);
                showThreadGroupInformation();
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }
    }

    public int getThreadGroupDepth()
    {
        int result = 0;
        while(null != targetThreadGroup.getParent())
        {
            result++;
        }

        return result;
    }

    public void showThreadGroupInformation()
    {
        System.out.println("The number of thread: " + targetThreadGroup.activeCount());
        System.out.println("Thread group depth: " + getThreadGroupDepth());
    }
}
