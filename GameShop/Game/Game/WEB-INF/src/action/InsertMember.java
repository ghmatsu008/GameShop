package action;

import java.io.*;

import action.CheckUtil;

import javax.servlet.*;
import javax.servlet.http.*;

import java.sql.*;

public class InsertMember extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException{

		response.setContentType("text/html; charset=UTF-8");
		// キャッシュ無効
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control","no-cache");
		PrintWriter out = response.getWriter();

		CheckUtil ck = new CheckUtil();
		
		String nam = request.getParameter("dat1");
//		if(nam!=null){
//			nam = new String(nam.getBytes("ISO-8859-1"),"UTF-8");
//		}
		// Ajaxから受け取る
		String mail = request.getParameter("dat1");
		String lpass = request.getParameter("dat2");
		String lpass_conf = request.getParameter("LOGIN_PASSWORD_CONF");
		String lnam = request.getParameter("dat3");
		String fnam = request.getParameter("dat4");
		String post = request.getParameter("dat5");
		
		int pref = Integer.parseInt(request.getParameter("dat6"));
		String address = request.getParameter("dat7");
		String strTel = request.getParameter("dat8");
		
		ck.requirdCheck(mail, "メールアドレス");
		ck.requirdCheck(lpass,"パスワード");
		ck.regExCheck(mail, "^[0-9a-zA-Z@.]{5,30}$", "メールアドレス");
		ck.regExCheck(lpass, "^[0-9a-zA-Z]{6,10}$", "パスワード");
		ck.regExCheck(post,"^[0-9]{3}-[0-9]{4}$", "郵便番号");
	
		long tel = ck.numberCheck(strTel, "電話番号");
		ck.regExCheck(strTel, "^[0-9]{9,11}$", "電話番号");
		ck.lengthCheck(lpass, 10,6, "パスワード");
		ck.duplicateCheck(mail , "SELECT  idNam From idpass WHERE idNam= ?", "メールアドレス");
		
		if(ck.hasErrors()){
			request.setAttribute("err", ck.getErrorList());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/memberErro.jsp");
			dispatcher.forward(request, response);
		}else{
		
		
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
//			Statement stmt = conn.createStatement();
			
			String sql = "insert into idpass(idNam, log_pass, l_name, f_name, post, ken, address, tel) values(?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement stat = conn.prepareStatement(sql);
			
//			String sct ="SELECT c_id FROM category2 WHERE c_nam=\'"+cat+"\'";
//			ResultSet rs2 = stmt.executeQuery(sct);
//	
//			rs2.next();
//			ct = (rs2.getInt("c_id"));
//	 		rs2.close();
			stat.setString(1,mail);
			stat.setString(2,lpass);
			stat.setString(3,lnam);
			stat.setString(4,fnam);
			stat.setString(5,post);
			stat.setInt(6,pref);
			stat.setString(7,address);
			stat.setString(8,strTel);
		
			int num = stat.executeUpdate();
//			stat.execute(); 
			
//			sql = "select * from product2 where id=(select max(id) from product2)";
//			stmt = conn.createStatement();
//			ResultSet rs = stmt.executeQuery(sql);
//
//			rs.next();
//			
//				 pr = rs.getInt("price");
//				 nam = rs.getString("nam");
//				 ct = rs.getInt("id_cat");
//				 asin = rs.getString("ASIN");
//				 ranck = rs.getInt("ranck");
				
//			rs.close();
//			stmt.clCose();
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
		
		out.println("会員登録しました。<font color=\"#FF0000\"></font>");
					
			
		out.println("</body>");
		out.println("</html>");
		
		out.close();
		}
	}
}