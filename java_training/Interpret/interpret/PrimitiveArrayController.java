package interpret;

import java.awt.*;
import java.awt.event.*;

public class PrimitiveArrayController extends Dialog implements ActionListener
{
    private static final long serialVersionUID = 1L;
    private byte[] byteArray;
    private short[] shortArray;
    private int[] intArray;
    private long[] longArray;
    private float[] floatArray;
    private double[] doubleArray;
    private char[] charArray;
    private String type;

    private Choice valueChoice = new Choice();
    private TextArea valueTextArea = new TextArea();
    private Button showButton = new Button("Show");
    private Button setButton = new Button("Set");
    private Label showLabel = new Label();

    public PrimitiveArrayController(Frame owner, byte[] ar_array)
    {
        super(owner);
        byteArray = ar_array;

        this.setTitle("byte controllor");

        for (int i = 0; i < byteArray.length; i++)
        {
            valueChoice.add(i + " - byte");
        }
        type = "byte";

        commonInitialize();
    }

    public PrimitiveArrayController(Frame owner, short[] ar_array)
    {
        super(owner);
        shortArray = ar_array;

        this.setTitle("short controllor");

        for (int i = 0; i < shortArray.length; i++)
        {
            valueChoice.add(i + " - short");
        }
        type = "short";

        commonInitialize();
    }

    public PrimitiveArrayController(Frame owner, int[] ar_array)
    {
        super(owner);
        intArray = ar_array;

        this.setTitle("int controllor");

        for (int i = 0; i < intArray.length; i++)
        {
            valueChoice.add(i + " - int");
        }
        type = "int";

        commonInitialize();
    }

    public PrimitiveArrayController(Frame owner, long[] ar_array)
    {
        super(owner);
        longArray = ar_array;

        this.setTitle("long controllor");

        for (int i = 0; i < longArray.length; i++)
        {
            valueChoice.add(i + " - long");
        }
        type = "long";

        commonInitialize();
    }

    public PrimitiveArrayController(Frame owner, float[] ar_array)
    {
        super(owner);
        floatArray = ar_array;

        this.setTitle("float controllor");

        for (int i = 0; i < floatArray.length; i++)
        {
            valueChoice.add(i + " - float");
        }
        type = "float";

        commonInitialize();
    }

    public PrimitiveArrayController(Frame owner, double[] ar_array)
    {
        super(owner);
        doubleArray = ar_array;

        this.setTitle("double controllor");

        for (int i = 0; i < doubleArray.length; i++)
        {
            valueChoice.add(i + " - double");
        }
        type = "double";

        commonInitialize();
    }

    public PrimitiveArrayController(Frame owner, char[] ar_array)
    {
        super(owner);
        charArray = ar_array;

        this.setTitle("char controllor");

        for (int i = 0; i < charArray.length; i++)
        {
            valueChoice.add(i + " - char");
        }
        type = "char";

        commonInitialize();
    }

    public void commonInitialize()
    {
     // ウィンドウを閉じられるようにする
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                System.exit(0);
            }
        });

        this.setSize(300, 200);
        this.setResizable(true);

        // レイアウトの設定
        this.setLayout(new GridLayout(1, 5));
        this.add(valueChoice);
        this.add(showButton);
        showButton.addActionListener(this);
        this.add(showLabel);
        this.add(valueTextArea);
        this.add(setButton);
        setButton.addActionListener(this);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if ("Show" == e.getActionCommand())
        {
            if ("byte" == type)
            {
                showLabel.setText(String.valueOf((byteArray[valueChoice.getSelectedIndex()])));
            }
            else if ("short" == type)
            {
                showLabel.setText(String.valueOf((shortArray[valueChoice.getSelectedIndex()])));
            }
            else if ("int" == type)
            {
                showLabel.setText(String.valueOf((intArray[valueChoice.getSelectedIndex()])));
            }
            else if ("long" == type)
            {
                showLabel.setText(String.valueOf((longArray[valueChoice.getSelectedIndex()])));
            }
            else if ("float" == type)
            {
                showLabel.setText(String.valueOf((floatArray[valueChoice.getSelectedIndex()])));
            }
            else if ("double" == type)
            {
                showLabel.setText(String.valueOf((doubleArray[valueChoice.getSelectedIndex()])));
            }
            else if ("char" == type)
            {
                showLabel.setText(String.valueOf((charArray[valueChoice.getSelectedIndex()])));
            }
        }

        if ("Set" == e.getActionCommand())
        {
            if ("byte" == type)
            {
                byteArray[valueChoice.getSelectedIndex()] = Byte.valueOf(valueTextArea.getText());
            }
            else if ("short" == type)
            {
                shortArray[valueChoice.getSelectedIndex()] = Short.valueOf(valueTextArea.getText());
            }
            else if ("int" == type)
            {
                intArray[valueChoice.getSelectedIndex()] = Integer.valueOf(valueTextArea.getText());
            }
            else if ("long" == type)
            {
                longArray[valueChoice.getSelectedIndex()] = Long.valueOf(valueTextArea.getText());
            }
            else if ("float" == type)
            {
                floatArray[valueChoice.getSelectedIndex()] = Float.valueOf(valueTextArea.getText());
            }
            else if ("double" == type)
            {
                doubleArray[valueChoice.getSelectedIndex()] = Double.valueOf(valueTextArea.getText());
            }
            else if ("char" == type)
            {
                charArray[valueChoice.getSelectedIndex()] = valueTextArea.getText().charAt(0);
            }
        }

    }
}
