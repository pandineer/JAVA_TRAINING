package ex01_14;

public class walkman3rd extends Walkman2nd {
	private String customer;

	public void communication(String who)
	{
		customer = who;
	}

	public void showCustomer()
	{
		System.out.println("Communication customer: " + customer);
	}

	public static void main(String[] args) {
		walkman3rd test = new walkman3rd();

		test.listener("Bob");
		test.showListener();

		test.listen2nd("Jason");
		test.showListener2ne();

		test.communication("communication cutomer");
		test.showCustomer();
	}

}
