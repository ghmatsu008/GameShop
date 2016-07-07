<?xml version="1.0"?>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.sql.*, javax.naming.*, javax.sql.*" %>
<%@ page import="test.*"%>
<%@ page import="login.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.List"%>

<%
	Calendar objCal1 = Calendar.getInstance();
	Calendar objCal2 = Calendar.getInstance();
	objCal2.set(1970, 0, 1, 0, 0, 0);
	response.setDateHeader("Last-Modified", objCal1.getTime().getTime());
	response.setDateHeader("Expires", objCal2.getTime().getTime());
	response.setHeader("pragma", "no-cache");
	response.setHeader("Cache-Control", "no-cache");
	request.setCharacterEncoding("UTF-8");
	
	String tmp = request.getParameter("id");
	int pid = Integer.parseInt(tmp);
System.out.println(pid+"pid");
	
	CategoryData da=null;
	GetCategory cat = new GetCategory();
	List<CategoryData> catList = cat.getCategory();
	
	
	StringBuffer sel = new StringBuffer();
	sel.append("<OPTION value=0 selected>選んでください</OPTION>");
	
	for ( Iterator<CategoryData> ite = catList.iterator(); ite.hasNext();) {
		
		da = (CategoryData)ite.next(); // <-- コレクション内移動
		tmp = "<OPTION value="+da.getid()+" >"+da.getname()+"</OPTION>";
		sel.append( tmp ); 
	}
	

	String id = null;
	String passwd = null;

	String sign_mess = null;
	String sign_tag = null;
	

	// JSPの場合、セッション自動生成されてしまう。
	// この場合、セッションの有無とid及びパスワードの組み合わせ判定が必要になる。
	// これでもいい場合があるが、今回は
	// 各 JSP ページにおいて、セッション生成を page ディレクティブで禁止する。
	// 分かりやすく、スマートにログインログアウトの機能が作れる。

	// セッションチェック(認証済みか否か)
	Checkdat dat = Check.check(request, response, null, null);
	//debug
	//   	System.===================-- index.jsp =======================
	// 	dat.show();

	switch (dat.getstate()) {
		case 0 :
		case -1 :
		case -2 :
			// 		System.サインインしていません。
			sign_mess = "サインインしていません。";

			sign_tag = "<form method=POST action=/GameShop/Signin >"
					+ "<input type=submit style=background-color: #fff;value=サインインへ />"
					+ "</form>";
			break;
		case 1 :
		case 2 :
			// 		System.サインインしています
			sign_mess = dat.getid() + "さん、サインインしています。";
			sign_tag = "<form method=POST action=http://localhost:8080/GameShop/Signout >"
					+ "<input type=submit style=background-color: #fff; value=サインアウト />"
					+ "</form>";
			break;
		default :
// 			System.Checkuser.check()の戻り値がおかしい
			break;
	}
%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>top.jsp</title>
	<link rel="stylesheet" type="text/css" href="css/ss.css">
</head>

<body>
<div id="wrap">
<div class="head"> 
 			<h1>GAME通信販売</h1> 
 			<br /> <span class="label"><%=sign_mess%> <%=sign_tag%> </span>
		<ul class="menu">
				<li><a href="http://localhost:8080/GameShop/index2.jsp">TOP</a></li>
				<li><a href="item1.html">商品一覧</a></li>
				<li><a href="idinfo.html">会員登録</a></li>
				<li><a href="">カートBOX</a></li>
				<li><a href="mail.html">問い合わせ</a></li>
			</ul>
			<form method="GET" action="SelectServlet2">
				<div class="itemsearch">
					価格 
					<select name="pr">
						<option value="">---</option>
						<option value="1">0円～5000円</option>
						<option value="2">5000円～10000円</option>
						<option value="3">10000円～25000円</option>
						<option value="4">25000円～50000円</option>
						<option value="5">50000円～</option>
					</select> コンディション 
					<select name="ra">
						<option value="">---</option>
						<option value="new">新品</option>
						<option value="used">中古品</option>
					</select> ジャンル 
					<select name="cat">
						<%out.println( sel.toString() );%>
					</select>
<!-- 					<select name="cat" > -->
<!-- 						<option value="">---</option> -->
<!-- 						<option value="ps4">PS4</option> -->
<!-- 						<option value="ps3">PS3</option> -->
<!-- 						<option value="ds">ニンテンドーDS</option> -->
<!-- 						<option value="3ds">ニンテンドー3DS</option> -->
<!-- 						<option value="vita">プレイステーション Vita</option> -->
<!-- 						<option value="xbox">Xbox 360</option> -->
<!-- 						<option value="u">Wii U</option> -->
<!-- 					</select>  -->
					商品名 
					<input type="text" size="30" name="nam" /> 
					<input type="submit" value="検索" />
			</form>
		</div>
		<!--検索ボックス-->
		</form>

	</div>
	
	
	
	<div class =side>
		<p class=side-cart>
		<a href=http://mp.moshimo.com/cart?shop_id=556410&amp;signature=SD>
		<img src=http://image.moshimo.com/static/publish/sd/img/templates/orange/show-cart.gif alt=カートを見る /></a></p>
		<div class=section><h4><class=caption>商品検索</h4>
		<div class=side-search>
		 <form action=http://mp.moshimo.com/article/search method=GET><input type=hidden name=shop_id value=556410 />
		<input type=hidden name=signature value= /><input type=text name=words class=words value= /><input type=image src=http://image.moshimo.com/static/publish/sd/img/templates/orange/search.gif value=検索 /></form>
		</div></div><div class=section><p class=genre-all><h4>カテゴリー</h4></p><ul class=side-link>
		<li><a href=item1.html>PS4</a></li><br><li><a href=item2.html>PS3</a></li><br><li><a href=item3.html>ニンテンドーDS</a></li><br><li><a href=item1.html>Xbox 360</a></li><br><li><a href=item1.html>Wii U</a></li><br><li><a href=ranking2.html>人気ランキング</a></li>	</ul></div></div>

		<div class =main>
		<p class=freeshipping><img src=http://image.moshimo.com/static/publish/sd/img/templates/orange/freeshipping.jpg alt=税込8000円以上のお買い上げで送料無料 /></p>

		 <h3 align=center>商品詳細</h3><table width=700 border=0 bgcolor=><tr><td colspan=6 class=line height=1 bgcolor=#CDCDCD>&nbsp;</td></tr>

		 <p class=clearfix>
		 <form method=POST action=MainController>

	<%
	
		try {
			Class.forName("com.mysql.jdbc.Driver");

			String DB_NAME = "mydb";
			String url = "jdbc:mysql://localhost:3306/" + DB_NAME + "?useUnicode=true&characterEncoding=UTF-8";
			String user = "root";
			String pass = "matsuo";

			Connection con = DriverManager.getConnection(url, user, pass);
			Statement stmt = con.createStatement();
			String sql = "SELECT * FROM product2 LEFT JOIN category2 ON product2.id_cat = category2.c_id WHERE id='" + pid + "'";
			String sql2 = "SELECT * FROM idpass WHERE idNam='" + dat.getid() + "'";
		
			int a = 0;
			ResultSet rs2 = stmt.executeQuery(sql2);
			if(dat.getid() !=null){
// 				rs2.next();
// 			    log_id = (rs2.getInt("id"));
// 		 		rs2.close();
			}
	 		
	 		ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			String jan = rs.getString("jan");
			jan = jan.trim();
			int qua = rs.getInt("quantity");
	%>
			<div>
				<img src="http://www.comshop.ne.jp/images/item/<%=jan%>_a.jpg">
			</div>
			
			<div id=itemInfo>
			<p>商品名 :<br/><%=rs.getString("nam")%></p>
			<p>プラットフォーム :<%=rs.getString("c_nam")%></p>
			<p>価格 :<%=rs.getInt("price")%>円（税込）</p>
			<p>Amazonランキング:<%=rs.getInt("ranck")%>位</p>
			
			<input type=hidden name=logId value=<%=log_id%>/>
			<input type=hidden name=action value=add/>
			<input type=hidden name=index value=<%=pid%>/>
			数量 : <input type=text name=qua value=1 size=2>
			&nbsp;&nbsp;&nbsp;<b><font color=red>残りあと<%=qua %>個</font></b><br />
		</p></p>
<%
			a++;

			rs.close();
			stmt.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	%>
		

		<BUTTON type=submit  style=background-color: #f91;><big>カートに入れる</big></BUTTON>
		<br><br/><br/><br/><br/><br/><br/><br/>
		<hr></div><div class =footer></div>
		</form></body></html>
	