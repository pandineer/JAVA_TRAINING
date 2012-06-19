/*
 * 練習問題13.4 p.274
 * "type value"形式の行を持つ入力文字列を読み込むプログラムを書きなさい。
 * typeは、ラッパークラス名(Boolean, Characterなど)の１つであり、valueはその型のコンストラクタが変換できる文字列です。
 * 各行に対して、指定された値を持つ、指定された型のオブジェクトを生成してArrayListに追加しなさい(ArrayList
 * については、512頁の「ArrayList」を参照)。全ての行が読みこまれたら、最終結果を表示しなさい。
 * 各行は、単純に改行文字'\n'で終了していると想定しなさい。
 */

package ch13.ex13_04;

import java.io.*;
import java.util.ArrayList;

public class Input
{
    ArrayList<Object> arrayList = new ArrayList<Object>();
    public void inputToArray(String filename)
    {
        String[] tmp = new String[2]; // 分割された一行が入る
        try
        {
            // System.out.println(new File(".").getAbsolutePath());  // デバッグ用 カレントディレクトリを表示する
            FileReader f = new FileReader(filename);
            BufferedReader b = new BufferedReader(f);
            String row;
            while((row = b.readLine()) != null)
            {
                // System.out.println(row); // デバッグ用
                tmp = row.split(" ");
                // System.out.println(tmp[0]); // デバッグ用
                // System.out.println(tmp[1]); // デバッグ用

                arrayList.add(new String("test")); // TODO: ジェネリックス使って、指定したオブジェクトを返す関数作ったらいいかな？
                System.out.println(arrayList.get(0) instanceof String);
                System.out.println(arrayList.get(0) instanceof Integer);
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
            System.out.println("ファイル読み込み失敗");
        }
    }
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        Input test = new Input();
        test.inputToArray("JPL/ch13/ex13_04/input_data.dat");
    }

}
