<?xml version="1.0" encoding="UTF-8"?>

<%@page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ page import="test/*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>top.jsp</title>
</head>
<body>


<div id ="wrap">
	<div class="head">
		<h1>GAME通信販売</h1>
		<br /> <a href="login.html"> <span class="label"><BUTTON type="submit" style="background-color: #fff;">
					<big>ログイン</big>
				</BUTTON></span>
		</a>
		<ul class="menu">
			<li><a href="http://localhost:8080/GameShop/indextop.html">TOP</a></li>
			<li><a href="item1.html">商品一覧</a></li>
			<li><a href="idinfo.html">会員登録</a></li>
			<li><a href="">カートBOX</a></li>
			<li><a href="mail.html">問い合わせ</a></li>
		</ul>
	<form method="GET" action="SelectServlet2">
		<div class="itemsearch">
			価格帯 <select name="pr">
				<option value="">---</option>
				<option value="1_5">0円～5000円</option>
				<option value="5_10">5000円～10000円</option>
				<option value="10_25">10000円～25000円</option>
				<option value="25_50">25000円～50000円</option>
				<option value="50_">50000円～</option>
			</select> コンディション 
			<select name="ra">
				<option value="">---</option>
				<option value="new">新品</option>
				<option value="used">中古品</option>
			</select> ジャンル
			 <select name="cat" id="get_texts">
				<option value="">---</option>
				<option value="ps4">PS4</option>
				<option value="ps3">PS3</option>
				<option value="ds">ニンテンドーDS</option>
				<option value="3ds">ニンテンドー3DS</option>
				<option value="vita">プレイステーション Vita</option>
				<option value="xbox">Xbox 360</option>
				<option value="u">Wii U</option>
			</select>
		

			 <input  type="text" size="30" name="nam" value="" /> 
			<input type="submit" value="検索" />
		</form>
	</div>
	<!--検索ボックス-->
	</form>

	</div>
	<!--/head -->

	<div id="wrap">
		<div class="side">

			<p class="side-cart">
				<a href="http://mp.moshimo.com/cart?shop_id=556410&amp;signature=SD"><img
					src="http://image.moshimo.com/static/publish/sd/img/templates/orange/show-cart.gif"
					alt="カートを見る" /></a>
			</p>
			<div class="section">
				<h4>
					<div class="caption">商品検索</div>
				</h4>
				<div class="side-search">
					<form action="http://mp.moshimo.com/article/search" method="GET">
						<input type="hidden" name="shop_id" value="556410" /> <input
							type="hidden" name="signature" value="" /> <input type="text"
							name="words" class="words" value="" /><input type="image"
							src="http://image.moshimo.com/static/publish/sd/img/templates/orange/search.gif"
							value="検索" />
					</form>
				</div>
			</div>
			<div class="section">
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
			</div>
		</div>
		<!--  side -->


		<div id="main">
			<img
				src="http://image.moshimo.com/static/publish/sd/img/templates/orange/freeshipping.jpg"
				alt="税込8000円以上のお買い上げで送料無料" />
			<div class="item">
				<h2>
					<div class="caption" style="">新着商品</div>
				</h2>
				<table width="600" border="0" bgcolor="">
					<tr>
						<td>

							<p class="image">
								<a href="" target="_blank"><img width="120" height="120"
									src="imeg/new (1).jpg" /></a>
							</p>
							<h3 class="name">
								<a href="./0000136441.html" target="_blank">マリオカート8</a>
							</h3>
							<p class="price">￥ 5,232</p>

						</td>
						<td></td>
						<td>
							<p class="image">
								<a href="./0000136440.html" target="_blank"><img width="120"
									height="120" src="imeg/new (2).jpg" /></a>
							</p>
							<h3 class="name">
								<a href="./0000136440.html" target="_blank">マリオゴルフ ワールドツアー</a>
							</h3>
							<p class="price">￥ 4,005</p>

						</td>
						<td></td>
						<td>
							<p , class="image">
								<a href="./0000136439.html" target="_blank"><img width="120"
									height="120" src="imeg/new (3).jpg" /></a>
							</p>
							<h3 class="name">
								<a href="./0000136439.html" target="_blank">シアトリズム
									ファイナルファンタジー カーテンコール</a>
							</h3>
							<p class="price">￥ 5,115</p>

						</td>
						<td></td>
						<td>

							<p class="image">
								<a href="./0000136436.html" target="_blank"><img width="120"
									height="120" src="imeg/new (4).jpg" /></a>
							</p>
							<h3 class="name">
								<a href="./0000136436.html" target="_blank">俺の屍を越えてゆけ2
									(初回封入特典「レア神様」(男神・女神)プロダクトコード 同梱) 初回限定特典「俺の屍を越えてゆけ スペシャルコミックブック」
									付</a>
							</h3>
							<p class="price">￥ 6,264</p>

						</td>
					</tr>
				</table>
				<br>
				<table width="600" border="0" bgcolor="">
					<tr>
						<td>

							<p class="image">
								<a href="./0000133213.html" target="_blank"><img width="120"
									height="120" src="imeg/new (5).jpg" /></a>
							</p>
							<h3 class="name">
								<a href="./0000133213.html" target="_blank">ウォッチドッグス【CEROレーティング「Z」】初回限定特典（スペシャルコンテンツプロダクトコード+PS4版限定DLC同梱）＆ウォッチドッグス
									アートブック付き</a>
							</h3>
							<p class="price">￥ 7,208</p>

						</td>
						<td></td>
						<td>

							<p class="image">
								<a href="./0000104745.html" target="_blank"><img width="120"
									height="120" src="imeg/new (6).jpg" /></a>
							</p>
							<h3 class="name">
								<a href="./0000104745.html" target="_blank">ソードアート・オンライン
									―ホロウ・フラグメント― (初回封入特典「ゲーム内で使用出来るスペシャル衣装が解放される」プロダクトコード 同梱)</a>
							</h3>
							<p class="price">￥ 5,299</p>

						</td>
						<td></td>
						<td>

							<p class="image">
								<a href="./0000102341.html" target="_blank"><img width="120"
									height="120" src="imeg/new (7).jpg" /></a>
							</p>
							<h3 class="name">
								<a href="./0000102341.html" target="_blank"><font color=red>妖怪ウォッチ2
										本家 (永久同梱特典:ジバニャンメダル コマニャチ 同梱)</a>
							</h3>
							<p class="price">￥ 4,064</p>

						</td>
						<td></td>
						<td>

							<p class="image">
								<a href="./0000136435.html" target="_blank"><img width="120"
									height="120" src="imeg/new (8).jpg" /></a>
							</p>
							<h3 class="name">
								<a href="./0000136435.html" target="_blank">ソードアート・オンライン
									―ホロウ・フラグメント― 初回限定生産版 (初回封入特典「ゲーム内で使用出来るスペシャル衣装が解放される」プロダクトコード
									同梱)</a>
							</h3>
							<p class="price">￥ 10,545</p>

						</td>
					</tr>
				</table>



				<dl class="stock-info">
					<dt>更新情報</dt>
					<dd>
						<ul>
							<li><span>[お知らせ]</span>&nbsp;消費税率変更に伴う販売価格についてのお知らせ</li>
							<li><span>[お知らせ] </span>&nbsp;女性向け商品ポータルサイト「Sweet
								Girl's」について</li>
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
		</div>
	</div>



	<div class="footer"></div>

</div> <!-- warp -->
</body>
</html>

