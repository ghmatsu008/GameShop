package login;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Signout  extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doPost( HttpServletRequest request, 
            HttpServletResponse response ) 
            throws IOException, ServletException {
		
		response.setContentType("text/html; charset=UTF-8");
		// キャッシュ無効
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control","no-cache");
        // 出力用
        PrintWriter out = response.getWriter();

        request.setCharacterEncoding("UTF-8");
        
		Calendar cal = Calendar.getInstance();
		response.setDateHeader("Last-Modified" , cal.getTime().getTime() );
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control","no-cache");
		
		
	      System.out.println("----------------------------------------------");
	      System.out.println("class Signout サインアウトします。");
	      System.out.println("----------------------------------------------");
	      
		
		// ====================================
		// ここからが処理
		// ====================================
		//　Check_datインスタンスの取得
		Checkdat dat = Checkdat.getInstance();
		
		// セッションの開始
	    // クッキーにセッションIDがなければ当然新規ではあるが、
	    // getSession(false)で、セッションの有無を調べます。なければ、 null が帰ります。
		HttpSession session = request.getSession(false);
		if( session != null ){
	      // セッションの無効化
			session.invalidate();

			dat.setstate(0);  // <-- 0の場合、認証できなかった。
			dat.setSession(null);
			dat.setid(null);
			dat.setpasswd(null);
			dat.setErrormess("サインアウトしました。");
			
		      System.out.println("----------------------------------------------");
	    	  System.out.println("セッションを破棄しました。");
		      System.out.println("----------------------------------------------");
		      
		      
		      // debug
		      session = request.getSession(false);

		      if (session == null){
		        System.out.println("★<p>セッションは破棄されました</p>");
		      }else{
		    	  System.out.println("★<p>セッションが残っています</p>");
		      }

		      	//debug
		      dat.show();

		      
		}
		
		
		/*
		リダイレクトについて
		ステータス・コードが恒久的な移転の際に用いる301ではなく、
		一時的な移転のステータス・コード302では、SEO対策としてはよく無いと言われます。
		ここの、 Signoutでの採用は、意味がありませんが、参考のために
		
		恒久的な移転の際に用いる301にする方法を取ります。

		・302
			String page = "http://localhost:8080/TestSession/Jsp/index.jsp";
			response.sendRedirect( page );
		
		
		・301
			response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
			response.setHeader("Location","http://www.test.com");
			return;
	    */
		

	    

		/*
		RequestDispatcher dispatch=null;
		String page = "Jsp/index.jsp";
        dispatch = request.getRequestDispatcher(page);
			dispatch.forward(request, response);
		*/
		
	    // 301 リダイレクト
		response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
		response.setHeader("Location","http://localhost:8080/GameShop/index2.jsp");
		//return;
		
	}
	
}
