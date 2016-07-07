package login;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Signin extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		// HTTPヘッダのContent-Typeを指定
		response.setContentType("text/html; charset=UTF-8");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");

		PrintWriter out = response.getWriter();

		request.setCharacterEncoding("UTF-8");
		// キャッシュ無効処理
		Calendar cal = Calendar.getInstance();
		response.setDateHeader("Last-Modified", cal.getTime().getTime());
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");

		// ここからが処理 (1) クッキー
		Cookie cookie[] = request.getCookies();

		// 【実験】 クライアントからのクッキーを表示してみる
		if (cookie != null) {
			System.out.println("Signin:クライアントから送信されたクッキーを表示します");
			for (int i = 0; i < cookie.length; i++) {
				System.out.println("cookie[" + cookie[i].getName() + " = " + cookie[i].getValue() + "]");
			}
			System.out.println("----------------------------------------------");
		} else {
			System.out.println("初回の接続のようです。");
		}

		// ★リクエストスコープで渡されたパラメータ(エラーメッセージとエラー状態)
		String mess = (String) request.getAttribute("error");

		if (mess == null)
			mess = "";
System.out.println("リクエストスコープ : mess:[" + mess + "]");

		// パスワードの誤りで、ID は合っているいる場合
		String idstr = "";
		
		if (request.getAttribute("state") != null) {
			int state = (int) request.getAttribute("state");

			// System.out.println("★☆★Signin : getAttribute : state =["+state+"]");

			if (state == -2) {
				if (request.getAttribute("id") != null) {
					idstr = (String) request.getAttribute("id");
					System.out.println("★☆★Signin : getAttribute : id =["
							+ idstr + "]");
				} else {
					System.out.println("NULL Signin : getAttribute : id =["
							+ idstr + "]");
				}
			}
		}

		// HTML
		out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
		out.println("<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"ja\" lang=\"ja\">");
		out.println("<head>");
		out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />");
		out.println("<title>プロジェクト「TestServlet」 Signin </title>");
		out.println("<script  type=\"text/javascript\" src=\"http://ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js\"></script>");
		out.println("<link rel=\"stylesheet\" href=\"//ajax.googleapis.com/ajax/libs/jqueryui/1.10.4/themes/smoothness/jquery-ui.css\" />");
		out.println("<script src=\"//ajax.googleapis.com/ajax/libs/jqueryui/1.10.4/jquery-ui.min.js\"></script>");
		out.println("<script src=\"js/my.js\"></script>");
		// out.println("<link rel=\"stylesheet\" href=\"style.css\" type=\"text/css\" />");
		out.println("</head>");
		out.println("<body>");

	    


	

	out.println("<center>"+
		"<div align=\"right\" style=\"font-size: x-small;width:600px;\">"+
			"<a href=\"index2.jsp\">ショップへ戻る</a>"+
		"</div>"+
	"<div style=\"height:10px;\"></div>"+

		"<div class=\"info\" style=\"width:580px;line-height:20px;\">"+
			"<br><h3>ログインID（メールアドレス）とパスワードを入力してください。</h3><br>"+
			"<a href=\"#\"><b>パスワードを忘れた方はこちら</b></a>"+
		"</div>"+
	"</div>");

	
			
	
		// サインインフォーム

		out.print("<form id=\"f1\" method=\"POST\" action=\"Checkuser\" >");
		out.print("<table border=\"1\" cellpadding=\"2\" cellspacing=\"1\" class=\"table1\" align=\"center\" style=\"margin:10px auto 0px;\" >");
		out.print("<tr>");
		out.print("<td class=\"CELL_1_L\">ユーザーＩＤ　</td>");
		out.print("<td><input id=\"idtsr\" type=\"text\" name=\"username\" style=\"width:200px;\" size=\"15\" value=\""+ idstr + "\" /></td>");
		out.print("</tr>");
		out.print("<tr>");
		out.print("<td align=\"right\">パスワード：</td>");
		out.print("<td><input type=\"password\" name=\"password\" style=\"width:200px;\" size=\"15\" /></td>");
		out.print("</tr>");
		out.print("<tr>");
		out.print("</tr>");
		out.print("</table>");
		out.print("<input type=\"submit\" value=\"ログイン\" />");
		out.print("<input type=\"reset\"  value=\"取消\" />");
		out.print("<div id=\"error_mess\" ></div>");
		out.print("<input id=\"errinfo\" type=\"hidden\" name=\"error\" value=\""
				+ mess + "\">");
		out.print("</form></center>");

		out.println("</body></html>");

	}

}
