/*
 * 練習問題6.4 p.135
 * 132頁の練習問題6.1の信号機の色のenumを修正して、各enum定数が適切なColorオブジェクトを持ち、
 * そのオブジェクトをgetColorで取り出せるようにしなさい。
 */

package ch06.ex06_04;

public class TrafficLightColor
{
    enum LightColor
    {
        GREEN, YELLOW, RED;

        static Color Green = new Color("Green");
        static Color Yellow = new Color("Yellow");
        static Color Red = new Color("Red");

        static Color Error = new Color("Error");

        public static Color getColor(LightColor lightColor)
        {
            switch (lightColor)
            {
                case GREEN:
                    return Green;
                case YELLOW:
                    return Yellow;
                case RED:
                    return Red;
                default:
                    return Error;
            }
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        System.out.println(LightColor.getColor(LightColor.GREEN).ColorName);
        System.out.println(LightColor.getColor(LightColor.YELLOW).ColorName);
        System.out.println(LightColor.getColor(LightColor.RED).ColorName);
    }

}
