import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.metal.*;
import javax.swing.text.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.TimeUnit;


public class CodeField extends JTextPane implements ActionListener, KeyListener {

    private String ProjectName;
    private String ProjectPath;
    private String ProjectSDKPath;
    private String FileName;

    public StyleContext context = new StyleContext();
   public  Style style = context.addStyle("test", null);


    public CodeField(String theProjectName, String theProjectPath, String theFileName) {
        super();
        ProjectName = theProjectName;
        ProjectPath = theProjectPath;
        FileName = theFileName;

        this.setBackground(Color.WHITE);
        this.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.lightGray));
        this.setPreferredSize(new Dimension(700, 1000));
        this.addKeyListener(this);


//        JScrollPane scroll = new JScrollPane(this);
//        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
//        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//        this.add(scroll, BorderLayout.CENTER);

    }


    public void actionPerformed(ActionEvent e) {
        String buttonString = e.getActionCommand();
    }

    public void keyReleased(KeyEvent e) {

    }

    // Save Code by pressing Ctrl + S
    public void keyPressed(KeyEvent e) {
        if ((e.getKeyCode() == KeyEvent.VK_S) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
//        if ((e.getKeyCode() == KeyEvent.VK_F4)) {
            try {
                String filePath = ProjectPath + "/" + ProjectName + "/src/" + FileName;
                FileWriter out = new FileWriter(filePath);
                System.out.println(filePath);
                String text = this.getText();
                System.out.println(text);
                out.write(text);
                out.close();
            } catch (Exception f) {
                f.printStackTrace();
            }

        }

    }

    public void keyTyped(KeyEvent e) {
        String[] kevin = new String[10];
        kevin[0] = "+";
        kevin[1] = "-";
        kevin[2] = "/";
        kevin[3] = "||";
        String length = this.getText();
        int stringlength = length.length() - 1;

        if (this.getText().charAt(stringlength) == '+' || this.getText().charAt(stringlength) == '-' || this.getText().charAt(stringlength) == '*' || this.getText().charAt(stringlength) == '/' || (this.getText().charAt(stringlength) == '|' && this.getText().charAt(stringlength - 1) == '|') || (this.getText().charAt(stringlength) == '&' && this.getText().charAt(stringlength - 1) == '&')) {
            if ((this.getText().charAt(stringlength) == '|' && this.getText().charAt(stringlength - 1) == '|')) {
                StyleConstants.setForeground(style, Color.red);
                //CodeField.setForeground(Color.red);
                this.getStyledDocument().setCharacterAttributes(stringlength - 1, stringlength, style, false);
                //CodeField.setForeground(Color.black);
                StyleConstants.setForeground(style, Color.red); // changes it to red
                this.setForeground(Color.black);
            }
            if ((this.getText().charAt(stringlength) == '&' && this.getText().charAt(stringlength - 1) == '&')) {
                StyleConstants.setForeground(style, Color.red);
                //CodeField.setForeground(Color.red);

                this.getStyledDocument().setCharacterAttributes(stringlength - 1, stringlength, style, false);
                //CodeField.setForeground(Color.black);
                StyleConstants.setForeground(style, Color.red); // changes it to red
                this.setForeground(Color.black);
            }
            StyleConstants.setForeground(style, Color.red);
            //CodeField.setForeground(Color.red);

            this.getStyledDocument().setCharacterAttributes(stringlength, stringlength, style, false);
            //CodeField.setForeground(Color.black);
            StyleConstants.setForeground(style, Color.red); // changes it to red
            this.setForeground(Color.black);
            StyleConstants.setForeground(style, Color.black);


        }


    }
}