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
        data = readBytesFromFile(file);
        // lastFile = file; // Original
        lastFile = new WeakReference<File>(file);
        lastData = new WeakReference<byte []>(data);
        return data;
    }

    public byte[] readBytesFromFile(File file)
    {
        // 中身は適当
        file.toString();
        return new byte[5];
    }

    public static void main(String[] args)
    {
        // テスト…？
    }

}
