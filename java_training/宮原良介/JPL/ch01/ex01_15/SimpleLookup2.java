package ex01_15;

public class SimpleLookup2 implements ExtendedLookup {

	static final int MAX = 4;

	private String[] names;
	private Object[] values;

	private int count;
	@Override
	public Object find(String name) {
		for (int i = 0; i < names.length; i++)
		{
			if (names[i].equals(name))
			{
				return values[i];
			}
		}
		return null;	// 見つからなかった
	}

	@Override
	public void add(String name) {
		if (this.count < MAX)
		{
			names[count++] = name;
		}
		else
		{
			System.out.println("add error: 規定人数に達しています");
		}

	}

	@Override
	public void remove() {
		if (0 < this.count)
		{
			names[--count] = null;
		}
		else
		{
			System.out.println("remove error: 誰も登録されていません");
		}
	}

	public void showCurrentName()
	{
		if (0 < this.count)
		{
			System.out.println(this.names[this.count - 1]);
		}
		else
		{
			System.out.println("show error: 誰も登録されていません");
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		SimpleLookup2 test = new SimpleLookup2();

		test.names = new String[MAX];
		test.showCurrentName();
		test.add("Bob");
		test.showCurrentName();
		test.add("Jason");
		test.showCurrentName();
		test.add("Banana");
		test.showCurrentName();
		test.add("Panda");
		test.showCurrentName();
		test.add("hoge");
		test.showCurrentName();
		for (int i = 0; i < 5; i++)
		{
			test.remove();
			test.showCurrentName();
		}
	}

}
