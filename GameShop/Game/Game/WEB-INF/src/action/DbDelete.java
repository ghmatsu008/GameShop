package action;



import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;
import java.util.ArrayList;
import java.util.List;
import test.*;

import java.sql.*;

public class  DbDelete extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException{

		response.setContentType("text/html; charset=UTF-8");
		// キャッシュ無効
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control","no-cache");
		PrintWriter out = response.getWriter();

		String foward = "/jsp/DbDelete.jsp";
		Connection conn = null;

		String DB_NAME = "mydb";
		String url = "jdbc:mysql://localhost:3306/" + DB_NAME + "?useUnicode=true&characterEncoding=UTF-8";
		String user = "root";
		String pass = "matsuo";

		ItemData[] items = new ItemData[0];

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(url, user, pass);
			Statement stmt = conn.createStatement();


			if( request.getParameter("delete")!=null ){
				String sql ="SELECT count(id) as count FROM product2";
				ResultSet rs = stmt.executeQuery(sql);

				rs.next();
				int itemCount = rs.getInt("count");

				boolean isDelete = false;
				StringBuffer sb = new StringBuffer("DELETE FROM product2 WHERE id in (");

				for( int i=0; i<itemCount; i++ ){
					System.out.println(request.getParameter("delete_id_"+i));
					String id = request.getParameter("delete_id_"+i);
					if( id==null ){
						continue;
					}
					sb.append( id ).append(",");

					isDelete = true;
				}
							//カンマ削除
				sql = new String(sb);      
				if( sql.endsWith(",") ){
					sql = sql.substring(0, sql.length()-1 );
				}
				sql = sql +")";
				System.out.println("削除SQL="+sql);
				int result = 0;
				if( isDelete ){
					result = stmt.executeUpdate( sql );
				}
				System.out.println("削除数："+result);
			}

			//抽出
			String sql = "SELECT * FROM product2"; //order by id";
			ResultSet rs = stmt.executeQuery( sql );

			List<ItemData> list = new ArrayList<ItemData>();
			while(rs.next()){
				ItemData item = new ItemData();
				item.setId(rs.getInt("id"));
				item.setNam(rs.getString("nam"));
				item.setPrice(rs.getInt("price"));
				list.add( item );
			}
			items = list.toArray(new ItemData[0]);
			stmt.close();
			conn.close();
		}catch (Exception e) {
			e.printStackTrace();
		}

		// request に登録JSP で<jsp:useBean id="items" ... >で取得
		request.setAttribute( "items", items );

		ServletContext sc = getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher( foward );
		rd.forward(request, response);
	}
}


