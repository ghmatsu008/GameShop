package filter;


	/*
	 * 
	 * フィルタを使った　セキュリティポリシーのテスト

	 * 
	 * サーブレット	 TestFilter を呼んで実験
	 * 
	 * http://localhost:8080/TestAttack/TestFilter
	 * 
	 * 参考サイト
	 * https://www.owasp.org/index.php/Content_Security_Policy
	 * 
	 *
	 *   web.xml
	 *   
	 *   <filter>
	 *    <filter-name>filtertest</filter-name>
	 *    <filter-class>test.PoliciesFilter</filter-class>
	 *  </filter>
	 *
	 *  <filter-mapping>
	 *    <filter-name>filtertest</filter-name>
	 *    <url-pattern>/TestFilter</url-pattern>
	 *  </filter-mapping>
	 * 
	 * 
	 */


	import java.io.Console;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

	import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Hex;

	public class CSRF_Filter  implements Filter  {

		@Override
		public void init(FilterConfig fConfig) throws ServletException { }

		@Override
		public void destroy() { }

		@Override
		public void doFilter(ServletRequest ss_request, ServletResponse ss_response, FilterChain fchain) 
						throws IOException, ServletException {

						// 応答の準備
			HttpServletResponse response = (HttpServletResponse) ss_response;
			HttpServletRequest request = (HttpServletRequest) ss_request;
			
			// HTTPヘッダのContent-Typeを指定 
			response.setCharacterEncoding( "UTF-8");
			response.setContentType("text/html");
			//response.setContentType("text/html; charset=UTF-8");
			
			// キャッシュ無効処理
			Calendar objCal1=Calendar.getInstance();
			Calendar objCal2=Calendar.getInstance();
			objCal2.set(1970,0,1,0,0,0);
			response.setDateHeader("Last-Modified",objCal1.getTime().getTime());
			response.setDateHeader("Expires",objCal2.getTime().getTime());
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control","no-cache");

	        // 呼び出し側が、UTF-8 なので、パラメータは、UTF-8で受け取る　 GETには無効です。
	        request.setCharacterEncoding("UTF-8");
	        
	        if(request.getMethod().equals("GET")){
	        	createToken(request);
	System.out.println("GET  token");
	        }else{
	System.out.println("POST  token");
	        	if(!checkToken(request)){
	        		throw new ServletException("不正なアクセスが行。");
	
	        	}
	        }
			//debug
			System.out.println("キャッシュ用フィルタ実行しました。");
			
			/* Step 3 : 最後の処理 Let request continue chain filter */
			// fchain.doFilter()で、本来呼び出された次のサーブレットへ処理を移します。
			fchain.doFilter( ss_request , ss_response );
		}
		
		//トークン生成
		private void createToken(HttpServletRequest request){
			MessageDigest md = null;
			HttpSession session = request.getSession();
			try{
				md = MessageDigest.getInstance("MD5");
				md.update(session.getId().getBytes());
				session.setAttribute("token", toHex(md.digest()));
System.out.println(toHex(md.digest())+"toHex");
			}catch(NoSuchAlgorithmException e){
				e.printStackTrace();
			}
		}
		
		private boolean checkToken(HttpServletRequest request){
			HttpSession session = request.getSession();
			String s_token = (String)session.getAttribute("token");

			String r_token = request.getParameter("token");
System.out.println("session  token"+s_token+"  r_token="+r_token);			
			if(s_token == null || r_token == null || r_token.isEmpty() || s_token.length() < 2){
				return false;
			}
			return s_token.equals(r_token);
		}
		
		//バイトデータを16進数の文字に変換
		private String toHex(byte[] digest){
			StringBuffer buff = new StringBuffer();
			for(int i=0; i < digest.length; i++){
				buff.append(Integer.toHexString((digest[i] >> 4 ) & 0x0F));
				buff.append(Integer.toHexString(digest[i] & 0x0F));
			}
			return buff.toString();
		}

}
