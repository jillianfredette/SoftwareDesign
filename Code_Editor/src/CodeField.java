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


public class CodeField extends JEditorPane implements ActionListener {

    public CodeField() {
        super();
        this.setBackground(Color.WHITE);
        this.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.lightGray));
        this.setPreferredSize(new Dimension(700, 1000));


//        JScrollPane scroll = new JScrollPane(this);
//        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
//        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//        this.add(scroll, BorderLayout.CENTER);

    }


    public void actionPerformed(ActionEvent e) {
        String buttonString = e.getActionCommand();
    }
}
