package registration;
import java.util.*;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

public class loginform extends JFrame implements ActionListener
{	
	private Container c; 
	private Container b;
	private JLabel title;
	private JLabel title1;
	JLabel usn ;;
	JLabel name; 
	JLabel sem ;
	
	JTextField usnf; 
	JTextField namef; 
	JTextField semf ;
	JButton enter;
	JButton reset;
	JButton done;
	
	Connection con;
	 String url="jdbc:mysql://localhost:3306/register";
     String uname="root";
     String pass="pavana";
	
	public static String student_usn="";
	public static String student_name="";
	public static String semester="";
	
	public loginform() throws SQLException 
	{
		
		setTitle("student course Registration "); 					
        setBounds(300, 90, 900, 500); 						//title for the frame
        setDefaultCloseOperation(EXIT_ON_CLOSE); 			
        setResizable(false); 
  
        c = getContentPane(); 
        c.setLayout(null); 
  
        title = new JLabel("Registration Form"); 
        title.setFont(new Font("Arial", Font.PLAIN, 30)); 		
        title.setSize(300, 30); 
        title.setLocation(300, 30); 
        c.add(title); 
		
		usn= new JLabel("enter usn:");
				 usn.setBounds(50, 30, 90, 40);
				 
		name = new JLabel("enter name:");
				name.setBounds(50, 60, 90, 40);
		
		usnf= new JTextField(20);
			usnf.setBounds(320, 30, 230, 35);
			
		namef = new JTextField(20);
			namef.setBounds(320, 200, 230, 35);
			
		enter = new JButton("ENTER");
		 enter.setBounds(200, 200, 300, 500);
		 
		reset= new JButton("RESET");
		 reset.setBounds(200, 200, 300, 100);
	        
			
		 c = new JPanel();
	    
	     
		c.add(usn);
		c.add(usnf);
		c.add(name);
		c.add(namef);
		c.add(enter);
		c.add(reset);		
		add(c);
		
		enter.addActionListener(this);
		reset.addActionListener(this); 
			
		//connection with mysql
	     con=DriverManager.getConnection(url,uname,pass);
		
	}
	
	public void actionPerformed(ActionEvent evt)
	{	
		if(evt.getSource()==enter)
		{	
			 student_usn= usnf.getText();
			 student_name = namef.getText();
			if(student_usn.isEmpty()||student_name.isEmpty())
			{			
				JOptionPane.showMessageDialog(null,"Enter valid Details");
			}
			else {														//to enable the user to select the semester
				String choices[]={"1st sem","2nd sem","3rd sem",
						"4th sem","5th sem","6th sem","7th sem","8th sem"};
				int n1=JOptionPane.showOptionDialog(null, "click valid semester",
						"enter semester", 0, JOptionPane.INFORMATION_MESSAGE,
						null, choices, choices[0]);
				
				 semester=choices[n1];
				 
						semester one=new semester();
						try {
							one.subjects(student_usn,student_name,semester);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 
				}
					
			}
			else if (evt.getSource() == reset) { 
	            String def = ""; 
	            namef.setText(def); 			//to reset the details entered
	            usnf.setText(def); 
	             
	        } 
	}

}
