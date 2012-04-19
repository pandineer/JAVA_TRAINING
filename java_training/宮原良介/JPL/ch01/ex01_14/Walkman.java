package ex01_14;

public class Walkman {
	private String listener;

	public void listener(String who)
	{
		listener = who;
	};

	public void showListener()
	{
		System.out.println("Walkman listener: " + listener);
	}

}
