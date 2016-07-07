package login;

import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import test.*;

public class Checkuser extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public void doPost( HttpServletRequest request, 
            HttpServletResponse response ) 
            throws IOException, ServletException {
		
		
		
		// パラメータを取得
		String id = request.getParameter("username");
		String passwd = request.getParameter("password");
		
		Token tk = new Token();
		// ユーザチェックのメイン
		Checkdat dat = Check.check( request, response , id , passwd );
		
		HttpSession session =null;
		String page = null;
		RequestDispatcher dispatch=null;
		// 実験結果
		switch( dat.getstate() ){
			case 0:
			case -1:
			case -2:
				System.out.println("サインイン失敗しました。拒否です。");
		          page = "/Signin";

		          dispatch = request.getRequestDispatcher(page);
		          // フォワード実行
		          // 呼び出し元が、doPost なので、フォワード先も doPost である必要があります。
		          // 元の要求で使用した同一のHTTPメソッドが使用される。
		          
		  			// リクエストスコープへのエラーメッセージの登録
		  			request.setAttribute( "error",  "★"+dat.getErrormess() );
		  			request.setAttribute( "state",  dat.getstate() );
		  			if( dat.getstate()  == -2 ){
		  				
		  				request.setAttribute( "id",  dat.getid() );
		  			}
		  			
		  			dispatch.forward(request, response);
		          
				break;
			case 1:
				System.out.println("サインインしているユーザです。");

		        page = "http://localhost:8080/GameShop/index2.jsp";

					response.sendRedirect( page );
				
				break;
			case 2:
				System.out.println("AAサインイン成功しました。");
//				System.out.println("Checkuser.check()で新規にセッションを発行した");
				tk.createToken(request);
		          page = "http://localhost:8080/GameShop/index2.jsp";
		          /*
		          dispatch = request.getRequestDispatcher(page);
		          // フォワード実行★リダイレクトがいい おきにいり保存や、SEO的にも
		          dispatch.forward(request, response);
		          */

				response.sendRedirect( page );

		          
				break;
			default:
				System.out.println("Checkuser.check()の戻り値がおかしい");
				break;
		}
		
		
	}
	
	/**
	 * ユーザチェックメソッド
	 * 
	 * (1) 引数 id  , passwd があるか？
	 * (2A) ある場合、「新規サインイン」の要求と見なして、idとpasswdを調べてサインイン処理する。
	 * (2B) ない場合、
	 * (3A)「有効セッション」を持っていれば、セッション変数に格納されているはずの、id,passwd を検査する。
	 * 		検査にパスすれば、セッションの継続となる。
	 * 
	 * 	(3B)「有効なセッション」が無ければ、サインインは拒否され、その理由を「Checkdatインスタンス」に格納して終了する。
	 * 引数に、idとパスワードが無ければ、サインインしているとはみなさない。
	 * サインインできない理由などのメッセージと状況を格納した、 「Checkdat」インスタンスを
	 * 返す。
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @param passwd
	 * @return Checkdatインスタンス
	 * @throws IOException
	 * @throws ServletException
	 */
	
	
	
}
