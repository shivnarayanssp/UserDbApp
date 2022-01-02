package my;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;

public class PrintUser extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out=null;
		try {
			out=resp.getWriter();
			String e=req.getParameter("user_email");
			String n=(String)req.getAttribute("name");
			Integer a=(Integer)req.getAttribute("age");
			String p=(String)req.getAttribute("phone");
			out.print("<html><body>");
			out.print("<h1>User Details:</h1>");
			out.print("<p>Name: <b>"+n+"</b></p>");
			out.print("<p>Email: <b>"+e+"</b></p>");
			out.print("<p>Age: <b>"+a+"</b></p>");
			out.print("<p>Phone: <b>"+p+"</b></p>");
			
			//hit counter
			ServletContext ctx=getServletContext();
			Integer c=(Integer)ctx.getAttribute("count");
			if(c==null) {
				c=0;
			}
			ctx.setAttribute("count", ++c);
			
			out.print("<hr/><p>Visitor: <b>"+c+"</b></p>");
			
			out.print("</body></html>");
		}catch (Exception e) {
			out.print(e);
		}
	}
	
}
