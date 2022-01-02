package my;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;

public class DeleteUser extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out=null;
		try {
			out=resp.getWriter();
			out.print("<html><body>");
			HttpSession session=req.getSession(false);
			if(session!=null) {
				String e=(String)session.getAttribute("email");
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/userdb","root","Shiv@250000");
				Statement st=c.createStatement();
				st.executeUpdate("delete from users where email='"+e+"'");
				
				session.invalidate();
				
				out.print("<h2>User Successfully Deleted.</h2>");
				out.print("<a href='Home.html'> Home </a>");
			}else {
				out.print("<h2>Plz login First!</h2>");
				out.print("<a href='Home.html'> Home </a>");
			}
			out.print("</body></html>");
		}catch (Exception e) {
			out.print(e);
		}
	}
	
}
