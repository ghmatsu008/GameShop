<?xml version="1.0"?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8" />
	  <link rel="stylesheet" href="/resources/demos/style.css"/>	
	<title>Insert</title>
</head>
<%
// response.setContentType("text/html; charset=UTF-8");
// response.setHeader("pragma", "no-cache");
// response.setHeader("Cache-Control", "no-cache");
%>
<body>

	<h2>支払方法の選択</h2>
					<form method="POST" action="pay2.jsp">
					<table width="650px" class="optionchoices" id="pay1" border="1">
					<tr>
						<th style="background-color:#F1F4D0;">
							<input type="radio" name="payment" value="クレジット" id="cred" checked><label for="cred">クレジットカード払い<span class="chuui">※1</span></label>
						</th>
						<td>
							クレジット一括払いでお支払いいただけます。<br>
							使用できるカード:
							<table style="text-align:center;">
							<tr>
								<td><img src="imeg/visa.gif" alt="VISA" width="40" height="20"></td>
								<td><img src="imeg/mc.gif" alt="MasterCard" width="44" height="28"></td>
								<td><img src="imeg/jcb.gif" alt="JCB" width="48" height="37"></td>
								<td><img src="imeg/aex.gif" alt="AMERICANEXPRESS" width="32" height="32"></td>
<!-- 								<td><img src="./img/card_diners_std.gif" alt="DinersClubInternational" width="51" height="37"></td> -->
							</tr>
							<tr>
								<td>VISA</td>
								<td>MASTER</td>
								<td>JCB</td>
								<td>AMEX</td>
<!-- 								<td>Diners</td> -->
							</tr>
							</table>
						</td>
					</tr>
					<tr>
						<th style="background-color:#F1F4D0;">
							<input type="radio" name="payment" value="代引" id="dai"><label for="dai">代金引換払<span class="chuui">※1</span></label>
						</th>
						<td>
							代金引換サービスです。<br>
							商品をお受け取りになる際に代金をお支払いいただきます。<br>
							手数料として５００円をいただきます。<br>
						</td>
					</tr>
					<tr>
						<th style="background-color:#F1F4D0;">
							<input type="radio" name="payment" value="PayPal" id="paypal"><label for="paypal">PayPal払い<span class="chuui">※1</span></label>
						</th>
						<td>
							PayPal払いでお支払いいただけます。<br>
							<br>
						</td>
					</tr>
					<tr>
						<th style="background-color:#C3D2F2;">
							<input type="radio" name="payment" value="振込" id="gin"><label for="gin">銀行振込<span class="chuui">※2</span></label><br>
							<br>
							▽振込先銀行の選択<br>
							<select name="institution_number">
								<option value="">選択してください</option>
								<option value="0036">楽天銀行(旧イーバンク)</option>
								<option value="0009">三井住友銀行</option>
								<option value="0005">三菱東京ＵＦＪ銀行</option>
								<option value="9900">ゆうちょ銀行</option>
								<option value="0033">ジャパンネット銀行</option>
							</select>
					<br>
						</th>
						<td>
							当店指定口座に代金を振り込んでいただき、当店が入金を確認してから商品を発送するシステムです。<br>
							「振込先銀行の選択」よりご希望の金融機関を選択して下さい。<br>
							※振込口座は別途、弊社で商品を確認・検品した後、メールにてご案内させていただきます。<br>
							振込手数料はお客様負担となります。<br>
							
							<strong style="margin:1px;">***　お振込み時のご注意　***<br>
							お振込みはおつりのでないよう、お願いいたします。<br>
							なお、ご購入金額以上をお振込みされた場合でも、返金は致しかねますので、予めご了承ください。</strong><br>
							※商品発送までにお時間がかかる場合がございます。
						</td>
					</tr>
					<tr>
						<th style="background-color:#C3D2F2;">
							<input type="radio" name="payment" value="書留" id="tome"><label for="tome">現金書留<span class="chuui">※2</span></label>
						</th>
						<td>
							当店宛てに代金を現金書留で送っていただき、当店が確認次第商品を発送するシステムです。<br>
							書留封筒等の諸費用はお客様負担となります。
						</td>
					</tr>
					
					<div style="margin: 5 20 30;">
					<p>※1　通常１日～２日程度で発送させて頂きます。<br>
					品切れまたは商品の状態により価格の変動が生じた場合は、確認後の発送となります。</p>
					<p>※2　入金を確認してからの発送となります。</p>
					</div>
					</table>
						<input type="submit" value="次へ ＞"><br style="clear: both;">
</form>
</body>
</html>