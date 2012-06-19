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
        FileReader f;
        BufferedReader b;
        String row;
        try
        {
            System.out.println(filename);
            f = new FileReader(filename);
            b = new BufferedReader(f);
            while ((row = b.readLine()) != null)
            {
                tmp = row.split(" ");
                addArrayList(tmp[0], tmp[1]);
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public void addArrayList(String type, String value)
    {
        if (type.equals("Integer"))
        {
            arrayList.add(new Integer(value));
        }
        else if (type.equals("Short"))
        {
            arrayList.add(new Short(value));
        }
        else if (type.equals("Long"))
        {
            arrayList.add(new Long(value));
        }
        else if (type.equals("Byte"))
        {
            arrayList.add(new Byte(value));
        }
        else if (type.equals("Float"))
        {
            arrayList.add(new Float(value));
        }
        else if (type.equals("Double"))
        {
            arrayList.add(new Double(value));
        }
        else if (type.equals("Boolean"))
        {
            arrayList.add(new Boolean(value));
        }
        else if (type.equals("Character"))
        {
            arrayList.add(new Character(value.toCharArray().clone()[0]));
        }
        else
        {
            throw new IllegalArgumentException("Error in addArrayList");
        }
    }

    public void showAllArray()
    {
        for (int i = 0; i < arrayList.size(); i++)
        {
            System.out.println("Class: " + arrayList.get(i).getClass().getName() + ", value: " + arrayList.get(i));
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        Input test = new Input();
        test.inputToArray("JPL/ch13/ex13_04/input_data.dat");
        test.showAllArray();
    }

}
