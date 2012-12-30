import javax.swing.*;

import java.awt.Color;
// import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.image.BufferedImage;

public class JComboBoxTest18 extends JFrame{

    /**
     *
     */
    private static final long serialVersionUID = 7870501353097900879L;
    private BufferedImage iconImage = new BufferedImage(20, 20, BufferedImage.TYPE_INT_BGR);
    private ImageIcon icon;

  public static void main(String[] args){
    JComboBoxTest18 frame = new JComboBoxTest18();

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setBounds(10, 10, 300, 200);
    frame.setTitle("タイトル");
    frame.setVisible(true);

  }

  JComboBoxTest18(){
    DefaultComboBoxModel model = new DefaultComboBoxModel();

    for (int y = 0; y < 20; y++)
    {
        for (int x = 0; x < 20; x++)
        {
            iconImage.setRGB(x, y, 255);
        }
    }

    icon = new ImageIcon(iconImage);

    model.addElement(new ComboLabel("test1", icon));
    model.addElement(new ComboLabel("test2", icon));
    /*
    model.addElement(new ComboLabel("Lion", new ImageIcon("./img/reo1s.png")));
    model.addElement(new ComboLabel("Elephant", new ImageIcon("./img/zou1s.png")));
    model.addElement(new ComboLabel("Ostrich", new ImageIcon("./img/dacho_s.png")));
    model.addElement(new ComboLabel("Hippo", new ImageIcon("./img/hip03s.png")));
    */

    JComboBox combo = new JComboBox(model);

    MyCellRenderer renderer = new MyCellRenderer();
    combo.setRenderer(renderer);

    JPanel p = new JPanel();
    p.add(combo);

    getContentPane().add(p, BorderLayout.CENTER);
  }

  class ComboLabel{
    String text;
    Icon icon;

    ComboLabel(String text, Icon icon){
      this.text = text;
      this.icon = icon;
    }

    public String getText(){
      return text;
    }

    public Icon getIcon(){
      return icon;
    }
  }

  class MyCellRenderer extends JLabel implements ListCellRenderer{

    /**
     *
     */
    private static final long serialVersionUID = 8321430207533611819L;

    MyCellRenderer(){
      setOpaque(true);
    }

    public Component getListCellRendererComponent(
            JList list,
            Object value,
            int index,
            boolean isSelected,
            boolean cellHasFocus){

      ComboLabel data = (ComboLabel)value;
      setText(data.getText());
      setIcon(data.getIcon());

      if (isSelected){
        setForeground(Color.white);
        setBackground(Color.black);
      }else{
        setForeground(Color.black);
        setBackground(Color.white);
      }

      return this;
    }
  }
}