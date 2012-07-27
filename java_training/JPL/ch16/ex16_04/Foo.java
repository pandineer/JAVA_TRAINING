package ch16.ex16_04;

@interface ClassInfo
{
    String created();
}

@ClassInfo
(
        created = "Jan 31 2005"
)
public class Foo
{
    @Override
    public String toString()
    {
        return "!";
    }
}
