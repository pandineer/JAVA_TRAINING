/*
 * 練習問題20.10 p.480
 * 入力ファイルを単語に分解して、そのファイル内で各単語の出現数を数えて表示するために
 * StreamTokenizerオブジェクトを使用するプログラムを作成しなさい。
 * 単語とその出現数を管理するためにHashMapを使用しなさい。
 */

package ch20.ex20_10;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class CheckWordFrequency
{
    public static void checkWordFrequency(Reader source) throws IOException
    {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        StreamTokenizer in = new StreamTokenizer(source);
        int count = 0;
        while(in.nextToken() != StreamTokenizer.TT_EOF)
        {
            if (map.containsKey(in.sval))
            {
                count = map.get(in.sval);
            }
            else
            {
                count = 0;
            }
            count++;
            map.put(in.sval, count);
        }

        // Mapの要素をすべて表示する
        Set<Map.Entry<String, Integer>> entrySet = map.entrySet();
        Iterator<Map.Entry<String, Integer>> it = entrySet.iterator();
        while(it.hasNext())
        {
            Map.Entry<String, Integer> entry = it.next();
            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println(key + ": " + value);
        }
    }

    public static void main(String[] args)
    {
        try
        {
            CheckWordFrequency.checkWordFrequency(new FileReader("JPL/ch20/ex20_10/test.txt"));
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}
