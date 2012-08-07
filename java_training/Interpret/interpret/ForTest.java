package interpret;

public class ForTest
{
    public int intValue;
    public String stringValue;

    private final int intPFValue;
    private final String stringPFValue;

    public ForTest()
    {
        intPFValue = 0;
        stringPFValue = "default";
    }

    public int getIntValue()
    {
        return intValue;
    }

    public void setIntValue(int intValue)
    {
        this.intValue = intValue;
    }

    public String getStringValue()
    {
        return stringValue;
    }

    public void setStringValue(String stringValue)
    {
        this.stringValue = stringValue;
    }

    public int getIntPFValue()
    {
        return intPFValue;
    }

    public String getStringPFValue()
    {
        return stringPFValue;
    }

    public ForTest(int ar_int)
    {
        intPFValue = ar_int;
        stringPFValue = "default";
    }

    public ForTest(int ar_int, String ar_string)
    {
        intPFValue = ar_int;
        stringPFValue = ar_string;
    }




}
