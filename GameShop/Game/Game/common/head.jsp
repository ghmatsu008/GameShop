
<%-- %> --%>

<!-- <html> -->
<!-- <head> -->
<!-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> -->
<!-- <title>top.jsp</title> -->
<!-- <link rel="stylesheet" type="text/css" href="css/styleTop.css"> -->
<!-- </head> -->
<!-- <body> -->
<!-- 	<div id="wrap"> -->
<!-- 		<div class="head"> -->
<!-- 			<h1>GAME通信販売</h1> -->
<%-- 			<br /> <span class="label"><%=sign_mess%> <%=sign_tag%> </span> --%>
			<!-- 					<big>ログイン</big> -->
			<!-- 				</BUTTON></span> </a> -->
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