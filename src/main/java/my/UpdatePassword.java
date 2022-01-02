package my;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;

public class UpdatePassword extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out=null;
		try {
			out=resp.getWriter();
			out.print("<html><body>");
			HttpSession session=req.getSession(false);
			if(session!=null) {
				String e=(String)session.getAttribute("email");
				String op=req.getParameter("old_pass");
				String np=req.getParameter("new_pass");
				String cp=req.getParameter("confirm_pass");
				if(np.equals(cp)) {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/userdb","root","Shiv@250000");
					Statement st=c.createStatement();
					int x=st.executeUpdate("update users set password='"+np+"' where email='"+e+"' and password='"+op+"'");
					if(x!=0) {
						out.print("<h2>Password Successfully Updated.</h2>");
						out.print("<a href='edit'> Home </a>");
					}else {
						out.print("<h2>Old Password is Wrong!</h2>");
						out.print("<a href='cp'> Home </a>");
					}
					
				}else {
					out.print("<h2>Password mismatched!.</h2>");
					out.print("<a href='cp'> Home </a>");
				}
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
