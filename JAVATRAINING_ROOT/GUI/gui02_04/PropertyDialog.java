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
import java.awt.Component;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class PropertyDialog extends JDialog implements ActionListener, ItemListener
{

    static public String stringColor[] =
        { "Black", "White", "Red", "Green", "Blue", "Cyan", "Magenta", "Yellow",
                "Orange" };
    static public Color colorColor[] =
        { Color.black, Color.white, Color.red, Color.green, Color.blue, Color.cyan,
                Color.magenta, Color.yellow, Color.orange };

    private JComboBox choiceFontType = new JComboBox();
    private JComboBox choiceFontSize = new JComboBox();
    private JComboBox choiceFontColor;
    private JComboBox choiceBackgroundColor;
    private BufferedImage iconImage[] = new BufferedImage[stringColor.length];
    private ImageIcon icon[] = new ImageIcon[stringColor.length];

    private JLabel labelFontType = new JLabel("Font Type: ");
    private JLabel labelFontSize = new JLabel("Font Size: ");
    private JLabel labelFontColor = new JLabel("Font Color: ");
    private JLabel labelBackgroundColor = new JLabel("Background Color: ");
    private JLabel labelSample = new JLabel("■Sample: 15:00");

    private DefaultComboBoxModel fontColorModel = new DefaultComboBoxModel();
    private DefaultComboBoxModel backgroundColorModel = new DefaultComboBoxModel();

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
        this.setSize(480, 240);
        this.setResizable(false);

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
        for (int i = 0; i < stringColor.length; i++)
        {
            iconImage[i] = new BufferedImage(16, 16, BufferedImage.TYPE_INT_BGR);
            for (int y = 0; y < 16; y++)
            {
                for (int x = 0; x < 16; x++)
                {
                    iconImage[i].setRGB(x, y, colorColor[i].getRGB());
                }
            }
            icon[i] = new ImageIcon(iconImage[i]);
            fontColorModel.addElement(new ComboLabel(stringColor[i], icon[i]));
        }
        choiceFontColor = new JComboBox(fontColorModel);
        choiceFontColor.setRenderer(new MyCellRenderer());
        for(int i = 0; i < stringColor.length; i++)
        {
            if (stringColor[i].equals(defaultFontColor))
            {
                choiceFontColor.setSelectedItem(fontColorModel.getElementAt(i));
            }
        }
        gbc.gridx = 1;
        gbc.gridy = gridyCount;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbl.setConstraints(choiceFontColor, gbc);

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
        for (int i = 0; i < stringColor.length; i++)
        {
            iconImage[i] = new BufferedImage(16, 16, BufferedImage.TYPE_INT_BGR);
            for (int y = 0; y < 16; y++)
            {
                for (int x = 0; x < 16; x++)
                {
                    iconImage[i].setRGB(x, y, colorColor[i].getRGB());
                }
            }
            icon[i] = new ImageIcon(iconImage[i]);
            backgroundColorModel.addElement(new ComboLabel(stringColor[i], icon[i]));
        }
        choiceBackgroundColor = new JComboBox(backgroundColorModel);
        choiceBackgroundColor.setRenderer(new MyCellRenderer());
        for(int i = 0; i < stringColor.length; i++)
        {
            if (stringColor[i].equals(defaultBackgroundColor))
            {
                choiceBackgroundColor.setSelectedItem(backgroundColorModel.getElementAt(i));
            }
        }
        gbc.gridx = 1;
        gbc.gridy = gridyCount;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbl.setConstraints(choiceBackgroundColor, gbc);
        gbc.gridx = 2;
        gbc.gridy = gridyCount;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.WEST;
        labelSample.setForeground(digitalClock.getFontColor());
        labelSample.setBackground(digitalClock.getBackgroundColor());
        labelSample.setOpaque(true);
        gbl.setConstraints(labelSample, gbc);

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
        this.add(labelBackgroundColor);
        this.add(labelSample);
        this.add(choiceFontType);
        this.add(choiceFontSize);
        this.add(choiceFontColor);
        this.add(choiceBackgroundColor);
        this.add(OKButton);
        this.add(cancelButton);

        // リスナー登録
        choiceFontType.addItemListener(this);
        choiceFontSize.addItemListener(this);
        choiceFontColor.addItemListener(this);
        choiceBackgroundColor.addItemListener(this);
        OKButton.addActionListener(this);
        cancelButton.addActionListener(this);
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
        for(int i = 0; i < stringColor.length; i++)
        {
            if (stringColor[i].equals(defaultFontColor))
            {
                choiceFontColor.setSelectedItem(fontColorModel.getElementAt(i));
            }
        }
        // 背景色の初期選択値をStringで取得する
        defaultBackgroundColor = changeColorToString(digitalClock.getBackgroundColor());
        for(int i = 0; i < stringColor.length; i++)
        {
            if (stringColor[i].equals(defaultBackgroundColor))
            {
                choiceBackgroundColor.setSelectedItem(backgroundColorModel.getElementAt(i));
            }
        }
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
        // フォントサイズが選択された場合
        else if (choiceFontSize == e.getSource())
        {
            newFontSize = Integer.parseInt(e.getItem().toString());
        }
        // フォントカラーが選択された場合
        else if (choiceFontColor == e.getSource())
        {
            newFontColor = changeStringToColor((String)((ComboLabel)e.getItem()).getText());
            labelSample.setForeground(newFontColor);
        }
        // 背景色が選択された場合
        else if (choiceBackgroundColor == e.getSource())
        {
            newBackgroundColor = changeStringToColor((String)((ComboLabel)e.getItem()).getText());
            labelSample.setBackground(newBackgroundColor);
        }
        else
        {
            System.out.println("Error occurs at select property!");
            System.out.println(e);
        }

    }


    public class MyCellRenderer extends JLabel implements ListCellRenderer
    {
        private static final long serialVersionUID = -1252365831452353943L;

        public Component getListCellRendererComponent(JList list, Object value,
                int index, boolean isSelected, boolean cellHasFocus)
        {

            ComboLabel data = (ComboLabel) value;
            setText(data.getText());
            setIcon(data.getIcon());

            return this;
        }
    }

    public class ComboLabel
    {
        String text;
        Icon icon;

        ComboLabel(String text, Icon icon)
        {
            this.text = text;
            this.icon = icon;
        }

        public String getText()
        {
            return text;
        }

        public Icon getIcon()
        {
            return icon;
        }
    }


}
