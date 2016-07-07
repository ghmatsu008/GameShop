package action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.naming.NamingException;

public class CheckUtil {
	
	private ArrayList<String> errs = null;
	
	public CheckUtil() {
		this.errs = new ArrayList<String>();
	}
	//必須
	public void requirdCheck(String str , String name){
		if(str == null || str.trim().isEmpty()){
			this.errs.add(name+ "は必須入力です。");
			
		}
	}
	//最大文字数
	public void lengthCheck(String value, int max, int min, String name){
		if(value !=null && !value.trim().isEmpty()){
			if(value.length() > max && value.length() < min){
				this.errs.add(name+ "は" + min + "以上、"+max +"以下で入力してください。");
			}
		}
	}
	//　数値
	public long numberCheck(String value, String name){
		long nu=0;
System.out.println(value);		
		if(value !=null && !value.trim().isEmpty()){
			try{
				 nu = Long.parseLong(value);
	System.out.println(nu+"h");
			}catch(NumberFormatException e){
				this.errs.add(name+ "は数値で入力してください。");
			}
		}
		return nu;
	}
	//　形式
	public void regExCheck(String value, String pattern,String name){
		if(value !=null && !value.equals("")){
			if(!value.matches(pattern)){
				this.errs.add(name+"を形式に合わせて入力してください。");
			}
		}
	}
	//　重複
	public void duplicateCheck(String value, String sql, String name){
		
		Connection con = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		
		String DB_NAME = "mydb";
		String url = "jdbc:mysql://localhost:3306/" + DB_NAME + "?useUnicode=true&characterEncoding=UTF-8";
		String user = "root";
		String pass = "matsuo";
		
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection(url, user, pass);
			
			stat = con.prepareStatement(sql);
			stat.setString(1,value);
			rs = stat.executeQuery();
			if(rs.next()){
				this.errs.add(name+ "が重複しています。");
			}
			
		}catch(SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e){
			e.printStackTrace();
		}finally{
			try{
				if(rs !=null){rs.close();}
				if(stat != null){stat.close();}
				if(con != null){con.close();}
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
	}
	//　エラーの有り無し
	public boolean hasErrors(){
		return !this.errs.isEmpty();
	}
	
	//　errsの値を<ul>に整形
	public String getErrorList(){
		StringBuffer buf = new StringBuffer();
		buf.append("<ul style='color:RED;'>");
		for(String err : this.errs){
			buf.append("<li>"+ err + "<li>");
		}
		buf.append("</ul>");
		return buf.toString();
	}

	
}
		
	

