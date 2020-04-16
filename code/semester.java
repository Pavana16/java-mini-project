package registration;
import java.util.*;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

public class semester extends JFrame implements ActionListener {
	private Container c; 
	private JCheckBox term[]=new JCheckBox[20];; 
	private JLabel title;
	private JLabel title1;
	private JButton done;
	int n=0;
	Connection con;
	String url="jdbc:mysql://localhost:3306/register";
    String uname="root";
    String pass="pavana";
    
    String name="";
    String usn="";
    String sem="";
	public void subjects(String student_usn,String student_name,String semester) throws SQLException
	{
		usn=student_usn;
		name=student_name;
		sem=semester;
		
		setTitle(" Subjects "); 
        setBounds(300, 60, 800, 600); 
        setDefaultCloseOperation(EXIT_ON_CLOSE); 
        setResizable(false); 
        
        c = getContentPane(); 
        c.setLayout(null); 
  
        title = new JLabel("Subject Registration "); 
        title.setFont(new Font("Arial", Font.PLAIN, 30)); 
        title.setSize(300, 50); 
        title.setLocation(250, 30); 
        c.add(title); 
        
        done = new JButton("DONE");
		done.setBounds(350, 450, 100, 40);
		c.add(done);
		done.addActionListener(this);
		
		if(sem=="5th sem" || sem=="6th sem" )
		{
			title1 = new JLabel("Choose any one Elective"); 
	        title1.setFont(new Font("Arial", Font.PLAIN, 20)); 			//for elective option
	        title1.setSize(600, 30); 
	        title1.setLocation(300, 370); 
	        c.add(title1); 
		}

		if(sem=="7th sem"  )
		{
			title1 = new JLabel("Choose any one under Elective-C,Elective-D,Elective-E"); 
	        title1.setFont(new Font("Arial", Font.PLAIN, 20)); 
	        title1.setSize(600, 30); 
	        title1.setLocation(100, 400); 
	        c.add(title1); 
		}
				
      //connection with mysql
		 		con=DriverManager.getConnection(url,uname,pass);
		
	   
	     
	     PreparedStatement pq= con.prepareStatement("select subject_name,credits "
	     		+ "from subjects where semester=?" );
	     pq.setString(1, sem);
	     ResultSet rs=pq.executeQuery();
	     ArrayList<String> subjects = new ArrayList<String>(); 
	  
	     while(rs.next())
	     {
	    	 subjects.add(rs.getString("subject_name"));
	     }
	      n=subjects.size();
	    int i=1;
	    int j=250,k=100;
	    for(String a:subjects)
	    {	
		    
		    	term[i] = new JCheckBox(a); 
		        term[i].setFont(new Font("Arial", Font.PLAIN, 15)); 
		        term[i].setSize(450, 20); 
		        term[i].setLocation(j, k); 
		        c.add(term[i]); 
		    i++;
		  
		    k=k+20;
	    }
	   
	     setVisible(true); 
	}
	
	public void actionPerformed(ActionEvent evt) 
	{ 	 
		ArrayList <String> selectedsubjects=new ArrayList<String>();

		if(evt.getSource()==done)
		{
			for(int i=1;i<=n;i++)
			{
				 if(term[i].isSelected())  
				  {  
					 selectedsubjects.add(term[i].getText());  //subjects selected is added into the arraylist
				  }  
			}
		}
		if(selectedsubjects.isEmpty())
		{
			JOptionPane.showMessageDialog(null,"Select the subjects"); //check if subjects are selected
		}
		else {
			creditcalculate cr=new creditcalculate();
			try {
				
				cr.credit(selectedsubjects,usn, name, sem);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		
	}
}
