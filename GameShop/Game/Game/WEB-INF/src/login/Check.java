package login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import login.Checkdat;

public class Check {
	public static Checkdat check(HttpServletRequest request,
			HttpServletResponse response, String id, String passwd)
					throws IOException, ServletException {

		// 　Checkdatインスタンスの取得 情報のやり取り用です、
		Checkdat dat = Checkdat.getInstance();

		// doPost から呼ばれた場合(サインインして来た時)

		HttpSession session = null;

		String test_id = "";
		String test_passwd = "";

		// 引数チェック ★サインインページ以外から呼ばれる時は、 id==null passwd == null
		// 引数に、idとパスワードが無ければ、通常サインインしているとはみなさないが、
		// すでに有効なセッションを持っていれば、セッション変数を参照して取得する。
		//
		if (id == null && passwd == null) {

			// セッションの開始
			// (1)セッションの継続には、HttpSession session = request.getSession(false)を使う。
			// (2)セッションの新規開始には、HttpSession session =
			// request.getSession(true)を使う。
			//
			// クッキーにセッションIDがなければ当然新規ではあるが、
			// getSession(false)で、セッションの有無を調べます。
			//
			// getSessionメソッドの引数をfalseにすることで、HttpSessionが無い場合、
			// null が帰ります。★新しいオブジェクトは生成されません。
			//
			// 【注意】　（JSPではセッションかせ暗黙的に作られるらしい・・・・・）
			session = request.getSession(false);
			if (dat.getid() != null) {
				// すでに有効なセッションを持っていても、認証できるユーザとは限らない。
				// とくに、 JSP から呼ばれると、ディフォルトで生成されたセッションが活きてしまう。
				// test
				System.out.println("---------Checkuser#check-------------");
				System.out.println("(1)セッションＩＤを持っているユーザです。");
				// セッションIDの確認
				String session_id = session.getId();
				System.out.println("session id:[" + session_id + "]");
				System.out.println("内部保存session id:[" + dat.getSession() + "]");
				System.out.println("state=[" + dat.getstate() + "]");
				System.out.println("test_id=[" + dat.getid() + "]");
				System.out.println("test_passwd=[" + dat.getpasswd() + "]");
				System.out.println("error_mess=[" + dat.getErrormess() + "]");
				System.out.println("------------------------------");
				System.out.println(session.getAttribute("token")+"=====");

				// セッション変数の取得　生成済のセッションオブジェクトで取得する。
				id = (String) session.getAttribute("id");
				passwd = (String) session.getAttribute("passwd");

				dat.setSession(session);
				dat.setid(id);
				dat.setpasswd(passwd);
				dat.setErrormess("すでにセッションを持っています。サインイン済みです。");
				dat.setstate(1);
				return dat;

				// ★すでに有効なセッションを持っていても、認証できるユーザとは限らないとして、request.getSession(false)が
				// null でなくても検査を継続するひつようがあるなら、リターンしないようにする。
				// ただし、新規セッションは生成しない。

			} 
					// セッションを持っていない場合
			else { 
				System.out.println("------------------------------");
				System.out.println("★☆★サインインしていないよ★☆★");
				System.out.println("------------------------------");
				
				dat.setstate(0); // <-- 0の場合、認証できなかった。
				dat.setSession(null);
				dat.setid(null);
				dat.setpasswd(null);
				dat.setErrormess("★サインインしていません");
				return dat;
			}

		} 

		// id pass がある場合
		// 新規セッション発行の前に、idとpasswdをチェックする。
		// ユーザの認証 本来は、データベースに問合せをする

		try{
			Class.forName("com.mysql.jdbc.Driver");

			String DB_NAME = "mydb";
			String url = "jdbc:mysql://localhost:3306/"+DB_NAME+"?useUnicode=true&characterEncoding=UTF-8";
			String user = "root";
			String pass = "matsuo";

			Connection con = DriverManager.getConnection(url,user,pass);
			Statement stmt = con.createStatement();
			String sql ="SELECT * FROM idpass WHERE idNam=\""+id+"\"" ;
			ResultSet rs = stmt.executeQuery(sql);
			int a = 0;
			while(rs.next()){
				test_id = rs.getString("idNam");
				test_passwd = rs.getString("log_pass");

			}
			rs.close();
			stmt.close();
			con.close();

		}catch(Exception e){
			e.printStackTrace();
		}



		if (test_id.equals(id)) {

			if (test_passwd.equals(passwd)) {

				// セッションの開始
				// クッキーにセッションIDがなければ当然新規ではあるが、
				// getSession(false)で、セッションの有無を調べます。なければ、 null が帰ります。
				System.out.println("----------Checkuser#check:サインイン成功---------");
				System.out.println("サーバーに該当セッションはありません。セッションは開始されていません。");
				System.out.println("よって、新たにセッションを開始します。");
				// 引数が　"true"　の場合、セッションを新規に作成して返します。
				session = request.getSession(true); // セッション開始メソッド
				// セッション変数のセット
				session.setAttribute("id", id);
				session.setAttribute("passwd", passwd);

				// セッションIDの確認
				String session_id = session.getId();
				System.out.println("session:[" + session_id + "]");

				dat.setSession(session);
				dat.setid(id);
				dat.setpasswd(passwd);
				dat.setstate(2);
				dat.setErrormess("新たにセッションを開始しました。サインイン成功です。");

			} else {
				System.out.println("----------Checkuser#check:サインイン失敗--------パスワードに誤り");
				dat.setstate(-2); // <-- 0の場合、認証できなかった。
				dat.setSession(null);
				dat.setid(id); // <---------------- id は合っているので返す。
				dat.setpasswd(null);
				dat.setErrormess("パスワードに誤りがあります。");
			}

		} else {
			System.out.println("--Checkuser:サインイン失敗--ユーザIDに誤り id=["+ id + "]");
			dat.setstate(-1); // <-- 0の場合、認証できなかった。
			dat.setSession(null);
			dat.setid(null);
			dat.setpasswd(null);
			dat.setErrormess("ユーザIDに誤りがあります。");
		}

		return dat;

	}

}
