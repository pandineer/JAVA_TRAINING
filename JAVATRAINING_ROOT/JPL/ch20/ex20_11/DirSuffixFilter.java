/*
 * 練習問題20.11 p481
 * ディレクトリと接尾語をパラメータとして取り、その接尾語を持つすべてのファイルを表示するプログラムを
 * FilenameFilterあるいはFileFilterを使用して作成しなさい。
 */

package ch20.ex20_11;

import java.io.File;
import java.io.FilenameFilter;

public class DirSuffixFilter implements FilenameFilter
{
    private String suffix;

    public DirSuffixFilter(String suffix)
    {
        this.suffix = suffix;
    }

    @Override
    public boolean accept(File dir, String name)
    {
        return name.endsWith(suffix);
    }

    public static void main(String[] args)
    {
        File dir = new File(args[0]);
        String[] files = dir.list(new DirSuffixFilter(args[1]));
        for(String file : files)
        {
            System.out.println(file);
        }
    }
}
