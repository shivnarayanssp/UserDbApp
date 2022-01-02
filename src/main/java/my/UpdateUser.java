package my;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;

public class UpdateUser extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out=null;
		try {
			out=resp.getWriter();
			out.print("<html><body>");
			HttpSession session=req.getSession(false);
			if(session!=null) {
				String e=(String)session.getAttribute("email");
				String n=req.getParameter("user_name");
				String p=req.getParameter("phone");
				int a=Integer.parseInt(req.getParameter("age"));
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/userdb","root","Shiv@250000");
				Statement st=c.createStatement();
				st.executeUpdate("update users set name='"+n+"', phone='"+p+"', age="+a+" where email='"+e+"'");
				
				session.setAttribute("name", n);
				session.setAttribute("age", a);
				session.setAttribute("phone", p);
				
				out.print("<h2>User Successfully Updated.</h2>");
				out.print("<a href='up'> Home </a>");
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
