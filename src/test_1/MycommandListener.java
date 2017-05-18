package test_1;
import javax.swing.*;
import java.awt.event.*;
interface MyCommandListener extends ActionListener {
     public void setJTextField(JTextField text);
     public void setJTextField_1(JTextField text_1);
     public void setJTextArea(JTextArea area); 
     public void setJTable(JTable table);
}