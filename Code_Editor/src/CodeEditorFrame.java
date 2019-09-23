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

import static java.awt.BorderLayout.WEST;


public class CodeEditorFrame extends JFrame implements ActionListener {

    JTextField CodeField;
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
        JMenuItem ExecuteProject = new JMenuItem("Execute Project");
        RunMenu.add(ExecuteProject);

        MenuBar.add(FileMenu);
        MenuBar.add(RunMenu);

        // Project Panel
//        ProjectPanel = new JPanel();
//        this.add(ProjectPanel, WEST);
//        ProjectPanel.setPreferredSize(new Dimension(200, 800));

           // Code Field


        JTextPane CodeField = new JTextPane();
        StyledDocument document = CodeField.getStyledDocument();
        StyleContext context= new StyleContext();
        Style style = context.addStyle("test", null);
        StyleConstants.setForeground(style,Color.black);

        document.addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent documentEvent) {
               // DocumentEvent.EventType type = documentEvent.getType();


                String length = CodeField.getText();
                int stringlength =length.length() -1;


                if(CodeField.getText().charAt(stringlength) == '+' ||CodeField.getText().charAt(stringlength) == '-' ||CodeField.getText().charAt(stringlength) == '*' ||CodeField.getText().charAt(stringlength) == '/'||(CodeField.getText().charAt(stringlength) == '|'&& CodeField.getText().charAt(stringlength-1) == '|' )||(CodeField.getText().charAt(stringlength) == '&'&& CodeField.getText().charAt(stringlength-1) == '&' )){

                    System.out.println(CodeField.getText().charAt(stringlength));
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {

                            if((CodeField.getText().charAt(stringlength) == '|'&& CodeField.getText().charAt(stringlength-1) == '|' )){
                                StyleConstants.setForeground(style,Color.red);
                                //CodeField.setForeground(Color.red);

                                document.setCharacterAttributes(stringlength-1,stringlength,style,false);
                                //CodeField.setForeground(Color.black);
                                StyleConstants.setForeground(style,Color.red); // changes it to red
                                CodeField.setForeground(Color.black);
                            }
                            if((CodeField.getText().charAt(stringlength) == '&'&& CodeField.getText().charAt(stringlength-1) == '&' )){
                                StyleConstants.setForeground(style,Color.red);
                                //CodeField.setForeground(Color.red);

                                document.setCharacterAttributes(stringlength-1,stringlength,style,false);
                                //CodeField.setForeground(Color.black);
                                StyleConstants.setForeground(style,Color.red); // changes it to red
                                CodeField.setForeground(Color.black);
                            }
                            StyleConstants.setForeground(style,Color.red);
                                //CodeField.setForeground(Color.red);

                               document.setCharacterAttributes(stringlength,stringlength,style,false);
                                //CodeField.setForeground(Color.black);
                            StyleConstants.setForeground(style,Color.red); // changes it to red
                            CodeField.setForeground(Color.black);
                            StyleConstants.setForeground(style,Color.black);

                        }
                    });


                }


            }
            @Override
            public void removeUpdate(DocumentEvent documentEvent) {

            }

            @Override
            public void changedUpdate(DocumentEvent documentEvent) {

            }
        });
        try {

            document.insertString(0," ",style);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        this.add(CodeField, BorderLayout.CENTER);
//        CodeField.setBackground(Color.WHITE);
//        CodeField.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.lightGray));
        CodeField.setPreferredSize(new Dimension(700, 800));





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




        //========================================================================================================================================



        // RIGHT CLICK DELETE/EDIT FUNCTIONALITY
        // WORKS FOR COMPILER AND STATS SECTION BUT NOT THE FILE BROWSERR SECTION
//        JPopupMenu rMenu = new JPopupMenu();
//        JMenuItem delete = new JMenuItem("Delete");
//
//        addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                if(e.getButton() == MouseEvent.BUTTON3){
//                    rMenu.show(e.getComponent(), e.getX(), e.getY());
//                    System.out.println("Right Click, Activated");
//                }
//            }
//        });
//
//        delete.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent actionEvent) {
//                System.out.println("This item has been deleted!");
//            }
//        });
//        rMenu.add(delete);



//========================================================================================================================================
    }




    public void actionPerformed(ActionEvent e) {
        String buttonString = e.getActionCommand();

        if (buttonString.equals("New Project")) {
            newProject();
        } else if (buttonString.equals("Open Project")) {
            this.openProject();

        } else if (buttonString.equals("Save Project")) {
            saveProject();
        } else if (buttonString.equals("Close Project")) {
            closeProject();
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


    public void displayNewProject (String theProjectName, String theProjectPath, String theProjectSDK) {

        ProjectName = theProjectName;
        ProjectPath = theProjectPath;
        ProjectSDKPath = theProjectSDK;

        // Create Project Tree
        theProjectTree = new ProjectTree(this, ProjectName, ProjectPath);
        this.add(theProjectTree, WEST);
        theProjectTree.setVisible(false);
        theProjectTree.setVisible(true);

//        CodeField theCodeField = new CodeField();
//        this.add(theCodeField, BorderLayout.CENTER);

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
        JFrame openProjectFrame;
        openProjectFrame = new JFrame("Open Project");

        // Create the File Chooser
        JFileChooser theFileChooser = new JFileChooser("f:");
        theFileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Java", "java"));

        // Invoke the showsOpenDialog function to show the save dialog
        int r = theFileChooser.showOpenDialog(this);

        // Gets the file selected using getSelectedFile function
        File theFile = theFileChooser.getSelectedFile();

        // Passes the path to a String using the getAbsolutePath function
        String fileName = theFile.getAbsolutePath();

        // Opens the file the user selects if the file is acceptable
        if (r == JFileChooser.APPROVE_OPTION) {
                try {
                    // Reads the filename and opens the file
                    FileReader reader = new FileReader(fileName);

                    // Displays the file
                    BufferedReader scan = new BufferedReader(reader);
                    CodeField.read(scan,null);
                    scan.close();
                    CodeField.requestFocus();

                }
                catch(Exception e) {
                    JOptionPane.showMessageDialog(null,e);
                }
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

    public void closeProject() {
        // write your code for Close Project functionality here

        //open new frame to confirm closing of project
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

        //if save & close, save the project and then close it
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

        //if yes, close project
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

        //if cancel, end close operation
        buttoncancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CloseProjectFrame.dispose();
            }
        });


        CloseProjectFrame.setVisible(true);

    }




}


