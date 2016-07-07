package test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Token {

	public void createToken(HttpServletRequest request){
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
	
	public boolean checkToken(HttpServletRequest request){
		HttpSession session = request.getSession();
		String s_token = (String)session.getAttribute("token");

		String r_token = request.getParameter("token");
System.out.println("session  token"+s_token+"  r_token="+r_token);			
		if(s_token == null || r_token == null || r_token.isEmpty()){
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
