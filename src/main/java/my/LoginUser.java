package my;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;

public class LoginUser extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out=null;
		try {
			out=resp.getWriter();
			String e=req.getParameter("user_email");
			String p=req.getParameter("password");
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/userdb","root","Shiv@250000");
			Statement st=c.createStatement();
			ResultSet rs=st.executeQuery("select * from users where email='"+e+"' and password='"+p+"'");
			if(rs.next()) {
				HttpSession session=req.getSession();
				session.setAttribute("email", e);
				session.setAttribute("name", rs.getString("name"));
				session.setAttribute("age", rs.getInt("age"));
				session.setAttribute("phone", rs.getString("phone"));
				RequestDispatcher rd=req.getRequestDispatcher("up");
				rd.forward(req, resp);
			}else {
				resp.sendRedirect("usernotfound.html");
			}
		}catch (Exception e) {
			out.print(e);
		}
	}
	
}
