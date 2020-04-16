package registration;
import java.util.*;
import java.sql.*;

public class creditcalculate {
	Connection con;
	String url="jdbc:mysql://localhost:3306/register";
	String uname="root";
	String pass="pavana";
	public int totalcredits=0;
	String sub_code="";
	public void credit(ArrayList<String> subj,String usn,String name,String sem) throws SQLException
	{
		con=DriverManager.getConnection(url,uname,pass);
		
	     Statement st= con.createStatement();
	     PreparedStatement ps=con.prepareStatement("select credits,"
	     		+ "subject_code from subjects where subject_name=?");
	     
	     for(String a:subj) {
	     ps.setString(1, a);
	     ResultSet rs=ps.executeQuery();
	    		 
	     
		     while(rs.next())
		     {
		    	 totalcredits=totalcredits+rs.getInt(1);		//calculate the credits of the subjects selected
		    	 sub_code=sub_code+rs.getString(2)+",";			//the respective subject code
		     }
	     }
	     					
	     dbupdate db =new dbupdate();							//creating object for performing update and insert calculation
	     db.update(usn,name,sem,totalcredits,sub_code);
	}
}
