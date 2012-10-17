package ch22.ex22_15;

import java.util.Stack;

public class SimpleCalculator
{
    static Stack<Object> stack = new Stack<Object>();
    static double tmp = 0;
    static String[] splitString;

    // need Reversed Polish Notation(delimiter = " ")
    static double calc(String expression)
    {

        splitString = expression.split(" ");

        for (int i = 0; i < splitString.length; i++)
        {
            stack.push(splitString[i]);
        }

        switch (stack.pop().toString().charAt(0))
        {
            case '+':
                stack.push(Double.valueOf(stack.pop().toString())
                        + Double.valueOf(stack.pop().toString()));
                break;
            case '-':
                stack.push(Double.valueOf(stack.pop().toString())
                        - Double.valueOf(stack.pop().toString()));
                break;
            case '*':
                stack.push(Double.valueOf(stack.pop().toString())
                        * Double.valueOf(stack.pop().toString()));
                break;
            case '/':
                stack.push(Double.valueOf(stack.pop().toString())
                        / Double.valueOf(stack.pop().toString()));
                break;
            case '%':
                stack.push(Double.valueOf(stack.pop().toString())
                        % Double.valueOf(stack.pop().toString()));
                break;
        }

        return Double.valueOf(stack.pop().toString());
    }

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        System.out.println(SimpleCalculator.calc("12 34 +"));
    }

}
