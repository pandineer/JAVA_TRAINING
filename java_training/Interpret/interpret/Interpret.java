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
    private Object[] constructorArgumentValue = new Object[100];
    private Object[] createdObject = new Object[10];
    private CreatedObjectDialog[] createdObjectDialog = new CreatedObjectDialog[10];
    private int createdObjectNumber = 0;

    private TextArea classNameTextArea = new TextArea("interpret.Interpret");
    private Button checkTheClassButton = new Button("Check the class");
    private Choice choiceConstructor = new Choice();
    private Button selectTheConstructorButton = new Button("Select the constructor");
    private Choice choiceConstructorArgs = new Choice();
    private TextArea constructorArgumentTextArea = new TextArea();
    private Button setConstructorArgumentButton = new Button("Set constructor argument");
    private Button createInstanceButton = new Button("Create instance");
    private Label errorLabel = new Label("Error message is shown here");


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

    public Interpret(String title, int in)
    {
        // タイトルバーの設定
        super(title + in);

        commonInitialize();
    }

    public Interpret(String title, int in, double db)
    {
        // タイトルバーの設定
        super(title + in + db);

        commonInitialize();
    }

    // 共通初期化部分。コンストラクタから呼ぶ
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
        this.setLayout(new GridLayout(9, 2));
        {
            // クラス名入力
            this.add(new Label("Input class name: "));
            this.add(classNameTextArea);

            // クラスチェックボタン
            this.add(new Label(""));
            this.add(checkTheClassButton);
            checkTheClassButton.addActionListener(this);

            // コンストラクタの選択肢を表示する
            this.add(new Label("Constructor: "));
            this.add(choiceConstructor);

            // コンストラクタを選ぶボタン
            this.add(new Label("Select constructor"));
            this.add(selectTheConstructorButton);
            selectTheConstructorButton.addActionListener(this);

            // コンストラクタの引数リスト
            this.add(new Label("Select to input construtctor argument"));
            this.add(choiceConstructorArgs);

            // コンストラクタの引数入力
            this.add(new Label("Input constructor argument"));
            this.add(constructorArgumentTextArea);

            // コンストラクタの引数設定ボタン
            this.add(new Label("Set constructor argument"));
            this.add(setConstructorArgumentButton);
            setConstructorArgumentButton.addActionListener(this);

            // インスタンス生成ボタン
            this.add(new Label("Create Instanace: "));
            this.add(createInstanceButton);
            createInstanceButton.addActionListener(this);

            // エラーメッセージ表示
            this.add(errorLabel);
            this.add(errorLabel);
        }

        setSize(800, 640);
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

        // Select the constructorボタン
        if ("Select the constructor" == e.getActionCommand())
        {
            // 初期化
            choiceConstructorArgs.removeAll();
            for (int i = 0; i < constructorArgumentValue.length; i++)
            {
                constructorArgumentValue[i] = null;
            }

            if (choiceConstructor.getSelectedIndex() >= 0)
            {
                for(int i = 0; i < constructor[choiceConstructor.getSelectedIndex()].getGenericParameterTypes().length; i++)
                {
                    constructorArgument[i] = constructor[choiceConstructor.getSelectedIndex()].getGenericParameterTypes()[i];
                    choiceConstructorArgs.add(constructorArgument[i].toString());
                }
            }
        }

        // Set constructor argumentボタン
        if ("Set constructor argument" == e.getActionCommand())
        {
            if (choiceConstructorArgs.getSelectedIndex() >= 0)
            {
                constructorArgumentValue[choiceConstructorArgs.getSelectedIndex()] = constructorArgumentTextArea.getText();
            }
        }

        // Create instanceボタン
        if ("Create instance" == e.getActionCommand() && choiceConstructor.getSelectedIndex() >= 0)
        {
            // コンストラクタ用引数を必要な数だけコピーしてからnewInstanceを実行する
            Constructor<?> targetConstructor = c.getConstructors()[choiceConstructor.getSelectedIndex()];
            Object[] tempConstructorArgument = new Object[targetConstructor.getGenericParameterTypes().length];
            try
            {
                for (int i = 0; i < targetConstructor.getGenericParameterTypes().length; i++)
                {
                    // 基本型でString以外のものは変換してから代入
                    if (targetConstructor.getGenericParameterTypes()[i].toString().equals("byte"))
                    {
                        tempConstructorArgument[i] = Byte.valueOf(constructorArgumentValue[i].toString());
                    }
                    else if (targetConstructor.getGenericParameterTypes()[i].toString().equals("short"))
                    {
                        tempConstructorArgument[i] = Short.valueOf(constructorArgumentValue[i].toString());
                    }
                    else if (targetConstructor.getGenericParameterTypes()[i].toString().equals("int"))
                    {
                        tempConstructorArgument[i] = Integer.valueOf(constructorArgumentValue[i].toString());
                    }
                    else if (targetConstructor.getGenericParameterTypes()[i].toString().equals("long"))
                    {
                        tempConstructorArgument[i] = Long.valueOf(constructorArgumentValue[i].toString());
                    }
                    else if (targetConstructor.getGenericParameterTypes()[i].toString().equals("float"))
                    {
                        tempConstructorArgument[i] = Float.valueOf(constructorArgumentValue[i].toString());
                    }
                    else if (targetConstructor.getGenericParameterTypes()[i].toString().equals("double"))
                    {
                        tempConstructorArgument[i] = Double.valueOf(constructorArgumentValue[i].toString());
                    }
                    else
                    {
                        tempConstructorArgument[i] = constructorArgumentValue[i];
                    }
                }
                if (createdObjectNumber < 10)
                {
                    createdObject[createdObjectNumber] = targetConstructor.newInstance(tempConstructorArgument);
                    createdObjectDialog[createdObjectNumber] = new CreatedObjectDialog(this, createdObject[createdObjectNumber], c);
                    createdObjectNumber++;
                }
                else
                {
                    this.errorLabel.setText("Limit of creatable object number");
                }
            }
            catch(Exception ex)
            {
                System.out.println(ex);
                this.errorLabel.setText(ex.toString());
            }
        }
    }

    public void checkClass(String className)
    {
        try
        {
            // クラスが存在するかどうか調べて、コンストラクタを取得する
            c = Class.forName(className);
            addConstructor(c);
        }
        catch (ClassNotFoundException e)
        {
            // クラスが見つからなかったらエラーメッセージを変更する
            errorLabel.setText("Unknown class: " + className);
        }
    }


    private void addConstructor(Class<?> c)
    {
        Constructor<?>[] tempConstructor;
        int i = 0;

        // addConstructor関数が呼ばれるのが最初だったら、コンストラクタ用のセレクタをクリアする
        choiceConstructor.removeAll();

        tempConstructor = c.getConstructors();
        for(i = 0; i < tempConstructor.length; i++)
        {
            constructor[i] = tempConstructor[i];
            choiceConstructor.add(tempConstructor[i].toString());
        }

        tempConstructor = c.getDeclaredConstructors();

        addDeclaredConstructor: for (int j =0; j < tempConstructor.length; j++)
        {
            // コンストラクタの重複チェック
            for (int k = 0; k < i; k++)
            {
                if (tempConstructor[j].toString().equals(constructor[k].toString()))
                {
                    continue addDeclaredConstructor;
                }
            }

            // 重複してなかったらconstructorに登録
            constructor[i++] = tempConstructor[j];
            choiceConstructor.add(tempConstructor[j].toString());
        }

    }


    public Class<?> getC()
    {
        return c;
    }

    public int testIntMethod(int a)
    {
        return a;
    }

    public String testStringMethod(String a)
    {
        return a;
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
