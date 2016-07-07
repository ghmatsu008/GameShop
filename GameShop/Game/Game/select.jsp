<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.List,test.*"%>
<?xml version="1.0"?>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import="test.*"%>
<%@ page import="login.*"%>
<%@ page import="java.util.*"%>


<%
	Calendar objCal1 = Calendar.getInstance();
	Calendar objCal2 = Calendar.getInstance();
	objCal2.set(1970, 0, 1, 0, 0, 0);
	response.setDateHeader("Last-Modified", objCal1.getTime().getTime());
	response.setDateHeader("Expires", objCal2.getTime().getTime());
	response.setHeader("pragma", "no-cache");
	response.setHeader("Cache-Control", "no-cache");

	String id = null;
	String passwd = null;

	String sign_mess = null;
	String sign_tag = null;
	
	CategoryData da=null;
	GetCategory g_cat = new GetCategory();
	List<CategoryData> catList = g_cat.getCategory();
	
	String tmp=null;
	StringBuffer sel = new StringBuffer();
	sel.append("<OPTION value=\"0\" selected>選んでください</OPTION>");
	
	for ( Iterator<CategoryData> ite = catList.iterator(); ite.hasNext();) {
		
		da = (CategoryData)ite.next(); // <-- コレクション内移動
		tmp = "<OPTION value=\""+da.getid()+"\" >"+da.getname()+"</OPTION>";
		sel.append( tmp ); 
	}

	// セッションチェック(認証済みか否か)
	Checkdat dat = Check.check(request, response, null, null);

	switch (dat.getstate()) {
	case 0:
	case -1:
	case -2:
		// 		System.out.println("サインインしていません。");
		sign_mess = "サインインしていません。";

		sign_tag = "<form method=\"POST\" action=\"/GameShop/Signin\" >"
				+ "<input type=\"submit\" style=\"background-color: #fff;\"value=\"サインインへ\" />"
				+ "</form>";
		break;
	case 1:
	case 2:
		// 		System.out.println("サインインしています");
		sign_mess = dat.getid() + "さん、サインインしています。";
		sign_tag = "<form method=\"POST\" action=\"http://localhost:8080/GameShop/Signout\" >"
				+ "<input type=\"submit\" style=\"background-color: #fff;\" value=\"サインアウト\" />"
				+ "</form>";
		break;
	default:
		System.out.println("Checkuser.check()の戻り値がおかしい");
		break;
	}
	
	HttpSession session =null;

	String pr  = (String) request.getAttribute("pr");
	String nam = (String) request.getAttribute("nam");
	String cat = (String) request.getAttribute("cat");
%>



<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>検索結果</title>
	<link rel="stylesheet" type="text/css" href="css/style.css">
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
		
<!-- 		----------------------  side   ---------------------------------------------------------------- -->

	<div class="side">
			<p class="side-cart">
				<a href="#"><img
					src="imeg/cart.jpg""
					alt="カートを見る" />
				</a>
			</p>
		
			
			
				<p class="genre-all">
				<h4>カテゴリー</h4>
				</p>
				<ul class="side-link">
					<li><a href="item1.html">PS4</a></li>
					<br>
					<li><a href="item2.html">PS3</a></li>
					<br>
					<li><a href="item3.html">ニンテンドーDS</a></li>
					<br>
					<li><a href="item1.html">Xbox 360</a></li>
					<br>
					<li><a href="item1.html">Wii U</a></li>
					<br>
					<li><a href="ranking2.html">人気ランキング</a></li>
				</ul>
		
<br>
<hr>
	<div class="section" >
              <h4>支払い方法</h4>
              <p align="center"class="side-info">
                【カード決済対応会社】
                <br>
                  下記カード会社に対応しております。
                  <ul class="card">
                    <li><img src="http://image.moshimo.com/static/publish/sd/img/templates/common/jcb.gif" alt="JCB" width="42" height="26" /></li>
                    <li><img src="http://image.moshimo.com/static/publish/sd/img/templates/common/mc.gif" alt="MasterCard" width="42" height="26" /></li>
                    <li><img src="http://image.moshimo.com/static/publish/sd/img/templates/common/visa.gif" alt="VISA" width="42" height="26" /></li>
                    <li><img src="http://image.moshimo.com/static/publish/sd/img/templates/common/aex.gif" alt="American Express" width="41" height="26" /></li>
                  </ul>
                
                <br><br>
                <p>【代金引換】</p>
  
                  <ul class="card">
                        <li>代引手数料</li>
                      <br><br>
                        <li>1万円まで</li>
                        <li>315円</li>
                       <br>
                        <li>3万円まで</li>
                        <li>420円</li>
                      <br>
                        <li>10万円まで</li>
                        <li>630円</li>
                      <br>
                        <li>30万円まで</li>
                        <li>1,050円</li>
                      </ul>
                      </p>
	</div>
	</div>
<!-- ---------------------------main------------------------------------------------------------------------------ -->
	
<div id="main">
			<p class="freeshipping">
				<img
					src="http://image.moshimo.com/static/publish/sd/img/templates/orange/freeshipping.jpg"
					alt="税込8000円以上のお買い上げで送料無料" />
			</p>
<%
		int a = 1;

		
// 		if(pr == null){
// 			pr = "";
// 		}
		
		
		int pStart = (Integer) request.getAttribute("pStart");
		int pNo = (Integer) request.getAttribute("pNo");
		int recMax = (Integer) request.getAttribute("recMax");
%>

			<h4 align="center"><%=nam %>検索結果 <%=recMax %> 件</h4>
			<table width="700" border="0" bgcolor="">
				<tr>
					<td colspan="6" class="line" height="1" bgcolor="#CDCDCD">&nbsp;</td>
				</tr>

				

<%
 		int pageMax = recMax / 20;
		List list = (List) request.getAttribute("list");
		
		
		for (int i = 0; i < list.size(); i++) {
			ItemData item = (ItemData) list.get(i);
		String jan = item.getJan().trim();
%>

				<tr height="48">
					<td align="center" class="woong"><%=pStart %></td>
					<td align="center" bgcolor="#FFFFFF">
					<a href="http://localhost:8080/GameShop/detail.jsp?id=<%=item.getId() %>">
							<img src="http://www.comshop.ne.jp/images/item/<%=jan%>_a.jpg?_ex=112x112" 
							 width="50" valign="absmiddle" alt="">
					</a></td>
					<td>
					<a href="http://localhost:8080/GameShop/detail.jsp?id=<%=item.getId() %>" >
					<u> <%=item.getNam()%></a> 
						<imeg align="absmiddle" border="0"></u></td>
					<td align="center"><%=item.getCnam()%></td>
					<td align="center"><font color="#F15800"><b> <%=item.getPrice()%>円
						</b></font></td>
				</tr>

				<tr>
					<td colspan="6" class="line" height="15"></td>
				</tr>
				<tr>
					<td colspan="6" class="line" height="15"></td>
				</tr>


<%
			pStart++;
 	}
// 		request.setAttribute("list", list);
// 		request.setAttribute("recMax", recMax);
		request.setAttribute("nam", nam);
		request.setAttribute("pNo", pNo);
		request.setAttribute("pStart", pStart);
		request.setAttribute("pr", pr);
		request.setAttribute("cat", cat);
		
		
%>		<br></table>
	<div class="page" align="center">

		<%if(pNo >= 3 ){ %>
			<a href="http://localhost:8080/GameShop/SelectServlet2?id=<%=pNo-2%>&pr=<%=pr %>&ra=&cat=<%=cat %>&nam=<%=nam%>" >[<%=pNo-2%>] </a>
		<% } 
		if(pNo >= 2) { %>
			<a href="http://localhost:8080/GameShop/SelectServlet2?id=<%=pNo-1%>&pr=<%=pr %>&ra=&cat=<%=cat %>&nam=<%=nam%>" >&nbsp;[<%=pNo-1%>] </a>
		<% } %>
			<span style="font-size: 22px;"> &nbsp;  [<%=pNo%>] &nbsp; </span> 
		<% if(pNo <= pageMax-1){ %>
			<a href="http://localhost:8080/GameShop/SelectServlet2?id=<%=pNo+1%>&pr=<%=pr %>&ra=&cat=<%=cat %>&nam=<%=nam%>" >[<%=pNo+1%>]&nbsp; </a>
		<% }
		if(pNo <= pageMax-2) {%>
			<a href="http://localhost:8080/GameShop/SelectServlet2?id=<%=pNo+2%>&pr=<%=pr %>&ra=&cat=<%=cat %>&nam=<%=nam%>" >[<%=pNo+2%>] </a>
		<% } %>
<%-- 		<%	if(i == pageMax) --%>


<%-- <%	}else{ --%>
<%-- 		for(int i=1; i<=6; i++){ %> --%>
<%-- 			<a href="http://localhost:8080/GameShop/SelectServlet2?id=<%=i%>&pr=<%=pr %>&ra=&cat=<%=cat %>&nam=<%=nam%>" >[<%=pNo%>] </a> --%>
<%-- 	<%   --%>

			
			<div class="navi">
	<%	if(pNo < pageMax ) {  %>
			<span style="width:1000px;  text-align:right;"><a href="http://localhost:8080/GameShop/SelectServlet2?id=<%=pNo+1%>&pr=<%=pr %>&ra=&cat=<%=cat %>&nam=<%=nam%>">次へ></a></span>
	<%} %>
	<%	if(pNo >= 2 ) { %>
			<span style="float: left";><a href="http://localhost:8080/GameShop/SelectServlet2?id=<%=pNo-1%>&pr=<%=pr %>&ra=&cat=<%=cat %>&nam=<%=nam%>"><戻る</a></span>
	<%} %></class>
	</div></div>
	
	
<div ID="footer">
	<hr>
	<center>
		(c)2014 GameShop Project.
	</center>
</div>
</body>
</html>