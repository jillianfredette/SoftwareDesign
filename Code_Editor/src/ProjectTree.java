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
import java.util.concurrent.TimeUnit;
import java.net.URL;
import javax.swing.event.*;

public class ProjectTree extends JPanel
        implements TreeSelectionListener {
    private JEditorPane htmlPane;
    private JTree tree;
    private URL helpURL;
    private static boolean DEBUG = false;

    //Optionally set the look and feel.
    private static boolean useSystemLookAndFeel = false;

    public ProjectTree() {
        super(new GridLayout(1, 0));

        //Create the nodes.
        DefaultMutableTreeNode top =
                new DefaultMutableTreeNode("Project name");
        createNodes(top);

        //Create a tree that allows one selection at a time.
        tree = new JTree(top);
        tree.getSelectionModel().setSelectionMode
                (TreeSelectionModel.SINGLE_TREE_SELECTION);

        //Listen for when the selection changes.
        tree.addTreeSelectionListener(this);

        //Create the scroll pane and add the tree to it.
        JScrollPane treeView = new JScrollPane(tree);

        //Add the tree view to this panel.
        add(treeView);
    }

    /**
     * Required by TreeSelectionListener interface.
     */
    public void valueChanged(TreeSelectionEvent e) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                tree.getLastSelectedPathComponent();

        if (node == null) return;

        Object nodeInfo = node.getUserObject();
        if (node.isLeaf()) {

        } else {

        }
    }

    private void createNodes(DefaultMutableTreeNode top) {
        DefaultMutableTreeNode folder = null;
        DefaultMutableTreeNode file = null;

        folder = new DefaultMutableTreeNode("Project Folder");
        top.add(folder);

        //original Tutorial
        file = new DefaultMutableTreeNode("File");
        folder.add(file);

    }

    //https://docs.oracle.com/javase/tutorial/uiswing/examples/components/TreeDemoProject/src/components/TreeDemo.java
}





