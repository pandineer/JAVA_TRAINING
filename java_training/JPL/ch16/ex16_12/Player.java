/*
 * 練習問題16.11 p.386
 * GameとPlayerを発展させて三目並べのような簡単なゲームを実装しなさい。
 * いくつかのPlayerの実装を、それぞれ何回か実行してスコアーを取りなさい。
 */

package ch16.ex16_12;

import ch16.ex16_12.Game.Reply;



public abstract class Player
{
    int answer = 0;
    int score = 0;
    Game.Reply previous_result = Reply.LESS;

    public final void play(Game game)
    {
        // strategyで値を決めて、checkにまわして、結果を受け取ってまたstrategyを実行する。
        // EQUALをgetしたら完了する

        while(true)
        {
            score++;
            answer = strategy(answer, previous_result);
            previous_result = game.check(answer);
            if (Reply.EQUAL == previous_result)
            {
                game.setScore(score);
                break;
            }
        }
    }

    // 継承して、値の決定方法を工夫する
    public abstract int strategy(int previous, Game.Reply result);
}
