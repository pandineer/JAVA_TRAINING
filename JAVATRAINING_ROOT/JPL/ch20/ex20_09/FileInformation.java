/*
 * 練習問題20.9 p.480
 * 1つかそれ以上のパス名を渡されて、それが表すファイルについて得られるすべての情報を表示する
 * メソッドを書きなさい。
 */

package ch20.ex20_09;

import java.io.File;
import java.util.Calendar;

public class FileInformation
{

    public static void showFileInformation(String[] filePath)
    {
        File file;
        Calendar cal = Calendar.getInstance();

        for (int i = 0; i < filePath.length; i++)
        {
            file = new File(filePath[i]);
            System.out.println("For first file: ");
            System.out.println("  getName(): " + file.getName());
            System.out.println("  getPath(): " + file.getPath());
            System.out.println("  getAbsolutePath(): " + file.getAbsolutePath());
            try
            {
                System.out.println("  getCanonicalPath(): " + file.getCanonicalPath());
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
            System.out.println("  getParent: " + file.getParent());
            System.out.println("  exists: " + file.exists());
            System.out.println("  canRead: " + file.canRead());
            System.out.println("  canWrite: " + file.canWrite());
            System.out.println("  isFile: " + file.isFile());
            System.out.println("  isDirectory: " + file.isDirectory());
            System.out.println("  isAbsolute: " + file.isAbsolute());
            System.out.println("  isHidden: " + file.isHidden());
            cal.setTimeInMillis(file.lastModified());
            System.out.println("  lastModifiled: " + cal.get(Calendar.YEAR) + " " + cal.get(Calendar.MONTH) + " " + cal.get(Calendar.DATE) + " " + cal.get(Calendar.HOUR_OF_DAY) + " " + cal.get(Calendar.MINUTE) + " " + cal.get(Calendar.SECOND));
            System.out.println("  length: " + file.length());
        }
    }

    public static void main(String[] args)
    {
        String[] filePathArray = {"JPL/ch20/ex20_09/FileInformation.java", "JPL/ch20/ex20_09/test.txt", "JPL/ch20/ex20_09/non_exist"};
        FileInformation.showFileInformation(filePathArray);
    }

}
