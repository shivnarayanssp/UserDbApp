package my;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;

public class ChangePassword extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out=null;
		try {
			out=resp.getWriter();
			out.print("<html><body>");
			HttpSession session=req.getSession(false);
			if(session!=null) {
				String n=(String)session.getAttribute("name");
				out.print("<h1>Welcome : <b>"+n+"</b></h1> <hr/>");
				out.print("<form action='updatePass' method='post'>");
				out.print("Old Password: <input type='password' name='old_pass' required/> <br/><br/>");
				out.print("New Password: <input type='password' name='new_pass' required/> <br/><br/>");
				out.print("Confirm Password: <input type='password' name='confirm_pass' required/> <br/><br/>");
				out.print("<button type='submit'>Submit</button>");
				out.print("</form>");
				out.print("<hr/> <a href='up'> Home </a>");
				out.print("<hr/> <a href='logout'> LOGOUT </a>");
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
