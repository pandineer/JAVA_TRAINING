/*
 * 練習問題23.1 p.590
 * plugTogetherメソッドを作成しなさい。スレッドを使用する必要があるでしょう。
 */

package ch23.ex23_02;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.io.PrintStream;

public class Plug
{
    static public Thread outToIn;
    static public boolean threadRun = true;
    static int count = 1;

    // 2つのストリームを結合するためのメソッド
    public static void plugTogether(PrintStream out, InputStream in)
    {
        class PlugThread implements Runnable
        {
            PrintStream out;
            BufferedReader br;
            String line;
            PlugThread(PrintStream a_out, InputStream a_in)
            {
                out = a_out;
                br = new BufferedReader(new InputStreamReader(a_in));
            }
            public void run()
            {
                while(threadRun)
                {
                    try
                    {
                        while((line = br.readLine()) != null)
                        {
                            System.out.print(count++ + ": ");
                            out.println(line);
                        }
                    }
                    catch(Exception e)
                    {
                        out.println(e);
                        break;
                    }
                }
            }
        }

        PlugThread plugThread = new PlugThread(out, in);
        outToIn = new Thread(plugThread);
        outToIn.start();
    }
}
