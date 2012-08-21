/*
 * 練習問題10.1 p.201
 * 文字列のパラメータを取り、その文字列中のすべての特殊文字を、対応するJava言語の表現で置き換えた文字列を
 * 返すメソッドを、if-elseを使用して書きなさい。
 * たとえば、文字列中に"が含まれていたら、"を\"で置き換えた文字列を返します。
 * （すべての特殊文字は、146頁の7.2.3節に列挙されています。）
 */

/*
 * p.146より
 * \n   改行(
 )
 * \t   タブ(\u0009)
 * \b   バックスペース(\u0008)
 * \r   復帰(
 )
 * \f   フォームフィード(\u000C)
 * \\   バックスラッシュ自身(\u005C)
 * \'   シングルクォート      (\u0027)
 * \"   ダブルクォート      (\u0022)
 * \ddd 8進数によるcharで、dは0-7の範囲
 */

package ch10.ex10_01;

public class SpecialCharacter
{
    static String replaceSpecialCharacter(String target)
    {
        String result = new String();
        for (int i = 0; i < target.length(); i++)
        {
            if (target.charAt(i) == 0xA)
            {
                result += "\\\n";
            }
            else if (target.charAt(i) == 0x9)
            {
                result += "\\\t";
            }
            else if (target.charAt(i) == 0x8)
            {
                result += "\\\b";
            }
            else if (target.charAt(i) == 0xD)
            {
                result += "\\\r";
            }
            else if (target.charAt(i) == 0xC)
            {
                result += "\\\f";
            }
            else if (target.charAt(i) == 0x5C)
            {
                result += "\\\\";
            }
            else if (target.charAt(i) == 0x27)
            {
                result += "\\\'";
            }
            else if (target.charAt(i) == 0x22)
            {
                result += "\\\"";
            }
        }
        return result;
    }

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        System.out.println(replaceSpecialCharacter("\t \' \""));
    }

}
