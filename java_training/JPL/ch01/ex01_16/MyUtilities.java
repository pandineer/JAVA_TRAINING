/*
 * 練習問題1.16 p.29
 * BadDataSetExceptionにフィールドを追加してデータの集まりの名前と、問題を通知しているI/O例外を保持できるようにしなさい。
 * それにより、その例外をキャッチしてエラーの詳細を知ることができるようになります。
 */

package ch01.ex01_16;

import java.io.FileInputStream;
import java.io.IOException;

public class MyUtilities
{
    public String DataName;
    public IOException HoldException;

    public double[] getDataSet(String setName) throws BadDataSetException
    {
        String file = setName + ".dest";
        FileInputStream in = null;
        try
        {
            in = new FileInputStream(file);
            return readDataSet(in);
        }
        catch (IOException e)
        {
            throw new BadDataSetException(setName, e);
        }
        finally
        {
            try
            {
                if (in != null)
                    in.close();
            }
            catch (IOException e)
            {
                ; // 無視：データの読み込みは成功しているか、あるいは、
                  // BadDataSetExceptionをスローしようとしている
            }
        }
    }

    // ... readDataSetの定義..
    double[] readDataSet(FileInputStream in)
    {
        double[] result =
        { 0.0, 0.1 };
        return result;
    }

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception
    {
        MyUtilities test = new MyUtilities();
        try
        {
            test.getDataSet("test");
        }
        catch (BadDataSetException e)
        {
            System.out.println("DataSet: " + e.DataName);
            System.out.println("Exception: " + e.HoldException);
        }
    }

}
