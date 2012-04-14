package ex01_16;

import java.io.IOException;

class BadDataSetException extends Exception
{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	String DataName;
	IOException HoldException;
	BadDataSetException(String a, IOException e)
	{
		DataName = a;
		HoldException = e;
	}
}