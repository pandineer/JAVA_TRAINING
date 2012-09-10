/*
 * 練習問題20.6 p.470
 * name op value形式の入力を受け取るプログラムを作成しなさい。
 * nameは自分で選んだ３個の単語の１つで、opは+、-、=のどれかで、valueは数です。
 * 名前付値に各演算子を適用しなさい。
 * 入力がなくなったら、３つの値を表示しなさい。
 * もし、興味があれば、AttributedImplで使用されたHashMapを使用してみなさい。
 * そうすれば、任意の個数の名前付定数を使用できます。
 */

package ch20.ex20_06;

import java.io.StreamTokenizer;
import java.io.StringReader;

public class NameOpValue
{
    public static void test()
    {
        Attribute panda = new Attribute("panda");
        Attribute dog = new Attribute("dog");
        Attribute cat = new Attribute("cat");


        String src = "panda = 0 dog = 1 panda + 5 cat = 3 panda + 8 dog + 2 dog - 1 cat - 1";
        StreamTokenizer in = new StreamTokenizer(new StringReader(src));
        try
        {
            while(in.nextToken() != StreamTokenizer.TT_EOF)
            {
                String target = in.sval;
                in.nextToken();
                int op = in.ttype;
                in.nextToken();
                double value = in.nval;

                if ("panda".equals(target))
                {
                    operation(panda, op, value);
                }
                else if ("dog".equals(target))
                {
                    operation(dog, op, value);
                }
                else if ("cat".equals(target))
                {
                    operation(cat, op, value);
                }
                else
                {
                    System.out.println("error");
                }
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

        System.out.println(panda.getName() + ": " + panda.getValue());
        System.out.println(dog.getName() + ": " + dog.getValue());
        System.out.println(cat.getName() + ": " + cat.getValue());
    }

    public static void operation(Attribute name, int op, double value)
    {
        if ('+' == op)
        {
            name.setValue(name.getValue() + value);
        }
        else if ('-' == op)
        {
            name.setValue(name.getValue() - value);
        }
        else if ('=' == op)
        {
            name.setValue(value);
        }
    }

    public static void main(String[] args)
    {
        NameOpValue.test();
    }
}
