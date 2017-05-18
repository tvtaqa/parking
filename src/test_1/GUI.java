package test_1;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import javafx.scene.control.TableColumn;

import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.ScrollPane;
import java.awt.Color;

public class GUI extends JFrame {
	
	private JTextArea textArea_1;//输出车位信息 单行
	private	JButton button;//确定按钮
	//private JPanel contentPane;
	JPanel  contentPane ;
	private JTextField textField;//输入车位号
	MyCommandListener listener;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;
	private JLabel label_5;
	private JTable table;
	private JScrollPane scp=new JScrollPane();
	private JTextField textField_1;//密码框
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		PoliceListen police=new PoliceListen();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
					frame.setMyCommandListener(police);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		setTitle("\u505C\u8F66\u7CFB\u7EDF");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 827, 562);
		contentPane =  new JPanel() {
            
           private static final long serialVersionUID = 1L;

           @Override
           protected void paintComponent(Graphics g) {
        	   super.paintComponent(g);
        	   ImageIcon icon = new ImageIcon("D:\\1.jpg");
        	   g.drawImage(icon.getImage(), 0, 0, null);
        	   }
           
       };
		//contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(224, 73, 93, 30);
		contentPane.add(textField);
		textField.setColumns(10);
		
		 lblNewLabel = new JLabel("\u505C\u8F66\u6536\u8D39\u7CFB\u7EDF");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 25));
		lblNewLabel.setBounds(319, 0, 179, 74);
		contentPane.add(lblNewLabel);
		
		 button = new JButton("\u786E\u5B9A");
		 button.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {
		 	}
		 });
		button.setBounds(224, 149, 77, 30);
		contentPane.add(button);
		
		 lblNewLabel_1 = new JLabel("\u8F93\u5165\u8F66\u4F4D\u53F7\uFF1A");
		lblNewLabel_1.setBounds(125, 76, 90, 25);
		contentPane.add(lblNewLabel_1);
		
		JLabel label = new JLabel("\u8F66\u4F4D\u4FE1\u606F\uFF1A");
		label.setBounds(416, 109, 93, 25);
		contentPane.add(label);
		
		label_1 = new JLabel("\u6240\u6709\u8F66\u4F4D\u4FE1\u606F\uFF1A");
		label_1.setBounds(57, 240, 135, 31);
		contentPane.add(label_1);
		
		 textArea_1 = new JTextArea();
		textArea_1.setBounds(510, 110, 245, 26);
		contentPane.add(textArea_1);
		
		label_2 = new JLabel("\u8F66\u4F4D\u53F7");
		label_2.setBounds(510, 74, 51, 29);
		contentPane.add(label_2);
		
		label_3 = new JLabel("\u7A7A\u95F2");
		label_3.setBounds(575, 79, 35, 18);
		contentPane.add(label_3);
		
		label_4 = new JLabel("\u505C\u8F66\u65F6\u95F4");
		label_4.setBounds(638, 79, 60, 18);
		contentPane.add(label_4);
		
		label_5 = new JLabel("\u4ED8\u6B3E");
		label_5.setBounds(723, 79, 51, 18);
		contentPane.add(label_5);
		
		Object[][] cellData = null;
		int i,j;
		int number = 0;//车位数量
		i=j=0;
		Object[] columnNames = {"车位号", "空闲","停车时长"};
	      
	      String connectionUrl = "jdbc:sqlserver://localhost:1433;"  
	                + "databaseName=AdventureWorks;integratedSecurity=true;";  
	  
	        String url = "jdbc:sqlserver://127.0.0.1:1443;databaseName=PARKING_FEE;user=sa;password=123";//sa身份连接  
	  
	      //  String url2 = "jdbc:sqlserver://127.0.0.1:1443;databaseName=PARKING_FEE;integratedSecurity=true;";//windows集成模式连接  
	  
	        // Declare the JDBC objects.  
	        Connection con = null;  
	        PreparedStatement stmt = null;
	        ResultSet rs = null;  
		try {  
            // Establish the connection.  
            System.out.println("begin.");  
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
            con = DriverManager.getConnection(url);  
            System.out.println("end.");  
  
            //车位的总数量
            String SQL = "SELECT  count(parking_lot) FROM P";  
            stmt =  stmt = con.prepareStatement(SQL);  
            rs = stmt.executeQuery();  
            if(rs.next())
            {
            	number=Integer.parseInt(rs.getString(1));
            }
           cellData =new Object[number][3];
           
           //显示所有车位的信息
          SQL = "SELECT  * FROM P";  
          stmt =  stmt = con.prepareStatement(SQL);  
          rs = stmt.executeQuery();  
          
            while (rs.next()) {  
            	cellData[i][j]=rs.getString(1);
            	cellData[i][++j]=rs.getString(2);
            	cellData[i][++j]=String.valueOf(rs.getString(3));
            	i++;
            	j=0;
                //System.out.println(rs.getString(1) + " " + rs.getString(2));  
            }  
        }  
  
 catch (Exception e1) {  
       e1.printStackTrace();  
   }  

   finally {  
       if (rs != null)  
           try {  
               rs.close();  
           } catch (Exception e1) {  
           }  
       if (stmt != null)  
           try {  
               stmt.close();  
           } catch (Exception e1) {  
           }  
       if (con != null)  
           try {  
               con.close();  
           } catch (Exception e1) {  
           }  
  } 
		table = new JTable(cellData,columnNames);
//		javax.swing.table.TableColumn firsetColumn_0 = table.getColumnModel().getColumn(0);
//		javax.swing.table.TableColumn firsetColumn_1 = table.getColumnModel().getColumn(1);
//		javax.swing.table.TableColumn firsetColumn_2 = table.getColumnModel().getColumn(2);
//		firsetColumn_0.setMaxWidth(90);
//		firsetColumn_1.setMaxWidth(90);
//		firsetColumn_2.setMaxWidth(90);	  
		table.setToolTipText("");
		table.setBounds(173, 205, 311, 231);
		 scp.setBounds(new Rectangle(193, 240, 451, 264));	
		 contentPane.add(scp);
		 scp.setViewportView(table);
		 
		 textField_1 = new JTextField();
		 textField_1.setBounds(224, 106, 93, 30);
		 contentPane.add(textField_1);
		 textField_1.setColumns(10);
		 
		 JLabel label_6 = new JLabel("\u8F93\u5165\u5BC6\u7801\uFF1A");
		 label_6.setBounds(135, 114, 75, 18);
		 contentPane.add(label_6);
		 
//		 JButton button_1 = new JButton("\u5237\u65B0");
//		 button_1.addActionListener(new ActionListener() {
//		 	public void actionPerformed(ActionEvent arg0) {	 		
//		 	}
//		 });
//		 button_1.setBounds(67, 284, 77, 25);
//		 contentPane.add(button_1);	
	}
	
	
	void setMyCommandListener(MyCommandListener  listener)
	{
		
		      this.listener = listener;
		      listener.setJTextField(textField);
		      listener.setJTextField_1(textField_1);
		      listener.setJTextArea(textArea_1);
		      //inputText.addActionListener(listener);  //inputText是事件源,listener是监视器
		      button.addActionListener(listener);     //button是事件源,listener是监视器
		  
	}
}
