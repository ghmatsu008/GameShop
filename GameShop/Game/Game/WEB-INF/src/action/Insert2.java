package action;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class Insert2
{
   public static void main(String[] args) throws Exception
   {
      //変数宣言
      Connection conn = null;
      Statement state = null;
      int count = 0;
      
//      String asin = request.getParameter("nam");
//		String cat = request.getParameter("memo");
//		
      try
      {
         //コネクション接続
    	  Class.forName("com.mysql.jdbc.Driver");
         String DB_NAME = "mydb";
			String url = "jdbc:mysql://localhost:3306/"+DB_NAME+"?useUnicode=true&characterEncoding=UTF-8";
			String user = "root";
			String pass = "matsuo";
			
		conn = DriverManager.getConnection(url,user,pass);
        state = conn.createStatement();

         //クエリ実行 count 数える
         count = state.executeUpdate("INSERT INTO product(nam) VALUES('?') ");
         String sql = "insert into product (nam, memo) values(?,?)";
			PreparedStatement stat = conn.prepareStatement(sql);

			//      int num = stmt.executeUpdate(sql);

			stat.setString(1, "前田");
			stat.setString(2, "hh");
			int num = stat.executeUpdate();

			stat.execute(); 
         //影響レコード数出力
         System.out.println(count);
      }
      finally
      {
         //ステートメントを閉じる
         if(state != null)
         {
            try
            {
               state.close();
            }
            catch (Exception e)
            {
               //このエラー処理は｢state.close｣でエラーが発生した場合でも
               //下記処理が実行される為に記述しています。
            }
         }
         //コネクションを閉じる
         if(conn != null)
         {
            conn.close();
         }
      }
   }
}