package test;


import java.io.IOException;

import login.*;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Detail extends HttpServlet {

	private static final long serialVersionUID = 5989725575563102736L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		response.setContentType("text/html; charset=UTF-8");
		// キャッシュ無効
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");

		PrintWriter out = response.getWriter();

		String tmp = request.getParameter("id");
		int pid = Integer.parseInt(tmp);
System.out.println(pid+"pid");
		
		Calendar objCal1 = Calendar.getInstance();
		Calendar objCal2 = Calendar.getInstance();
		objCal2.set(1970, 0, 1, 0, 0, 0);
		response.setDateHeader("Last-Modified", objCal1.getTime().getTime());
		response.setDateHeader("Expires", objCal2.getTime().getTime());
		response.setHeader("pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");

		String id = null;
		String passwd = null;

		String sign_mess = null;
		String sign_tag = null;
		
		int log_id = 0;

		// JSPの場合、セッション自動生成されてしまう。
		// この場合、セッションの有無とid及びパスワードの組み合わせ判定が必要になる。
		// これでもいい場合があるが、今回は
		// 各 JSP ページにおいて、セッション生成を page ディレクティブで禁止する。
		// 分かりやすく、スマートにログインログアウトの機能が作れる。

		// セッションチェック(認証済みか否か)
		Checkdat dat = Check.check(request, response, null, null);
		//debug
		//   	System.out.println("===================-- index.jsp =======================");
		// 	dat.show();

		switch (dat.getstate()) {
		case 0:
		case -1:
		case -2:
			// 		System.out.println("サインインしていません。");
			sign_mess = "サインインしていません。";

			sign_tag = "<form method=\"POST\" action=\"/GameShop/Signin\" >"
					+ "<input type=\"submit\" style=\"background-color: #fff;\"value=\"サインインへ\" />"
					+ "</form>";
			break;
		case 1:
		case 2:
			// 		System.out.println("サインインしています");
			sign_mess = dat.getid() + "さん、サインインしています。";
			sign_tag = "<form method=\"POST\" action=\"http://localhost:8080/GameShop/Signout\" >"
					+ "<input type=\"submit\" style=\"background-color: #fff;\" value=\"サインアウト\" />"
					+ "</form>";
			break;
		default:
			System.out.println("Checkuser.check()の戻り値がおかしい");
			break;
		}

		
		out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
		out.println("<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"ja\" lang=\"ja\">");
		out.println("<head>");
		out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />");
		out.println("<title>一覧</title>");
		out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/ss.css\">");
		out.println("</head>");
		
		
		out.println("<body>");
		out.println("<div id =\"wrap\"><div class =\"head\">");
		out.println("<h1>GAME通信販売</h1><br/>");
		out.println("<a href=\"login.html\">");
		out.println("<span class=\"label\"><BUTTON type=\"submit\" style=\"background-color: #fff;\"><big>ログイン</big></BUTTON></span>");
		out.println("</a><ul class=\"menu\"><li><a href=\"index.html\">TOP</a></li>	<li><a href=\"item1.html\">商品一覧</a></li><li><a href=\"idinfo.html\">会員登録</a></li><li><a href=\"\">カートBOX</a></li><li><a href=\"mail.html\">問い合わせ</a></li></ul></a>	");

		out.println("<class=\"itemsearch\">価格帯<select name=\"p\">");
		out.println("<option value=\"\">-</option>");
		out.println("<option value=\"0_1\"  >0円～1000円</option><option value=\"1_5\"  >1000円～5000円</option><option value=\"5_10\"  >5000円～10000円</option>");
		out.println("<option value=\"10_50\"  >10000円～50000円</option><option value=\"50\"  >100000円～</option></select>");
		out.println("<select name=\"ra\"><option value=\"\">---</option><option value=\"0\"  >新品</option><option value=\"5\" >中古品</option></select>");

		out.println("<select name=\"cat\" class=\"wd1\"><option value=\"\">---</option><option value=\"301\" >PS4</option><option value=\"323\" >PS3</option>");

		out.println("<option value=\"327\" >ニンテンドーDS</option><option value=\"404\" プレイステーション Vita</option><option value=\"304\" >Xbox 360</option><option value=\"311\" >Wii U</option>");
		out.println("</select><input name=\"kw\" type=\"text\" size=\"30\" value=\"\" /><input type=\"submit\" value=\"検索\" /></form></div>");
		out.println("<div class =\"side\">");
		out.println("<p class=\"side-cart\"><a href=\"http://mp.moshimo.com/cart?shop_id=556410&amp;signature=SD\"><img src=\"http://image.moshimo.com/static/publish/sd/img/templates/orange/show-cart.gif\" alt=\"カートを見る\" /></a></p>");
		out.println("<div class=\"section\"><h4><class=\"caption\">商品検索</h4>");
		out.println("<div class=\"side-search\">");
		out.println(" <form action=\"http://mp.moshimo.com/article/search\" method=\"GET\"><input type=\"hidden\" name=\"shop_id\" value=\"556410\" />");
		out.println("<input type=\"hidden\" name=\"signature\" value=\"\" /><input type=\"text\" name=\"words\" class=\"words\" value=\"\" /><input type=\"image\" src=\"http://image.moshimo.com/static/publish/sd/img/templates/orange/search.gif\" value=\"検索\" /></form>");
		out.println("</div></div><div class=\"section\"><p class=\"genre-all\"><h4>カテゴリー</h4></p><ul class=\"side-link\">");
		out.println("<li><a href=\"item1.html\">PS4</a></li><br><li><a href=\"item2.html\">PS3</a></li><br><li><a href=\"item3.html\">ニンテンドーDS</a></li><br><li><a href=\"item1.html\">Xbox 360</a></li><br><li><a href=\"item1.html\">Wii U</a></li><br><li><a href=\"ranking2.html\">人気ランキング</a></li>	</ul></div></div>");

		out.println("<div class =\"main\">");
		out.println("<p class=\"freeshipping\"><img src=\"http://image.moshimo.com/static/publish/sd/img/templates/orange/freeshipping.jpg\" alt=\"税込8000円以上のお買い上げで送料無料\" /></p>");

		// out.println("<h3 align=\"center\">商品一覧</h3><table width=\"700\" border=\"0\" bgcolor=\"\"><tr><td colspan=\"6\" class=\"line\" height=\"1\" bgcolor=\"#CDCDCD\">&nbsp;</td></tr>");

		out.println(" <p class=\"clearfix\">");
		out.println("<form method=\"POST\" action=\"MainController\">");

		try {
			Class.forName("com.mysql.jdbc.Driver");

			String DB_NAME = "mydb";
			String url = "jdbc:mysql://localhost:3306/" + DB_NAME
					+ "?useUnicode=true&characterEncoding=UTF-8";
			String user = "root";
			String pass = "matsuo";

			Connection con = DriverManager.getConnection(url, user, pass);
			Statement stmt = con.createStatement();
			String sql = "SELECT * FROM product2 WHERE id='" + pid + "'";
			String sql2 = "SELECT * FROM idpass WHERE idNam='" + dat.getid() + "'";
			
			int a = 0;
			ResultSet rs2 = stmt.executeQuery(sql2);
			if(dat.getid() !=null){
				rs2.next();
			    log_id = (rs2.getInt("id"));
		 		rs2.close();
			}
	 		
	 		ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			String jan = rs.getString("jan");
			jan = jan.trim();
			
			out.println("<img src=\"http://www.comshop.ne.jp/images/item/"+jan+"_a.jpg\">");
			http://www.comshop.ne.jp/images/item/<%=jan%>_a.jpg
			out.println("<div id=\"itemInfo\">");
			out.println("<p>商品名 :<br/>" + rs.getString("nam") + "</p>");
			out.println("<p>プラットフォーム :" + rs.getString("id_cat") + "</p>");
			out.println("<p>価格 :" + rs.getInt("price") + "円（税込）</p>");
			out.println("<p>Amazonランキング:" + rs.getInt("ranck") + "位</p>");

			a++;

			rs.close();
			stmt.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		out.println("<input type=\"hidden\" name=\"logId\" value=\""+log_id+"\"/>");
		out.println("<input type=\"hidden\" name=\"action\" value=\"add\"/>");
		out.println("<input type=\"hidden\" name=\"index\" value=\""+pid+"\"/>");
		out.println("数量 : <input type=\"text\" name=\"qua\" value=\"1\" size=2>");
		out.println("&nbsp;&nbsp;&nbsp;<b><font color=\"red\">残りあと10個</font></b><br />");
		out.println("</p></p>");

		out.println("<BUTTON type=\"submit\"  style=\"background-color: #f91;\"><big>カートに入れる</big></BUTTON>");
		out.println("<br><br/><br/><br/><br/><br/><br/><br/>");
		out.println("<hr></div><div class =\"footer\"></div>");
		out.println("</form></body></html>");
	}
}