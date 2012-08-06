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
    public String[] objectName = new String[100];
    public Object[] createdObject = new Object[100];
    private CreatedObjectDialog[] createdObjectDialog = new CreatedObjectDialog[10];
    private int createdObjectNumber = 0;
    public Object[][] createdArray = new Object[100][];
    private CreatedObjectDialog[][] createdObjectArrayDialog = new CreatedObjectDialog[100][];
    private int createdObjectArrayNumber = 0;
    public String[] objectArrayName = new String[100];
    private CreatedArrayController[] createdArrayController = new CreatedArrayController[100];

    private TextArea classNameTextArea = new TextArea("java.lang.String");
    private Button checkTheClassButton = new Button("Check the class");
    private Choice choiceConstructor = new Choice();
    private Button selectTheConstructorButton = new Button("Select the constructor");
    private Choice choiceConstructorArgs = new Choice();
    private TextArea constructorArgumentTextArea = new TextArea();
    private Button setConstructorArgumentButton = new Button("Set constructor argument");
    private Choice setConstructorArgumentBooleanChoice = new Choice();
    private Button setConstructorArgumentBooleanButton = new Button("Set constructor argument boolean");
    private Choice setConstructorArgumentObjectChoice = new Choice();
    private Button setConstructorArgumentObjectButton = new Button("Set constructor argument object");
    private Choice setConstructorArgumentArrayChoice = new Choice();
    private Button setConstructorArgumentArrayButton = new Button("Set constructor argument array");
    private TextArea setObjectNameTextArea = new TextArea();
    private Button createInstanceButton = new Button("Create instance");
    private TextArea setArrayNumberTextArea = new TextArea();
    private Button createInstanceArrayButton = new Button("Create instance array");
    private Label errorLabel = new Label("");


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

    public Interpret(boolean boo)
    {
        // タイトルバーの設定
        super("argument: " + boo);

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
        this.setLayout(new GridLayout(12, 3));
        {
            // クラス名入力&チェックボタン
            this.add(new Label("Input class name: "));
            this.add(classNameTextArea);
            this.add(checkTheClassButton);
            checkTheClassButton.addActionListener(this);

            // コンストラクタの選択
            this.add(new Label("Constructor: "));
            this.add(choiceConstructor);
            this.add(selectTheConstructorButton);
            selectTheConstructorButton.addActionListener(this);

            // コンストラクタの引数リスト
            this.add(new Label("Select to input construtctor argument"));
            this.add(choiceConstructorArgs);
            this.add(new Label(""));

            // コンストラクタの引数手入力
            this.add(new Label("Input constructor argument"));
            this.add(constructorArgumentTextArea);
            this.add(setConstructorArgumentButton);
            setConstructorArgumentButton.addActionListener(this);

            // コンストラクタの引数boolean入力
            this.add(new Label(""));
            setConstructorArgumentBooleanChoice.add("true");
            setConstructorArgumentBooleanChoice.add("false");
            this.add(setConstructorArgumentBooleanChoice);
            this.add(setConstructorArgumentBooleanButton);
            setConstructorArgumentBooleanButton.addActionListener(this);

            // コンストラクタの引数object選択
            this.add(new Label(""));
            this.add(setConstructorArgumentObjectChoice);
            this.add(setConstructorArgumentObjectButton);
            setConstructorArgumentObjectButton.addActionListener(this);

            // コンストラクタの引数配列
            this.add(new Label(""));
            this.add(setConstructorArgumentArrayChoice);
            this.add(setConstructorArgumentArrayButton);
            setConstructorArgumentArrayButton.addActionListener(this);

            // オブジェクトの名前設定
            this.add(new Label("Set object name: "));
            this.add(setObjectNameTextArea);
            this.add(new Label(""));

            // インスタンス生成ボタン
            this.add(new Label("Create Instanace: "));
            this.add(createInstanceButton);
            createInstanceButton.addActionListener(this);
            this.add(new Label(""));

            // 配列の要素数指定
            this.add(new Label("Input array number"));
            this.add(setArrayNumberTextArea);
            this.add(new Label(""));

            // インスタンス配列の生成ボタン
            this.add(new Label("Create instance array"));
            this.add(createInstanceArrayButton);
            createInstanceArrayButton.addActionListener(this);
            this.add(new Label(""));

            // エラーメッセージ表示
            this.add(new Label("Error is shown here: "));
            this.add(errorLabel);
            this.add(new Label(""));
        }

        setSize(800, 640);
        setResizable(true);
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
                // 基本型でString以外のものは変換してから代入
                if (constructorArgument[choiceConstructorArgs.getSelectedIndex()].toString().equals("byte"))
                {
                    constructorArgumentValue[choiceConstructorArgs.getSelectedIndex()] = Byte.valueOf(constructorArgumentTextArea.getText());
                }
                else if (constructorArgument[choiceConstructorArgs.getSelectedIndex()].toString().equals("short"))
                {
                    constructorArgumentValue[choiceConstructorArgs.getSelectedIndex()] = Short.valueOf(constructorArgumentTextArea.getText());
                }
                else if (constructorArgument[choiceConstructorArgs.getSelectedIndex()].toString().equals("int"))
                {
                    constructorArgumentValue[choiceConstructorArgs.getSelectedIndex()] = Integer.valueOf(constructorArgumentTextArea.getText());
                }
                else if (constructorArgument[choiceConstructorArgs.getSelectedIndex()].toString().equals("long"))
                {
                    constructorArgumentValue[choiceConstructorArgs.getSelectedIndex()] = Long.valueOf(constructorArgumentTextArea.getText());
                }
                else if (constructorArgument[choiceConstructorArgs.getSelectedIndex()].toString().equals("float"))
                {
                    constructorArgumentValue[choiceConstructorArgs.getSelectedIndex()] = Float.valueOf(constructorArgumentTextArea.getText());
                }
                else if (constructorArgument[choiceConstructorArgs.getSelectedIndex()].toString().equals("double"))
                {
                    constructorArgumentValue[choiceConstructorArgs.getSelectedIndex()] = Double.valueOf(constructorArgumentTextArea.getText());
                }
                else
                {
                    constructorArgumentValue[choiceConstructorArgs.getSelectedIndex()] = constructorArgumentTextArea.getText();
                }
            }
        }

        // Set constructor argument booleanボタン
        if ("Set constructor argument boolean" == e.getActionCommand())
        {
            if (choiceConstructorArgs.getSelectedIndex() >= 0)
            {
                if (0 == setConstructorArgumentBooleanChoice.getSelectedIndex())
                {
                    constructorArgumentValue[choiceConstructorArgs.getSelectedIndex()] = true;
                }
                else if (1 == setConstructorArgumentBooleanChoice.getSelectedIndex())
                {
                    constructorArgumentValue[choiceConstructorArgs.getSelectedIndex()] = false;
                }
                else
                {
                    errorLabel.setText("Set constructor argument boolean error");
                }

            }
        }

        // Set constructor argument objectボタン
        if ("Set constructor argument object" == e.getActionCommand())
        {
            if (choiceConstructorArgs.getSelectedIndex() >= 0)
            {
                constructorArgumentValue[choiceConstructorArgs.getSelectedIndex()] = createdObject[setConstructorArgumentObjectChoice.getSelectedIndex()];
            }
        }

        //  Set constructor argument arrayボタン
        if (" Set constructor argument array" == e.getActionCommand())
        {
            if (choiceConstructorArgs.getSelectedIndex() >= 0)
            {
                constructorArgumentValue[choiceConstructorArgs.getSelectedIndex()] = createdArray[setConstructorArgumentArrayChoice.getSelectedIndex()];
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
                    tempConstructorArgument[i] = constructorArgumentValue[i];
                }
                if (createdObjectNumber < 100)
                {
                    createdObject[createdObjectNumber] = targetConstructor.newInstance(tempConstructorArgument);
                    setConstructorArgumentObjectChoice.add(setObjectNameTextArea.getText());
                    objectName[createdObjectNumber] = setObjectNameTextArea.getText();
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

        // Create instance arrayボタン
        if ("Create instance array" == e.getActionCommand() && choiceConstructor.getSelectedIndex() >= 0 && Integer.valueOf(setArrayNumberTextArea.getText()) > 0)
        {
            Object[] tmpObjectArray = new Object[Integer.valueOf(setArrayNumberTextArea.getText())];
            createdObjectArrayDialog[createdObjectArrayNumber] = new CreatedObjectDialog[Integer.valueOf(setArrayNumberTextArea.getText())];

            Constructor<?> targetConstructor = c.getConstructors()[choiceConstructor.getSelectedIndex()];
            Object[] tempConstructorArgument = new Object[targetConstructor.getGenericParameterTypes().length];


            for (int i = 0; i < targetConstructor.getGenericParameterTypes().length; i++)
            {
                tempConstructorArgument[i] = constructorArgumentValue[i];
            }

            for (int i = 0; i < Integer.valueOf(setArrayNumberTextArea.getText()); i++)
            {
                try
                {
                    tmpObjectArray[i] = targetConstructor.newInstance(tempConstructorArgument);
                    System.out.println(tempConstructorArgument[0]);
                    createdObjectArrayDialog[createdObjectArrayNumber][i] = new CreatedObjectDialog(this, tmpObjectArray[i], c);
                    createdObjectArrayDialog[createdObjectArrayNumber][i].setVisible(false);
                }
                catch(Exception ex)
                {
                    System.out.println(ex);
                    this.errorLabel.setText(ex.toString());
                }
            }
            createdArray[createdObjectArrayNumber] = tmpObjectArray;
            objectArrayName[createdObjectArrayNumber] = setObjectNameTextArea.getText();
            setConstructorArgumentArrayChoice.add(objectArrayName[createdObjectArrayNumber]);
            createdArrayController[createdObjectArrayNumber] = new CreatedArrayController(this, createdObjectArrayDialog[createdObjectArrayNumber]);
            createdObjectArrayNumber++;
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
