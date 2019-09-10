import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.TimeUnit;

public class NewProjectFrame extends JFrame implements ActionListener {

    public NewProjectFrame() {
        // Create Frame
        super ("New Project");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.getContentPane().setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        // Inner Panel
        JPanel InnerPanel = new JPanel();
        this.add(InnerPanel, BorderLayout.WEST);
        InnerPanel.setBackground(Color.WHITE);
        InnerPanel.setPreferredSize(new Dimension(200, 200));

        // Project Name Label
        JLabel ProjectNameLabel = new JLabel("Project Name: ");
        InnerPanel.add(ProjectNameLabel, BorderLayout.NORTH);
        ProjectNameLabel.setPreferredSize(new Dimension(200, 50));

        // Location Label
        JLabel LocationLabel = new JLabel("Location: ");
        InnerPanel.add(LocationLabel);
        LocationLabel.setPreferredSize(new Dimension(200, 50));

        // Interpreter Label
        JLabel InterpreterLabel = new JLabel("Interpreter: ");
        InnerPanel.add(InterpreterLabel, BorderLayout.SOUTH);
        InterpreterLabel.setPreferredSize(new Dimension(200, 50));
    }

    public void actionPerformed(ActionEvent e) {
        String buttonString = e.getActionCommand();
    }
}


