/*
 * 練習問題21.2 p.522
 * 400頁のDataHandlerクラスを書きなおして、１つのWeakReferenceの代わりにWeakHashMapを使用して
 * 返されたデータを保持するようにしなさい。
 */

package ch21.ex21_02;

import java.util.WeakHashMap;
import java.io.File;

public class DataHandler
{
    // private File lastFile;                  // 最後に読んだファイル
    // private WeakReference<byte[]> lastData; // （おそらく）最後のデータ
    private WeakHashMap<Integer, byte[]> savedData;

    byte[] readFile(File file)
    {
        byte[] data;

        // データを記憶しているか調べる
        // if ((file.equals(lastFile)))
        if (savedData.containsKey(file.hashCode()))
        {
            // data = lastData.get();
            data = savedData.get(file.hashCode());
            if (data != null)
            {
                return data;
            }
        }

        // 記憶していないので、読み込む
        data = readBytesFromFile(file);
        // lastFile = file;
        // lastData = new WeakReference<byte[]>(data);
        savedData.put(file.hashCode(), data);
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
