

	package action;

	import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

	import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.omg.CORBA.INTERNAL;

	public class Test2 extends HttpServlet {

		private static final long serialVersionUID = 5989725575563102736L;

		public void doGet(HttpServletRequest request, HttpServletResponse response)
				throws IOException, ServletException {

			response.setContentType("text/html; charset=UTF-8");
			// キャッシュ無効
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");

			int a=0;
			
			int b;
			int maxRec;
//			final int PER_PAGE = 20;
			HttpSession session = request.getSession();
			
			Integer pageA = (Integer)session.getAttribute("pageA");
			Integer pageB = (Integer)session.getAttribute("pageB");
	//	System.out.println(c);
//				 n = Integer.parseInt(pageA);
//				 c = Integer.parseInt(pageB);
				
				pageA +=20;
				pageB +=20;

			PrintWriter out = response.getWriter();

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
			out.println("</a><ul class=\"menu\"><li><a href=\"index.html\">TOP</a></li>	<li><a href=\"item1.html\">商品一覧</a></li><li><a href=\"idinfo.html\">会員登録</a></li><li><a href=\"\">カートBOX</a></li><li><a href=\"mail.html\">問い合わせ</a></li></ul></a>	");

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
			out.println("<h3 align=\"center\">商品一覧</h3><table width=\"700\" border=\"0\" bgcolor=\"\"><tr><td colspan=\"6\" class=\"line\" height=\"1\" bgcolor=\"#CDCDCD\">&nbsp;</td></tr>");

			try {
				Class.forName("com.mysql.jdbc.Driver");

				String DB_NAME = "mydb";
				String url = "jdbc:mysql://localhost:3306/" + DB_NAME
						+ "?useUnicode=true&characterEncoding=UTF-8";
				String user = "root";
				String pass = "matsuo";

				Connection con = DriverManager.getConnection(url, user, pass);
				Statement stmt = con.createStatement();

				String str = "SELECT count(*) as SUM FROM product2"; // トータルページ数
				ResultSet rc = stmt.executeQuery(str);

				rc.next();
				maxRec = rc.getInt("SUM");
				rc.close();

				String sql;
				ResultSet rs;
				// rs = (ResultSet)request.getAttribute("rs");
				// rs.absolute(20 * (n - 1));
				
				sql = "SELECT * FROM product2 LIMIT " + pageA + "," +pageB;
				rs = stmt.executeQuery(sql);
					while (rs.next()) {

						int j = rs.getInt("id");
						// System.out.println(a);

						out.println("<tr height=\"48\">");
						out.println("<td align=\"center\" class=\"woong\">" + a
								+ "</td>");
						out.println("<td align=\"center\" bgcolor=\"#FFFFFF\">");
						out.println("<a href=\"http://localhost:8080/GameShop/Detail?id="
								+ j + "\">");
						out.println("<img src=\"imeg/ps4 (1).jpg\"\" border=\"0\"  width=50  align=\"center\" valign=\"absmiddle\" alt=\"\"></a></td>");
						out.println("<td><a href=\"http://localhost:8080/GameShop/Detail?id="
								+ j + "\">");
						out.println("<u>"
								+ rs.getString("nam")
								+ "</a> <imeg align=\"absmiddle\" border=\"0\"></u>");
						out.println("</td><td align=\"center\">"
								+ rs.getInt("id_Category") + "</td>");
						out.println("<td align=\"center\"><font color=\"#F15800\"><b>"
								+ rs.getInt("price") + "円</b></font></td></tr>");
						out.println("<tr><td colspan=\"6\" class=\"line\" height=\"15\"></td></tr>");
						out.println("<tr><td colspan=\"6\" class=\"line\" height=\"15\"></td></tr>");
						out.println("<tr><td colspan=\"6\" class=\"line\" height=\"15\"></td></tr>");

						a++;
					}
					rs.close();
					stmt.close();
					con.close();

					session.setAttribute("pageA", (pageA));
					session.setAttribute("pageB", (pageB));
				

				
			
		} catch (Exception e) {
			e.printStackTrace();
		}

			out.println("<p class=\"navi\">");
			out.println("<p class=\"next\"><a href=\"http://localhost:8080/GameShop/Test2\">次へ</a></p>");
			out.println("<p class=\"back\"><a href=\"http://localhost:8080/GameShop/Test2\">戻る</a></p>");
			out.println("</p></div>");
			out.println("</body></html>");
		}

	}