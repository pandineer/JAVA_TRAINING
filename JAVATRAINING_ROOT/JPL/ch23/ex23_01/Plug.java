/*
 * 練習問題23.1 p.590
 * plugTogetherメソッドを作成しなさい。スレッドを使用する必要があるでしょう。
 */

package ch23.ex23_01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;

public class Plug
{
    static Thread inToOut;
    static Thread outToIn;

    // 親プロセスの標準ストリームを子プロセスの標準ストリームに結びつけます
    // その結果、ユーザがタイプした文字は指定されたプログラムへ送られて、
    // プログラムの出力はユーザに見えるようになります
    public static Process userProg(String cmd) throws IOException
    {
        Process proc = Runtime.getRuntime().exec(cmd);
        plugTogether(System.in, proc.getOutputStream());
        plugTogether(System.out, proc.getInputStream());
        plugTogether(System.err, proc.getErrorStream());
        return proc;
    }

    // 2つのストリームを結合するためのメソッド
    public static void plugTogether(InputStream in, OutputStream out)
    {
        class PlugThread implements Runnable
        {
            // int b;
            String s;
            InputStream in;
            BufferedReader br;
            OutputStream out;
            BufferedWriter bw;
            PlugThread(InputStream a_in, OutputStream a_out)
            {
                in = a_in;
                out = a_out;
                br = new BufferedReader(new InputStreamReader(in));
                bw = new BufferedWriter(new OutputStreamWriter(out));
            }
            public void run()
            {
                while(true)
                {
                    try
                    {
                        // while ((b = in.read()) != -1)
                        while ((s = br.readLine()) != null)
                        {
                            // out.write(b);
                            bw.write(s);
                            System.out.println(s);
                        }
                    }
                    catch(Exception e)
                    {
                        System.out.println(e);
                    }
                }
            }
        }

        PlugThread plugThread= new PlugThread(in, out);
        inToOut = new Thread(plugThread);
        inToOut.start();
    }

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
                while(true)
                {
                    try
                    {
                        while((line = br.readLine()) != null)
                        {
                            out.println(line);
                        }
                    }
                    catch(Exception e)
                    {
                        out.println(e);
                    }
                }
            }
        }

        PlugThread plugThread = new PlugThread(out, in);
        outToIn = new Thread(plugThread);
        outToIn.start();
    }

    public static void main(String[] args)
    {
        try
        {
            // Plug.userProg("ping -n 3 127.0.0.1");
            // Plug.userProg("sort.exe");
            // Plug.userProg("notepad.exe");
            Plug.userProg("cmd.exe");
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

}
