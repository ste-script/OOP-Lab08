package it.unibo.oop.lab.mvcio2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import it.unibo.oop.lab.mvcio.Controller;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {
    private final JFrame frame = new JFrame();
    private final Controller c = new Controller();
    /*
     * Starting from the application in mvcio:
     * 
     * 1) Add a JTextField and a button "Browse..." on the upper part of the
     * graphical interface.
     * Suggestion: use a second JPanel with a second BorderLayout, put the panel
     * in the North of the main panel, put the text field in the center of the
     * new panel and put the button in the line_end of the new panel.
     * 
     * 2) The JTextField should be non modifiable. And, should display the
     * current selected file.
     * 
     * 3) On press, the button should open a JFileChooser. The program should
     * use the method showSaveDialog() to display the file chooser, and if the
     * result is equal to JFileChooser.APPROVE_OPTION the program should set as
     * new file in the Controller the file chosen. If CANCEL_OPTION is returned,
     * then the program should do nothing. Otherwise, a message dialog should be
     * shown telling the user that an error has occurred (use
     * JOptionPane.showMessageDialog()).
     * 
     * 4) When in the controller a new File is set, also the graphical interface
     * must reflect such change. Suggestion: do not force the controller to
     * update the UI: in this example the UI knows when should be updated, so
     * try to keep things separated.
     */
    SimpleGUIWithFileChooser() {
        //Frame setup
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / 2, sh / 2);

        //panel1 setup
        final JPanel panel = new JPanel(new BorderLayout());
        frame.setContentPane(panel);
        final JButton save = new JButton("Save");
        final JTextArea textArea = new JTextArea();
        panel.add(textArea, BorderLayout.CENTER);
        panel.add(save, BorderLayout.SOUTH);

        //panel2 setup
        final JPanel panel2 = new JPanel(new BorderLayout());
        final JButton browse = new JButton("Browse...");
        final JTextField textField = new JTextField();
        textField.setEditable(false);
        textField.setText(c.getFile().getPath());
        panel2.add(textField, BorderLayout.CENTER);
        panel2.add(browse, BorderLayout.LINE_END);
        panel.add(panel2, BorderLayout.NORTH);

        frame.setLocationByPlatform(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        //listeners
        save.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent arg0) {
                try {
                    c.writeString(textArea.getText());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });

        browse.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent arg0) {
                final File f;
                final JFileChooser fileChooser = new JFileChooser();
                final int choice = fileChooser.showSaveDialog(textField);
                if (choice == JFileChooser.APPROVE_OPTION) {
                    f = fileChooser.getSelectedFile();
                    c.setFile(f);
                    textField.setText(c.getFile().getPath());
                    return;
                }
                if (choice == JFileChooser.CANCEL_OPTION) {
                    return;
                }
                JOptionPane.showMessageDialog(textField, "ERROR");
            }
        });

    }

    public static void main(final String... args) {
        new SimpleGUIWithFileChooser();
    }

}
