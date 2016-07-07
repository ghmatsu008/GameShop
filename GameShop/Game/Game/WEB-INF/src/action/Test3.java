

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

	public class Test3 extends HttpServlet {

		private static final long serialVersionUID = 5989725575563102736L;

		public void doGet(HttpServletRequest request, HttpServletResponse response)
				throws IOException, ServletException {

			response.setContentType("text/html; charset=UTF-8");
			// キャッシュ無効
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			
			String tmp = request.getParameter("id");
			int pNo = Integer.parseInt(tmp);


			int a=0;
			
			int b;
			int recMax;
			int PER = 20;
			int pageAll = 0;  //　総ページ数
			int pageCnt = 0;  //　ページ数
//			final int PER_PAGE = 20;
			HttpSession session = request.getSession();
			
			Integer n = (Integer)session.getAttribute("pageA");
			Integer c = (Integer)session.getAttribute("pageB");
//			Integer pageNo = (Integer)session.getAttribute("pNo");
			Integer no = (Integer)session.getAttribute("no");
				
//				n +=20;
//				c +=20;

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
				recMax = rc.getInt("SUM");
				rc.close();
				
				//ページ始★★★★★★★★★★★★★★★★★★★★★★★★★★
			
				
				
				
				pageAll += PER;
				pageCnt = recMax / PER;
				
//				sNo = dbab.isNull(sNo, "0");
				
//				int start = (Integer)session.getAttribute("end") ;
//				int end = start + PER;
//System.out.println(start+"  "+end);
//				session.setAttribute("end",(end));
				
				int start = pNo * 20;  
//				pageNo = start / PER;
//				int iStart = PER * pageNo;
//				int iEnd = PER * (pageNo + 1);
				
				System.out.println(pNo);
				
				//int recCnt = 0;
				//データ表示部
				//while (rs.next()) {
				// if((iStart <= recCnt) && (iEnd > recCnt)){
				// }
				// recCnt++;
				//}
				//リンク作成部
				no = start;
				start ++;
				String sql;
				ResultSet rs;
				// rs = (ResultSet)request.getAttribute("rs");
				// rs.absolute(20 * (n - 1));
				
				sql = "SELECT * FROM product2 LIMIT " + start + "," + PER;
				rs = stmt.executeQuery(sql);
					while (rs.next()) {

						int j = rs.getInt("id");
						// System.out.println(a);

						out.println("<tr height=\"48\">");
						out.println("<td align=\"center\" class=\"woong\">" + no
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

						no++;
					}
					rs.close();
					stmt.close();
					con.close();

//					pageNo++;
//					session.setAttribute("pageA", (iStart));
//					session.setAttribute("pageB", (iEnd));
//					session.setAttribute("pNo", (pageNo));
					session.setAttribute("no", (no));

				
			
		} catch (Exception e) {
			e.printStackTrace();
		}
			for(int i = pNo-3; i <= pNo+3; i++){ 
				out.print("<a href=\"http://localhost:8080/GameShop/Test3?id="+i+"\">[" + i + " ]    </a>  ");
//				System.out.println(i);
			}
			
			
			out.println("<p class=\"navi\">");
			out.println("<p class=\"next\"><a href=\"http://localhost:8080/GameShop/Test3?id="+(pNo+1)+"\">次へ</a></p>");
			out.println("<p class=\"back\"><a href=\"http://localhost:8080/GameShop/Test3\">戻る</a></p>");
			out.println("</p></div>");
			out.println("</body></html>");
		}

	}