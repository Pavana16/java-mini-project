package registration;
import java.sql.*;
import java.util.*;
import javax.swing.*;


public class delete_details {
	Connection con;
	String url="jdbc:mysql://localhost:3306/register";
    String uname="root";
    String pass="pavana";
    
	public void delete() throws SQLException
	{
		con=DriverManager.getConnection(url,uname,pass);
		String query="select usn from student_details where (total_credits=200)";
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(query);
		
		while(rs.next())
		{
			PreparedStatement ps =con.prepareStatement("delete from student_details where (usn=?)");
			ps.setString(1, rs.getString(1));
			int n=ps.executeUpdate();
			JOptionPane.showMessageDialog(null,"RECORDS WITH TOTAL CREDITS OF 200 DELETED");
		}
	}
}
