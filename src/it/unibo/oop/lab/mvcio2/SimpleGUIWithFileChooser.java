package it.unibo.oop.lab.mvcio2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    private final Controller c = new Controller();
    private final JFrame frame = new JFrame("My second Java graphical interface");

    /*
     * Starting from the application in mvcio:
     * 
     * 1) Add a JTextField and a button "Browse..." on the upper part of the
     * graphical interface. Suggestion: use a second JPanel with a second
     * BorderLayout, put the panel in the North of the main panel, put the text
     * field in the center of the new panel and put the button in the line_end of
     * the new panel.
     * 
     * 2) The JTextField should be non modifiable. And, should display the current
     * selected file.
     * 
     * 3) On press, the button should open a JFileChooser. The program should use
     * the method showSaveDialog() to display the file chooser, and if the result is
     * equal to JFileChooser.APPROVE_OPTION the program should set as new file in
     * the Controller the file chosen. If CANCEL_OPTION is returned, then the
     * program should do nothing. Otherwise, a message dialog should be shown
     * telling the user that an error has occurred (use
     * JOptionPane.showMessageDialog()).
     * 
     * 4) When in the controller a new File is set, also the graphical interface
     * must reflect such change. Suggestion: do not force the controller to update
     * the UI: in this example the UI knows when should be updated, so try to keep
     * things separated.
     */

    public SimpleGUIWithFileChooser() {
        final JPanel canvas = new JPanel();
        canvas.setLayout(new BorderLayout());
        final JTextField browseField = new JTextField(c.getPath());
        browseField.setEditable(false);
        final JButton browse = new JButton("Browse...");
        final JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        canvas.add(panel, BorderLayout.NORTH);
        panel.add(browseField, BorderLayout.CENTER);
        panel.add(browse, BorderLayout.EAST);
        final JTextArea box = new JTextArea();
        canvas.add(box, BorderLayout.CENTER);
        final JButton save = new JButton("Save on file");
        canvas.add(save, BorderLayout.SOUTH);
        save.addActionListener(new ActionListener() {
            @Override
            /**
             * Saves the content of the Text Area in the file
             */
            public void actionPerformed(final ActionEvent e) {
                try {
                    c.writeOnFile(box.getText());
                } catch (final IOException e1) {
                    e1.printStackTrace();
                }

            }
        });

        browse.addActionListener(new ActionListener() {

            @Override
            /**
             * Implements the file chooser that uses the file chosen by the user
             */
            public void actionPerformed(final ActionEvent e) {
                final JFileChooser dialog = new JFileChooser();
                dialog.setFileSelectionMode(JFileChooser.FILES_ONLY);
                final var output = dialog.showSaveDialog(dialog);
                if (JFileChooser.APPROVE_OPTION == output) {
                    c.setFile(dialog.getSelectedFile());
                    browseField.setText(dialog.getSelectedFile().toString());

                } else if (JFileChooser.CANCEL_OPTION != output) {
                    JOptionPane.showMessageDialog(dialog, "An error has occurred");
                }
            }
        });

        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / 2, sh / 2);
        frame.setContentPane(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);

    }

    public static void main(final String... args) {
        new SimpleGUIWithFileChooser();

    }

}
