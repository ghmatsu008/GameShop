package test;

import java.io.IOException;


import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Search extends HttpServlet {

	private static final long serialVersionUID = 5989725575563102736L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		request.setCharacterEncoding("UTF-8");

		response.setContentType("text/html; charset=UTF-8");
		// キャッシュ無効
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");

		PrintWriter out = response.getWriter();
		
		
		String title = request.getParameter("nam");
		
		if(title !=null){
			title = new String(title.getBytes("ISO-8859-1"),"UTF-8");
		}
		String pr = request.getParameter("pr");
		String cat = request.getParameter("cat");
		
		String start = "and";
		int a = 0;
	
		out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
		out.println("<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"ja\" lang=\"ja\">");
		out.println("<head>");
		out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />");

		out.println("<title>一覧</title>");
		out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">");
		out.println("</head>");
		out.println("<body>");
		out.println("<div id =\"wrap\"><div class =\"head\">");
		out.println("<h1>GAME通信販売</h1><br/>");
		out.println("<a href=\"login.html\">");
		out.println("<span class=\"label\"><BUTTON type=\"submit\" style=\"background-color: #fff;\"><big>ログイン</big></BUTTON></span>");
		out.println("</a><ul class=\"menu\"><li><a href=\"indextop.html\">TOP</a></li>	<li><a href=\"item1.html\">商品一覧</a></li><li><a href=\"idinfo.html\">会員登録</a></li><li><a href=\"\">カートBOX</a></li><li><a href=\"mail.html\">問い合わせ</a></li></ul></a>	");

		out.println("<div class=\"itemsearch\">価格帯<select name=\"p\">");
		out.println("<option value=\"\">-</option>");
		out.println("<option value=\"0_1\"  >0円～1000円</option><option value=\"1_5\"  >1000円～5000円</option><option value=\"5_10\"  >5000円～10000円</option>");
		out.println("<option value=\"10_50\"  >10000円～50000円</option><option value=\"50\"  >100000円～</option></select>");
		out.println("<select name=\"ra\"><option value=\"\">---</option><option value=\"0\"  >新品</option><option value=\"5\" >中古品</option></select>");

		out.println("<select name=\"cat\" class=\"wd1\"><option value=\"\">---</option><option value=\"301\" >PS4</option><option value=\"323\" >PS3</option>");

		out.println("<option value=\"327\" >ニンテンドーDS</option><option value=\"404\" プレイステーション Vita</option><option value=\"304\" >Xbox 360</option><option value=\"311\" >Wii U</option>");
		out.println("</select><input name=\"kw\" type=\"text\" size=\"30\" value=\"\" /><input type=\"submit\" value=\"検索\" /></div></form></div>");
		out.println("<div class =\"side\">");
		out.println("<p class=\"side-cart\"><a href=\"http://mp.moshimo.com/cart?shop_id=556410&amp;signature=SD\">"
				+ "<img src=\"http://image.moshimo.com/static/publish/sd/img/templates/orange/show-cart.gif\" alt=\"カートを見る\" /></a></p>");
		out.println("<div class=\"section\"><h4><div class=\"caption\">商品検索</div></h4>");
		out.println("<div class=\"side-search\">");
		out.println(" <form action=\"http://mp.moshimo.com/article/search\" method=\"GET\"><input type=\"hidden\" name=\"shop_id\" value=\"556410\" />");
		out.println("<input type=\"hidden\" name=\"signature\" value=\"\" /><input type=\"text\" name=\"words\" class=\"words\" value=\"\" /><input type=\"image\" src=\"http://image.moshimo.com/static/publish/sd/img/templates/orange/search.gif\" value=\"検索\" /></form>");
		out.println("</div></div><div class=\"section\"><p class=\"genre-all\"><h4>カテゴリー</h4></p><ul class=\"side-link\">");
		out.println("<li><a href=\"item1.html\">PS4</a></li><br><li><a href=\"item2.html\">PS3</a></li><br><li><a href=\"item3.html\">ニンテンドーDS</a></li><br><li><a href=\"item1.html\">Xbox 360</a></li><br><li><a href=\"item1.html\">Wii U</a></li><br><li><a href=\"ranking2.html\">人気ランキング</a></li>	</ul></div></div>");

		out.println("<div class =\"main\">");
		out.println("<p class=\"freeshipping\"><img src=\"http://image.moshimo.com/static/publish/sd/img/templates/orange/freeshipping.jpg\" alt=\"税込8000円以上のお買い上げで送料無料\" /></p>");
		out.println("<h3 align=\"center\">"+title+" の検索結果</h3><table width=\"700\" border=\"0\" bgcolor=\"\"><tr><td colspan=\"6\" class=\"line\" height=\"1\" bgcolor=\"#CDCDCD\">&nbsp;</td></tr>");

		try {
			Class.forName("com.mysql.jdbc.Driver");

			String DB_NAME = "mydb";
			String url = "jdbc:mysql://localhost:3306/" + DB_NAME
					+ "?useUnicode=true&characterEncoding=UTF-8";
			String user = "root";
			String pass = "matsuo";

			Connection con = DriverManager.getConnection(url, user, pass);
			Statement stmt = con.createStatement();
			// String sql = "SELECT * FROM product2 WHERE nam LIKE '%"+ title +
			// "%'" ; // and price >"+price;
			String sql = "SELECT * FROM product2 LEFT JOIN category2 ON product2.id_Category = category2.c_id WHERE 1=1 ";
//	System.out.println(get_texts);	
//			if (title !="") {
				
				sql = sql + start + " nam LIKE '%" + title + "%'";
				if (pr.equals("1_5"))
					sql = sql + start + " price BETWEEN 1 and 4999 ";
				if (pr.equals("5_10"))
					sql = sql + start + " price BETWEEN 5000 and 9999 ";
				if (pr.equals("10_25"))
					sql = sql + start + " price BETWEEN 10000 and 25000 ";
				if (pr.equals("25_50"))
					sql = sql + start + " price BETWEEN 25000 and 50000 ";
				if (pr.equals("50_"))
					sql = sql + start + " price > 50000 ";
				if (cat.equals("ps4"))
					sql = sql + start + " category2.c_nam = "+cat;
				if (cat.equals("ps3"))
					sql = sql + start + " c_id = 2";
//				if (pr.equals("ds"))
//					sql = sql + start + " price > 50000 ";
//				if (pr.equals("50_"))
//					sql = sql + start + " price > 50000 ";
//				if (pr.equals("50_"))
//					sql = sql + start + " price > 50000 ";
//				if (pr.equals("50_"))
//					sql = sql + start + " price > 50000 ";

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				
				int j = rs.getInt("id");
				// System.out.println(a);

				out.println("<tr height=\"48\">");
				out.println("<td align=\"center\" class=\"woong\">" + a
						+ "</td>");
				out.println("<td align=\"center\" bgcolor=\"#FFFFFF\">");
				out.println("<a href=\"http://localhost:8080/GameShop/Detail?id="
						+ j + "\">");
				out.println("<img src=\"imeg/ss.jpg\"\" border=\"0\"  width=50  align=\"center\" valign=\"absmiddle\" alt=\"\"></a></td>");
				out.println("<td><a href=\"http://localhost:8080/GameShop/Detail?id="
						+ j + "\">");
				out.println("<u>" + rs.getString("nam")
						+ "</a> <imeg align=\"absmiddle\" border=\"0\"></u>");
				out.println("</td><td align=\"center\">"
						+ rs.getString("c_nam") + "</td>");
				out.println("<td align=\"center\"><font color=\"#F15800\"><b>"
						+ rs.getInt("price") + "円</b></font></td></tr>");
				out.println("<tr><td colspan=\"6\" class=\"line\" height=\"15\"></td></tr>");
				out.println("<tr><td colspan=\"6\" class=\"line\" height=\"15\"></td></tr>");
				out.println("<tr><td colspan=\"6\" class=\"line\" height=\"15\"></td></tr>");

				a+=1;
			}
			rs.close();
			stmt.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	
		out.println("</body></html>");
	}
}


