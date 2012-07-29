package ch16.ex16_09;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@interface ClassInfo
{
    String created();
}

@Retention(RetentionPolicy.RUNTIME)
@interface MethodInfo
{
    String text();
}

@ClassInfo
(
        created = "Jul 28 20"
)
public class Foo
{
    public int int_variable;

    @MethodInfo
    (
            text = "test text"
    )
    public String toString()
    {
        return "!";
    }

    public int testMethod(int a)
    {
        int tmp = a;
        return tmp;
    }
}
