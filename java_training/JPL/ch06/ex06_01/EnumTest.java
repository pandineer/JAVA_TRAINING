/*
 * 練習問題6.1 p.132
 * 週の曜日と信号機の色に対する単純なenumを定義しなさい。
 */

package ch06.ex06_01;

public class EnumTest {
	enum day
	{
		SUNDAY,
		MONDAY,
		TUESDAY,
		WEDNESDAY,
		THURSDAY,
		FRIDAY,
		SATURDAY,
	}
	enum trafficLightColor
	{
		GREEN,
		YELLOW,
		RED,
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Sunday: " + day.SUNDAY);
		System.out.println("Monday: " + day.MONDAY);
		System.out.println("Tuesday: " + day.TUESDAY);
		System.out.println("Wednesday: " + day.WEDNESDAY);
		System.out.println("Thursday: " + day.THURSDAY);
		System.out.println("Friday: " + day.FRIDAY);
		System.out.println("Sunday: " + day.SATURDAY);
		System.out.println("Green: " + trafficLightColor.GREEN);
		System.out.println("Yellow: " + trafficLightColor.YELLOW);
		System.out.println("Red: " + trafficLightColor.RED);
	}

}
