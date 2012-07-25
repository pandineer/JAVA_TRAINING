/*
 * 練習問題16.3 p.359
 * すべての宣言されているメンバーとすべての継承されているpublicのメンバーに関する情報を表示するように
 * ClassContesntsを修正しなさい。
 * 同じ物を2度表示しないようにしなさい。
 */

package ch16.ex16_03;

import java.lang.reflect.*;

public class ClassContents
{
    public static void main(String[] args)
    {
        try
        {
            Class<?> c = Class.forName(args[0]);
            System.out.println(c);
            printMembers(c.getFields());
            printMembers(c.getConstructors());
            printMembers(c.getMethods());
        }
        catch(ClassNotFoundException e)
        {
            System.out.println("unknown class: " + args[0]);
        }
    }

    // TODO: 現状はテキスト通り。これから練習問題の回答を実装する必要あり
    private static void printMembers(Member[] mems)
    {
        for (Member m : mems)
        {
            if (m.getDeclaringClass() == Object.class)
            {
                continue;
            }
            String decl = m.toString();
            System.out.print(" ");
            System.out.println(strip(decl, "java.lang."));
        }
    }

    public static String strip(String source, String removeTarget)
    {
        return source.replaceAll(removeTarget, "");
    }

}
