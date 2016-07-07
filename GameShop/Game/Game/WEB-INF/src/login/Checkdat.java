package login;

import javax.servlet.http.HttpSession;

public class Checkdat {
	private static final Checkdat obj = new Checkdat();
	int state; // 1:すでにセッションを持っている , 2:新規にセッションを発行した , 0:不明　, -1 : IDにあやまり , -2 パスワードに誤り
	HttpSession session;
	String test_id;
	String test_passwd;
	String error_mess;
	
	private Checkdat(){
		this.state = 0;
		this.session = null;
		this.test_id = null;
		this.test_passwd = null;
		this.error_mess = null;
	}
	
	public void show(){
		System.out.println("---------------   Checkdat    ----------------");
		System.out.println("state"+getstate()+"]");
		System.out.println("HttpSession session=["+this.session+"]");
		System.out.println("test_id=["+getid()+"]");
		System.out.println("test_passwd=["+getpasswd()+"]");
		System.out.println("error_mess=["+getErrormess()+"]");
		System.out.println("----------------------------------------------");
	}
    /**
     * このクラスの唯一のインスタンスを返す。
     */
    public static Checkdat getInstance() {
        return obj;
    }

	public int getstate() {
		return this.state;
	}
	public void setstate(int state) {
		this.state = state;
	}
	/**
	/**
	 * @return error_mess
	 */
	public String getErrormess() {
		return error_mess;
	}
	/**
	 * @param error_mess セットする error_mess
	 */
	public void setErrormess(String error_mess) {
		this.error_mess = error_mess;
	}
	/**
	 * @return session
	 */
	public  HttpSession getSession() {
		return session;
	}
	/**
	 * @param session セットする session
	 */
	public   void setSession(HttpSession session) {
		this.session = session;
	}
	/**
	 * @return test_id
	 */
	public String getid() {
		return test_id;
	}
	/**
	 * @param test_id セットする test_id
	 */
	public void setid(String test_id) {
		this.test_id = test_id;
	}
	/**
	 * @return test_passwd
	 */
	public String getpasswd() {
		return test_passwd;
	}
	/**
	 * @param test_passwd セットする test_passwd
	 */
	public void setpasswd(String test_passwd) {
		this.test_passwd = test_passwd;
	}
	
	
}

