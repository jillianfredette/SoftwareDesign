import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.metal.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.TimeUnit;


public class CodeField extends JEditorPane implements ActionListener, KeyListener {

    private String ProjectName;
    private String ProjectPath;
    private String ProjectSDKPath;
    private String FileName;

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
    public void keyPressed(KeyEvent e){
        if ((e.getKeyCode() == KeyEvent.VK_S) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
//        if ((e.getKeyCode() == KeyEvent.VK_F4)) {
            try {
                String filePath = ProjectPath + "/" + ProjectName + "/src/" + FileName;
                FileWriter out = new FileWriter(filePath);
                System.out.println(filePath);
                out.write(this.getText());
                out.close();
            } catch (Exception f) {
                f.printStackTrace();
            }

        }

    }

    public void keyTyped(KeyEvent e) {

    }
}
