/*
 * 練習問題23.1 p.590
 * plugTogetherメソッドを作成しなさい。スレッドを使用する必要があるでしょう。
 */

package ch23.ex23_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;

public class Plug
{

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
        int b;
        // InputStreamReader ir = new InputStreamReader(in);
        try
        {
            while ((b = in.read()) != -1)
            // for (int i = 0; i < 5; i++)
            {
                out.write(b);
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    // 2つのストリームを結合するためのメソッド
    public static void plugTogether(PrintStream out, InputStream in)
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String line;
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

    public static void main(String[] args)
    {
        try
        {
            // Plug.userProg("ping -n 3 127.0.0.1");
            Plug.userProg("sort.exe");
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

}
