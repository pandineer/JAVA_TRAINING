import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class JPopupMenuTest extends JFrame implements ActionListener, MouseListener {
    JPopupMenu popup = new JPopupMenu();
    JPopupMenuTest() {
        JMenuItem menuA = new JMenuItem("menuA");
        JMenuItem menuB = new JMenuItem("menuB");
        menuA.addActionListener(this);
        menuB.addActionListener(this);
        popup.add(menuA);
        popup.add(menuB);

        getContentPane().addMouseListener(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("JPopupMenuTest");
        setSize(200, 100);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
    }
    public void mousePressed(MouseEvent e) {
        if (e.isPopupTrigger()) {
            popup.show(e.getComponent(), e.getX(), e.getY());
        }
    }
    public void mouseReleased(MouseEvent e) {
        if (e.isPopupTrigger()) {
            popup.show(e.getComponent(), e.getX(), e.getY());
        }
    }
    public void mouseClicked(MouseEvent e) {
    }
    public void mouseEntered(MouseEvent e) {
    }
    public void mouseExited(MouseEvent e) {
    }
    public static void main(String[] args) {
        new JPopupMenuTest();
    }
}