package cart;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import cart.*;


public class SvMail extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private String host = null;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		// 使用する smtp サーバーを、 web.xml の書き換えで変更できるようにしています。
	
		this.host = config.getServletContext().getInitParameter("smtp.host");
	}
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8"); 
		HttpSession session = request.getSession();
		CartItem ct = new CartItem();
		SimpleEmail email = new SimpleEmail();
		ItemDao dao = new ItemDao();
		
		int lid = (Integer.parseInt(request.getParameter("lid")));
		String mail = request.getParameter("mail");
		String payment = request.getParameter("payment");
		String bank = request.getParameter("bank");
		String lNam = request.getParameter("lNam");
		String fNam = request.getParameter("fNam");
		String post = request.getParameter("post");
		String address = request.getParameter("addres");
		String price = request.getParameter("price");
		String qua = request.getParameter("qua");
		
		session.setAttribute("token", "0");

		dao.remove(lid, 0);
		
		try {
			email.setHostName(this.host);
			email.setSmtpPort(587); 	// submission port 設定  25番はidとpassがいる 
			// TLS:TLS（トランスポート レイヤー セキュリティ）
			// メールを安全に配信するために暗号化を行う SSL（セキュア ソケット レイヤー）テクノロジーに基づいた業界全体の標準。
			// Secure Sockets Layer (SSL) とも呼ばれている。
			email.setStartTLSEnabled(true);
			// ★アカウント取得して設定してください。
			email.setAuthentication("1", ""); //id  pass
	    	email.addTo("f@gmail.com"); // <-- 送り先
			email.setFrom("a@gmail.com"); // <-- 送信者のメールアドレス
			email.setSubject("TEST MAIL 実験です。");
			email.setContent("MAIL BODY", "text/plain; charset=iso-2022-jp");
			email.setCharset("iso-2022-jp");
			email.setDebug(false);
			email.setMsg("注文情報\r\nメールアドレス＝"+mail+"\r\n合計金額= "+payment+"\r\nお客様\r\n名前 "+lNam+fNam+
					"\r\n住所"+post+"\r\n"+address+"\r\n"+price+"円\r\n"+"数量"+qua);
						
			email.send();

		} catch (EmailException e) {
			System.out.println("mailerror");
			throw new ServletException(e);
			
		}
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
		out.println("<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"ja\" lang=\"ja\">");
		out.println("<head>");
		out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />"+
				"<script type=\"text/javascript\">"+
					"setTimeout(\"link()\", 5000);"+
					"function link(){"+
					"location.href = \"http://localhost:8080/GameShop/index2.jsp\""+
				"}</script>");
		
		out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/ss.css\">");
		out.println("</head>");
		
		
		out.println("<body>");
		out.println("<h3>ご注文ありがとうございました。</h3>");
		out.println("<br>5秒後に　TOPページに移動します。<br>"+
				"<br><a href=\"https://localhost:8080/GameShop/index2.jsp\">移動しない場合はここをクリック</a>");
				



	}
}
