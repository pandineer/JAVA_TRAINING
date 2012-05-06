package test;

public class Test implements Cloneable{

	/**
	 * @param args
	 */

    public final Test clone()
    {
        return new Test();
    }
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Object test = "a";
		System.out.println("!");
		System.out.println(test.toString());
	}

}
