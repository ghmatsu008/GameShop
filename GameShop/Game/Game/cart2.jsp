<?xml version="1.0"?>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="test.*"%>
<%@ page import="cart.*"%>
<%@page import="java.sql.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="login.*"%>
<%@ page import="java.util.*" %>
<%@ page import="java.util.Calendar"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8" />
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css" />

<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<link rel="stylesheet" href="/resources/demos/style.css" />
<title>cartBOX</title>
</head>

<%
	Calendar objCal1 = Calendar.getInstance();
	Calendar objCal2 = Calendar.getInstance();
	objCal2.set(1970, 0, 1, 0, 0, 0);
	response.setDateHeader("Last-Modified", objCal1.getTime().getTime());
	response.setDateHeader("Expires", objCal2.getTime().getTime());
	response.setHeader("pragma", "no-cache");
	response.setHeader("Cache-Control", "no-cache");
	
	ItemDao dao = new ItemDao();
	HttpSession session = request.getSession(true);
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
	//   	System.out.println("===================-- index.jsp =======================");
	// 	dat.show();

	switch (dat.getstate()) {
		case 0 :
		case -1 :
		case -2 :
	// 		System.out.println("サインインしていません。");
			sign_mess = "サインインしていません。";

			sign_tag = "<form method=\"POST\" action=\"/GameShop/Signin\" >"
				+ "<input type=\"submit\" style=\"background-color: #fff;\"value=\"サインインへ\" />"
				+ "</form>";
			break;
		case 1 :
		case 2 :
		// 		System.out.println("サインインしています");
			sign_mess = dat.getid() + "さん、サインインしています。";
			sign_tag = "<form method=\"POST\" action=\"https://localhost:8080/GameShop/Signout\" >"
					+ "<input type=\"submit\" style=\"background-color: #fff;\" value=\"サインアウト\" />"
					+ "</form>";
			break;
		default :
			System.out.println("Checkuser.check()の戻り値がおかしい");
			break;
	}
	String DB_NAME = "mydb";
	String url = "jdbc:mysql://localhost:3306/" + DB_NAME
			+ "?useUnicode=true&characterEncoding=UTF-8";
	String user = "root";
	String pass = "matsuo";
	
	String nam="";
	int logid;
	int price=0;
	int count=0;
	
	ItemArray list = new ItemArray();;

		
	
%>

<html>
<head>
	<meta https-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>top.jsp</title>
	<link rel="stylesheet" type="text/css" href="css/styleTop.css">
</head>
<body>
<div id="wrap">
		
		<div class="head">
			<h1>GAME通信販売</h1>
			<br /> <span class="label"><%=sign_mess%> <%=sign_tag%> </span>
			<!-- 					<big>ログイン</big> -->
			<!-- 				</BUTTON></span> </a> -->
			<ul class="menu">
				<li><a href="https://localhost:8080/GameShop/index2.jsp">TOP</a></li>
				<li><a href="item1.html">商品一覧</a></li>
				<li><a href="idinfo.html">会員登録</a></li>
				<li><a href="https://localhost:8080/GameShop/cart.jsp">カートBOX</a></li>
				<li><a href="mail.html">問い合わせ</a></li>
			</ul>

		</div> 
<!-- 	------------ head -------- -->

<script type="text/javascript">

$(document).ready(function() {
	$("#button2").hide();
	$('#button1').click(function() {
		
		var url = "pay.jsp";
		
		$.ajax({
			type: 'GET',
			url: url,
			dataType: 'html',
			cache: false,
			success: function(data, textStatus){
				
				$('#div1').html(data);
				$('#div1').show('slow');
				$("#button2").show("slow");
				$("#button1").hide('slow'); 
			},
			error: function(xhr, textStatus, errorThrown){
				alert('Error!' + textStatus + '' + errorThrown);
			}
		});
	});
	
	$('#button2').click(function() {
		$("#div1").hide('slow');
		$("#button2").hide();
	});
	
});

</script>
		<center>
			<h3>カートBOX</h3>
			<div id="pro" >
			<table>
<%-- 				<c:choose> --%>
<%--  					<c:when test="${empty cartArray }">  --%>
<!-- 						<tr> -->
<!-- 							<td>カートは空です。</td> -->
<!-- 						</tr> -->
<%-- 					</c:when> --%>

<%-- 					<c:otherwise> --%>
						<tr valign="top">
							<td align="center">
						<%	if(dat.getid() == null){
								out.print("<h3>購入はログインが必要です。</h3><br><br>");
								out.println("<a href=\"http://localhost:8080/GameShop/memberReg.jsp\">会員登録する</a><br><br>");
							}else{ 
						%>
								<table border="1">
									<th width="80" colspan="2">商品名</th>
									<th width="60" colspan="0">価格</th>
									<th width="40" colspan="0">数量</th>
									
									
									<%
									try {
										Class.forName("com.mysql.jdbc.Driver");
										Connection con = DriverManager.getConnection(url, user, pass);
										String sql2 = "SELECT * FROM idpass WHERE idNam='"+ dat.getid() + "'";
										
										
										Statement stmt = con.createStatement();
										ResultSet rs2 = stmt.executeQuery(sql2);
										rs2.next();
										logid = rs2.getInt("id");
// 					System.out.println(logid);
										rs2.close();
										
										String sql = "SELECT * FROM cartbox WHERE log_id='"+ logid + "'";
										
										stmt = con.createStatement();
										ResultSet rs = stmt.executeQuery(sql);

										while(rs.next()){
											
											price += rs.getInt("price"); 
									%>
											
											<tr>
												<td><input type="checkbox" name="delete_id" /></td>
												<td><%=rs.getString("nam")%></td>
												<td><%=rs.getInt("price")%></td>
												<td><input type="text" name="qua" size="2" value="<%=rs.getInt("qua")%>"/></td>

												<td><form  method="POST" action="MainController">
														<!-- 						<form action="MainController" method="post"> -->
														<input type="hidden" name="action" value="remove">
														<input type="hidden" name="index" value=<%=rs.getInt("pid") %>>
														<input type="hidden" name="log_id" value=<%=rs.getInt("log_id") %>>
														<input type="submit" value="削除">
													</form>
												</td>
											</tr>
										<%	
											count++;
											} 

											rs.close();
											stmt.close();
										%>
											<tr>
											<td colspan="4" align="right" ><B>
													<span style="background-color:#ffff66;">合計<%=count %>個  - ￥<%=price %></span></B></td>
										</tr>
								</table>
										
								<%	
									}catch(SQLException ex){
										System.out.println("SQL failed");
										ex.printStackTrace();
									}
									
								%>
<%-- 									
<%-- 									</c:forEach> --%>
										
							</td>
						</tr>
						<tr>
							<td colspan="2">
						<%
							if(count >= 1){
						%>								
								<input id="button1" type="button" value="レジへ"></input>
							<% } %>
							</td>	
						</tr>
						
<%-- 					</c:otherwise> --%>
<%-- 				</c:choose> --%>
			<% } %>
			
			</table>
			<div id="div1"></div>
<!-- 			<input id="button2" type="submit" value="次へ ＞"> -->
			<a href="#">TOPへ戻る</a>
		</center>
		</div>
</div>
		
</body>
</html>