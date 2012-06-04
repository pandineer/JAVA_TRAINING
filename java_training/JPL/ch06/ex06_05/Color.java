/*
 * 練習問題6.5 p.137
 * 練習問題6.4で、getColorをabstractとして、各enum定数が正しいColor
 * オブジェクトを返すように定数固有のメソッドを定義しなさい。
 * Colorオブジェクトを返すために、定数固有のメソッドを使用することを推奨しますか。
 */

package ch06.ex06_05;

public class Color
{
    public String ColorName;

    Color(String name)
    {
        ColorName = name;
    }
}
