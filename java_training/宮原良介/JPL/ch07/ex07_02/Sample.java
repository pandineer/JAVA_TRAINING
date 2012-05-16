package ex07_02;

public class Sample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int int_temp = (int)3.5f;
		System.out.println(int_temp);	// 3 が表示される

		byte byte_temp;
		byte_temp = (byte)Short.MAX_VALUE;
		System.out.println(byte_temp);	// -1 が表示される

		byte_temp = (byte)Integer.MAX_VALUE;
		System.out.println(byte_temp);	// -1 が表示される

	}

}
