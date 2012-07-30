/*
 * 練習問題16.11 p.386
 * GameとPlayerを発展させて三目並べのような簡単なゲームを実装しなさい。
 * いくつかのPlayerの実装を、それぞれ何回か実行してスコアーを取りなさい。
 */

package ch16.ex16_11;

public abstract class Player
{
    int answer = 0;

    public void play(Game game)
    {
        // TODO: playの中身を実装する
        // strategyで値を決めて、checkにまわして、結果を受け取ってまたstrategyを実行する。
        // EQUALをgetしたら完了する

    }

    // 継承して、値の決定方法を工夫する
    public abstract int strategy();
}
