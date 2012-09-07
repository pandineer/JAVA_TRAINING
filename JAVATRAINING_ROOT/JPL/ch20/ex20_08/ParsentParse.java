/*
 * 練習問題20.8 p.476
 * %%で始まる行で分割されているエントリーを持つファイルを読み込み、各エントリーの開始位置を持つ
 * テーブルファイルを作成するプログラムを書きなさい。
 * そして、そのテーブルを使用してランダムにエントリーを表示するプログラムを作成しなさい。
 * （579頁の「MathとStrictMath」で説明されているMath.randomメソッドを参照）。
 */

package ch20.ex20_08;

import java.io.RandomAccessFile;

public class ParsentParse
{
    public static void createParseTableFile()
    {
        RandomAccessFile input = null;
        RandomAccessFile output = null;
        int current = 0;
        int previous = 0;

        try
        {
            input = new RandomAccessFile("JPL/ch20/ex20_08/test_input.txt", "r");
            output = new RandomAccessFile("JPL/ch20/ex20_08/test_output.txt", "rw");
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        try
        {
            while ((current = input.read()) != -1)
            {
                if (previous == '%' && current == '%')
                {
                    output.write(Byte.valueOf(String.valueOf(input.getFilePointer())));
                }
                previous = current;
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        try
        {
            input.close();
            output.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public static void showRandomEntry()
    {
        RandomAccessFile input = null;
        RandomAccessFile entry = null;
        int start = 0;
        int end = 0;

        try
        {
            input = new RandomAccessFile("JPL/ch20/ex20_08/test_output.txt", "r");
            entry = new RandomAccessFile("JPL/ch20/ex20_08/test_input.txt", "r");
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

        try
        {
            input.seek((int)(Math.random()*input.length()));
            start = input.read();
            end = input.read();
            if (end == -1)
            {
                end = (int)entry.length();
            }
            else
            {
                end = end - 2;
            }

        }
        catch(Exception e)
        {
            System.out.println(e);
        }

        try
        {
            entry.seek(start);
            for (int i = 0; i < (end - start); i++)
            {
                System.out.print(String.valueOf((char)entry.read()));
            }
        }
        catch(Exception e)
        {
            System.out.println("show" + e);
        }



    }

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        ParsentParse.createParseTableFile();
        ParsentParse.showRandomEntry();
    }
}
