package test_1;
import java.awt.event.*;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;
public class PoliceListen implements  MyCommandListener {
	   JTextField textInput;//输入的车位号
	   JTextField textInput_1;//输入取车密码
	   JTextArea textShow;//显示车位的具体信息
	   JTable tableshow;
	   String str_1=" ";
	   public void setJTextField(JTextField text) {
	      textInput = text;
	   }
	   public void setJTextField_1(JTextField text_1)
	   {
		   textInput_1=text_1;
	   }
	   public void setJTextArea(JTextArea area) {
	      textShow = area;
	   } 
	   public void setJTable(JTable table) {
		      tableshow = table;
		   }
	   public void actionPerformed(ActionEvent e) {
		   textShow.setText(null);
	      String str_parknumber=textInput.getText();
	      String str_key=textInput_1.getText();
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
	            System.out.println("开始连接");  
	            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
	            con = DriverManager.getConnection(url);  
	            System.out.println("连接成功");  
	         //Scanner reader=new Scanner(System.in);
	         //String input=reader.nextLine();
	            // Create and execute an SQL statement that returns some data.  
	            String SQL = "SELECT  * FROM P WHERE parking_lot=? and key_number=?";  
	            stmt = con.prepareStatement(SQL);  
	            stmt.setObject(1, str_parknumber);
	            stmt.setObject(2, str_key);
	            rs = stmt.executeQuery(); 
	            // Iterate through the data in the result set and display it.  
	            if (rs.next()) {  
	            	double price;
	            	price=Integer.parseInt(rs.getString(3))*0.5;
	            	str_1=rs.getString(1)+"        "+rs.getString(2)+"       "+rs.getString(3)+"                "+price;
	                System.out.println(str_1);
	                textShow.append(str_1);
	            }  else
				{
					JOptionPane.showMessageDialog(null,"车位信息错误","Warnning!",JOptionPane.WARNING_MESSAGE);
					textInput.setText(null);	
					textInput_1.setText(null);
				}
	            //System.out.println(str_1);
	           // textShow.append(str_1);
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
	 

	   

	        


  }
	   
}
