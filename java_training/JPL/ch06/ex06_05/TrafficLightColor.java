/*
 * 定数固有のメソッドを使用することを推奨します。getColor()の色ごとの設定し忘れを防止できるため。
 */

package ex06_05;

public class TrafficLightColor {
	enum LightColor
	{
		GREEN
		{
			Color getColor()
			{
				return Green;
			}
		}
		,
		YELLOW
		{
			Color getColor()
			{
				return Yellow;
			}
		}
		,
		RED
		{
			Color getColor()
			{
				return Red;
			}
		}
		;

		static Color Green = new Color("Green");
		static Color Yellow = new Color("Yellow");
		static Color Red = new Color("Red");

		static Color Error = new Color("Error");

		abstract Color getColor();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(LightColor.GREEN.getColor().ColorName);
		System.out.println(LightColor.YELLOW.getColor().ColorName);
		System.out.println(LightColor.RED.getColor().ColorName);
	}

}
