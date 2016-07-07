package action;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import java.sql.*;

public class Insert extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException{

		response.setContentType("text/html; charset=UTF-8");
		// キャッシュ無効
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control","no-cache");
		PrintWriter out = response.getWriter();

		String nam = request.getParameter("dat1");
//		if(nam!=null){
//			nam = new String(nam.getBytes("ISO-8859-1"),"UTF-8");
//		}
		String asin = request.getParameter("dat2");
		String cat = request.getParameter("dat4");
		String spr = request.getParameter("dat3");
		String ran = request.getParameter("dat5");
//		Date date = (DATE)request.getParameter("date");
		int pr = Integer.parseInt(spr); 
		int ranck = Integer.parseInt(ran);
		int ct;
		
		
//		if (cat != null) {
//			if (cat.equals("ps4"))
//				 ct = 1; 
//			if (cat.equals("ps3"))
//				ct = 2;
//			if (cat.equals("3ds"))
//				sql = sql + start + " category2.c_id = 3";
//			if (pr.equals("ds"))
//				sql = sql + start + " category2.c_id = 4";
//			if (pr.equals("vita"))
//				sql = sql + start + " category2.c_id = 7";
//			if (pr.equals("xbox"))
//				sql = sql + start + " category2.c_id = 8";
//			if (pr.equals("u"))
//				sql = sql + start + " category2.c_id = 5";
		
	
		
		out.println("<html>");
		out.println("<head>");
		out.println("<title>テスト</title>");
		out.println("</head>");
		out.println("<body>");

		Connection conn = null;

		String DB_NAME = "mydb";
		String url = "jdbc:mysql://localhost:3306/" + DB_NAME + "?useUnicode=true&characterEncoding=UTF-8";
		String user = "root";
		String pass = "matsuo";

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(url, user, pass);
			Statement stmt = conn.createStatement();
			
			String sql = "insert into product2 (nam, ASIN, id_cat, price, ranck) values(?, ?, ?, ?, ?)";
			PreparedStatement stat = conn.prepareStatement(sql);
			
			String sct ="SELECT c_id FROM category2 WHERE c_nam=\'"+cat+"\'";
			ResultSet rs2 = stmt.executeQuery(sct);
	
			rs2.next();
			ct = (rs2.getInt("c_id"));
	 		rs2.close();
			
			
			stat.setString(1, nam);
			stat.setString(2, asin);
			stat.setInt(3, ct);
			stat.setInt(4, pr);
			stat.setInt(5, ranck);
		
			int num = stat.executeUpdate();
			stat.execute(); 
			

			sql = "select * from product2 where id=(select max(id) from product2)";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			rs.next();
			
				 pr = rs.getInt("price");
				 nam = rs.getString("nam");
				 ct = rs.getInt("id_cat");
				 asin = rs.getString("ASIN");
				 ranck = rs.getInt("ranck");
				
			

			rs.close();
			stmt.close();
			stat.close();

		}catch (Exception e){
			out.println("Exception:" + e.getMessage());
		}finally{
			try{
				if (conn != null){
					conn.close();
				}
			}catch (SQLException e){
				out.println("SQLException:" + e.getMessage());
			}
		}
		out.println("<center><h2>");
		
		out.println("商品登録しました。<font color=\"#FF0000\"></font></h2>登録結果<br><br><br>"+
					
						"<table  border=\"1\">"+
						"<tr>"+
			"<th>商品名</th>"+
			"<th>ASIN</th>"+
			"<th>価格</th>"+
			"<th>ランキング</th>"+
			"<th>カテゴリー</th>"+
		"</tr><tr>"+
			"<td>"+nam+"</td>"+
			
				"<td>"+asin+"</td>"+
				"<td>"+pr+"</td>"+
				"<td>"+ranck+"</td>"+
				"<td>"+cat+"</td>"+
		
	"</table></center>");
		out.println("</body>");
		out.println("</html>");
		
		out.close();
	}
}