<?xml version="1.0"?>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="test.*"%>
<%@ page import="cart.*"%>
<%@page import="java.sql.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%@ page session="false"%> --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="login.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.Calendar"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8" />
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css" />

<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<link rel="stylesheet" href="css/pay2.css" />
<title>cartBOX</title>
</head>

<%


	Calendar objCal1 = Calendar.getInstance();
	Calendar objCal2 = Calendar.getInstance();
	objCal2.set(1970, 0, 1, 0, 0, 0);
	response.setDateHeader("Last-Modified", objCal1.getTime().getTime());
	response.setDateHeader("Expires", objCal2.getTime().getTime());
	//文字化け対策
	request.setCharacterEncoding("UTF-8"); 
	response.setHeader("pragma", "no-cache");
	response.setHeader("Cache-Control", "no-cache");
	
	ItemDao dao = new ItemDao();
// 	HttpSession session = request.getSession(true);
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
	int logid=0;
	int price=0;
	int count=0;
	
	String mail = "";
	String lNam = "";
	String fNam = "";
	String post = "";
	int ken = 0;
	String address = "";
	String tel = "";
	int qua = 0;
	String pNam = "";
	
	List list = new ArrayList();
	
	String payment = request.getParameter("payment");
System.out.println(payment);
	String bank = request.getParameter("institution_number");
System.out.println(bank);
%>


<body>
	<CENTER>

		<table id="container" cellspacing="0" cellpadding="0" border="0">
			<tr>
				<td colspan="2">
					<!-- トップ部分 --> <!-- TITLE-AREA BEGIN -->


					<div id="header">
						
					</div> 
					
				</td>
			</tr>
			<tr>
				<!-- 中央コンテンツ -->
				<!-- メイン部分 -->
				<td valign="top" class="center" id="center3">
					<!--ヘッダーフリーエリア-->

					<div class="header_area">&nbsp;</div> <!-- SHOP-PAGE CONTENT-AREA BEGIN -->
					<!-- KAGOIDログアウト確認JS --> <script language="JavaScript"
						type="text/JavaScript">
				<!--
					var TARGET_COOKIE_NAME = 'KAGO';
					var CART_SCRIPT_NAME = 'cart.cgi';
					var COOKIE_KEY = 0;
					var COOKIE_VALUE = 1;

					/*********************************************************
					 * カゴ画面に移動
					 * -------------------------------------------------------
					 * @param	なし
					 * @return	なし
					 *********************************************************/
					function _gotoKago() {
						if (document.PREV == undefined) {
							if (document.LOGOUT == undefined) {
								// 複数宛先住所登録時
								window.close();
							} else {
								// カゴ画面だがログアウトが必要な時
								document.LOGOUT.submit();
							}
						} else {
							document.PREV.action = CART_SCRIPT_NAME;
							document.PREV.KAGOID.value = '';
							document.PREV.submit();
						}
					}

					/*********************************************************
					 * クッキーとPOSTされるKAGOIDの整合性確認
					 * -------------------------------------------------------
					 * @param	なし
					 * @return	true 正常 | false 異常	
					 *********************************************************/
					function _isValidKagoID() {
						var eachCookieValue = document.cookie.split(";");
						var isValidKagoID = false;
						var matchResult = 0;
						var valueCookie = '';

						// KAGOID を入れているはずのFORMが存在しない場合は true を返す
						if (document.NEXT == undefined)
							return true;
						var kagoid = document.NEXT.KAGOID.value;

						for (var i = 0; i < eachCookieValue.length; i++) {
							unitCookie = eachCookieValue[i].split("=");
							matchResult = unitCookie[COOKIE_KEY]
									.indexOf(TARGET_COOKIE_NAME);
							if (matchResult < 0)
								continue;

							// ----------------------------------
							// クッキー中の KAGOID の値を確認
							// ----------------------------------

							if (unitCookie.length < 2)
								break;

							valueCookie = unitCookie[COOKIE_VALUE];
							if (valueCookie == undefined)
								break;
							if (valueCookie == '')
								break;

							matchResult = valueCookie.indexOf(kagoid, 0);
							if (matchResult == -1)
								break;

							// ----------------------------------
							// クッキーとPOSTされるKAGOIDが一致した場合
							// ----------------------------------
							isValidKagoID = true;
							break;
						}

						// 正常なKAGOIDの場合は、true を返す
						if (isValidKagoID == true)
							return true;

						// 異常なKAGOIDの場合は、カゴ画面に戻る
						_gotoKago();

						return false;
					}
				//-->
				</script> <!-- /KAGOIDログアウト確認JS --> <script language="JavaScript">
<!--
	var fin = 0;
	function gonext(fname) {
		if (_isValidKagoID() == true)
			fname.submit();
		return;
	}

	function goback(fname) {
		if (fin == 0) {
			if (_isValidKagoID() == true)
				fname.submit();
		}
		return;
	}

	function commit_order() {
		if (_isValidKagoID() == false)
			return;

		document.getElementById('confirm').style.display = "none";
		document.getElementById('wait').style.display = "block";

		document.NEXT.submit();
	}
//-->
</script>

					<table border="0" cellpadding="0" cellspacing="0">
						<tr>
							<!-- 中央コンテンツ -->
							<td id="center1">
								<%	if(dat.getid() == null){
										out.print("<h3>購入はログインが必要です。</h3><br><br>");
										out.println("<a href=\"http://localhost:8080/GameShop/memberReg.jsp\">会員登録する</a><br><br>");
									}else if{ 
											if(dat.getid() == null){
												out.print("<h3>購入はログインが必要です。</h3><br><br>");
												out.println("<a href=\"http://localhost:8080/GameShop/memberReg.jsp\">会員登録する</a><br><br>");
						%> <!-- centerbox -->
								<div class="centerbox">

									<h3>ショッピングカート</h3>
									<!-- mainFrame -->
									<div class="mainFrame">
										<!-- mainCont-->
										<div class="mainCont">

											<div id="confirm" style="display: block">

												<p>注文内容をご確認ください。間違いがなければ「この内容で注文する」ボタンをクリックしてください。</p>
												<p align="center">
													<input type="button" value="戻る" class="button1" action="#";"><input
														type="button" class="button1"
														onClick="this.disabled=true; gonext(document.NEXT);"
														value="　この内容で注文する　">
												</p>



												<!-- エラーメッセージ //-->


												<INPUT TYPE="hidden" NAME="STORENAME" VALUE="1000156.s2"><INPUT
													TYPE="hidden" NAME="KAGOID"
													VALUE="bdec280946f8a3364057262e421e2635"><INPUT
													TYPE="hidden" NAME="DELIVORD_LIST" VALUE="1042407"><INPUT
													TYPE="hidden" NAME="CMD" VALUE=""><INPUT
													TYPE="hidden" NAME="REFERER"
													VALUE="http://www.wakashachiya.co.jp/shopping/"> <!-- カゴ ここから //-->
												<h4>▼ ご注文内容</h4>
												<div  center">
													<table class="bordlayoutp3" width="95%" border="0" cellpadding="0"
														cellspacing="0" class="border">
														<tr>
															<td width="15%" class="center backcolor1">画像</td>
															<td width="25%" class="center backcolor1">品名</td>
															<td width="15%" class="center backcolor1">価格</td>
															<td width="10%" class="center backcolor1">数量</td>
															<td class="center backcolor1">小計</td>
														</tr>

														<!-- カゴ中身 ループ ここから //-->

														

															<%
									try {
										Class.forName("com.mysql.jdbc.Driver");
										Connection con = DriverManager.getConnection(url, user, pass);
										String sql2 = "SELECT * FROM idpass WHERE idNam='"+ dat.getid() + "'";
										
										Statement stmt = con.createStatement();
										ResultSet rs2 = stmt.executeQuery(sql2);
										rs2.next();
										
										logid = rs2.getInt("id");
										mail = rs2.getString("idNam");
										lNam = rs2.getString("l_name");
										fNam = rs2.getString("f_name");
										post = rs2.getString("post");
										ken = rs2.getInt("ken");
										address = rs2.getString("address");
										tel = rs2.getString("tel");
										
										
										rs2.close();
										String sql = "SELECT * FROM cartbox WHERE log_id='"+ logid + "'";
										
										ResultSet rs = stmt.executeQuery(sql);


										while(rs.next()){
											
											CartItem ct = new CartItem();
											
											price += rs.getInt("price")+800; 
											qua += rs.getInt("qua");
											ct.setNam(rs.getString("nam"));
											
										%><tr class="backcolor2">
												<td><img src="/vol1blog/1/1000156.s2.shopserve.jp/docs/pic-labo/timg/waka_nisshin.jpg"
														width="50" height="25"></td>
												<td><%=rs.getString("nam")%></td>
												<td align="right"><%=rs.getInt("price")%>円</td>
												<td align="center"><%=rs.getInt("qua")%></td>
												<td align="right"><%=rs.getInt("price")%>円</td>
											</tr>

														<!-- カゴ中身 ループ ここまで //-->
														<%	
											
											list.add(ct);
											count++;
											} 

											rs.close();
											stmt.close();
											session.setAttribute("list",list);
										%>


														<!-- 送料 //-->

														<tr class="backcolor2">
															<td colspan="4" align="right">送料</td>
															<td align="right">800円</td>
														</tr>


														<!-- のし・ラッピング等 //-->



														<!-- 手数料 //-->


														<!-- ポイント //-->


														<tr class="backcolor2">
															<td colspan="4" align="right">合計金額</td>
															<td align="right"><%=price+800 %>円</td>
														</tr>
													</table>
												</div>

												<!-- カゴ　空の場合 //-->

												<!-- カゴ ここまで //-->


												<h4>▼ 購入者情報</h4>

												<div class="right">
													<input type="button" class="button1"
														onClick="goback(document.PREV_CHKOUT)" value="編集">
												</div>

												<div class="bordlayoutp center">
													<table width="95%" border="0" cellspacing="0"
														cellpadding="0" class="border">

														<tr align="center">
															<td align="left" class="backcolor1">お名前</td>
															<td align="left" class="backcolor2"><%=lNam%>&nbsp;<%= fNam %></td>
														</tr>

														<tr align="center">
															<td align="left" class="backcolor1">郵便番号</td>
															<td align="left" class="backcolor2"><%=post %></td>
														</tr>

														<tr align="center">
															<td align="left" class="backcolor1">都道府県</td>
															<td align="left" class="backcolor2"><%=ken%></td>
														</tr>
														<tr align="center">
															<td align="left" class="backcolor1">住所</td>
															<td align="left" class="backcolor2"><%=address %></td>
														</tr>



														<tr align="center">
															<td align="left" class="backcolor1">お電話番号</td>
															<td align="left" class="backcolor2"><%=tel%></td>
														</tr>


														<tr align="center">
															<td align="left" class="backcolor1">メールアドレス</td>
															<td align="left" class="backcolor2"><%=mail %>
															</td>
														</tr>
														
														<%
// 											stmt2.close();
// 										rs3.close();
										
									}catch(SQLException ex){
										System.out.println("SQL failed");
										ex.printStackTrace();
									}
										%>
													</table>
													<hr>
												</div>

												<div align="right">
													<input type="button" class="button1"
														onClick="goback(document.PREV_CHKOUT)" value="編集">
												</div>
												<div class="bordlayoutp center">
													<table width="95%" border="0" cellspacing="0"
														cellpadding="0">
														<tr>
															<td align="left" class="backcolor1">お支払方法</td>
														</tr>
														<tr>
															<td align="left" class="backcolor2"><%=payment %> <%
															 if(bank !=null){
																	if(bank.equals("0036")){out.println("   【振込先】 楽天銀行");}
																	if(bank.equals("")){out.println("   【振込先】 楽天銀行");}
																	
															} 
															%> <BR></td>
														</tr>
													</table>
													
												</div>
<hr>
												<div class="bordlayoutp center">

													<!-- お届け希望日　ここから //-->

													<br>
													<table width="95%" border="0" cellspacing="0"
														cellpadding="0" class="border">

														<tr align="center">
															<td width="30%" align="left" class="backcolor1">お届け希望日</td>
															<td align="left" class="backcolor2">希望日指定なし 時間指定
																指定なし</td>
														</tr>

													</table>
												</div>

												<br>
												<div align="right">
													<input type="button" class="button1"
														onClick="goback(document.PREV_ENQUETE)" value="編集">
												</div>
												<div class="bordlayoutp center">
													<table width="95%" border="0" cellspacing="0"
														cellpadding="0">

							<!-- 購入者アンケート設定　ここから //-->


														<tr>
															<td align="left" class="backcolor1">今後当店からのお知らせメールを受け取りますか</td>
														</tr>
														<tr>
															<td align="left" class="backcolor2">いいえ</td>
														</tr>

													</table>
												</div>
											</div>
											<br>
										</div>

										<!-- しばらくお待ちください ここから //-->
										<div id="wait" style="display: none">
											<div class="bordlayoutp center">
												<table width="95%" border="0" cellspacing="0"
													cellpadding="0" class="border">
													<tr>
														<td align="center"><br>
														<img src="/img/nowloading.gif" width="92" height="92"></td>
													</tr>
													<tr>
														<!--
    <td nowrap><span class="txt">ただいま、の処理中です。<br>
      もうしばらくそのままお待ちください。</span></td>
//-->
														<td nowrap><font color="#000000">ただいま、の処理中です。<br>
																	もうしばらくそのままお待ちください。</font></td>
													</tr>
												</table>
												<br>
												<br>
											</div>
										</div>
										<!-- しばらくお待ちください ここまで //-->

										<div class="spacebox"></div>
									</div>
									<!-- //mainCont -->
								</div> <!-- //mainFrame -->
								</div> <!-- //centerbox-->
							</td>
							<!-- //中央コンテンツ -->
						</tr>
					</table>
					<form method="POST" action="SvMail">
						<!-- 						<form action="MainController" method="post"> -->
						<input type="hidden" name="token" value="${sessionScope['token']}">
						<input type="hidden" name="mail" value=<%=mail %>>
						<input type="hidden" name="payment" value=<%=payment %>>
						<input type="hidden" name="lid" value=<%=logid %>>
						<input
							type="hidden" name="bank" value=<%=bank %>><input
							type="hidden" name="lNam" value=<%=lNam %>><input
							type="hidden" name="fNam" value=<%=fNam %>><input
							type="hidden" name="post" value=<%=post %>><input
							type="hidden" name="addres" value=<%=address %>><input
							type="hidden" name="price" value=<%=price %>><input
							type="hidden" name="qua" value=<%=qua %>><input
							type="submit" value="この内容で注文する">
					</form> <% } %> <!-- if文 -->

					<div class="footer_area">&nbsp;</div>

				</td>
			</tr>
			<tr>
				<td>
					<!-- footer -->
					<div id="footer">
						<a href="">個人情報の取り扱いについて</a> | <a href="">特定商取引法に関する表示</a>
					</div>
				</td>
			</tr>
		</table>
	</CENTER>
</body>
</html>









