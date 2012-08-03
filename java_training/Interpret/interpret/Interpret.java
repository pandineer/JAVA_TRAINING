/*
 * 第16章練習問題について

練習問題16.6、16.7、16.8、16.10をそれぞれ作成する代わりに、Interpretプログラムを１つ作成する。
練習問題で指定された操作ができること。更に、以下のことも考慮して作成する。

GUIで作成する（AWT/Swingのどちらでも良い）
自分自身を起動できるようにする
java.awt.FrameのsetVisible()、setTitle()、setSize()、setBackground()を呼び出すデモができること
private finalのインスタンスフィールドの書き換えもできること

なお、Interpretの課題は、私からOKがでる何度も再提出（多分、3回が限度）してもらい確認を行います。
OKが出ない場合には、受講資格を失うこともありますので、注意してください。

課題提出時には、「JPL」や「GUI」のフォルダと同じ階層に「Interpret」というフォルダを作成し、
その中にコードを入れて提出する。
 */

/*
 * 練習問題16.10 p.378
 * Interpretをさらに修正して、ユーザが生成する配列の型とサイズを指定できて、その配列の要素を
 * 読みだしたり設定したりできて、また、配列の要素として含まれているオブジェクトを指定して、
 * そのオブジェクトのフィールドにアクセスしたりメソッドを呼び出したりできるようにしなさい。
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


// TODO: Interpret用のGUIと、他一般オブジェクト用にGUIを分けるのが吉なのかなぁ。。。


package interpret;

import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.*;

public class Interpret extends Frame implements ActionListener
{
    private static final long serialVersionUID = 1L;
    private Class<?> c;
    private Constructor<?>[] constructor = new Constructor[100];
    private Type[] constructorArgument = new Type[100];

    private TextArea classNameTextArea = new TextArea();
    private Button checkTheClassButton = new Button("Check the class");
    private Choice choiceConstructor = new Choice();
    private Button selectTheConstructorButton = new Button("Select the constructor");
    private Choice choiceConstructorArgs = new Choice();
    private Button createInstanceButton = new Button("Create instance");
    private Label errorLabel = new Label("a");

    public Interpret()
    {
        // タイトルバーの設定
        super("hoge");

        commonInitialize();
    }

    public Interpret(String title)
    {
        // タイトルバーの設定
        super(title);

        commonInitialize();
    }

    private void commonInitialize()
    {

        // ウィンドウを閉じられるようにする
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                System.exit(0);
            }
        });

        // レイアウトの設定
        this.setLayout(new GridLayout(7, 2));
        {
            // クラス名
            this.add(new Label("Input class name: "));
            this.add(classNameTextArea);

            // クラスチェック
            this.add(new Label(""));
            this.add(checkTheClassButton);
            checkTheClassButton.addActionListener(this);

            // コンストラクタの選択肢を表示する
            this.add(new Label("Constructor: "));
            choiceConstructor.add("---");
            this.add(choiceConstructor);

            // コンストラクタを選ぶ
            this.add(new Label("Select constructor"));
            this.add(selectTheConstructorButton);

            // コンストラクタの引数リスト
            this.add(new Label("Input construtctor argument"));
            this.add(choiceConstructorArgs);

            // インスタンス生成ボタン
            this.add(new Label("Create Instanace: "));
            this.add(createInstanceButton);
            createInstanceButton.addActionListener(this);

            // エラーメッセージ表示
            this.add(errorLabel);
            this.add(errorLabel);
        }

        setSize(640, 640);
        setResizable(false);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        // Check the classボタン
        if ("Check the class" == e.getActionCommand())
        {
            // テキストエリアの名前を関数に渡す
            checkClass(classNameTextArea.getText());
        }

        // Create instanceボタン
        if ("Create instance" == e.getActionCommand())
        {
            try
            {
                // c.newInstance();
                // c.getConstructors()[1].newInstance("mogemoge");
                System.out.println(choiceConstructor.getSelectedIndex());


            }
            catch(Exception ex)
            {
                System.out.println(ex);
            }
        }

        // Select the constructorボタン
        if ("Select the constructor" == e.getActionCommand())
        {
            // TODO: getGenericParameterTypes()を使って、コンストラクタの引数の型一覧を取得・表示する
            for(int i = 0; i < )
        }
    }

    public void checkClass(String className)
    {
        try
        {
            c = Class.forName(className);
            addConstructor(c.getConstructors(), true);
            addConstructor(c.getDeclaredConstructors(), false);
        }
        catch (ClassNotFoundException e)
        {
            errorLabel.setText("Unknown class: " + className);
        }
    }


    private void addConstructor(Constructor<?>[] tempConstructor, boolean isFirst)
    {
        if (true == isFirst)
        {
            choiceConstructor.removeAll();
        }
        checkMember: for (int i = 0; i < tempConstructor.length; i++)
        {
            if (tempConstructor[i].getDeclaringClass() == Object.class)
            {
                continue;
            }
            if (true == isFirst)
            {
                constructor[i] = tempConstructor[i];
            }
            if (false == isFirst)
            {
                for (int j = 0; j < constructor.length; j++)
                {
                    if (tempConstructor[i].toString().equals(constructor[j].toString()))
                    {
                        continue checkMember;
                    }
                }
            }
            choiceConstructor.add(tempConstructor[i].toString());
        }
    }




    /**
     * @param args
     */
    public static void main(String[] args)
    {
        Interpret main = new Interpret("Interpret");
        main.setTitle("Interpret");
    }

}
