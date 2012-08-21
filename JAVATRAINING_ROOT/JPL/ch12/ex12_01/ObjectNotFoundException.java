/*
 * 練習問題12.1 p.249
 * 以前の練習問題で作成したLinkedListクラスに対するObjectNotFoundExceptionクラスを作成しなさい。
 * リスト中のオブジェクトを探すfindメソッドを追加して、要求されたオブジェクトが含まれるLinkedListオブジェクトを返すか、
 * オブジェクトがリスト中に発見されなければその例外をスローするようにしなさい。
 * オブジェクトが発見されなかった時に、nullを返すより、例外をスローする方がなぜ好ましいですか。
 * なにか付け加えるとしたら、ObjectNotFoundExceptionはどのような追加データを保持すべきですか。
 *
 * answer: なぜ好ましいか：プログラマが例外処理を忘れない、例外発生時のデータを保持できる
 * answer: 追加すべきデータ：探していたオブジェクトを保持すべき
 */

package ch12.ex12_01;

public class ObjectNotFoundException extends Exception
{
    Object findTarget;

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    ObjectNotFoundException(Object findTarget)
    {
        this.findTarget = findTarget;
    }

}
