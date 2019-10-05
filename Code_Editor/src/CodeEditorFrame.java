import com.sun.deploy.util.ArrayUtil;
import com.sun.tools.javac.util.ArrayUtils;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.metal.*;
import javax.swing.text.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.lang.StringBuilder;
import java.util.ArrayList;

import static java.awt.BorderLayout.WEST;


public class CodeEditorFrame extends JFrame implements ActionListener {

    CodeField theCodeField;
    private ProjectTree theProjectTree;
    private String ProjectName;
    private String ProjectPath;
    private String ProjectSDKPath;
    private NewProjectFrame theNewProjectFrame;


    public CodeEditorFrame() {
        // Create Frame
        super ("Code Editor");
        setSize(1500, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        try {
            // Set look and feel
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");

            // Set theme to ocean
            MetalLookAndFeel.setCurrentTheme(new OceanTheme());
        }
        catch (Exception e) {

        }

        // Menu Bar
        JMenuBar MenuBar = new JMenuBar();
        this.setJMenuBar(MenuBar);
        MenuBar.setPreferredSize(new Dimension(1500, 40));

        // File Menu
        JMenu FileMenu = new JMenu("File");
        //FileMenu.addActionListener(Project);

        JMenuItem NewProject = new JMenuItem("New Project");
        FileMenu.add(NewProject);
        NewProject.addActionListener(this);

        JMenuItem OpenProject = new JMenuItem("Open Project");
        FileMenu.add(OpenProject);
        OpenProject.addActionListener(this);

        JMenuItem SaveProject = new JMenuItem("Save Project");
        FileMenu.add(SaveProject);
        SaveProject.addActionListener(this);

        JMenuItem EditProject = new JMenuItem("Edit Project");
        FileMenu.add(EditProject);

        JMenuItem CloseProject = new JMenuItem("Close Project");
        FileMenu.add(CloseProject);
        CloseProject.addActionListener(this);

        // Run Menu
        JMenu RunMenu = new JMenu("Run");
        JMenuItem CompileProject = new JMenuItem("Compile Project");
        RunMenu.add(CompileProject);
        CompileProject.addActionListener(this);

        JMenuItem ExecuteProject = new JMenuItem("Execute Project");
        RunMenu.add(ExecuteProject);
        ExecuteProject.addActionListener(this);

        MenuBar.add(FileMenu);
        MenuBar.add(RunMenu);


         // Statistic Panel
        JPanel StatPanel = new JPanel();
        this.add(StatPanel, BorderLayout.EAST);
        StatPanel.setBackground(Color.WHITE);
        StatPanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.lightGray));
        StatPanel.setPreferredSize(new Dimension(400, 800));

        // Code Executing Panel
        JPanel ExecPanel = new JPanel();
        ExecPanel.setLayout(new FlowLayout());
        this.add(ExecPanel, BorderLayout.SOUTH);
        ExecPanel.setBackground(Color.WHITE);
        ExecPanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.lightGray));
        ExecPanel.setPreferredSize(new Dimension(1500, 200));




/*========================================================================================================================================
        RIGHT CLICK DELETE/EDIT FUNCTIONALITY
        WORKS FOR COMPILER AND STATS SECTION BUT NOT THE FILE BROWSER SECTION
        JPopupMenu rMenu = new JPopupMenu();
        JMenuItem delete = new JMenuItem("Delete");

        addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            if(e.getButton() == MouseEvent.BUTTON3){
                rMenu.show(e.getComponent(), e.getX(), e.getY());
                System.out.println("Right Click, Activated");
                }
            }
        });

        delete.addActionListener(new ActionListener() {
            @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    System.out.println("This item has been deleted!");
                }
        });
        rMenu.add(delete);
========================================================================================================================================*/
    }

    public void displayNewProject (String theProjectName, String theProjectPath, String theProjectSDK) {

        ProjectName = theProjectName;
        ProjectPath = theProjectPath;
        ProjectSDKPath = theProjectSDK;

        // Create Project Tree
        theProjectTree = new ProjectTree();
        theProjectTree.createProjectTree(this, ProjectName, ProjectPath);
        this.add(theProjectTree, WEST);
        theProjectTree.setVisible(false);
        theProjectTree.setVisible(true);
/*
        CodeField theCodeField = new CodeField();
        theCodeField.addStyle();
        this.add(theCodeField, BorderLayout.CENTER);
*/
    }

    public void newProject() {
        // create frame using NewProjectFrame class
        theNewProjectFrame = new NewProjectFrame(this);
        theNewProjectFrame.setVisible(true);



//        CodeField theCodeField = new CodeField();
//        this.add(theCodeField, BorderLayout.CENTER);


        // write your code for New Project functionality here


    }

    public void openProject() {
        // create frame
        JFrame openProjectFrame = new JFrame("Open Project");

        // Create the File Chooser
        JFileChooser theFileChooser = new JFileChooser("f");

        // Opens only Directories
        theFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        // Invoke the showsOpenDialog function to show the save dialog
        int option = theFileChooser.showOpenDialog(this);

        // Opens the file the user selects if the file is acceptable
        if (option == JFileChooser.APPROVE_OPTION) {
                // Gets the selected directory
                File theDirectory = theFileChooser.getSelectedFile();

                ProjectPath = theDirectory.getPath();

                // Reads the filename and opens the file
                //FileReader reader = new FileReader(fileName);

                // Create Project Tree
                theProjectTree = new ProjectTree();
                theProjectTree.openProjectTree(ProjectPath);
                this.add(theProjectTree, WEST);
                theProjectTree.setVisible(false);
                theProjectTree.setVisible(true);
                /*
                // Displays the file
                BufferedReader scan = new BufferedReader(reader);
                theCodeField.read(scan,null);
                scan.close();
                theCodeField.requestFocus();
                */
                //theCodeField.addStyle();
        }
        // If the user cancelled the operation
        else
            JOptionPane.showMessageDialog(openProjectFrame, "the user cancelled the operation");

    }

    public void saveProject() {
        // write your code for Save Project functionality here
        JFrame saveProjectFrame = new JFrame("Save Project");
        JFileChooser fileChooser = new JFileChooser("f");
        JLabel saveLabel = new JLabel();

        // Set the selection mode to directories only
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        // Invoke the showsOpenDialog function to show the save dialog
        int saveDialog = fileChooser.showSaveDialog(null);

        if (saveDialog == JFileChooser.APPROVE_OPTION) {
            // set the label to the path of the selected directory
            saveLabel.setText(fileChooser.getSelectedFile().getAbsolutePath());
        }
        // if the user cancelled the operation
        else
//            saveLabel.setText("the user cancelled the operation");
            JOptionPane.showMessageDialog(saveProjectFrame, "the user cancelled the operation");

    }

    public void compileProject() {

        com.sun.tools.javac.Main javac = new com.sun.tools.javac.Main();

        // create "out" folder
        File outputFolder = new File(ProjectPath + '/' + ProjectName + '/' + "out");
        outputFolder.mkdir();
        String outputPath = ProjectPath + '/' + ProjectName + '/' + "out";

        // get all java file names in the "src" folder
        File folder = new File(ProjectPath + '/' + ProjectName + '/' + "src");
        File[] listOfFiles = folder.listFiles();
        ArrayList<String> listOfJavaFiles = new ArrayList<String>();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                String fileName = listOfFiles[i].getName();

                if (fileName.length() > 5) {
                    String lastFiveDigits = "";     //substring containing last 5 characters
                    lastFiveDigits = fileName.substring(fileName.length() - 5);

                    if (lastFiveDigits.equals(".java")){
                        listOfJavaFiles.add(fileName);

                    }
                }
            }
        }

        //        String fileToCompile = ProjectPath + '/' + ProjectName + "/src/Main.java";
        String[] args = new String[listOfJavaFiles.size() + 2];
        args[0] =  "-d";
        args[1] = outputPath;
        for (int i=0; i<listOfJavaFiles.size(); i++){
            args[i+2] = ProjectPath + '/' + ProjectName + "/src/" + listOfJavaFiles.get(i);
        }

        int status = javac.compile(args);
        System.out.println(status);
    }

    public void closeProject() {
        // Write your code for Close Project functionality here

        // Open new frame to confirm closing of project
        JFrame CloseProjectFrame = new JFrame("Close Project");
        CloseProjectFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        CloseProjectFrame.setSize(300,150);
        CloseProjectFrame.setLocation(550, 300);

        JPanel main = new JPanel();
        CloseProjectFrame.add(main);

        main.setLayout(new BoxLayout(main, BoxLayout.PAGE_AXIS));
        JPanel message = new JPanel(new BorderLayout());

        main.add(message);

        JLabel confirmClose = new JLabel("Are you sure you want to close your project?", SwingConstants.CENTER);
        message.add(confirmClose, BorderLayout.CENTER);

        JPanel buttons = new JPanel(new FlowLayout());
        main.add(buttons);

        JButton buttonsc = new JButton("Save & Close");
        JButton buttonclose = new JButton("Close");
        JButton buttoncancel = new JButton("Cancel");

        buttons.add(buttonsc);
        buttons.add(buttonclose);
        buttons.add(buttoncancel);

        // If save & close, save the project and then close it
        buttonsc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveProject();
                //close project
                theProjectTree.closeProjectTree();
                theNewProjectFrame.remove(theProjectTree);
                theNewProjectFrame.dispose();


                CloseProjectFrame.dispose();
            }
        });

        // If yes, close project
        buttonclose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //close project
                theProjectTree.closeProjectTree();
                theNewProjectFrame.remove(theProjectTree);
                theNewProjectFrame.dispose();


                CloseProjectFrame.dispose();
            }
        });

        // If cancel, end close operation
        buttoncancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CloseProjectFrame.dispose();
            }
        });

        CloseProjectFrame.setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {
        String buttonString = e.getActionCommand();

        if (buttonString.equals("New Project")) {
            newProject();
        } else if (buttonString.equals("Open Project")) {
            openProject();

        } else if (buttonString.equals("Save Project")) {
            saveProject();
        } else if (buttonString.equals("Close Project")) {
            closeProject();
        } else if (buttonString.equals("Compile Project")) {
            compileProject();
        }
    }

    public void rightClickPerformed(){
        JPopupMenu rMenu = new JPopupMenu();
        JMenuItem delete = new JMenuItem("Delete");

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getButton() == MouseEvent.BUTTON3){
                    rMenu.show(e.getComponent(), e.getX(), e.getY());
                    System.out.println("Right Click, Activated");
                }
            }
        });

        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("This item has been deleted!");
            }
        });
        rMenu.add(delete);
    }

    public String getProjectName() {
        return ProjectName;
    }

    public String getProjectPath() {
        return ProjectPath;
    }

    public String getProjectSDKPath() {
        return ProjectSDKPath;
    }

}


