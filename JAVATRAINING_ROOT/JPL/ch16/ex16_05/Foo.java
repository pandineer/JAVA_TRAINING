package ch16.ex16_05;

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
    @MethodInfo
    (
            text = "test text"
    )
    public String toString()
    {
        return "!";
    }
}
