import java.awt.Dimension;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main extends JFrame {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public Main() {
        // Create icon "C"
        JButton jb = new JButton("C");
        jb.setMargin(new Insets(0, 0, 0, 0));
        jb.setBounds(245, 2, 18, 18);

        // Create combo box
        String[] languages = new String[]{"Java", "C#", "PHP"};
        JComboBox jc = new JComboBox(languages);
        // jc.setEditable(true);
        jc.add(jb);

        getContentPane().add(jc);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(300, 58));
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final Main main = new Main();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                main.setVisible(true);
            }
        });
    }
}