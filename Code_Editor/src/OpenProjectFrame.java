import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.TimeUnit;

public class OpenProjectFrame extends JFrame implements ActionListener {

    public OpenProjectFrame() {
        // Create Frame
        super ("Open Project");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.getContentPane().setBackground(Color.WHITE);
        setLayout(new BorderLayout());


    }

    public void actionPerformed(ActionEvent e) {
        String buttonString = e.getActionCommand();
    }
}
