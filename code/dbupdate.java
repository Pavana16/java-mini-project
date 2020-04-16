package registration;

import java.sql.*;

import java.util.*;
import javax.swing.*;

public class dbupdate {
	Connection con;
	String url="jdbc:mysql://localhost:3306/register";
    String uname="root";
    String pass="pavana";
	
	public void update(String usn,String name,String sem,int totcredits,String sub_code)throws SQLException
	{	
		con=DriverManager.getConnection(url,uname,pass);
    	PreparedStatement ps =con.prepareStatement("select usn from student_details");
    	ResultSet rs=ps.executeQuery();
    	boolean flag=false;
    	while(rs.next())
    	{
    		if(rs.getString(1).equals(usn))
			{
				flag=true;
				break;
			}
    	}
    	if(flag==true)
    	{
    			//if student details already exists in the database, update is done with adding new record
    		String query1="select subject_code,total_credits from student_details where usn=?";
    		PreparedStatement ps2=con.prepareStatement(query1);
    		ps2.setString(1, usn);
    		ResultSet rs1=ps2.executeQuery();
    		while(rs1.next())
    		{
    			sub_code=sub_code+rs1.getString(1);
    			totcredits=totcredits+rs1.getInt(2);
    		}
    		
    		String query2="UPDATE student_details SET subject_code=?,total_credits=?,sem=? WHERE (usn=?)";
    		PreparedStatement ps3=con.prepareStatement(query2);
    		ps3.setString(1, sub_code);
    		ps3.setInt(2,totcredits);
    		ps3.setString(3, sem);
    		ps3.setString(4, usn);
    		
    		
    		int n1=ps3.executeUpdate();
    		System.out.println(n1+"rows affected");
    		JOptionPane.showMessageDialog(null,"REGISTRATION DONE AND RECORDS UPDATED SUCCESSFULLY");
    	}
    	else
    	{		//new record added to the database
    		String query = " insert into student_details (usn,name,sem,subject_code,total_credits)"
    	        + " values (?, ?, ?, ?, ?)";
    		PreparedStatement ps1=con.prepareStatement(query);
    		ps1.setString(1,usn);
    		ps1.setString(2,name);
    		ps1.setString(3,sem);
    		ps1.setString(4,sub_code);
    		ps1.setInt(5,totcredits);
    		
    		int n=ps1.executeUpdate();
    		System.out.println(n+"rows affected");
    		JOptionPane.showMessageDialog(null,"REGISTRATION DONE");
    	}
    	
    delete_details db=new delete_details();			//to perform delete operation for total credits = 200
    db.delete();
	
	}
}
