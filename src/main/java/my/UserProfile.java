package my;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;

public class UserProfile extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out=null;
		try {
			out=resp.getWriter();
			out.print("<html><body>");
			HttpSession session=req.getSession(false);
			if(session!=null) {
				String e=(String)session.getAttribute("email");
				String n=(String)session.getAttribute("name");
				Integer a=(Integer)session.getAttribute("age");
				String p=(String)session.getAttribute("phone");
				out.print("<h1>Welcome : <b>"+n+"</b></h1> <hr/>");
				out.print("<p>Email: <b>"+e+"</b></p>");
				out.print("<p>Age: <b>"+a+"</b></p>");
				out.print("<p>Phone: <b>"+p+"</b></p>");
				out.print("<hr/> <a href='logout'> LOGOUT </a>");
				out.print("<hr/> <a href='edit'> Edit Profile </a>");
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
