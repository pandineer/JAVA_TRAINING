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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.*;

public class CreatedObjectDialog extends Dialog implements ActionListener
{
    private static final long serialVersionUID = 1L;
    private Interpret interpret;
    private Object createdObject;
    private Class<?> c;
    private Field[] field = new Field[500];

    private Choice choiceField = new Choice();
    private Label fieldValueLabel = new Label();
    private Button getFieldValueButton = new Button("Get field value");
    private TextArea inputFieldValueTextArea = new TextArea();
    private Button setFieldValueButton = new Button("Set field value");

    public CreatedObjectDialog(Frame owner, Object ar_createdObject, Class<?> ar_c)
    {
        super(owner);
        interpret = (Interpret)owner;
        createdObject = ar_createdObject;
        c = ar_c;


        // ウィンドウを閉じられるようにする
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                System.exit(0);
            }
        });

        this.setTitle(interpret.getC().toString());
        this.setSize(800, 180);
        this.setResizable(false);

        // レイアウトの設定
        this.setLayout(new GridLayout(5, 2));
        {
            // フィールド一覧
            this.add(new Label("Field: "));
            addChoiceObjectDialog();
            this.add(choiceField);

            // フィールド選択ボタン
            this.add(new Label("Select field"));
            this.add(getFieldValueButton);
            getFieldValueButton.addActionListener(this);

            // フィールドの値表示
            this.add(new Label("Selected field value: "));
            this.add(fieldValueLabel);

            // フィールドの値変更値入力
            this.add(new Label("Input field value: "));
            this.add(inputFieldValueTextArea);

            // フィールドの値変更確定ボタン
            this.add(new Label("Set field value"));
            this.add(setFieldValueButton);
            setFieldValueButton.addActionListener(this);

        }
        this.setVisible(true);


    }

    private void addChoiceObjectDialog()
    {
        int i = 0;
        for (i = 0; i < c.getFields().length; i++)
        {
            field[i] = c.getFields()[i];
            choiceField.add(field[i].toString());
        }
        declaredFieldLabel: for (int j = 0; j < c.getDeclaredFields().length; j++)
        {
            for (int k =0; k < i; k++)
            {
                if (c.getDeclaredFields()[j].toString().equals(field[k].toString()))
                {
                    continue declaredFieldLabel;
                }
            }
            field[i] = c.getDeclaredFields()[j];
            choiceField.add(field[i].toString());
            i++;
        }

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        // getFieldValueButton
        if ("Get field value" == e.getActionCommand())
        {
            field[choiceField.getSelectedIndex()].setAccessible(true);
            try
            {
                fieldValueLabel.setText(field[choiceField.getSelectedIndex()].get(createdObject).toString());
            }
            catch(Exception ex)
            {
                System.out.println(ex);
            }
        }

        // setFieldValueButton
        if ("Set field value" == e.getActionCommand())
        {
            field[choiceField.getSelectedIndex()].setAccessible(true);
            try
            {
             // 基本型でString以外のものは変換してから代入
                if (field[choiceField.getSelectedIndex()].getGenericType().toString().equals("byte"))
                {
                    field[choiceField.getSelectedIndex()].set(createdObject, Byte.valueOf(inputFieldValueTextArea.getText()));
                }
                else if (field[choiceField.getSelectedIndex()].getGenericType().toString().equals("short"))
                {
                    field[choiceField.getSelectedIndex()].set(createdObject, Short.valueOf(inputFieldValueTextArea.getText()));
                }
                else if (field[choiceField.getSelectedIndex()].getGenericType().toString().equals("int"))
                {
                    field[choiceField.getSelectedIndex()].set(createdObject, Integer.valueOf(inputFieldValueTextArea.getText()));
                }
                else if (field[choiceField.getSelectedIndex()].getGenericType().toString().equals("long"))
                {
                    field[choiceField.getSelectedIndex()].set(createdObject, Long.valueOf(inputFieldValueTextArea.getText()));
                }
                else if (field[choiceField.getSelectedIndex()].getGenericType().toString().equals("float"))
                {
                    field[choiceField.getSelectedIndex()].set(createdObject, Float.valueOf(inputFieldValueTextArea.getText()));
                }
                else if (field[choiceField.getSelectedIndex()].getGenericType().toString().equals("double"))
                {
                    field[choiceField.getSelectedIndex()].set(createdObject, Double.valueOf(inputFieldValueTextArea.getText()));
                }
                else
                {
                    field[choiceField.getSelectedIndex()].set(createdObject, inputFieldValueTextArea.getText());
                }

            }
            catch(Exception ex)
            {
                System.out.println(ex);
            }
        }

    }

}
