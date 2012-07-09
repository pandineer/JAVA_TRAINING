/*
 * 練習問題 14.8 p.317
 * Friendlyプログラムを試しなさい。
 * 使用しているシステムでどの程度の頻度で実際にデッドロックが発生しますか。
 * yield呼び出しを追加したら、デッドロックの頻度を変更できますか。
 * もし可能なら、この練習問題を1種類以上のシステムで試しなさい。
 * 同期を削除することなくデッドロックの可能性を取り除いてみなさい。
 */

package ch14.ex14_08;

public class Friendly
{
    private Friendly partner;
    private String name;

    public Friendly(String name)
    {
        this.name = name;
    }

    public synchronized void hug()
    {
        System.out.println(Thread.currentThread().getName() + " in " + name + ".hug() trying to invoke " + partner.name + ".hugBack()");
        partner.hugBack();
    }

    private synchronized void hugBack()
    {
        System.out.println(Thread.currentThread().getName() + " in " + name + ".hugBack()");
    }

    public void becomeFriend(Friendly partner)
    {
        this.partner = partner;
    }
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        final Friendly jareth = new Friendly("jaretj");
        final Friendly cory = new Friendly("cory");

        jareth.becomeFriend(cory);
        cory.becomeFriend(jareth);

        new Thread(new Runnable()
        {

            public void run()
            {
                synchronized (cory)
                {
                jareth.hug();
                }
            }
        }, "Thread1").start();

        new Thread(new Runnable()
        {
            public void run()
            {
                cory.hug();
            }
        }, "Thread2").start();
    }

}
