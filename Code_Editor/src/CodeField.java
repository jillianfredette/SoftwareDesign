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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.TimeUnit;


public class CodeField extends JEditorPane implements ActionListener, KeyListener {
    private JTextPane CodeField;
    private String ProjectName;
    private String ProjectPath;
    private String FileName;

    public CodeField() {
        super();
        CodeField = new JTextPane();

        this.setBackground(Color.WHITE);
        this.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.lightGray));
        this.setPreferredSize(new Dimension(700, 1000));
        this.addKeyListener(this);
        this.addStyle();
        //JScrollPane scroll = new JScrollPane(this);
        //scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        //this.add(scroll, BorderLayout.CENTER);

    }

    public void addStyle(){
        // Code Field
        StyledDocument document = CodeField.getStyledDocument();
        StyleContext context= new StyleContext();
        Style style = context.addStyle("test", null);
        StyleConstants.setForeground(style,Color.black);

        document.addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent documentEvent) {
                // DocumentEvent.EventType type = documentEvent.getType();
                StyleConstants.setForeground(style,Color.black);

                String length = CodeField.getText();
                int stringlength =length.length() -1;

//              Red operators
                if(     CodeField.getText().charAt(stringlength) == '+' || CodeField.getText().charAt(stringlength) == '-' ||
                        CodeField.getText().charAt(stringlength) == '*' || CodeField.getText().charAt(stringlength) == '/'||
                        (CodeField.getText().charAt(stringlength) == '|' && CodeField.getText().charAt(stringlength-1) == '|' ) ||
                        (CodeField.getText().charAt(stringlength) == '&' && CodeField.getText().charAt(stringlength-1) == '&' ) ||
                        (CodeField.getText().charAt(stringlength) == '=' && CodeField.getText().charAt(stringlength-1) == '=' ) ||
                        (CodeField.getText().charAt(stringlength) == '!' && CodeField.getText().charAt(stringlength-1) == '=' ) ){

                    System.out.println(CodeField.getText().charAt(stringlength));
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {

                            if((CodeField.getText().charAt(stringlength) == '='&& CodeField.getText().charAt(stringlength-1) == '=' )){
                                StyleConstants.setForeground(style,Color.red);
                                document.setCharacterAttributes(stringlength-1,stringlength,style,false);
                                // changes the operator to red
                                StyleConstants.setForeground(style,Color.red);
                                CodeField.setForeground(Color.black);
                            }
                            else if((CodeField.getText().charAt(stringlength) == '!'&& CodeField.getText().charAt(stringlength-1) == '=' )){
                                StyleConstants.setForeground(style,Color.red);
                                document.setCharacterAttributes(stringlength-1,stringlength,style,false);
                                // changes the operator to red
                                StyleConstants.setForeground(style,Color.red);
                                CodeField.setForeground(Color.black);
                            }
                            else if((CodeField.getText().charAt(stringlength) == '|'&& CodeField.getText().charAt(stringlength-1) == '|' )){
                                StyleConstants.setForeground(style,Color.red);
                                document.setCharacterAttributes(stringlength-1,stringlength,style,false);
                                // changes the operator to red
                                StyleConstants.setForeground(style,Color.red);
                                CodeField.setForeground(Color.black);
                            }
                            if((CodeField.getText().charAt(stringlength) == '&'&& CodeField.getText().charAt(stringlength-1) == '&' )){
                                StyleConstants.setForeground(style,Color.red);
                                document.setCharacterAttributes(stringlength-1,stringlength,style,false);
                                // changes the operator to red
                                StyleConstants.setForeground(style,Color.red);
                                CodeField.setForeground(Color.black);
                            }
                            StyleConstants.setForeground(style,Color.red);
                            document.setCharacterAttributes(stringlength,stringlength,style,false);
                            // changes the operator to red
                            StyleConstants.setForeground(style,Color.red);
                            CodeField.setForeground(Color.black);
                            StyleConstants.setForeground(style,Color.black);

                        }
                    });


                }
//              Blue Keywords
                if(     CodeField.getText().contentEquals("abstract") || CodeField.getText().contentEquals("boolean") ||
                        CodeField.getText().contentEquals("break") || CodeField.getText().contentEquals("case") ||
                        CodeField.getText().contentEquals("catch")|| CodeField.getText().contentEquals("char") ||
                        CodeField.getText().contentEquals("class") || CodeField.getText().contentEquals("continue") ||
                        CodeField.getText().contentEquals("do") || CodeField.getText().contentEquals("double") ||
                        CodeField.getText().contentEquals("else") || CodeField.getText().contentEquals("enum") ||
                        CodeField.getText().contentEquals("extends") || CodeField.getText().contentEquals("final") ||
                        CodeField.getText().contentEquals("float") || CodeField.getText().contentEquals("for") ||
                        CodeField.getText().contentEquals("if") || CodeField.getText().contentEquals("int") ||
                        CodeField.getText().contentEquals("interface") || CodeField.getText().contentEquals("long") ||
                        CodeField.getText().contentEquals("new") || CodeField.getText().contentEquals("package") ||
                        CodeField.getText().contentEquals("private") || CodeField.getText().contentEquals("protected") ||
                        CodeField.getText().contentEquals("public") || CodeField.getText().contentEquals("return") ||
                        CodeField.getText().contentEquals("short") || CodeField.getText().contentEquals("static") ||
                        CodeField.getText().contentEquals("super") || CodeField.getText().contentEquals("switch") ||
                        CodeField.getText().contentEquals("this") || CodeField.getText().contentEquals("throw") ||
                        CodeField.getText().contentEquals("throws") || CodeField.getText().contentEquals("try") ||
                        CodeField.getText().contentEquals("void") || CodeField.getText().contentEquals("while") ||
                        CodeField.getText().contentEquals("true") || CodeField.getText().contentEquals("null") ||
                        CodeField.getText().contentEquals("false")){
                    StyleConstants.setForeground(style,Color.blue);
                    CodeField.setForeground(Color.black);
                    StyleConstants.setForeground(style,Color.black);
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
        } catch (BadLocationException ex) {
            ex.printStackTrace();
        }
        this.add(CodeField, BorderLayout.CENTER);
        //CodeField.setBackground(Color.WHITE);
        //CodeField.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.lightGray));
    }
    public void actionPerformed(ActionEvent e) {
        String buttonString = e.getActionCommand();
    }

    public void keyReleased(KeyEvent e) {

    }

    public void setProjectInfo(String theProjectName, String theProjectPath, String theFileName){
        ProjectName = theProjectName;
        ProjectPath = theProjectPath;
        FileName = theFileName;
    }

    // Save Code by pressing Ctrl + S
    public void keyPressed(KeyEvent e){
        if ((e.getKeyCode() == KeyEvent.VK_S) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
//        if ((e.getKeyCode() == KeyEvent.VK_F4)) {
            try {
                String filePath = ProjectPath + "/" + ProjectName + "/src/" + FileName;
                FileWriter out = new FileWriter(filePath);
                System.out.println(filePath);
                out.write(this.getText());
                out.close();
            } catch (Exception f) {
                f.printStackTrace();
            }

        }

    }

    public void keyTyped(KeyEvent e) {

    }
}
