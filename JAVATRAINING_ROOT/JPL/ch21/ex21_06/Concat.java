/*
 * 練習問題21.6 p.542
 * 463頁のプログラムConcatを書き直して、1度に1つのFileInputStreamオブジェクトだけを
 * オープンするEnumerationの実装を使用するようにしなさい。
 */

package ch21.ex21_06;

import java.io.*;
import java.util.*;

class Concat
{
    public static void main(String[] args) throws IOException
    {
        InputStream in; // 文字を読み込むべきストリーム
        Enumeration<InputStream> files = null;
        if (args.length == 0)
        {
            in = System.in;
        }
        else
        {
            InputStream fileIn, bufIn;
            List<InputStream> inputs = new ArrayList<InputStream>(args.length);
            for (String arg : args)
            {
                fileIn = new FileInputStream(arg);
                bufIn = new BufferedInputStream(fileIn);
                inputs.add(bufIn);
            }
            // Enumeration<InputStream> files = Collections.enumeration(inputs);
            files = Collections.enumeration(inputs);
            // in = new SequenceInputStream(files);
        }
        int ch;
        while(files.hasMoreElements())
        {
            in = files.nextElement();
            while((ch = in.read()) != -1)
            {
                System.out.write(ch);
            }
        }
        System.out.flush();
    }

}
