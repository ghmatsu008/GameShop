<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8" />
	<link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css"/>
	  <script src="//code.jquery.com/jquery-1.10.2.js"></script>
	  <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
	  <link rel="stylesheet" href="/resources/demos/style.css"/>	
	<title>Insert</title>
</head>
<body>

<script type="text/javascript">

	$(document).ready( function() {
		
		$('#div1').hide();

		
// 		$('#testform [name=nam]').val("");
// 		$('#testform [name=asin]').val("");
		
		$("#sub").click(function(){
			var dat1 = $('#testform [name=nam]').val();
			var dat2 = $('#testform [name=asin]').val();
			var dat3 = $('#testform [name=pr]').val();
			var dat4 = $('#testform [name=cat]').val();
			var dat5 = $('#testform [name=ranck]').val();
		
			dat1 = jQuery.trim(dat1);
			dat2 = jQuery.trim(dat2);
			
			
			    if (!confirm('登録内容を保存します\nよろしいですか？')) {
			        return false;
			    }
			
			
			if(dat1.length <= 0){
				input_error_num();
				return false;
			}
			if(dat2.length <= 0){
				input_error_name();
				return false;
			}
			var data = { "dat1" : dat1, "dat2" : dat2, "dat3" : dat3, "dat4" : dat4, "dat5" : dat5};
			//var data = { "dat1" : dat1, "dat2" : dat2};

// 			alert( "["+dat3 + "] [" +dat4+"]" );
			
				var url = "Insert";
				$.ajax({
					type: 'POST',
					url: url,
					data: data,
					dataType: 'html',
					cache: false,
					success: function(data, textStatus){
						$('#div1').html(data);
						$('#div1').show("slow");
					},
					error: function(xhr, textStatus, errorThrown){
						alert('Error!' + textStatus + '' + errorThrown);
					
					}
					
					
					
		});
	});
	function input_error_num(){
		$('#e1').html("<p>番号入力してください<p>");
		$('#e1').show("slow");
	}
	function input_error_nume(){
		$('#e2').html("<p>名前を入力してください<p>");
		$('#e2').show("slow");
	}
		
});					
</script>


<h3>GameShop  &nbsp; slellercentral </h3>
<%--ロゴ--%>
<A HREF="sellerTop.jsp">TOP</A>
<br>
<hr>
<center><h2>商品登録</h2>
<form id="testform" >

	<table>
		<tr>
			<th align="right">商品名</th>
			<td><input type="text" name="nam" size="40" maxlength="50" /></td>
		</tr>
		<tr>
			<th align="right">ASIN</th>
			<td><input type="text" name="asin" size="25" maxlength="10" /></td>
		</tr>
		<tr>
			<th align="right">カテゴリー</th>
			<td>
			<select name="cat"> 
 				<option value="">---</option> 
 				<option value="ps4">PS4</option>
				<option value="ps3">PS3</option> 
 				<option value="ds">ニンテンドーDS</option> 
 				<option value="3ds">ニンテンドー3DS</option>
 				<option value="vita">プレイステーション Vita</option> 
 				<option value="xbox">Xbox 360</option> 
 				<option value="u">Wii U</option> 
 			</select> 
		</td>
		</tr>
		<tr>
			<th align="right">販売価格</th>
			<td><input type="text" name="pr" size="10" maxlength="10" /></td>
		</tr>
		<tr>
			<th align="right">ランキング</th>
			<td><input type="text" name="ranck" size="10" maxlength="10" /></td>
		</tr>
		<tr>
			<th align="right">数量</th>
			<td><input type="text" name="old" size="3" maxlength="3" /></td>
		</tr>
		<tr>
			<th align="right">発売日</th>
			<td><input type="date" name="date"/></td>
		</tr>
		<tr>
		
	
		<td colspan="2">
			<input id = "sub" type="button"   value="登録"/>
			<input type="reset" value="クリア"/>
		</td>	
		</tr>
		</table>	
</form>

	</center>
<!-- 	<form id="testform"> -->
<!-- 	番号:<input  type="text" name="data1" value="" /><br/> -->
<!-- 		<div id="e1"></div> -->
<!-- 	名前:<input  type="text" name="data2" value="" /><br/> -->
<!-- 	<input id="button1" type="button" value="sbmit" /> -->
	
<!-- 		<div id="e2"></div> -->
<!-- 	</form> -->
	<hr/>
<div id="div1"></div>


</body>
</html>