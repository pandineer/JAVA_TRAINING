/*
 * 練習問題22.15 p.580
 * （少なくとも）基本演算子+, -, *, /, %とすべてのMathあるいはStrictMathの機能を持つ電卓を作成しなさい。
 * （最も簡単な形式は、おそらく、演算子の優先順位が問題にならない逆ポーランドのスタック電卓です。）
 */

package ch22.ex22_15;

import java.util.Stack;

public class SimpleCalculator
{
    static Stack<Object> stack = new Stack<Object>();
    static String tmp = null;
    static String[] splitString;

    // need Reversed Polish Notation(delimiter = " ")
    static double calc(String expression)
    {

        splitString = expression.split(" ");

        for (int i = 0; i < splitString.length; i++)
        {
            switch (splitString[i].charAt(0))
            {
                case '+':
                    tmp = stack.pop().toString();
                    stack.push(Double.valueOf(stack.pop().toString()) + Double.valueOf(tmp));
                    continue;
                case '-':
                    tmp = stack.pop().toString();
                    stack.push(Double.valueOf(stack.pop().toString()) - Double.valueOf(tmp));
                    continue;
                case '*':
                    tmp = stack.pop().toString();
                    stack.push(Double.valueOf(stack.pop().toString()) * Double.valueOf(tmp));
                    continue;
                case '/':
                    tmp = stack.pop().toString();
                    stack.push(Double.valueOf(stack.pop().toString()) / Double.valueOf(tmp));
                    continue;
                case '%':
                    tmp = stack.pop().toString();
                    stack.push(Double.valueOf(stack.pop().toString()) % Double.valueOf(tmp));
                    continue;
            }

            if (splitString[i].equals("sin"))
            {
                stack.push(Math.sin(Double.valueOf(stack.pop().toString())));
                continue;
            }
            if (splitString[i].equals("cos"))
            {
                stack.push(Math.cos(Double.valueOf(stack.pop().toString())));
                continue;
            }
            if (splitString[i].equals("tan"))
            {
                stack.push(Math.tan(Double.valueOf(stack.pop().toString())));
                continue;
            }
            if (splitString[i].equals("asin"))
            {
                stack.push(Math.asin(Double.valueOf(stack.pop().toString())));
                continue;
            }
            if (splitString[i].equals("acos"))
            {
                stack.push(Math.acos(Double.valueOf(stack.pop().toString())));
                continue;
            }
            if (splitString[i].equals("atan"))
            {
                stack.push(Math.atan(Double.valueOf(stack.pop().toString())));
                continue;
            }
            if (splitString[i].equals("atan2"))
            {
                tmp = stack.pop().toString();
                stack.push(Math.atan2(Double.valueOf(stack.pop().toString()), Double.valueOf(tmp)));
                continue;
            }
            if (splitString[i].equals("toRadians"))
            {
                stack.push(Math.toRadians(Double.valueOf(stack.pop().toString())));
                continue;
            }
            if (splitString[i].equals("toDegrees"))
            {
                stack.push(Math.toDegrees(Double.valueOf(stack.pop().toString())));
                continue;
            }
            if (splitString[i].equals("exp"))
            {
                stack.push(Math.exp(Double.valueOf(stack.pop().toString())));
                continue;
            }
            if (splitString[i].equals("sinh"))
            {
                stack.push(Math.sinh(Double.valueOf(stack.pop().toString())));
                continue;
            }
            if (splitString[i].equals("cosh"))
            {
                stack.push(Math.cosh(Double.valueOf(stack.pop().toString())));
                continue;
            }
            if (splitString[i].equals("tanh"))
            {
                stack.push(Math.tanh(Double.valueOf(stack.pop().toString())));
                continue;
            }
            if (splitString[i].equals("pow"))
            {
                tmp = stack.pop().toString();
                stack.push(Math.pow(Double.valueOf(stack.pop().toString()), Double.valueOf(tmp)));
                continue;
            }
            if (splitString[i].equals("log"))
            {
                stack.push(Math.log(Double.valueOf(stack.pop().toString())));
                continue;
            }
            if (splitString[i].equals("log10"))
            {
                stack.push(Math.log10(Double.valueOf(stack.pop().toString())));
                continue;
            }
            if (splitString[i].equals("sqrt"))
            {
                stack.push(Math.sqrt(Double.valueOf(stack.pop().toString())));
                continue;
            }
            if (splitString[i].equals("cbrt"))
            {
                stack.push(Math.cbrt(Double.valueOf(stack.pop().toString())));
                continue;
            }
            if (splitString[i].equals("signum"))
            {
                stack.push(Math.signum(Double.valueOf(stack.pop().toString())));
                continue;
            }
            if (splitString[i].equals("ceil"))
            {
                stack.push(Math.ceil(Double.valueOf(stack.pop().toString())));
                continue;
            }
            if (splitString[i].equals("floor"))
            {
                stack.push(Math.floor(Double.valueOf(stack.pop().toString())));
                continue;
            }
            if (splitString[i].equals("rint"))
            {
                stack.push(Math.rint(Double.valueOf(stack.pop().toString())));
                continue;
            }
            if (splitString[i].equals("round"))
            {
                stack.push(Math.round(Double.valueOf(stack.pop().toString())));
                continue;
            }
            if (splitString[i].equals("abs"))
            {
                stack.push(Math.abs(Double.valueOf(stack.pop().toString())));
                continue;
            }
            if (splitString[i].equals("max"))
            {
                tmp = stack.pop().toString();
                stack.push(Math.max(Double.valueOf(stack.pop().toString()), Double.valueOf(tmp)));
                continue;
            }
            if (splitString[i].equals("min"))
            {
                tmp = stack.pop().toString();
                stack.push(Math.min(Double.valueOf(stack.pop().toString()), Double.valueOf(tmp)));
                continue;
            }
            if (splitString[i].equals("hypot"))
            {
                tmp = stack.pop().toString();
                stack.push(Math.hypot(Double.valueOf(stack.pop().toString()), Double.valueOf(tmp)));
                continue;
            }

            stack.push(splitString[i]);
        }

       return Double.valueOf(stack.pop().toString());
    }



    public static void main(String[] args)
    {
        System.out.println(SimpleCalculator.calc("1 2 + 3 4 - +"));
        System.out.println(SimpleCalculator.calc("100 200 max 200 5000 min +"));
        System.out.println(SimpleCalculator.calc("4 sqrt 8 pow"));
    }

}
