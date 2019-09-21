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

    private String ProjectName;
    private String ProjectPath;
    private String ProjectSDKPath;
    private JTextField textField1;


    public NewProjectFrame() {
        // Create Frame
        super("New Project");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.getContentPane().setBackground(Color.WHITE);
        JPanel InnerPanel = new JPanel();
        this.add(InnerPanel, BorderLayout.WEST);
        InnerPanel.setBackground(Color.WHITE);
        InnerPanel.setPreferredSize(new Dimension(800, 400));
//        InnerPanel.setLocation(50, 50);
        BoxLayout boxLayout = new BoxLayout(InnerPanel, BoxLayout.Y_AXIS);
        InnerPanel.setLayout(boxLayout);
        InnerPanel.add(Box.createRigidArea(new Dimension(10, 40)));

        // Project Name Label
        JLabel ProjectNameLabel = new JLabel("Project Name: ");
        InnerPanel.add(ProjectNameLabel, BorderLayout.WEST);
        InnerPanel.add(Box.createRigidArea(new Dimension(10, 10)));

        // Inner Panel for Project Name Field
        JPanel InnerPanel1 = new JPanel();
        FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT);
        InnerPanel1.setLayout(flowLayout);
//        InnerPanel.add(InnerPanel1);
        InnerPanel1.setBackground(Color.WHITE);
        InnerPanel1.setPreferredSize(new Dimension(300, 65));
        InnerPanel.add(InnerPanel1, BorderLayout.WEST);
        InnerPanel1.setAlignmentX(LEFT_ALIGNMENT);

        // Project Name Field
        textField1 = new JTextField(20);
        textField1.setAlignmentX(LEFT_ALIGNMENT);

        InnerPanel1.add(textField1, BorderLayout.WEST);
        InnerPanel.add(Box.createRigidArea(new Dimension(10, 40)));
//        ProjectNameLabel.setPreferredSize(new Dimension(200, 50));

        // Location Label
        JLabel LocationLabel = new JLabel("Project Location: ");
        InnerPanel.add(LocationLabel, BorderLayout.WEST);
        InnerPanel.add(Box.createRigidArea(new Dimension(10, 10)));

        // Choose Location button
        JButton LocationButton = new JButton("Choose Project Location");
        InnerPanel.add(LocationButton);
        InnerPanel.add(Box.createRigidArea(new Dimension(10, 10)));

        // Project Location Text Label
        JLabel DestinationLabel = new JLabel(" ");
        InnerPanel.add(DestinationLabel);
        InnerPanel.add(Box.createRigidArea(new Dimension(10, 40)));

        // Sec action for "Choose Project Location" button
        LocationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

//                JFrame saveProjectFrame = new JFrame();

                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Choose Project Location");

                // Set the selection mode to directories only
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                int option = fileChooser.showOpenDialog(null);
                if(option == JFileChooser.APPROVE_OPTION){
                    File file = fileChooser.getSelectedFile();
                    ProjectPath = file.getPath();
                    System.out.println(ProjectPath);
                    DestinationLabel.setText(ProjectPath);
                }
            }
        });

        // Project SDKs
        JLabel SDKLabel = new JLabel("Project SDKs ");
        InnerPanel.add(SDKLabel, BorderLayout.WEST);
        InnerPanel.add(Box.createRigidArea(new Dimension(10, 10)));

        // Choose Location button
        JButton SDKButton = new JButton("Choose Project SDKs:");
        InnerPanel.add(SDKButton);
        InnerPanel.add(Box.createRigidArea(new Dimension(10, 10)));

        // Project Location Text Label
        JLabel SDKOptionLabel = new JLabel(" ");
        InnerPanel.add(SDKOptionLabel);
        InnerPanel.add(Box.createRigidArea(new Dimension(10, 200)));

        // Sec action for "Choose Project Location" button
        SDKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

//                JFrame saveProjectFrame = new JFrame();

                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Choose Project SDKs");

                // Set the selection mode to directories only
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                int option = fileChooser.showOpenDialog(null);
                if(option == JFileChooser.APPROVE_OPTION){
                    File file = fileChooser.getSelectedFile();
                    ProjectSDKPath = file.getPath();
                    System.out.println(ProjectSDKPath);
                    SDKOptionLabel.setText(ProjectSDKPath);
                }

            }
        });

        JButton button1 = new JButton("Submit");
        InnerPanel.add(button1, BorderLayout.SOUTH);
        InnerPanel.add(Box.createRigidArea(new Dimension(10, 280)));
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ProjectName = textField1.getText();

                System.out.println(ProjectName);
                System.out.println(ProjectPath);
                System.out.println(ProjectSDKPath);

                File newProjectFolder = new File(ProjectPath + '/' + ProjectName);

                try {
                    if (newProjectFolder.mkdir()) {
                        System.out.println("Folder is created");
                        setVisible(false);

//                        dispose();

                    } else {
                        JFrame openProjectFrame;
                        openProjectFrame = new JFrame("Folder Exists");
                        JOptionPane.showMessageDialog(openProjectFrame, "Folder Name Already Exists.");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

    }

    public String getProjectName() {
        ProjectName = textField1.getText();
        return ProjectName;
    }

    public String getProjectPath() {
        return ProjectPath;
    }

    public String getProjectSDKPath() {
        return ProjectSDKPath;
    }

    public void actionPerformed(ActionEvent e) {
        String buttonString = e.getActionCommand();
    }


}


