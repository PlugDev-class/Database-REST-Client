package de.plugdev.client.popupUi;

import javax.swing.*;
import java.awt.event.*;

public class LoginPanel extends JDialog {
    private JPanel contentPane;
    private JButton buttonOkay;
    private JPasswordField passwordField1;

    public LoginPanel() {
        setContentPane(contentPane);
        setModal(true);

        buttonOkay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        LoginPanel dialog = new LoginPanel();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
