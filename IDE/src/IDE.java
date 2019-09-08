import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.io.*;







public class IDE {

    final String inputPath = "Users/mitalpatel/Documents/School/Senior Year/Software Design/Assets/";

    private static void createWindow() {

        JFrame frame1 = new JFrame("Yeet");
        frame1.setPreferredSize(new Dimension(500, 500));
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JMenuBar mb = new JMenuBar();
        JMenu m = new JMenu("File");
        JMenuItem mi1 = new JMenuItem("Save");
        JMenuItem mi2 = new JMenuItem("Load");
        JMenuItem mi3 = new JMenuItem("Exit");


        m.add(mi1);
        m.add(mi2);
        m.add(mi3);
        mb.add(m);
        frame1.setJMenuBar(mb);

        JLabel l1 = new JLabel("Words");
        JLabel l2 = new JLabel("Characters");

        frame1.add(l1);
        frame1.add(l2);

        //add(l1, SwingConstants.CENTER);
        l1.setBounds(50,25,100,30);
        l2.setBounds(160,25,100,30);



        Container content = frame1.getContentPane();
        JEditorPane editor1 = new JEditorPane();
        JTextArea textArea = new JTextArea();
        textArea.setBounds(20,75,250,200);
        JScrollPane pane = new JScrollPane(textArea);
        content.add(pane, BorderLayout.CENTER);
        frame1.setSize(350, 150);
        frame1.setVisible(true);


        mi1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.append("Words");
                String area = textArea.getText();
                String words[] = area.split("\\s");
                l1.setText("Words: " + words.length);
                l2.setText("Characters: " + area.length());
            }
        });




        frame1.setLocationRelativeTo(null);
        frame1.pack();
        frame1.setVisible(true);


    }
















































    public static void main(String[] args) {
        createWindow();
    }

}



