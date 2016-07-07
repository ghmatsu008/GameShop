<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8" />
	<link rel="stylesheet"
		href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css" />
	<script src="//code.jquery.com/jquery-1.10.2.js"></script>
	<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
	<link rel="stylesheet" href="css/member.css" />
	<title>Insert</title>
</head>
<body>

	<SCRIPT src="js/member.js"></SCRIPT>

	<div id="wrap">
		<div class="head">
			<h1>GAME通信販売</h1>
			<br /> <span class="label"></span>
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
		<%--ロゴ--%>
		<A HREF="https://localhost:8080/GameShop/index2.jsp">TOP</A> <br>
		<hr>
		<center>

			<h2>会員登録</h2>
			<div id="div1"></div>

			<form id="testform">
				<input type="hidden" id="_k" name="_k"
					value="97a03ada5f2c55ade36232fd8387f27b579ae6c9"> <input
					type="hidden" id="_m" name="_m" value="post"> <input
					type="hidden" name="VID" value="user.user.register">
				<!-- ▼ message area -->

				<!-- ▲ message area -->
				<table class="com_dtl_entire" width="500">
					<tr>
						<td align="center">※ <input type="text" name="LOGIN_ID" value="メールアドレス"
							style="color: #CCC; height: 30px;" onfocus="onf(this);"
							onblur="isMail(this);" maxlength="40">
						</td>
						<td>※半角英数記号</td>
						<!-- <td><input onfocus="if (this.value == 'メールアドレス') this.value = ''; " -->
						<!-- 	onblur="if (this.value == '') this.value = 'メールアドレス'; this.style.color ='#CCC';" style="color:#CCC" value="メールアドレス" > -->

						<!-- </td> -->
						<!-- <td><input type="text" name="LOGIN_ID" value="" -->
						<!-- 						 autoComplete="off" maxLength="250" -->
						<!-- 						size="40"><br><span class="com_fnt_note">※メールアドレスを入力してください</span></td> -->
					</tr>
					<tr class="e1"></tr>
					<tr>
						

						<td align="center">※ <input type="password" name="LOGIN_PASSWORD"
							value="ログインパスワード"
							style=" color: #808080;" height: 30px;"
							onfocus="onf(this);" onblur="isReg(this);" maxlength="10">

						</td>
						<td width="200">パスワード<br/>※半角英数記号(6～10桁)</td>
						<!-- <td><input type="password" name="LOGIN_PASSWORD" value="" -->
						<!-- 						class="com_ipt_text_min" id="loginPassword" autoComplete="off" -->
						<!-- 						maxLength="10" size="20"><span class="com_fnt_note">※半角英数記号(6～10桁)</span></td> -->

						<!-- </tr> -->
						
					<tr class="e2"></tr>
					<tr>
						
						<td align="center">※ <input type="password" name="LOGIN_PASSWORD_CONF" value=""
							style="color: #808080; height: 30px;" onblur="isR(this);" onfocus="onf(this);"
							 maxlength="10"></td>

						<td width="200">パスワード確認※<br/>半角英数記号(6～10桁)</td>
					</tr>
					<tr class="e9"></tr>
					<tr>
						<td>-</td>
					</tr>
					<tr>
						<td align="center">※ <input type="text"  name="LAST_NAME" value="姓"
							style="color: #808080; height: 20px;" onfocus="onf(this);"
							onblur="isLnam(this);" maxlength="10">
						</td>
						<td> （例： 山田</td>
					</tr>
					<tr class="e3"></tr>
					<tr>
						<td align="center">※ <input type="text"  name="FIRST_NAME" value="名"
							style="color: #808080; height: 20px;" onfocus="onf(this);"
							onblur="isFnam(this);" maxlength="10">
						</td>
						<td> （例： 太郎</span></td>
					</tr>
					<tr class="e4"></tr>
					<tr>
						<td align="center">  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="LAST_NAME_KANA" value="姓(ｶﾅ)"
							style="color: #808080; height: 30px;" onfocus="onf(this);"
							onblur="isLnamK(this);" maxlength="7"></td>
						<!-- <td><input type="text" name="妙(カナ)" value="" -->
						<td>（例： ヤマダ</td>
					</tr>
					<tr>
						<td align="center">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="FIRST_NAME_KANA" value="名(ｶﾅ)"
							style="color: #808080; height: 30px;" onfocus="onf(this);"
							onblur="isFnamK(this);" maxlength="7"></td>
						<!-- <td><input type="text" name="FIRST_NAME_KANA" value="" -->
						<!-- 						id="firstNameKana" maxLength="250"><span class="gray">　（例：　タロウ</span></td> -->
						<td>（例： タロウ</td>
					</tr>
					<tr>
						<td align="center">※ <input type="text" name="ZIP" value="郵便番号"
							style="color: #808080; height: 30px;" onfocus="onf(this);"
							onblur="isZip(this);" maxlength="8"> <!-- <td><input type="text" name="ZIP" value="" -->
							<!-- 						class="com_ipt_text_min" id="zipCode1" autoComplete="off" -->
							<!-- 						maxLength="7" size="7"> -->
						</td>
						<td> ※半角（例： 444-0823</span></td>
					</tr>
					<tr class="e5"></tr>
					<tr>
						<td align="center"><select name="PREFECTURE" class="com_ipt_select" size="1" >
								<option value="" selected>--都道府県 選択してください--</option>
								<option value="1">1:&nbsp;北海道</option>
								<option value="2">2:&nbsp;青森県</option>
								<option value="3">3:&nbsp;岩手県</option>
								<option value="4">4:&nbsp;宮城県</option>
								<option value="5">5:&nbsp;秋田県</option>
								<option value="6">6:&nbsp;山形県</option>
								<option value="7">7:&nbsp;福島県</option>
								<option value="8">8:&nbsp;茨城県</option>
								<option value="9">9:&nbsp;栃木県</option>
								<option value="10">10:&nbsp;群馬県</option>
								<option value="11">11:&nbsp;埼玉県</option>
								<option value="12">12:&nbsp;千葉県</option>
								<option value="13">13:&nbsp;東京都</option>
								<option value="14">14:&nbsp;神奈川県</option>
								<option value="15">15:&nbsp;新潟県</option>
								<option value="16">16:&nbsp;富山県</option>
								<option value="17">17:&nbsp;石川県</option>
								<option value="18">18:&nbsp;福井県</option>
								<option value="19">19:&nbsp;山梨県</option>
								<option value="20">20:&nbsp;長野県</option>
								<option value="21">21:&nbsp;岐阜県</option>
								<option value="22">22:&nbsp;静岡県</option>
								<option value="23">23:&nbsp;愛知県</option>
								<option value="24">24:&nbsp;三重県</option>
								<option value="25">25:&nbsp;滋賀県</option>
								<option value="26">26:&nbsp;京都府</option>
								<option value="27">27:&nbsp;大阪府</option>
								<option value="28">28:&nbsp;兵庫県</option>
								<option value="29">29:&nbsp;奈良県</option>
								<option value="30">30:&nbsp;和歌山県</option>
								<option value="31">31:&nbsp;鳥取県</option>
								<option value="32">32:&nbsp;島根県</option>
								<option value="33">33:&nbsp;岡山県</option>
								<option value="34">34:&nbsp;広島県</option>
								<option value="35">35:&nbsp;山口県</option>
								<option value="36">36:&nbsp;徳島県</option>
								<option value="37">37:&nbsp;香川県</option>
								<option value="38">38:&nbsp;愛媛県</option>
								<option value="39">39:&nbsp;高知県</option>
								<option value="40">40:&nbsp;福岡県</option>
								<option value="41">41:&nbsp;佐賀県</option>
								<option value="42">42:&nbsp;長崎県</option>
								<option value="43">43:&nbsp;熊本県</option>
								<option value="44">44:&nbsp;大分県</option>
								<option value="45">45:&nbsp;宮崎県</option>
								<option value="46">46:&nbsp;鹿児島県</option>
								<option value="47">47:&nbsp;沖縄県</option>
						</select></td>
					</tr>
					<tr>
						<td align="center">※ <input type="text" name="ADDRESS"  value="住所"
							style="color: #808080; height: 40px;" onfocus="onf(this);"
							onblur="isAdrs(this);" maxlength="50"> 
						</td>
						<td>（例： 岡崎市上地二丁目17-1
							コムショップアパート101号 <br>※アパート・マンション名・部屋番号まで入力下さい</td>
					</tr>
					<tr class="e6"></tr>
					<tr>
						<td align="center">※ <input type="text" name="TEL" value="TEL"
							style="color: #808080; height: 30px;" onfocus="onf(this);"
							onblur="isTel(this);" maxlength="16"></td>
						<!-- <td><input type="text" name="TEL" value="" -->
						<!-- 						class="com_ipt_text_min" autoComplete="off" maxLength="20" -->
						<!-- 						size="40"><br><span class="gray"> -->
						<td width="250">（例：ハイフン無し<br>携帯電話番号でもOKです。
						</td>
						</span>
					</tr>
					<tr class="e7"></tr>
					<tr>
						
						<td align="center">メール配信許可<br/><input type="radio" name="EMAIL_SUBSCRIBE_FLAG" value="1"
							class="com_ipt_text_min" id="emailSubscribeFlag-1" checked><label
							for="emailSubscribeFlag-1">希望する</label> <input type="radio"
							name="EMAIL_SUBSCRIBE_FLAG" value="0" class="com_ipt_text_min"
							id="emailSubscribeFlag-2"><label
							for="emailSubscribeFlag-2">希望しない</label>
						</td><td><br>コムショップからのメールマガジン「コムショップ通信」を配信します。</td>
					</tr>
					<tr>
						<td align="center">
						HTMLメール配信許可<br><input type="radio" name="EMAIL_HTML_SUBSCRIBE_FLAG"
							value="1" class="com_ipt_text_min" id="emailHtmlSubscribeFlag-1"
							checked><label for="emailHtmlSubscribeFlag-1">希望する</label>
							<input type="radio" name="EMAIL_HTML_SUBSCRIBE_FLAG" value="0"
							class="com_ipt_text_min" id="emailHtmlSubscribeFlag-2"><label
							for="emailHtmlSubscribeFlag-2">希望しない</label></td>
					</tr>

				<tr>
					<td align="center" colspan="3"><input id="sub" type="button" value="登録" /> <input
						type="reset" value="クリア" /></td>
							
					</tr>
		
				</table>


			</form>
</center>
	</div>

	</div>
	<form name="_vm" method="post">
		<input type="hidden" id="_k" name="_k"
			value="97a03ada5f2c55ade36232fd8387f27b579ae6c9"><input
			type="hidden" id="_m" name="_m" value="get">

	</form>
	</div>
</body>
</html>