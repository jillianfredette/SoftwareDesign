//https://docs.oracle.com/javase/tutorial/uiswing/examples/components/TreeDemoProject/src/components/TreeDemo.java

import javax.swing.*;
import javax.swing.tree.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.nio.file.Path;
import javax.swing.event.*;


public class ProjectTree extends JPanel implements TreeSelectionListener {
    private JTree tree;
    DefaultMutableTreeNode top;
    private DefaultTreeModel treeModel;
    //Optionally set the look and feel.
    private static boolean useSystemLookAndFeel = false;

    private String ProjectName;
    private String ProjectPath;
    private String ProjectSDKPath;
    private CodeEditorFrame theCodeEditorFrame;
    private JTabbedPane tabbedPane = null;

    public ProjectTree(CodeEditorFrame theMainFrame, String theProjectName, String theProjectPath) {
        super(new GridLayout(1, 0));

        ProjectName = theProjectName;
        ProjectPath = theProjectPath;
        theCodeEditorFrame = theMainFrame;

        // Root of the tree of the Project's path
        // File fileRoot = new File(ProjectPath);
        // DefaultMutableTreeNode root = new DefaultMutableTreeNode(new FileNode(fileRoot));

        // Creates the nodes from the directory's path
        top = new DefaultMutableTreeNode(ProjectName);
        createNodes(top);

        treeModel = new DefaultTreeModel(top);

        // Creates a tree that allows one selection at a time.
        tree = new JTree(treeModel);

        // Creates the children nodes, doesn't matter if its a file or a folder
        // ChildNode ccn = new ChildNode(fileRoot, top);
        // new Thread(ccn).start();

        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

        //Listen for when the selection changes.
        tree.addTreeSelectionListener(this);

        //Create the scroll pane and add the tree to it.
        JScrollPane treeView = new JScrollPane(tree);

        //Add the tree view to this panel.
        add(treeView);


        // Show Popup on Tree
        final TreePopup treePopup = new TreePopup(tree);
        tree.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                if(e.getButton() == MouseEvent.BUTTON3) {
                    treePopup.show(e.getComponent(), e.getX(), e.getY());
                    System.out.println(e.getComponent());
                }
            }
        });
    }

    // Required by TreeSelectionListener interface.

    public void valueChanged(TreeSelectionEvent e) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                tree.getLastSelectedPathComponent();

        if (node == null) return;

        Object nodeInfo = node.getUserObject();
        if (node.isLeaf()) {
            System.out.println("Node is a leaf");
        } else {

        }
    }

    // Creates Nodes for Tree
    private void createNodes(DefaultMutableTreeNode top) {
        DefaultMutableTreeNode folder = null;
        DefaultMutableTreeNode file = null;

        folder = new DefaultMutableTreeNode("src");
        top.add(folder);

        //original Tutorial
        file = new DefaultMutableTreeNode("Main.java");
        folder.add(file);

    }

    // Popup Specifics
    class TreePopup extends JPopupMenu {
        public TreePopup(JTree tree1) {
            JMenuItem delete = new JMenuItem("Delete");
            JMenuItem add = new JMenuItem("Add File");
            JMenuItem open = new JMenuItem("Open File");
            JMenuItem close = new JMenuItem("Close File");
            JMenuItem addFolder = new JMenuItem("Add Folder");
            JTextField field1 = new JTextField("File Name");
            delete.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    System.out.println("Delete child");
                    DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getSelectionPath().getLastPathComponent();
                    DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
                    model.removeNodeFromParent(selectedNode);
                }
            });



            add.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    System.out.println("Add child");
                    DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getSelectionPath().getLastPathComponent();
                    DefaultMutableTreeNode tempRoot = selectedNode.getPreviousNode();
                    DefaultMutableTreeNode root = (DefaultMutableTreeNode) selectedNode.getRoot();
                    //System.out.println("This is first leaf: " + selectedNode.getPreviousNode());
                    // IS EMPTY OR NOT - System.out.println("This is first leaf: " + selectedNode.isLeaf());

                    // If selected node is a file, make a sibiling file
//                    if(selectedNode.isLeaf()){
//                        String input = JOptionPane.showInputDialog("New File Name");
//                        DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
//                        tempRoot.add(new DefaultMutableTreeNode(input + ".java"));
//                        model.reload();
//
//                    } else {
//                        String input = JOptionPane.showInputDialog("New Folder Name");
//                        DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
//                        DefaultMutableTreeNode folder = null;
//                        selectedNode.add(new DefaultMutableTreeNode(input));
////                        root.add(new DefaultMutableTreeNode("top" + input));
//                        model.reload();
//                    }

                    if(selectedNode.isLeaf()){

                    } else {
                        String input = JOptionPane.showInputDialog("New File Name");

                        if (input != null) {
                            DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
                            DefaultMutableTreeNode folder = null;
                            selectedNode.add(new DefaultMutableTreeNode(input));

                            File newClass = new File(ProjectPath + '/' + ProjectName + '/' + "src" + '/' + input);

                            try {
                                PrintWriter pw = null;
                                FileWriter fw = new FileWriter(newClass, true);
                                pw = new PrintWriter(fw);
                                pw.println("// This is the " + input + " file.");
                                pw.close();
                            } catch (IOException e) {
                                System.err.println("Problem writing to the file.");
                            }

                            model.reload();
                        }

                    }
                    //selectedNode.add();

                }
            });
            add(add);
            add(new JSeparator());
            add(delete);
            add(new JSeparator());

            open.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getSelectionPath().getLastPathComponent();


                    if(selectedNode.isLeaf()) {
                        String diskPath = ProjectPath + "/" + ProjectName + "/src/" + selectedNode.getUserObject().toString();
                        System.out.println(diskPath);

                        File newFile = new File(diskPath);

                        if (tabbedPane == null) {
                            tabbedPane = new JTabbedPane();
//                        tabbedPane.setUI(new SpacedTabbedPaneUI());
                            CodeField theCodeField = new CodeField(ProjectName, ProjectPath, selectedNode.getUserObject().toString());
//                        theCodeField.setEditable(false);

                            if (newFile != null) {
                                try {
                                    theCodeField.setPage(newFile.toURI().toURL());
                                }

                                catch (IOException e1) {
                                    System.err.println("Attempted to read a bad file " + newFile );
                                    e1.printStackTrace();
                                }
                            }

                            else {
                                System.err.println("Couldn't find file");
                            }

                            tabbedPane.addTab(selectedNode.getUserObject().toString(), theCodeField);

                            theCodeEditorFrame.add(tabbedPane,  BorderLayout.CENTER);
                            tabbedPane.setVisible(false);
                            tabbedPane.setVisible(true);


                        } else {
                            CodeField theCodeField = new CodeField(ProjectName, ProjectPath, selectedNode.getUserObject().toString());
//                        theCodeField.setEditable(false);

                            if (newFile != null) {
                                try {
                                    theCodeField.setPage(newFile.toURI().toURL());
                                }

                                catch (IOException e1) {
                                    System.err.println("Attempted to read a bad file " + newFile );
                                    e1.printStackTrace();
                                }
                            }

                            else {
                                System.err.println("Couldn't find file");
                            }

                            tabbedPane.addTab(selectedNode.getUserObject().toString(), theCodeField);

                            theCodeEditorFrame.add(tabbedPane,  BorderLayout.CENTER);
                            tabbedPane.setVisible(false);
                            tabbedPane.setVisible(true);

                        }





//                        theCodeEditorFrame.add(theCodeField, BorderLayout.CENTER);
//                        theCodeField.setVisible(false);
//                        theCodeField.setVisible(true);




                    }
                }
            });
            add(open);
            add(new JSeparator());

            close.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getSelectionPath().getLastPathComponent();

                    JFrame CloseProjectFrame = new JFrame("Close File");
                    CloseProjectFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    CloseProjectFrame.setSize(600,150);
                    CloseProjectFrame.setLocation(550, 300);

                    JPanel main = new JPanel();
                    CloseProjectFrame.add(main);

                    main.setLayout(new BoxLayout(main, BoxLayout.PAGE_AXIS));
                    JPanel message = new JPanel(new BorderLayout());

                    main.add(message);

                    JLabel confirmClose = new JLabel("Are you sure you want to close this file? Don't forget to save before closing using 'Ctrl + S'.", SwingConstants.CENTER);
                    message.add(confirmClose, BorderLayout.CENTER);

                    JPanel buttons = new JPanel(new FlowLayout());
                    main.add(buttons);

                    JButton buttonclose = new JButton("Close");
                    JButton buttoncancel = new JButton("Cancel");

                    buttons.add(buttonclose);
                    buttons.add(buttoncancel);


                    //if yes, close project
                    buttonclose.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            //close file
                            if(selectedNode.isLeaf()){

                                tabbedPane.remove(tabbedPane.getSelectedComponent());
                            }
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
            });
            add(close);


        }
    }


    public void deleteDirectory(Path path) throws IOException {

    }

    public void closeProjectTree(){
        tabbedPane.removeAll();
        theCodeEditorFrame.remove(tabbedPane);
        DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
        root.removeAllChildren();
        model.reload();
        model.setRoot(null);
    }


}