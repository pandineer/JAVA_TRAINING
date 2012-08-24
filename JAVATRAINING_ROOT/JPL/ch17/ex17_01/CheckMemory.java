/*
 * 練習問題17.1 p.397
 * 起動時と多くのオブジェクトを生成した後で、利用可能なメモリ量を調べるプログラムを書きなさい。
 * ガーベッジコレクタを呼び出して、空きメモリ量がどのように変化するかを調べなさい。
 * もちろん、新たに生成されたオブジェクトへの参照を間違いなく保持していないようにしてください。
 */

/*
 * result:
 * At first:
 *  Free memory is: 15973296
 * After 100 CheckMemory instances are created:
 *  Free memory is: 15882760
 * After garbage collection:
 *  Free memory is: 16061696

 */

package ch17.ex17_01;

public class CheckMemory
{
    Runtime rt = Runtime.getRuntime();

    public void showFreeMemory()
    {
        System.out.println("Free memory is: " + rt.freeMemory());
    }

    public void garbateCollect()
    {
        rt.gc();
    }

    public static void main(String[] args)
    {
        CheckMemory test = new CheckMemory();

        // 最初のメモリを見る
        System.out.println("At first: ");
        System.out.print("  ");
        test.showFreeMemory();

        // 誰も参照してないけどStringを生成しまくる
        for (int i = 0; i < 100; i++)
        {
            new String("hoge" + Integer.valueOf(i));
        }

        // すぐに空きメモリ見る
        System.out.println("After 100 CheckMemory instances are created: ");
        System.out.print("  ");
        test.showFreeMemory();

        // ガーベッジコレクタを呼び出してメモリを見る
        test.garbateCollect();
        System.out.println("After garbage collection: ");
        System.out.print("  ");
        test.showFreeMemory();

    }

}
