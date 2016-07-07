package cart;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ItemDao {

	String DB_NAME = "mydb";
	String url = "jdbc:mysql://localhost:3306/" + DB_NAME
			+ "?useUnicode=true&characterEncoding=UTF-8";
	String user = "root";
	String pass = "matsuo";

	protected Connection createConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, user, pass);
			return con;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	protected void closeConnection(Connection con) {
		try {
			con.close();
		} catch (Exception ex) {
		}
	}

	// 　全て取り出すメソッド
	public ItemArray findAll() {

		ItemArray array = new ItemArray();
		array.setList(new ArrayList());
		String sql = "SELECT * FROM product2";

		Connection con = createConnection();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int id = rs.getInt("id");
				String nam = rs.getString("nam");
				int price = rs.getInt("price");
				array.addItem(new CartItem(id, nam, price));
			}

			rs.close();
			stmt.close();
			return array;

		} catch (SQLException ex) {
			System.out.println("SQL failed");
			ex.printStackTrace();
		} finally {
			closeConnection(con);
		}
		return null;
	}

	// 検索メソッド
	public ItemArray select(int pid) { // 検索メソッド

		ItemArray array = new ItemArray();
		array.setList(new ArrayList());
		String sql = "SELECT * FROM product2 WHERE id='" + pid + "'";

		Connection con = createConnection();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {

				int id = rs.getInt("id");
				String nam = rs.getString("nam");
				int price = rs.getInt("price");

				array.addItem(new CartItem(id, nam, price));
			}

			rs.close();
			stmt.close();

			return array;

		} catch (SQLException ex) {
			System.out.println("SQL failed");
			ex.printStackTrace();
		} finally {
			closeConnection(con);
		}
		return null;
	}

	// 　カートに追加
	public void cartIn(CartItem ct, int lid){
		ItemArray array = new ItemArray();
		array.setList(new ArrayList());

		String sql = "insert into cartbox (pid, nam, price,qua,log_id) values(?, ?, ?, ?, ?)";
		//		on duplicate key update qua= "+
		//						"if(log_id<>'"+lid+"',qua,qua+\'1\')";
		String sql2 = "SELECT * FROM cartbox ";
		//		String sql2 = "SELECT * FROM cartbox LEFT JOIN idpass ON cartbox.log_id = idpass.id where cartbox.log_id='"+ lid + "'";
		Connection con = createConnection();
		String sqlTotal="";
		int n = 0;
		int ver=0;

		try{

			Statement stmt = con.createStatement();
			ResultSet rs2 = stmt.executeQuery(sql2);

			while(rs2.next()){
				if(rs2.getInt("pid")==ct.getId()){
					sql = "UPDATE cartbox set qua=qua+1 WHERE pid="+ct.getId();
					ver = 2;
					
				} 
			 if(rs2.getInt("pid")==ct.getId() && rs2.getInt("log_id") != lid){
					sql = "insert into cartbox (pid, nam, price,qua,log_id) values(?, ?, ?, ?, ?)";
					ver = 1;
					continue;
				}
			}
		rs2.close();
			
				System.out.println(ct.getId()+"ver"+ver);
				PreparedStatement stat = con.prepareStatement(sql);
			if(ver != 2){
				
				stat.setInt(1, ct.getId());
				stat.setString(2, ct.getNam());
				stat.setInt(3, ct.getPrice());
				stat.setInt(4,1);
				stat.setInt(5,lid);
			}
				stat.executeUpdate();
				//			stat.execute(); 
				//		array.addItem(new CartItem(ct.getId(),ct.getNam(),ct.getPrice()));
				stat.close();
				
			
		}catch(SQLException ex){
			System.out.println("SQL failed");
			ex.printStackTrace();
		}
		finally{
			closeConnection(con);
		}
	}
		public ItemArray remove(int lid, int pid) {

			Connection con = createConnection();
			
			try{	
				Statement stmt = con.createStatement();
				String sql ="DELETE FROM cartbox WHERE log_id='"+lid+"'";
				if(pid >= 1){
					 sql += " AND pid='"+pid+"'";
//				ResultSet rs = stmt.executeQuery(sql);
				}else{
					
				}
System.out.println(sql+"////ItemDao 177");
				int result = 0;
				
				stmt.executeUpdate( sql );
			
				} catch (SQLException ex) {
				System.out.println("SQL failed");
				ex.printStackTrace();
				
			} finally {
				closeConnection(con);
			}
			return null;
		}
	}