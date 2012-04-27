package test;

public class TestEnum
{
    public enum Style {
        BOLD()   { public String apply(String s) { return "<b>" + s + "</b>"; }},
        ITALIC() { public String apply(String s) { return "<i>" + s + "</i>"; }},
        RED()    { public String apply(String s) { return "<span style=\"color:#FF0000\">" + s + "</span>"; }};

        abstract public String apply(String s);
      }
    public static void main(String[] args) {
        System.out.println(Style.BOLD.getClass());
        System.out.println(Style.BOLD.getDeclaringClass());
        System.out.println(Style.ITALIC.getClass());
        System.out.println(Style.ITALIC.getDeclaringClass());

        System.out.println("");

        System.out.println(Style.values()[0]);
        System.out.println(Style.values()[1]);
        System.out.println(Style.values()[2]);
        System.out.println("");
        System.out.println(Style.class.getEnumConstants()[0]);
        System.out.println(Style.class.getEnumConstants()[1]);
        System.out.println(Style.class.getEnumConstants()[2]);

    }
}
