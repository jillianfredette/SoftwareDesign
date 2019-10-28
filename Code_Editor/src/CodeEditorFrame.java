import com.sun.codemodel.internal.JOp;
import com.sun.tools.javac.jvm.Code;

import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.nio.Buffer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static java.awt.BorderLayout.WEST;


public class CodeEditorFrame extends JFrame implements ActionListener {

    JTextArea CodeField;
    private ProjectTree theProjectTree;
    private String ProjectName;
    private String ProjectPath;
//    private String ProjectSDKPath;
    private NewProjectFrame theNewProjectFrame;
    private String outputPath;
    private JTextPane ExecutionPane;


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

        JMenuItem CountCharacters = new JMenuItem("Count Characters");
        FileMenu.add(CountCharacters);
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
        StatPanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.ORANGE));
        StatPanel.setPreferredSize(new Dimension(400, 800));
        JLabel l1 = new JLabel("Words", JLabel.LEFT);
        JLabel l2 = new JLabel("Characters");
        StatPanel.add(l1);
        StatPanel.add(l2);
        l1.setBounds(50,25,100,30);
        l2.setBounds(160,25,100,30);

        CountCharacters.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //CodeField.append("Words");

                String area = CodeField.getText();
                String words[] = area.split("\\s");
                l1.setText("Words: " + words.length);
                l2.setText("Characters: " + area.length());
            }
        });

        // Code Executing Panel
        JPanel ExecPanel = new JPanel();
        ExecPanel.setLayout(new BorderLayout());
        this.add(ExecPanel, BorderLayout.SOUTH);
        ExecPanel.setBackground(Color.WHITE);
        ExecPanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.lightGray));
        ExecPanel.setPreferredSize(new Dimension(1500, 200));
        ExecutionPane = new JTextPane();
        ExecPanel.add(ExecutionPane);



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

    public void displayNewProject (String theProjectName, String theProjectPath) {

        ProjectName = theProjectName;
        ProjectPath = theProjectPath;
//        ProjectSDKPath = theProjectSDK;

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
/*
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

                String wholeProjectPath = theDirectory.getPath();
                Path path = Paths.get(wholeProjectPath);
                ProjectName = path.getFileName().toString();
                ProjectPath = theDirectory.getParent();
                System.out.println("Here " + ProjectPath);

                // Reads the filename and opens the file
                //FileReader reader = new FileReader(path);

                // Create Project Tree
                theProjectTree = new ProjectTree();
                theProjectTree.openProjectTree(this, ProjectPath, ProjectName);
                this.add(theProjectTree, WEST);
                theProjectTree.setVisible(false);
                theProjectTree.setVisible(true);


                /*
                // Displays the file
                BufferedReader scan = new BufferedReader(reader);
                CodeField.read(scan,null);
                scan.close();
                CodeField.requestFocus();

                //theCodeField.addStyle();
        }
        // If the user cancelled the operation
        else
            JOptionPane.showMessageDialog(openProjectFrame, "the user cancelled the operation");

    }
    */

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

        String wholeProjectPath = theDirectory.getPath();
        Path path = Paths.get(wholeProjectPath);
        ProjectName = path.getFileName().toString();
        ProjectPath = theDirectory.getParent();
        System.out.println("Here " + ProjectPath);

        // Reads the filename and opens the file
        //FileReader reader = new FileReader(path);

        // Create Project Tree
        theProjectTree = new ProjectTree();
        theProjectTree.openProjectTree(this, ProjectPath, ProjectName);
        this.add(theProjectTree, WEST);
        theProjectTree.setVisible(false);
        theProjectTree.setVisible(true);


                /*
                // Displays the file
                BufferedReader scan = new BufferedReader(reader);
                CodeField.read(scan,null);
                scan.close();
                CodeField.requestFocus();
                */
        //theCodeField.addStyle();
    }
    // If the user cancelled the operation
    else
        JOptionPane.showMessageDialog(openProjectFrame, "the user cancelled the operation");

}


    // Save all files in the project
    public void saveProject() {
//        // write your code for Save Project functionality here
//        JFrame saveProjectFrame = new JFrame("Save Project");
//        JFileChooser fileChooser = new JFileChooser("f");
//        JLabel saveLabel = new JLabel();
//
//        // Set the selection mode to directories only
//        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//
//        // Invoke the showsOpenDialog function to show the save dialog
//        int saveDialog = fileChooser.showSaveDialog(null);
//
//        if (saveDialog == JFileChooser.APPROVE_OPTION) {
//            // set the label to the path of the selected directory
//            saveLabel.setText(fileChooser.getSelectedFile().getAbsolutePath());
//        }
//        // if the user cancelled the operation
//        else
////            saveLabel.setText("the user cancelled the operation");
//            JOptionPane.showMessageDialog(saveProjectFrame, "the user cancelled the operation");

        int tabCount = theProjectTree.getTabCounts();
        for (int i = 0; i < tabCount; i++){
            String fileName = theProjectTree.getTabTitle(i);
            System.out.println(fileName);
            try {
                String filePath = ProjectPath + "/" + ProjectName + "/" + fileName;
                FileWriter out = new FileWriter(filePath);
                System.out.println(filePath);
                CodeField thisCodeField = (CodeField)theProjectTree.getTabbedPane().getComponent(i);
                out.write(thisCodeField.getText());
                out.close();
            } catch (Exception f) {
                f.printStackTrace();
            }

        }



    }

    public void compileProject() {

        com.sun.tools.javac.Main javac = new com.sun.tools.javac.Main();

        // create "out" folder
        File outputFolder = new File(ProjectPath + '/' + ProjectName + '/' + "out");
        outputFolder.mkdir();
        outputPath = ProjectPath + '/' + ProjectName + '/' + "out";

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

    public void executeProject() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, ClassNotFoundException, IOException, InterruptedException, BadLocationException {

        compileProject();

        String mainClass = "";

        File outFolder = new File(outputPath);
        File[] listOfFiles = outFolder.listFiles();
        String className;

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                String fileName = listOfFiles[i].getName();
                if(fileName.substring(fileName.length() - 6).equals(".class")){
                    className = fileName.substring(0,fileName.length() - 6);
                    Process find = Runtime.getRuntime().exec("javap -cp "+outputPath+";. "+className);
                    find.waitFor();
                    BufferedReader reader2=new BufferedReader(new InputStreamReader(find.getInputStream()));
                    String line;
                    while((line = reader2.readLine()) != null){
                        if(line.length() > 25) {
                            if (line.substring(0, 25).equals("  public static void main"))
                                mainClass = className;
                        }
                    }
                }
            }
        }
        if(!mainClass.equals("")) {
            JEditorPane executionPane = new JEditorPane();
            ExecutionPane.setText("");

            StyledDocument doc = ExecutionPane.getStyledDocument();
            SimpleAttributeSet att = new SimpleAttributeSet();
            StyleConstants.setAlignment(att, StyleConstants.ALIGN_LEFT);
            ExecutionPane.setParagraphAttributes(att, true);


            Process p = Runtime.getRuntime().exec("java -cp " + outputPath + ";. " + mainClass);

            int statusCode = p.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                doc.insertString(doc.getLength(), line + "\n", null);
            }


            doc.insertString(doc.getLength(), "\n\nProcess finished with exit code " + statusCode + "\n", null);
        }
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

                CloseProjectFrame.dispose();
            }
        });

        // If yes, close project
        buttonclose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //close project
                theProjectTree.closeProjectTree();

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
        else if (buttonString.equals("Execute Project")){
            try {
                executeProject();
            } catch (InvocationTargetException ex) {
                ex.printStackTrace();
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            } catch (NoSuchMethodException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (InterruptedException ex){
                ex.printStackTrace();
            }catch (BadLocationException ex){
                ex.printStackTrace();
            }
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

//    public String getProjectSDKPath() {
//        return ProjectSDKPath;
//    }

}


