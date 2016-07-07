<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.sql.*, javax.naming.*, javax.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta  content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
 		request.setCharacterEncoding("UTF-8");
		Connection db = null;
		PreparedStatement ps = null;

		try {
			Context cotx = new InitialContext();
			DataSource ds = (DataSource)cotx.lookup("java:comp/env/jdbc/gs");
			db = ds.getConnection();
			ps = db.prepareStatement("INSERT INTO product2(ASIN,nam) VALUES(?,?)");

			ps.setString(1,"jjj");
			ps.setString(2,"jjj");
			
			System.out.println(request.getParameter("asin"));

// 			ps.executeUpdate();
			
		} catch (Exception e) {
			throw new ServletException(e);

		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (db != null) {
					db.close();
				}
			} catch (Exception e) {
			}
		}
		response.sendRedirect("insert_form.jsp");
	%>


	成功


</body>
</html>