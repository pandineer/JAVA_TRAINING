package ex01_16;

import java.io.FileInputStream;
import java.io.IOException;

class BadDataSetException extends Exception {}

public class MyUtilities {
	public String DataName;
	public IOException HoldException;
	public double [] getDataSet(String setName) throws BadDataSetException
	{
		String file = setName + ".dest";
		FileInputStream in = null;
		try
		{
			in = new FileInputStream(file);
			return readDataSet(in);
		} catch (IOException e) {
			DataName = setName;
			HoldException = e;
			throw new BadDataSetException();
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (IOException e) {
				; // 無視：データの読み込みは成功しているか、あるいは、
				  // BadDataSetExceptionをスローしようとしている
			}
		}
	}

	// ... readDataSetの定義..
	double[] readDataSet(FileInputStream in)
	{
		double[] result = {0.0, 0.1};
		return result;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws BadDataSetException {
		MyUtilities test = new MyUtilities();

		try
		{
			test.getDataSet("test");
		}
		finally
		{
			System.out.println(test.DataName);
			System.out.println(test.HoldException);
		}

	}

}
