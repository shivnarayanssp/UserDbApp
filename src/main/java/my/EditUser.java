package my;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;

public class EditUser extends HttpServlet{

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
				out.print("<form action='update' method='post'>");
				out.print("Name: <input type='text' name='user_name' value='"+n+"' pattern='[a-z A-Z]+' required/> <br/><br/>");
				out.print("Phone: <input type='tel' name='phone' value='"+p+"' minlength='10' maxlength='10'   required/> <br/><br/>");
				out.print("Age: <input type='number' name='age' value='"+a+"' max='120' min='1' required/> <br/><br/>");
				out.print("<button type='submit'>Update User</button>");
				out.print("</form>");
				
				out.print("<form action='delete' method='post'>");
				out.print("<button type='submit'>Delete User</button>");
				out.print("</form>");

				out.print("<hr/> <a href='up'> Home </a>");
				out.print("<hr/> <a href='cp'> Change Password </a>");
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
