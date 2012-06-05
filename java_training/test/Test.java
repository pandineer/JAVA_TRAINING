
public class Test
{

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        System.out.println(Float.floatToRawIntBits( Float.POSITIVE_INFINITY + Float.NEGATIVE_INFINITY));
        System.out.println( Float.POSITIVE_INFINITY + Float.NEGATIVE_INFINITY);
        System.out.println(Float.floatToIntBits( Float.POSITIVE_INFINITY + Float.NEGATIVE_INFINITY));
        System.out.println('"' + "aaa");
    }

}
