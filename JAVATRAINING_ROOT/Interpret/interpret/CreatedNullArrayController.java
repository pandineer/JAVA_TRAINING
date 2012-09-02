package interpret;

import java.awt.*;
import java.awt.event.*;

public class CreatedNullArrayController extends Dialog implements ActionListener
{
    private static final long serialVersionUID = 1L;

    private Interpret interpret;
    private Object[] objectArray;
    private String[] objectName;

    private Choice arrayComponentChoice = new Choice();
    private Button checkButton = new Button("Check");
    private Label objectNameLabel = new Label("");
    private Choice setFieldObjectChoice = new Choice();
    private Button setButton = new Button("Set");
    private Button updateButton = new Button("Update");

    public CreatedNullArrayController(Frame owner, Object[] targetArray)
    {
        super(owner);
        interpret = (Interpret)owner;
        objectArray = targetArray;
        objectName = new String[objectArray.length];

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
        this.setLayout(new GridLayout(1, 6));

        for (int i = 0; i < objectArray.length; i++)
        {
            arrayComponentChoice.add(i + " - " + interpret.getC().toString());
        }
        this.add(arrayComponentChoice);
        this.add(checkButton);
        checkButton.addActionListener(this);
        this.add(objectNameLabel);
        setFieldObjectChoice.removeAll();
        for (int i = 0; i < interpret.createdObject.length; i++)
        {
            if (null == interpret.createdObject[i])
            {
                break;
            }
            setFieldObjectChoice.add(interpret.objectName[i]);
        }
        this.add(setFieldObjectChoice);
        this.add(setButton);
        setButton.addActionListener(this);

        this.add(updateButton);
        updateButton.addActionListener(this);

        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
    	if ("Check" == e.getActionCommand())
    	{
    		if (null == objectName[arrayComponentChoice.getSelectedIndex()])
    		{
    			objectNameLabel.setText("Null");
    		}
    		else
    		{
    			objectNameLabel.setText(objectName[arrayComponentChoice.getSelectedIndex()]);
    		}
    	}

    	if ("Set" == e.getActionCommand())
    	{
    		objectArray[arrayComponentChoice.getSelectedIndex()] = interpret.createdObject[setFieldObjectChoice.getSelectedIndex()];
    		objectName[arrayComponentChoice.getSelectedIndex()] = interpret.objectName[setFieldObjectChoice.getSelectedIndex()];

    	}

    	if ("Update" == e.getActionCommand())
    	{
    		setFieldObjectChoice.removeAll();
            for (int i = 0; i < interpret.createdObject.length; i++)
            {
                if (null == interpret.createdObject[i])
                {
                    break;
                }
                setFieldObjectChoice.add(interpret.objectName[i]);
            }
    	}
    }

}
