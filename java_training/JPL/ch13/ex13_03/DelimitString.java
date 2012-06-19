/*
 * 練習問題13.3 p.274
 * 本文中のdelimitedStringメソッドは、１つの入力文字列に１つの区切られた文字列しかないと仮定しています。
 * すべての区切られた文字列を取り出して配列にして返すバージョンを作成しなさい。
 */

package ch13.ex13_03;

public class DelimitString
{
    // すべての区切られた文字列を取り出すバージョン
    public static String[] improvedDelimitedString(String from, char start, char end)
    {
        int count = 0;
        String[] tmp= new String[100];  // とりあえず上限100
        String[] result;

        int startPos = 0;
        int endPos = 0;

        while(true)
        {
            startPos = from.indexOf(start, endPos);
            endPos = from.indexOf(end, startPos);

            if (startPos == -1) // 開始文字が見つからない
            {
                break;
            }
            else if (endPos == -1) // 終了文字が見つからない
            {
                tmp[count++] = from.substring(startPos);
            }
            else // 開示文字と終了文字が見つかった
            {
                tmp[count++] = from.substring(startPos, endPos + 1);
            }
        }

        // 区切られた文字列の数分の配列を作りなおして返す
        result = new String[count];
        int i = 0;
        while(null != tmp[i])
        {
            result[i] = tmp[i];
            i++;
        }

        return result;
    }

    // 本文バージョン
    public static String delimitedString(String from, char start, char end)
    {
        int startPos = from.indexOf(start);
        int endPos = from.lastIndexOf(end);

        if (startPos == -1) // 開始文字が見つからない
        {
            return null;
        }
        else if (endPos == -1) // 終了文字が見つからない
        {
            return from.substring(startPos);
        }
        else if (startPos > endPos) // 開始文字が終了文字の後ろにある
        {
            return null;
        }
        else // 開示文字と終了文字が見つかった
        {
            return from.substring(startPos, endPos + 1);
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        String[] test = improvedDelimitedString("aabaaabaaaaaab", 'a', 'b');
        for (int i = 0; i < test.length; i++)
        {
            System.out.println(test[i]);
        }
    }

}
