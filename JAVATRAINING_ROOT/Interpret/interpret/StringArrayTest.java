package interpret;

public class StringArrayTest
{
    private String[] stringArray;

    public StringArrayTest(String[] targetString)
    {
        stringArray = targetString;
    }

    public void showStringArray()
    {
        for (int i = 0; i < stringArray.length; i++)
        {
            System.out.println(stringArray[i]);
        }
    }
}
