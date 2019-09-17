//https://docs.oracle.com/javase/tutorial/uiswing/examples/components/TreeDemoProject/src/components/TreeDemo.java

import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.plaf.metal.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;
import java.net.URL;
import javax.swing.event.*;

public class ProjectTree extends JPanel implements TreeSelectionListener {
    private JEditorPane htmlPane;
    private JTree tree;
    private URL helpURL;
    private static boolean DEBUG = false;

    //Optionally set the look and feel.
    private static boolean useSystemLookAndFeel = false;

    public ProjectTree() {
        super(new GridLayout(1, 0));

        //Create the nodes.
        DefaultMutableTreeNode top = new DefaultMutableTreeNode("Project name");
        createNodes(top);

        //Create a tree that allows one selection at a time.
        tree = new JTree(top);
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

        } else {

        }
    }

    // Creates Nodes for Tree
    private void createNodes(DefaultMutableTreeNode top) {
        DefaultMutableTreeNode folder = null;
        DefaultMutableTreeNode file = null;

        folder = new DefaultMutableTreeNode("Project Folder");
        top.add(folder);

        //original Tutorial
        file = new DefaultMutableTreeNode("File");
        folder.add(file);

    }

    // Popup Specifics
    class TreePopup extends JPopupMenu {
        public TreePopup(JTree tree) {
            JMenuItem delete = new JMenuItem("Delete");
            JMenuItem add = new JMenuItem("Add");
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
//                    System.out.println("Add child");
//                    DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getSelectionPath().getLastPathComponent();
//                    JFrame addNodeFrame;
//                    addNodeFrame = new JFrame("Add File");
//                    addNodeFrame.add(field1);
//                    DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(field1.getText());
//                    selectedNode.add(newNode);

                }
            });
            add(add);
            add(new JSeparator());
            add(delete);
        }
    }


    public void deleteDirectory(Path path) throws IOException {

    }




}




