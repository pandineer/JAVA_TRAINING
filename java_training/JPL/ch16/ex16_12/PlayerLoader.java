/*
 * 練習問題16.12 p.388
 * 練習問題16.11の結果を修正して、findResouceとfindResourcesを実装することで、プレーヤーの戦略が
 * 付属するリソースを使用できるようにしなさい。
 */

/*
 * 練習問題16.11 p.386
 * GameとPlayerを発展させて三目並べのような簡単なゲームを実装しなさい。
 * いくつかのPlayerの実装を、それぞれ何回か実行してスコアーを取りなさい。
 */

package ch16.ex16_12;

import java.io.File;

// import java.io.FileInputStream;
// import java.io.IOException;

public class PlayerLoader extends ClassLoader
{
    /*
    public Class<?> findClass(String name) throws ClassNotFoundException
    {
        try
        {
            byte[] buf = bytesForClass(name);
            return defineClass(name, buf, 0, buf.length);
        }
        catch(IOException e)
        {
            throw new ClassNotFoundException(e.toString());
        }
    }

    // ... bytesForClassと他のメソッド...
    protected byte[] bytesForClass(String name) throws IOException, ClassNotFoundException
    {
        FileInputStream in = null;
        try
        {
            in = streamFor(name + ".class");
            int length = in.avaiable(); // バイト数を得る
            if (length == 0)
            {
                throw new ClassNotFoundException(name);
            }
            byte[] buf = new byte[length];
            in.read(buf); // バイト列を読み込む
            return buf;
        }
        finally
        {
            if (in != null)
            {
                in.close();
            }
        }
    }
    */

    @SuppressWarnings("deprecation")
    public static java.net.URL findResouce(String name)
    {
        File f = new File(name);
        if (!f.exists())
        {
            return null;
        }
        try
        {
            return f.toURL();
        }
        catch(java.net.MalformedURLException e)
        {
            return null;  // 起きるはずがない
        }
    }
}
