/*
 * 練習問題23.1 p.590
 * plugTogetherメソッドを作成しなさい。スレッドを使用する必要があるでしょう。
 */

package ch23.ex23_01;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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

    }

    // 2つのストリームを結合するためのメソッド
    public static void plugTogether(OutputStream out, InputStream in)
    {

    }

    public static void main(String[] args)
    {
        try
        {
            Plug.userProg("notepad.exe");
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

}
