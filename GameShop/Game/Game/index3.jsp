<?xml version="1.0"?>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="test.*"%>
<%@ page import="login.*"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="java.util.*"%>

<%
	Calendar objCal1 = Calendar.getInstance();
	Calendar objCal2 = Calendar.getInstance();
	objCal2.set(1970, 0, 1, 0, 0, 0);
	response.setDateHeader("Last-Modified", objCal1.getTime().getTime());
	response.setDateHeader("Expires", objCal2.getTime().getTime());
	response.setHeader("pragma", "no-cache");
	response.setHeader("Cache-Control", "no-cache");
	
	CategoryData da=null;
	GetCategory cat = new GetCategory();
	List<CategoryData> catList = cat.getCategory();
	
	String tmp=null;
	StringBuffer sel = new StringBuffer();
	sel.append("<OPTION value=\"0\" selected>選んでください</OPTION>");
	
	for ( Iterator<CategoryData> ite = catList.iterator(); ite.hasNext();) {
		
		da = (CategoryData)ite.next(); // <-- コレクション内移動
		tmp = "<OPTION value=\""+da.getid()+"\" >"+da.getname()+"</OPTION>";
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
			sign_tag = "<form method=\"POST\" action=\"//localhost:8080/GameShop/Signout\" >"
					+ "<input type=\"submit\" style=\"background-color: #fff;\" value=\"サインアウト\" />"
					+ "</form>";
			break;
		default :
			System.out.println("Checkuser.check()の戻り値がおかしい");
			break;
	}
 %>

<html>
<head>
<meta -equiv="Content-Type" content="text/html; charset=UTF-8">
<title>top.jsp</title>
<link rel="stylesheet" type="text/css" href="css/styleTop.css">
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
<!-- -----------------------------------side-------------------------- -->


	<div class="side">
		<p class="side-cart">
			<a href="//localhost:8080/GameShop/cart.jsp""><img
				src="imeg/cart.jpg" "
					alt="カートを見る" /> </a>
		</p>

		<div class="section">
			<h4>カテゴリー</h4>

			<ul class="side-link">
				<li><a href="item1.html">PS4</a></li>
				<li><a href="item2.html">PS3</a></li>
				<li><a href="item3.html">ニンテンドー DS</a></li>
				<li><a href="item3.html">ニンテンドー 3DS</a></li>
				<li><a href="item1.html">Xbox 360</a></li>
				<li><a href="item1.html">Wii U</a></li>
				<li><a href="ranking2.html">人気ランキング</a></li>
			</ul>
		</div>
		<br>
		<h4>支払い方法</h4>
		<p align="center" class="side-info">
			【カード決済対応会社】 <br> 下記カード会社に対応しております。 
		<ul class="card">
			<li><img
				src="imeg/jcb.gif"
				alt="JCB" width="42" height="26" /></li>
			<li><img
				src="imeg/mc.gif"
				alt="MasterCard" width="42" height="26" /></li>
			<li><img
				src="imeg/visa.gif"
				alt="VISA" width="42" height="26" /></li>
			<li><img
				src="imeg/aex.gif"
				alt="American Express" width="41" height="26" /></li>
		</ul>

		<br>
		<br>
		<p>【代金引換】</p>

		<ul class="card">
			<li>代引手数料</li>
			<br>
			<br>
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
	<!--  side -->

	<!-- -------------------------------main------------------------------------------ -->
	<script type="text/javascript"
		src="//ajax.googleapis.com/ajax/libs/jquery/1.8.1/jquery.min.js"></script>

	<script>
		$(function() {
			var $setElm = $('.viewer'), fadeSpeed = 4000, switchDelay = 3000;

			$setElm.each(function() {

				var targetObj = $(this);
				var findUl = targetObj.find('ul');
				var findLi = targetObj.find('li');
				var findLiFirst = targetObj.find('li:first');

				findLi.css({
					display : 'block',
					opacity : '0',
					zIndex : '99'
				});
				findLiFirst.css({
					zIndex : '100'
				}).stop().animate({
					opacity : '1'
				}, fadeSpeed);

				setInterval(function() {
					findUl.find('li:first-child').animate({
						opacity : '0'
					}, fadeSpeed).next('li').css({
						zIndex : '100'
					}).animate({
						opacity : '1'
					}, fadeSpeed).end().appendTo(findUl).css({
						zIndex : '99'
					});
				}, switchDelay);
			});
		});
	</script>
	<div id="main">



		<img
			src="//image.moshimo.com/static/publish/sd/img/templates/orange/freeshipping.jpg"
			alt="税込8000円以上のお買い上げで送料無料" />
		<div class="viewer">
			<ul>
				<li><img src="imeg/top2.jpg" width="500" height="250" alt=""></li>
				<li><img src="imeg/top1.jpg" width="500" height="250" alt=""></li>
				<li><img src="imeg/top3.jpg" width="500" height="250" alt=""></li>
				<li><img src="imeg/top4.jpg" width="500" height="250" alt=""></li>
			</ul>
		</div>
		<!--/.viewer-->
		<br>
		<hr>
		<div class="item">
			<h2>新着商品</h2>

			<table width="670" border="0" cellspacing="0" cellpadding="4"
				class="item_table">
				<tr>
					<td class="image" width="25%"><a href="#"><img
							src="imeg/new (8).jpg" width="120" height="120" /></a></td>
					<td class="image" width="25%"><a href="#"><img
							src="imeg/new (1).jpg" width="120" height="120" /></a></td>
					<td class="image" width="25%"><a href="#"><img
							src="imeg/new (2).jpg" width="120" height="120" /></a></td>
					<td class="image" width="25%"><a href="#"><img
							src="imeg/new (4).jpg" width="120" height="120" /></a></td>
				</tr>
				<tr>
				<TBODY valign="top">
					<td class="center8"><a href="kasou_b.html"> ソードアート・オンライン
							―ホロウ・フラグメント― 初回限定生産版 (初回封入特典「ゲーム内で使用出来るスペシャル衣装が解放される」プロダクトコード同梱)</a></td>
					<td class="center8"><a href="kasou_b.html">マリオカート8</a>
					<td class="center8"><a href="kasou_b.html">マリオゴルフ ワールドツアー</a></td>
					<td class="center8"><a href="kasou_b.html"> 俺の屍を越えてゆけ2
							(初回封入特典「レア神様」(男神・女神)プロダクトコード 同梱) 初回限定特典「俺の屍を越えてゆけ スペシャルコミックブック」付</a></td>
					</tr>
				</TBODY>
				<tr class="price">
					<td>&yen; 10,545</td>
					<td>&yen; 5,232</td>
					<td>&yen; 4,005</td>
					<td>&yen; 6,264</td>
				</tr>
			</table>
			<table width="670" border="0" cellspacing="0" cellpadding="4"
				class="item_table">
				<tr>
					<td class="image" width="25%"><a href="#"><img
							src="imeg/new (3).jpg" width="120" height="120" /></a></td>
					<td class="image" width="25%"><a href="#"><img
							src="imeg/new (5).jpg" width="120" height="120" /></a></td>
					<td class="image" width="25%"><a href="#"><img
							src="imeg/new (6).jpg" width="120" height="120" /></a></td>
					<td class="image" width="25%"><a href="#"><img
							src="imeg/new (7).jpg" width="120" height="120" /></a></td>
				</tr>
				<TBODY valign="top">
					<tr>
						<td class="center8"><a href="kasou_b.html">シアトリズムファイナルファンタジー
								カーテンコール</a></td>
						<td class="center8"><a href="kasou_b.html">ウォッチドッグス【CEROレーティング「Z」】初回限定特典（スペシャルコンテンツプロダクトコード+PS4版限定DLC同梱）＆ウォッチドッグスアートブック付き</a></td>
						<td class="center8"><a href="kasou_b.html">ソードアート・オンライン―ホロウ・フラグメント―
								(初回封入特典「ゲーム内で使用出来るスペシャル衣装が解放される」プロダクトコード 同梱)</a></td>
						<td class="center8"><a href="kasou_b.html">妖怪ウォッチ2本家
								(永久同梱特典:ジバニャンメダル コマニャチ 同梱)</a></td>
					</tr>
				</TBODY>
				<tr class="price">
					<td>&yen; 5,115</td>
					<td>&yen; 7,208</td>
					<td>&yen; 5,229</td>
					<td>&yen; 4,053</td>
				</tr>
			</table>
		</div>
		<!-- item -->
		<br>
		<br>
		<hr>
		<dl class="stock-info">
			<dt>&nbsp;更新情報</dt>
			<dd>
				<ul style="list-style: none;">
					<li><span>[お知らせ]</span>&nbsp;消費税率変更に伴う販売価格についてのお知らせ</li>
					<li><span>[お知らせ] </span>&nbsp;女性向け商品ポータルサイト「Sweet Girl's」について</li>
					<li><span>[14/4/17]</span>&nbsp;下天の華～桜見の宴～会場で販売していたグッズのお取扱いを開始！</li>
					<li><span>[14/4/17]</span>&nbsp;GAMECITY注文くじキャンペーンを実施！『下天の華』『下天の華
						夢灯り』関連商品を買ってオリジナルタペストリーを当てちゃおう！</li>
					<li><span>[14/4/15]</span>&nbsp;新作予約、戦国無双関連商品、The Best
						&定番シリーズ専門ショップ、ガスト専門ショップ、QuinRose専門ショップを更新！。</li>
					<li><span>[14/4/10]</span>&nbsp;『舞台遙かなる時空の中で ５』チケット販売受付開始！。</li>
					<li><span>[14/4/3]</span>&nbsp;新作予約を更新！</a></li>
				</ul>
			</dd>
		</dl>

	</div>

	<div ID="footer">
		<hr>
		<center>(c)2014 GameShop Project.</center>
	</div>
	</div>
	<!-- warp -->


</body>
</html>

