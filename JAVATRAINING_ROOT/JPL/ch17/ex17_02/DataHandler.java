/*
 * 練習問題17.2 p.401
 * DataHandlerを修正してlastFileも弱く保存されるようにしなさい。
 */

package ch17.ex17_02;

import java.lang.ref.*;
import java.io.File;

public class DataHandler
{
    // private File lastFile;                  // 最後に読んだファイル  // Original
    private WeakReference<File> lastFile;
    private WeakReference<byte[]> lastData; // （おそらく）最後のデータ

    Runtime rt = Runtime.getRuntime();

    byte[] readFile(File file)
    {
        byte[] data;

        // データを記憶しているか調べる
        if (file.equals(lastFile))
        {
            data = lastData.get();
            if (data != null)
            {
                return data;
            }
        }

        // 記憶していないので、読み込む
        // data = readBytesFromFile(file);
        // lastFile = file; // Original
        lastFile = new WeakReference<File>(file);
        // lastData = new WeakReference<byte []>(data);
        // eturn data;
        return null;
    }

    public byte[] readBytesFromFile(File file)
    {
        // 中身は適当
        file.toString();
        return new byte[80000000];
    }

    public void checkLastFile()
    {
        if (lastData != null)
        {
            System.out.println("lastFile has data. ");
            System.out.println(lastFile);
        }
        else
        {
            System.out.println("lastFile does not have data. ");
        }
    }

    public void garbateCollect()
    {
        rt.gc();
    }

    public void showFreeMemory()
    {
        System.out.println("Free memory is: " + rt.freeMemory());
    }

    public static void main(String[] args)
    {
        DataHandler test = new DataHandler();
        test.showFreeMemory();
        test.checkLastFile();
        test.readFile(new File("test"));
        test.showFreeMemory();
        test.checkLastFile();
        test.garbateCollect();
        test.showFreeMemory();
        int[] tmp = new int[40000000];
        test.checkLastFile();

    }

}
