import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.TimeUnit;


public class CodeEditorFrame extends JFrame implements ActionListener {

    public CodeEditorFrame() {
        // Create Frame
        super ("Code Editor");
        setSize(1500, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        // Menu Bar
        JMenuBar MenuBar = new JMenuBar();
        this.setJMenuBar(MenuBar);
        MenuBar.setBackground(Color.lightGray);
        MenuBar.setPreferredSize(new Dimension(1500, 50));

        // File Menu
        JMenu FileMenu = new JMenu("File");
//        FileMenu.addActionListener(Project);

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
        JMenuItem RemoveFile = new JMenuItem("Remove File");
        FileMenu.add(RemoveFile);
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
        JPanel ProjectPanel = new JPanel();
//        ProjectPanel = new JPanel();
        this.add(ProjectPanel, BorderLayout.WEST);
        ProjectPanel.setBackground(Color.WHITE);
        ProjectPanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.lightGray));
        ProjectPanel.setPreferredSize(new Dimension(400, 800));

        // Code Field
        JTextField CodeField = new JTextField();
        this.add(CodeField, BorderLayout.CENTER);
        CodeField.setBackground(Color.WHITE);
        CodeField.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.lightGray));
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

    public void newProject() {
        // create frame using NewProjectFrame class
        NewProjectFrame theNewProjectFrame = new NewProjectFrame();
        theNewProjectFrame.setVisible(true);

        // write your code for New Project functionality here


    }

    public void openProject() {
        // create frame using OpenProjectFrame class
        OpenProjectFrame theOpenProjectFrame = new OpenProjectFrame();
        theOpenProjectFrame.setVisible(true);

        // write your code for Open Project functionality here
        //

    }

    public void saveProject() {
        // write your code for Save Project functionality here

    }

    public void closeProject() {
        // write your code for Close Project functionality here

    }



}
