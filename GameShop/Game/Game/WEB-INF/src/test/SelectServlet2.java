package test;

import java.io.IOException;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.interceptors.SessionAssociationInterceptor;

public class SelectServlet2 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html; charset=UTF-8");
		// キャッシュ無効
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control","no-cache");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session =null;
		
		String nam = request.getParameter("nam");
	
	
			nam = new String(nam.getBytes("ISO-8859-1"),"UTF-8");
		
		nam = nam.trim();

		String pr = request.getParameter("pr");
		System.out.println("nam"+pr+"nam");
		String cat = request.getParameter("cat");
		String tmp = request.getParameter("id");  // ページNo
		
		
//		nam = (String) session.getAttribute("nam");
//		pr = (String) session.getAttribute("pr");
	
//		cat = (String) session.getAttribute("cat");
	
		String str = (String) request.getAttribute("recMax");
		String str2 = (String) request.getAttribute("pStart");
//		String str3 = (String) request.getAttribute("pNo");
		int recMax;
		int pStart = 0;
		int pNo = 1;
		
		if (tmp != null ) {
			pNo = Integer.parseInt(tmp);
			if(pNo == 2)
				pStart = 20;
			else if(pNo >=3)
				pStart = pNo * 20;
		}
		
		if(str != null){
			recMax = Integer.parseInt(str);
		}
		if(str2 != null){
			pStart = Integer.parseInt(str2);
			if( pStart < 0){
				pStart = 0;
			}
			
		}
		
		String DB_NAME = "mydb";
		String url = "jdbc:mysql://localhost:3306/" + DB_NAME
				+ "?useUnicode=true&characterEncoding=UTF-8";
		String user = "root";
		String pass = "matsuo";

		int a = 0;
		int b;
		int PER = 20;
		String start = "and";

		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection con = DriverManager.getConnection(url, user, pass);
			

			pStart ++;

			String sql = "SELECT * FROM product2 LEFT JOIN category2 ON product2.id_cat = category2.c_id WHERE 1=1 ";
			String sqlTotal;
//			= "SELECT count(*) as SUM FROM product2 LEFT JOIN category2 ON product2.id_Category = category2.c_id WHERE 1=1 ";
			// System.out.println(title);
			PreparedStatement stmt = con.prepareStatement(sql);
			if( !nam.equals("")) {
				sql = sql + start + " nam LIKE ?";
				stmt = con.prepareStatement(sql);
				stmt.setString(1,"%" + nam + "%" );
			} 
			if (!pr.equals("")) {
				switch(pr){
					case "1":
						sql = sql + start + " price BETWEEN 1 and 4999 ";
						break;
					case "2":
						sql = sql + start + " price BETWEEN 5000 and 9999 ";
						break;
					case "3":
						sql = sql + start + " price BETWEEN 10000 and 25000 ";
						break;
					case "4":
						sql = sql + start + " price BETWEEN 25000 and 50000 ";
						break;
					case "5":
						sql = sql + start + " price > 50000 ";
						break;
				}
			} 
			if (!cat.equals("")) {
					sql = sql + start + " category2.c_id = "+cat;
			}

			sqlTotal = sql.replace("SELECT * FROM","SELECT count(*) as SUM FROM");
			
			
			ResultSet rc = stmt.executeQuery(sqlTotal);
			rc.next();
			recMax = rc.getInt("SUM");
			
			sql += " LIMIT " + pStart + "," + PER;
			ResultSet rs = stmt.executeQuery(sql);
			
			List<CategoryData> cate = new ArrayList<>();
			List list = new ArrayList();

			while (rs.next()) {

				ItemData gi = new ItemData();
				
//				System.out.println(rs.getString("c_nam"));
				gi.setCnam(rs.getString("c_nam"));
				gi.setId(rs.getInt("id"));
				gi.setNam(rs.getString("nam"));
				gi.setAsin(rs.getString("asin"));
				gi.setJan(rs.getString("jan"));
				gi.setPrice(rs.getInt("price"));
				gi.setIdc(rs.getInt("id_cat"));
				gi.setRanck(rs.getInt("ranck"));
				// gi.setStrin(rs.getDate("date"));
				gi.setMemo(rs.getString("memo"));
				
				if(rs.getString("c_id") !=null ){
					cate.add( new CategoryData((short) rs.getInt("c_id"),rs.getString("c_nam")));
				}
				
				list.add(gi);
				a++;
			}
			rc.close();
			rs.close();
			stmt.close();
			con.close();

			
			request.setAttribute("list", list);
			request.setAttribute("nam", nam);
			request.setAttribute("pr", pr);
			request.setAttribute("cat", cat);
//			System.out.println(list);
			request.setAttribute("recMax", recMax);
			request.setAttribute("pNo", pNo);
			request.setAttribute("pStart", pStart);
//			session.setAttribute("list",list);
	

		} catch (Exception e) {
			e.printStackTrace();
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/select.jsp");
		dispatcher.forward(request, response);
		// request.getRequestDispatcher("/select.jsp").forward(request,
		// response);
	}
}
