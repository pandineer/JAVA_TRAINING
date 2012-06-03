
public class Test
{

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        Float temp = new Float("NaN");
        System.out.println(temp.floatToRawIntBits( Float.POSITIVE_INFINITY + Float.NEGATIVE_INFINITY));
        System.out.println( Float.POSITIVE_INFINITY + Float.NEGATIVE_INFINITY);
        System.out.println(temp.floatToIntBits( Float.POSITIVE_INFINITY + Float.NEGATIVE_INFINITY));

    }

}
