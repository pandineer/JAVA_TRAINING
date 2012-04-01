package ex01_14;

public class Walkman2nd extends Walkman {
	private String listener2nd;

	public void listen2nd(String who)
	{
		listener2nd = who;
	}

	public void showListener2ne()
	{
		System.out.println("Walkman 2nd listener: " + listener2nd);
	}

}
