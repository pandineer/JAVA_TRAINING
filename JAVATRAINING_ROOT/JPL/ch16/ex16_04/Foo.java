package ch16.ex16_04;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@interface ClassInfo
{
    String created();
}

@ClassInfo
(
        created = "Jul 28 20"
)
public class Foo
{
    @Override
    public String toString()
    {
        return "!";
    }
}
