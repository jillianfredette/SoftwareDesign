import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class NewProjectFrame extends JFrame implements ActionListener {

    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton button1;

    public NewProjectFrame() {
        // Create Frame
        super ("New Project Folder");
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
        textField1 = new JTextField(15);
        textField2 = new JTextField(15);
        textField3 = new JTextField(15);
        JLabel ProjectNameLabel = new JLabel("Project Name: ");
        InnerPanel.add(ProjectNameLabel, BorderLayout.NORTH);
        InnerPanel.add(textField1);
        ProjectNameLabel.setPreferredSize(new Dimension(200, 50));

        // Location Label
        JLabel LocationLabel = new JLabel("Location: ");
        InnerPanel.add(LocationLabel);
        InnerPanel.add(textField2);
        LocationLabel.setPreferredSize(new Dimension(200, 50));

        // Interpreter Label

        JButton button1 = new JButton("Submit");
        InnerPanel.add(button1);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                final String kevin = textField1.getText();
                final String kevins = textField2.getText();
                final String kevinss = textField3.getText();

                System.out.println(kevin); // name of folder
                System.out.println(kevins);// name of location
                System.out.println(kevinss);
                final String interpreter;


                File file = new File(kevins+ '/' + kevin); // create file


                try {
                    if(file.mkdir()){
                        System.out.println("Folder is created");

                        dispose();
                   //     JButton open = new JButton();
                   //     JFileChooser OpenFile = new JFileChooser();

                      //  OpenFile.setCurrentDirectory(new java.io.File("C:"));
                    //    OpenFile.setDialogTitle("Hello World");
                      //  OpenFile.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                      //  if(OpenFile.showOpenDialog(open) == JFileChooser.APPROVE_OPTION){
                    //       File hi = OpenFile.getSelectedFile();
                   //         Scanner input = new Scanner(file);

                  //          while(input.hasNext()){

                  //          }
                      //  }

                    }else{
                        System.out.println("Folder Already exists");
                        JFrame openProjectFrame;
                        openProjectFrame = new JFrame("Folder Exists");
                        JOptionPane.showMessageDialog(openProjectFrame, "Folder Exists");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });



    }

    public void actionPerformed(ActionEvent e) {
        String buttonString = e.getActionCommand();

    }

}


