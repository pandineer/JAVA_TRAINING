/*
 * 第16章練習問題について

練習問題16.6、16.7、16.8、16.10をそれぞれ作成する代わりに、Interpretプログラムを１つ作成する。
練習問題で指定された操作ができること。更に、以下のことも考慮して作成する。

GUIで作成する（AWT/Swingのどちらでも良い）
自分自身を起動できるようにする
java.awt.FrameのsetVisible()、setTitle()、setSize()、setBackground()を呼び出すデモができること
private finalのインスタンスフィールドの書き換えもできること

なお、Interpretの課題は、私からOKがでる何度も再提出（多分、3回が限度）してもらい確認を行います。OKが出ない場合には、受講資格を失うこともありますので、注意してください。

課題提出時には、「JPL」や「GUI」のフォルダと同じ階層に「Interpret」というフォルダを作成し、その中にコードを入れて提出する。
 */

/*
 * 練習問題16.10 p.378
 * Interpretをさらに修正して、ユーザが生成する配列の型とサイズを指定できて、その配列の要素を読みだしたり
 * 設定したりできて、また、配列の要素として含まれているオブジェクトを指定して、そのオブジェクトのフィールドに
 * アクセスしたりメソッドを呼び出したりできるようにしなさい。
 */

/*
 * 練習問題16.8 p.372
 * Interpretプログラムをさらに修正して、任意のクラスのコンストラクタをユーザが呼び出せるようにしなさい。
 * その際にどんな例外も表示しなさい。
 * また、オブジェクトの生成が成功したら、そのオブジェクトのメソッドをユーザが呼び出せるようにしなさい。
 */

/*
 * 練習問題16.7 p.370
 * オブジェクトに対してメソッドを呼び出すようにInterpretプログラムを修正しなさい。
 * 戻り値やスローされた例外を適切に表示するようにしなさい。
 */

/*
 * 練習問題16.6 p.368
 * 要求された型のオブジェクトを生成し、ユーザがそのオブジェクトのフィールドを調べて、
 * フィールドを修正できるInterpretプログラムを作成しなさい
 */

package interpret;

import java.awt.*;
import java.awt.Frame;
import java.awt.event.*;
import java.lang.reflect.Member;

public class Interpret extends Frame implements ActionListener
{
    private static final long serialVersionUID = 1L;
    private static String[] memberName = new String[1000];

    private Choice choiceConstructor = new Choice();


    public Interpret(String title)
    {
        // タイトルバーの設定
        super(title);

        // ウィンドウを閉じられるようにする
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                System.exit(0);
            }
        });

        // レイアウトの設定
        this.setLayout(new GridLayout(3, 3));
        {
            // クラス名
            this.add(new Label("Class name: "));
            this.add(new TextArea());

            // クラスチェック
            this.add(new Label(""));
            this.add(new Button("Check the class"));

            // コンストラクタの選択肢を表示する
            this.add(new Label("Constructor: "));
            choiceConstructor.add("con A");
            choiceConstructor.add("con b");
            this.add(choiceConstructor);

        }
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if ("Check the class" == e.getActionCommand())
        {
            // TODO: ユーザが入力したクラス名のStringを取得して、関数に渡すべし。
            checkClass("hoge");
        }
    }

    private static void printMembers(Member[] mems, boolean isFirst)
    {
        Member m;
        // for (Member m : mems)
        checkMember: for (int i = 0; i < mems.length; i++)
        {
            m = mems[i];
            if (m.getDeclaringClass() == Object.class)
            {
                continue;
            }
            if (true == isFirst)
            {
                memberName[i] = m.toString();
            }
            if (false == isFirst)
            {
                for (int j = 0; j < memberName.length; j++)
                {
                    if (m.toString().equals(memberName[j]))
                    {
                        continue checkMember;
                    }
                }
            }
            String decl = m.toString();
            System.out.print(" ");
            System.out.println(strip(decl, "java.lang."));
        }
    }

    public static String strip(String source, String removeTarget)
    {
        return source.replaceAll(removeTarget, "");
    }

    public checkClass(String className)
    {
        // TODO:
        // クラスが存在するかどうか
        // この後コンストラクタを取得する関数に続いていくのだろう。。。
    }




    /**
     * @param args
     */
    public static void main(String[] args)
    {
        Interpret main = new Interpret("Interpret");

        main.setSize(640, 480);
        main.setResizable(false);
        main.setVisible(true);

        try
        {
            Class<?> c = Class.forName("interpret.Interpret");
            System.out.println(c);
            printMembers(c.getFields(), true);
            printMembers(c.getDeclaredFields(), false);
            printMembers(c.getConstructors(), true);
            printMembers(c.getDeclaredConstructors(), false);
            printMembers(c.getMethods(), true);
            printMembers(c.getDeclaredMethods(), false);
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("unknown class: " + args[0]);
        }
    }

}
