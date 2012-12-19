/*
 * 課題2-2
 *  デジタル時計に次の機能追加を行なってください
 *   - メニューを付けて、プロパティダイアログを開ける
 *   - プロパティダイアログでは、以下の項目を設定できる
 *     1. フォントの指定
 *     2. フォントサイズの指定
 *     3. 文字色の指定（色がわかるようにカラーチップも表示すること）
 *     4. 時計の背景色の指定
 *   - フォントとフォントサイズを変更すると、時計を表示すべきフレームの大きさを適切に自動変更して、正しく表示されるようにする
 */

/*
 * 課題2-1
 * SwingのJFrameを使用して、時間を表示するデジタル時計（アナログ時計は不可）を作成してください。
 *  - javax.swing.JFrameを使用する
 *  - Windowsの普通のアプリケーションと同様にタイトルバーの「X」ボタンをクリックすると終了する。
 *  - デジタル時計の描画は、paintComponentメソッド内でGraphicsを使用して行う。テキストラベルによる単なる表示は、不可。
 */

package gui02_04;

import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.*;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PropertyDialog extends JDialog implements ActionListener, ItemListener, ChangeListener
{

    static public String stringColor[] =
        { "Black", "White", "Red", "Green", "Blue", "Cyan", "Magenta", "Yellow",
                "Orange" };
    static public Color colorColor[] =
        { Color.black, Color.white, Color.red, Color.green, Color.blue, Color.cyan,
                Color.magenta, Color.yellow, Color.orange };

    private JComboBox choiceFontType = new JComboBox();
    private JComboBox choiceFontSize = new JComboBox();
    private JComboBox choiceFontColor = new JComboBox();
    private JRadioButton radioButtonFontColor[] = new JRadioButton[stringColor.length];
    private ButtonGroup bg = new ButtonGroup();
    private JComboBox choiceBackgroundColor = new JComboBox();

    private JLabel labelFontType = new JLabel("Font Type: ");
    private JLabel labelFontSize = new JLabel("Font Size: ");
    private JLabel labelFontColor = new JLabel("Font Color: ");
    private JLabel labelFontColorSample = new JLabel("■Sample: 15:00");
    private JLabel labelRadioButtonSampleColorChip[] = new JLabel[colorColor.length];
    private JLabel labelBackgroundColor = new JLabel("Background Color: ");
    private JLabel labelBackgroundColorSample = new JLabel("■Sample: 15:00");

    private String defaultFontColor;
    private String defaultBackgroundColor;

    private String fonts[] = GraphicsEnvironment
            .getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

    private String newFontType = "TimesRoman";
    private int newFontSize = 10;
    private Color newFontColor = Color.black;
    private Color newBackgroundColor = Color.white;

    private JButton OKButton = new JButton("OK");
    private JButton cancelButton = new JButton("Cancel");

    private GridBagConstraints gbc = new GridBagConstraints();

    private GridBagLayout gbl = new GridBagLayout();

    private int gridyCount = 0;

    private DigitalClock digitalClock;



    public PropertyDialog(JFrame owner)
    {

        super(owner);

        digitalClock = (DigitalClock) owner;

        // 現在の時計の設定を取得する
        newFontType = digitalClock.getFontType();
        newFontSize = digitalClock.getFontSize();
        newFontColor = digitalClock.getFontColor();
        newBackgroundColor = digitalClock.getBackgroundColor();

        this.setLayout(gbl);
        this.setTitle("Property");
        this.setSize(300, 600);
        this.setResizable(false);

        // リスナー登録
        choiceFontType.addItemListener(this);
        choiceFontSize.addItemListener(this);
        // choiceFontColor.addItemListener(this);
        // fontColor用のリスナー登録
        choiceBackgroundColor.addItemListener(this);
        OKButton.addActionListener(this);
        cancelButton.addActionListener(this);

        // フォントタイプ
        // ラベル
        gbc.gridx = 0;
        gbc.gridy = gridyCount;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.EAST;
        gbl.setConstraints(labelFontType, gbc);
        // choice
        for (int i = 0; i < fonts.length; i++)
        {
            choiceFontType.addItem(fonts[i]);
        }
        choiceFontType.setSelectedItem(digitalClock.getFontType());
        gbc.gridx = 1;
        gbc.gridy = gridyCount;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbl.setConstraints(choiceFontType, gbc);

        gridyCount++;

        // フォントサイズ
        // label
        gbc.gridx = 0;
        gbc.gridy = gridyCount;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.EAST;
        gbl.setConstraints(labelFontSize, gbc);
        // choice
        for (Integer i = 50; i < 300; i = i + 30)
        {
            choiceFontSize.addItem(i.toString());
        }
        choiceFontSize.setSelectedItem(digitalClock.getFontSize().toString());
        gbc.gridx = 1;
        gbc.gridy = gridyCount;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbl.setConstraints(choiceFontSize, gbc);

        gridyCount++;

        // フォントカラー
        // フォントカラーの初期選択値をStringで取得する
        defaultFontColor = changeColorToString(digitalClock.getFontColor());
        // label
        gbc.gridx = 0;
        gbc.gridy = gridyCount;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.EAST;
        gbl.setConstraints(labelFontColor, gbc);
        // radio button
        // ラジオボタンバージョン
        // カラーチップの生成
        /*
        for (int i = 0; i < stringColor.length; i++)
        {
            labelRadioButtonSampleColorChip[i] = new JLabel("■");
            labelRadioButtonSampleColorChip[i].setForeground(colorColor[i]);
            gbc.gridx = 1;
            gbc.gridy = gridyCount;
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            gbc.anchor = GridBagConstraints.EAST;
            gbl.setConstraints(labelRadioButtonSampleColorChip[i], gbc);

            radioButtonFontColor[i] = new JRadioButton(stringColor[i]);
            radioButtonFontColor[i].addChangeListener(this); // 初期値の関係で、ここでリスナー登録する
            bg.add(radioButtonFontColor[i]); // ついでにボタングループ登録も行う

            gbc.gridx = 2;
            gbc.gridy = gridyCount++;
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            gbc.anchor = GridBagConstraints.WEST;
            gbl.setConstraints(radioButtonFontColor[i], gbc);
        }
        for (int i = 0; i < stringColor.length; i++)
        {
            if (defaultFontColor.equals(stringColor[i]))
            {
                radioButtonFontColor[i].setSelected(true);
            }
        }
        gridyCount--;
        */
        // choice
        for (int i = 0; i < stringColor.length; i++)
        {
            choiceFontColor.setForeground(colorColor[i]);
            choiceFontColor.addItem(stringColor[i]);
        }
        choiceFontColor.setSelectedItem(defaultFontColor);
        gbc.gridx = 1;
        gbc.gridy = gridyCount;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbl.setConstraints(choiceFontColor, gbc);
        // gbl.setConstraints(radioButtonFontColor[0], gbc);


        gridyCount++;

        // 背景色
        // 背景色の初期選択値をStringで取得する
        defaultBackgroundColor = changeColorToString(digitalClock.getBackgroundColor());
        // label
        gbc.gridx = 0;
        gbc.gridy = gridyCount;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.EAST;
        gbl.setConstraints(labelBackgroundColor, gbc);
        // choice
        for (int i = 0; i < stringColor.length; i++)
        {
            choiceBackgroundColor.addItem(stringColor[i]);
        }
        choiceBackgroundColor.setSelectedItem(defaultBackgroundColor);
        gbc.gridx = 1;
        gbc.gridy = gridyCount;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbl.setConstraints(choiceBackgroundColor, gbc);
        // sample color chip
        // TODO: labelBackgroundColorSampleを追加する
        gbc.gridx = 2;
        gbc.gridy = gridyCount;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.WEST;
        labelBackgroundColorSample.setForeground(digitalClock.getFontColor());
        labelBackgroundColorSample.setBackground(digitalClock.getBackgroundColor());
        labelBackgroundColorSample.setOpaque(true);
        gbl.setConstraints(labelBackgroundColorSample, gbc);

        gridyCount++;

        // OKボタン
        gbc.gridx = 1;
        gbc.gridy = gridyCount;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbl.setConstraints(OKButton, gbc);

        // Cancelボタン
        gbc.gridx = 2;
        gbc.gridy = gridyCount;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbl.setConstraints(cancelButton, gbc);

        // ダイアログボックスを閉じるとき
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                setVisible(false);
            }
        });

        this.add(labelFontType);
        this.add(labelFontSize);
        this.add(labelFontColor);
        this.add(labelFontColorSample);
        this.add(labelBackgroundColor);
        this.add(labelBackgroundColorSample);
        this.add(choiceFontType);
        this.add(choiceFontSize);
        this.add(choiceFontColor);
        /*
        for (int i = 0; i < stringColor.length; i++)
        {
            this.add(labelRadioButtonSampleColorChip[i]);
            this.add(radioButtonFontColor[i]);
        }
        */
        this.add(choiceBackgroundColor);
        this.add(OKButton);
        this.add(cancelButton);
    }

    private static final long serialVersionUID = 3853419917132576660L;

    public void resetParameter()
    {
        // 現在の時計の設定を取得する
        newFontType = digitalClock.getFontType();
        newFontSize = digitalClock.getFontSize();
        newFontColor = digitalClock.getFontColor();
        newBackgroundColor = digitalClock.getBackgroundColor();

        choiceFontType.setSelectedItem(digitalClock.getFontType());
        choiceFontSize.setSelectedItem(digitalClock.getFontSize().toString());
        // フォントカラーの初期選択値をStringで取得する
        defaultFontColor = changeColorToString(digitalClock.getFontColor());
        // choiceFontColor.setSelectedItem(defaultFontColor);
        // 背景色の初期選択値をStringで取得する
        defaultBackgroundColor = changeColorToString(digitalClock.getBackgroundColor());
        choiceBackgroundColor.setSelectedItem(defaultBackgroundColor);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if ("OK" == e.getActionCommand())
        {
            digitalClock.setFontType(newFontType);
            digitalClock.setFontSize(newFontSize);
            digitalClock.setFontColor(newFontColor);
            digitalClock.setBackgroundColor(newBackgroundColor);
            setVisible(false);
        }

        if ("Cancel" == e.getActionCommand())
        {
            setVisible(false);
        }
    }

    static public Color changeStringToColor(String colorString)
    {
        for (int i = 0; i < stringColor.length; i++)
        {
            if (colorString.equals(stringColor[i]))
            {
                return colorColor[i];
            }
        }
        return Color.black;
    }

    static public String changeColorToString(Color targetColor)
    {
        for (int i = 0; i < colorColor.length; i++)
        {
            if (targetColor.equals(colorColor[i]))
            {
                return stringColor[i];
            }
        }
        return stringColor[0];
    }

    @Override
    public void itemStateChanged(ItemEvent e)
    {
        // フォントタイプが選択された場合
        if (choiceFontType == e.getSource())
        {
            newFontType = e.getItem().toString();
        }
        else if (choiceFontSize == e.getSource())
        {
            newFontSize = Integer.parseInt(e.getItem().toString());
        }
        else if (choiceFontColor == e.getSource())
        {
            newFontColor = changeStringToColor((String)e.getItem());
            labelFontColorSample.setForeground(newFontColor);
            labelBackgroundColorSample.setForeground(newFontColor);
        }
        else if (choiceBackgroundColor == e.getSource())
        {
            newBackgroundColor = changeStringToColor((String)e.getItem());
            labelBackgroundColorSample.setBackground(newBackgroundColor);
        }
        else
        {
            System.out.println("Error occurs at select property!");
            System.out.println(e);
        }

    }

    @Override
    public void stateChanged(ChangeEvent e)
    {
        for (int i = 0; i < stringColor.length; i++)
        {
            if (radioButtonFontColor[i].isSelected())
            {
                newFontColor = colorColor[i];
                labelBackgroundColorSample.setForeground(newFontColor);
            }
        }
    }


}
