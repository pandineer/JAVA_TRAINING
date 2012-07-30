/*
 * 練習問題16.11 p.386
 * GameとPlayerを発展させて三目並べのような簡単なゲームを実装しなさい。
 * いくつかのPlayerの実装を、それぞれ何回か実行してスコアーを取りなさい。
 */

package ch16.ex16_11;

import java.util.Random;

// TODO: ゲームの中身の案
/*
 * 数字の大小しか返ってこないシステムに対して、如何に早く数字を当てられるか
 */

public class Game
{
    private static int playerNumber = 0;
    private int score = 0;

    private int target = 0;
    enum Reply
    {
        GREATER, LESS, EQUAL;
    }

    public Game()
    {
        // Randomクラスのインスタンス化
        Random rnd = new Random();

        // 当てる数字の決定（0～30）
        target = rnd.nextInt(30);
    }

    public static void main(String[] args)
    {
        String name;    // クラス名
        // while((name = getNextPlayer(args)) != null)
        for (int i = 0; i < args.length; i++)
        {
            name = args[i];
            try
            {
                PlayerLoader loader = new PlayerLoader();
                Class<? extends Player> classOf = loader.loadClass(name).asSubclass(Player.class);
                Player player = classOf.newInstance();
                Game game = new Game();
                player.play(game);
                game.reportScore(name);
            }
            catch(Exception e)
            {
                reportException(name, e);
            }
        }
    }

    // ...他のメソッドの定義...
    public static String getNextPlayer(String[] args)
    {
        if (playerNumber <= args.length)
        {
            return args[playerNumber];
        }
        return null;
    }

    public static void reportException(String name, Exception e)
    {
        System.out.println("name: " + name);
        System.out.println(e);
    }

    public void reportScore(String name)
    {
        System.out.println("Score of " + name + " is: " + score);
    }

    public Reply check(int answer)
    {
        if (answer == target)
        {
            return Reply.EQUAL;
        }
        else if (answer > target)
        {
            return Reply.GREATER;
        }
        else if (answer < target)
        {
            return Reply.LESS;
        }
        System.out.println("Error occurs. ");
        return null;
    }

    public void setScore(int score)
    {
        this.score = score;
    }
}
