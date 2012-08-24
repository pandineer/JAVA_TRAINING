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
    private static String[] memberName = new String[100];

    public static void main(String[] args)
    {
        try
        {
            Class<?> c = Class.forName(args[0]);
            System.out.println(c);
            printMembers(c.getFields(), true);
            printMembers(c.getDeclaredFields(), false);
            printMembers(c.getConstructors(), true);
            printMembers(c.getDeclaredConstructors(), false);
            printMembers(c.getMethods(), true);
            printMembers(c.getDeclaredMethods(), false);
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("unknown class: " + args[0]);
        }
    }

    private static void printMembers(Member[] mems, boolean isFirst)
    {
        Member m;
        // for (Member m : mems)
        checkMember: for (int i = 0; i < mems.length; i++)
        {
            m = mems[i];
            if (m.getDeclaringClass() == Object.class)
            {
                continue;
            }
            if (true == isFirst)
            {
                memberName[i] = m.toString();
            }
            if (false == isFirst)
            {
                for (int j = 0; j < memberName.length; j++)
                {
                    if (m.toString().equals(memberName[j]))
                    {
                        continue checkMember;
                    }
                }
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
