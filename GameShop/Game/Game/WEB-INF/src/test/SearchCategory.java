/*
 * カテゴリー検索するためのページ生成
 * 
 * JSPを使うと言い思いますが、Servlet で行ってみました。
 * 
 * http://localhost:8080/Shopdb/SearchCategory
 * 
 */
package test;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SearchCategory  extends HttpServlet {
			
			private static final long serialVersionUID = 1L;

			public void doGet( HttpServletRequest request, 
		            HttpServletResponse response ) 
		            throws IOException, ServletException {


				// HTTPヘッダのContent-Typeを指定
				response.setContentType("text/html; charset=UTF-8");
				// キャッシュ無効
				response.setHeader("Pragma", "no-cache");
				response.setHeader("Cache-Control","no-cache");
		        
		        
		        PrintWriter out = response.getWriter();
		        
				// first.html で、charset=UTF-8 宣言しているので
		        // パラメータは、UTF-8で受け取る
		        request.setCharacterEncoding("UTF-8");
		        
		        
		        
		        // カテゴリー
				// カテゴリーの全データを、前もって取得する方法にしました。
		        CategoryData da=null;
				GetCategory cat = new GetCategory();
				List<CategoryData> catList = cat.getCategory();
				
		        
				String tmp=null;
				StringBuffer sel = new StringBuffer();
				sel.append("<OPTION value=\"0\" selected>選んでください</OPTION>");
				for ( Iterator<CategoryData> ite = catList.iterator(); ite.hasNext();) {
					
					da = (CategoryData)ite.next(); // <-- コレクション内移動
					tmp = "<OPTION value=\""+da.getid()+"\" >"+da.getname()+"</OPTION>";
					sel.append( tmp ); 

				}
				
				//debug
				System.out.println("["+sel.toString()+"]");
				
				
		        // レスポンス
		        out.println("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
		        out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
		        out.println("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
		        out.println("<head>");
		        out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />");
		        out.println("<title>カテゴリーテスト</title>");
		        out.println("</head>");
		        out.println("<body>");
		        out.println("<form action=\"/Shopdb/ResSearch\" method=\"post\">");
		        out.println("<table border=\"0\">");
		        out.println("<tr>");
		        out.println("<td>カテゴリーを選択してください</td>");
		        out.println("<td>");
		        out.println("<select name=\"catname\">");
		        out.println( sel.toString() );
		        out.println("</select>");
		        out.println("</td>");
		        out.println("</tr>");
		        out.println("</table>");
		        out.println("<input type=\"submit\" VALUE=\"送信する\">");
		        out.println("<input type=\"reset\" VALUE=\"リセット\">");
		        out.println("</form>");
		        out.println("</body>");
		        out.println("</html>");

			}

			
}
