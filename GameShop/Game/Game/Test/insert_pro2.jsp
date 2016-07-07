<?xml version="1.0" encoding="UTF-8"?>

<!-- 本ソースのエンコードを示す、下記が必要 -->
<%@page contentType="text/html; charset=UTF-8"
	import="java.sql.*,java.io.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ja" lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>登録</title>
</head>
<body bgcolor="#FFFF00">

	<center>
		<font color="#0000FF">
			<h1>登録結果</h1>
		</font>
		<%
			request.setCharacterEncoding("UTF-8");

			String nam = request.getParameter("nam");
			String sold = request.getParameter("pr");
			int pr = Integer.parseInt(sold); 
// 			int price = Integer.parseInt(pr);
// 			String id = request.getParameter("id");
			Connection con = null;
			Statement stmt = null;
			try {

				Class.forName("com.mysql.jdbc.Driver");
				String DB_NAME = "mydb";
				String url = "jdbc:mysql://localhost:3306/" + DB_NAME
						+ "?useUnicode=true&characterEncoding=UTF-8";
				String user = "root";
				String pass = "matsuo";

				 con = DriverManager.getConnection(url, user, pass);
// 				 stmt = con.createStatement();
	
					String sql = "INSERT INTO product (nam,price) VALUES(?,?)";
					PreparedStatement pstmt = con.prepareStatement(sql);

					  pstmt.setString(1, "hhhh");
					  pstmt.setInt(2, 33);
					  
					  int num = pstmt.executeUpdate();
					  
			 System.out.println(request.getParameter("pr"));
					  
					  pstmt.close();

				      sql = "select * from member";
				      stmt = con.createStatement();
				      ResultSet rs = stmt.executeQuery(sql);

					      while(rs.next()){
					        int code = rs.getInt("old");
					        String company = rs.getString("nam");
					        out.println("<p>");
					        out.println("コード:" + code + ", 会社名:" + company);
					        out.println("</p>");
					      }

				      rs.close();
				      stmt.close();
				 }catch (ClassNotFoundException e){
				      out.println("ClassNotFoundException:" + e.getMessage());
				 }catch (SQLException e){
				      out.println("SQLException:" + e.getMessage());
				 }catch (Exception e){
				      out.println("Exception:" + e.getMessage());
				 }finally{
				     try{
					        if (con != null){
					          con.close();
					        }
					 }catch (SQLException e){
					        out.println("SQLException:" + e.getMessage());
						}
				 }
%>
				    </body>
				    </html>
				

				



