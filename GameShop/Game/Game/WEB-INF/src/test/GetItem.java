package test;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class GetItem {
	
	List<ItemData> it = new ArrayList<>();
	
	public List<ItemData>  getItem() {
		
		String sql2=null;

	    // データベース側の文字コードを指定
		String DB_NAME = "mydb";
	    String DB_URL  = "jdbc:mysql://localhost:3306/"+DB_NAME+"?useUnicode=true&characterEncoding=UTF-8";
	    String ADMIN_USER = "root";
	    String ADMIN_PASS = "matsuo";
	    String JDBC = "com.mysql.jdbc.Driver";
	    
		/* JDBCドライバクラスをロードする */
		try {
			Class.forName( JDBC );
		} catch (ClassNotFoundException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}

		sql2 = "SELECT * FROM product3 "; 
		// クエリーを実行して結果セットを取得
		try {
			Connection con = DriverManager.getConnection( DB_URL , ADMIN_USER , ADMIN_PASS );
			//ステートメントクラスのインスタンスを取得
			Statement stmt = con.createStatement();
			ResultSet rs2 = stmt.executeQuery(sql2);

			
			int i =0;
			while( rs2.next() ){ //よくあるIteratorのnext()と同様
					/*
					* getXxxx()メソッドは引数に指定したデータベースのフィールドを
					* JavaのXxxx型として取り出します
					*/
//						it.add( new ItemData(
//								rs2.getInt("id"),
//								rs2.getString("ASIN"),
//								rs2.getString("nam"),
//								rs2.getInt("id_Category"),
//								rs2.getInt("price"),
//								rs2.getInt("u_price"),
//								rs2.getInt("ranck"),
//								rs2.getDate("date"),
//								rs2.getString("memo")
//								
//								)
//						);
//								
								

				//Test
				//System.out.println("["+i+"] "+rs.getString("va_lastname"));
				i++;
			}

		       // 切断
				rs2.close();
				stmt.close();
				con.close();
				
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		return this.it;
	}
}