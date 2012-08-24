package interpret;

import java.awt.*;
import java.awt.event.*;

public class CreatedArrayController extends Dialog implements ActionListener
{
    private static final long serialVersionUID = 1L;

    private Interpret interpret;
    private CreatedObjectDialog[] objectArray;

    private Choice arrayComponentChoice = new Choice();
    private Button showButton = new Button("Show");
    private Button hideButton = new Button("Hide");

    public CreatedArrayController(Frame owner, CreatedObjectDialog[] targetArray)
    {
        super(owner);
        interpret = (Interpret)owner;
        objectArray = targetArray;

        // ウィンドウを閉じられるようにする
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                System.exit(0);
            }
        });

        this.setTitle(interpret.getC().toString());
        this.setSize(800, 160);
        this.setResizable(true);

        // レイアウトの設定
        this.setLayout(new GridLayout(1, 3));

        for (int i = 0; i < objectArray.length; i++)
        {
            arrayComponentChoice.add(i + " - " + interpret.getC().toString());
        }
        this.add(arrayComponentChoice);
        this.add(showButton);
        showButton.addActionListener(this);
        this.add(hideButton);
        hideButton.addActionListener(this);

        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if ("Show" == e.getActionCommand())
        {
            objectArray[arrayComponentChoice.getSelectedIndex()].setVisible(true);
        }

        if ("Hide" == e.getActionCommand())
        {
            objectArray[arrayComponentChoice.getSelectedIndex()].setVisible(false);
        }
    }

}
