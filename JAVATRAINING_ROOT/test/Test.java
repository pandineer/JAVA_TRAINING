import java.util.regex.Pattern;


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

        System.out.println("");

        // p.273
        String regex = "a*b";
        String str = "abc adb";
        String repStr = "e";

        System.out.println(Pattern.compile(regex));
        System.out.println(Pattern.compile(regex).matcher(str));
        System.out.println(Pattern.compile(regex).matcher(str).replaceFirst(repStr));
        System.out.println(Pattern.compile(regex).matcher(str).matches());

        System.out.println();
        System.out.println(System.in.getClass());
    }

}
