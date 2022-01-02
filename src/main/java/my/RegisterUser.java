package my;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;

public class RegisterUser extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out=null;
		try {
			out=resp.getWriter();
			String e=req.getParameter("user_email");
			String n=req.getParameter("user_name");
			String p=req.getParameter("phone");
			int a=Integer.parseInt(req.getParameter("age"));
			String pass=req.getParameter("password");
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/userdb","root","Shiv@250000");
			Statement st=c.createStatement();
			st.executeUpdate("insert into users (email,name,phone,age,password) values ('"+e+"','"+n+"','"+p+"',"+a+",'"+pass+"')");
			resp.sendRedirect("success.html");
		}catch (SQLIntegrityConstraintViolationException e) {
			resp.sendRedirect("error.html");
		}catch (Exception e) {
			out.print(e);
		}
	}
	
}
