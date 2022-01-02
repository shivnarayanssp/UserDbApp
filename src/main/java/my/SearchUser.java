package my;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;

public class SearchUser extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out=null;
		try {
			out=resp.getWriter();
			String e=req.getParameter("user_email");
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/userdb","root","Shiv@250000");
			Statement st=c.createStatement();
			ResultSet rs=st.executeQuery("select * from users where email='"+e+"'");
			if(rs.next()) {
				req.setAttribute("name", rs.getString("name"));
				req.setAttribute("age", rs.getInt("age"));
				req.setAttribute("phone", rs.getString("phone"));
				RequestDispatcher rd=req.getRequestDispatcher("pu");
				rd.forward(req, resp);
			}else {
				resp.sendRedirect("usernotfound.html");
			}
		}catch (Exception e) {
			out.print(e);
		}
	}
	
}
