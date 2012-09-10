package ch20.ex20_06;

public class Attribute
{
    private String name;
    public double value = 0;

    public Attribute(String givenName)
    {
        name = givenName;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public double getValue()
    {
        return value;
    }

    public void setValue(double value)
    {
        this.value = value;
    }
}
