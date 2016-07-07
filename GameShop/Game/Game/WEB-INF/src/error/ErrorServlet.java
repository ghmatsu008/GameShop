package error;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ErrorServlet")
public class ErrorServlet extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		ServletContext application = this.getServletContext();
		StringBuffer buf = new StringBuffer();
		buf.append(new java.util.Date());
		buf.append(System.getProperty("line.separator"));
		buf.append(request.getAttribute("javax.servlet.error.request_uri"));
		buf.append(System.getProperty("line.separator"));
		buf.append(request.getAttribute("javax.servlet.error.message"));
		
		application.log(buf.toString());
		
		response.sendRedirect(request.getContextPath()+ "/error.jsp");
		
	}
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		doGet(request,response);
	}
	
}
