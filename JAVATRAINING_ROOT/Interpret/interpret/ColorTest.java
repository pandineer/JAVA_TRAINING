package interpret;

import java.awt.Color;

public class ColorTest
{
    private final Color colorField;

    public ColorTest(Color ar_color)
    {
        colorField = ar_color;
    }

    public ColorTest(Color[] ar_color)
    {
        System.out.println("1");
        colorField = ar_color[0];
        System.out.println("2");
    }

    public ColorTest(Character[] aaa)
    {
        colorField = Color.black;
        System.out.println(aaa);
        System.out.println(String.valueOf(aaa));
    }

    public ColorTest(String[] bbb)
    {
        colorField = Color.black;
        for(int i = 0; i < bbb.length; i++)
        {

            System.out.println(bbb[i]);
        }
    }

    private String returnColorNum()
    {
        return(colorField.toString());
    }

    public static void main(String[] args)
    {
        ColorTest test = new ColorTest(new Color(1, 1, 1));
        System.out.println(test.returnColorNum());
    }
}
