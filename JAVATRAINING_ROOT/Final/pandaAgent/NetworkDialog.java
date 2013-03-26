package pandaAgent;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class NetworkDialog extends JDialog implements ActionListener, ChangeListener
{
    private static final long serialVersionUID = -263842725382458799L;
    private final PandaAgent pandaAgent;
    private boolean newProxyEnable;

    // Component
    GridBagLayout layout = new GridBagLayout();
    GridBagConstraints gbc = new GridBagConstraints();
    JCheckBox checkBox;
    JLabel host = new JLabel("Proxy Host: ");
    JTextField hostField;
    JLabel port = new JLabel("Proxy Port: ");
    JTextField portField;
    JLabel username = new JLabel("Username: ");
    JTextField usernameField;
    JLabel password = new JLabel("Password: ");
    JPasswordField passwordField;
    JButton okButton = new JButton("OK");
    JButton cancelButton = new JButton("Cancel");

    public NetworkDialog(PandaAgent owner)
    {
        super(owner);
        pandaAgent = owner;

        // Load parameter
        checkBox = new JCheckBox("Use Proxy?", pandaAgent.isProxyEnable());
        newProxyEnable = pandaAgent.isProxyEnable();
        hostField = new JTextField(pandaAgent.getProxyHost(), 20);
        portField = new JTextField(String.valueOf(pandaAgent.getProxyPort()), 20);
        usernameField = new JTextField(pandaAgent.getProxyUsername(), 20);
        passwordField = new JPasswordField(pandaAgent.getProxyPassword(), 20);


        this.setLayout(layout);
        this.setTitle("Network Property");
        this.addComponent(checkBox, 0, 0, 2, 1, GridBagConstraints.CENTER);
        this.add(checkBox);
        this.addComponent(host, 0, 1, 1, 1, GridBagConstraints.EAST);
        this.add(host);
        this.addComponent(hostField, 1, 1, 1, 1, GridBagConstraints.WEST);
        this.add(hostField);
        this.addComponent(port, 0, 2, 1, 1, GridBagConstraints.EAST);
        this.add(port);
        this.addComponent(portField, 1, 2, 1, 1, GridBagConstraints.WEST);
        this.add(portField);
        this.addComponent(username, 0, 3, 1, 1, GridBagConstraints.EAST);
        this.add(username);
        this.addComponent(usernameField, 1, 3, 1, 1, GridBagConstraints.WEST);
        this.add(usernameField);
        this.addComponent(password, 0, 4, 1, 1, GridBagConstraints.EAST);
        this.add(password);
        this.addComponent(passwordField, 1, 4, 1, 1, GridBagConstraints.WEST);
        this.add(passwordField);
        this.addComponent(okButton, 0, 5, 1, 1, GridBagConstraints.CENTER);
        this.add(okButton);
        this.addComponent(cancelButton, 1, 5, 1, 1, GridBagConstraints.CENTER);
        this.add(cancelButton);
        this.setSize(360, 200);
        this.setResizable(false);

        checkBox.addChangeListener(this);
        okButton.addActionListener(this);
        cancelButton.addActionListener(this);
    }

    private void addComponent(JComponent c, int x, int y, int w, int h, int anchor)
    {
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = w;
        gbc.gridheight = h;
        gbc.anchor = anchor;
        layout.setConstraints(c, gbc);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == okButton)
        {
            try
            {
                // If portField has NOT int value, throw Exception.
                pandaAgent.setProxyPort(Integer.parseInt(portField.getText()));
                pandaAgent.setProxyEnable(newProxyEnable);
                pandaAgent.setProxyHost(hostField.getText());
                pandaAgent.setProxyUsername(usernameField.getText());
                pandaAgent.setProxyPassword(new String(passwordField.getPassword()));
                this.setVisible(false);
            }
            catch(NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(null, "ポート番号は数値を入力してください。");
            }

        }
        if (e.getSource() == cancelButton)
        {
            this.setVisible(false);
            checkBox.setSelected(pandaAgent.isProxyEnable());
            hostField.setText(pandaAgent.getProxyHost());
            portField.setText(String.valueOf(pandaAgent.getProxyPort()));
            usernameField.setText(pandaAgent.getProxyUsername());
            passwordField.setText((pandaAgent.getProxyPassword()));
        }
    }

    @Override
    public void stateChanged(ChangeEvent e)
    {
        newProxyEnable = ((JCheckBox)e.getSource()).isSelected();
    }
}
