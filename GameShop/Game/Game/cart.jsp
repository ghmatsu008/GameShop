<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="test3.*" %>
<%@ page import="cart.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8" />
	<link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css"/>
	
	  <script src="//code.jquery.com/jquery-1.10.2.js"></script>
	  <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
	  <link rel="stylesheet" href="/resources/demos/style.css"/>	
	<title>Delete</title>
</head>
<body>

<script type="text/javascript">
$(function(){	
		$("#sub").click(function(){
			var code2 = $(':hidden[name="index"]').val();
			var code = $(':hidden[name="action"]').val();
			
			 if (!confirm('登録内容を保存します\nよろしいですか？')) {
			        return false;
			    }
			
//  			var data = { "dat1" : dat1, "dat2" : dat2};
			var data = { "action" : code, "index" : code2};

			alert( "["+code2 + "] [" +code+"]" );
			
				var url = "MainController";
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
		
});		
			
</script>

	<h3>GameShop  &nbsp; カートBox</h3>
		<A HREF="sellerTop.jsp">TOP</A>
	<br>
	<hr>
	
<p>商品一覧</p>

		<table align="center">
			<c:choose>
				<c:when test="${empty cart }">
				<tr>
					<td>カートは空です。</td>
				</tr>
				</c:when>
				
				<c:otherwise>
			<tr valign="top">
				<td align="center">
				<table border="1">
					<th width="80" colspan="2">商品名</th>
					<th width="60" colspan="2">価格</th>
					<c:forEach var="item" items="${cart.list}" varStatus="status">
					<tr>			
						<td><input type="checkbox" name="delete_id"/></td>
						<td>${item.nam}</td>
						<td>${item.price}</td>
						
						<td><form id="sub">
<!-- 						<form action="MainController" method="post"> -->
							<input type="hidden" name="action" value="remove">
							<input type="hidden" name="index" value="${status.index}">
							<input  type="button" value="削除">
						</form></td>
					</tr>
					</c:forEach>
					<tr>
						<td colspan="4" align="right" color="red" ><B>
							合計${cart.size}個　￥${cart.totalPrice}</B></td>
					</tr>
					</table>
				</td>
				</tr>
				<tr>
					<td align="center"><a href="#">購入する</a></td>
				</tr>
				</c:otherwise>
				</c:choose>
					<tr>
						<td align="center"><a href="#">TOPへ戻る</a></td>
					</tr>
				</table>						

	<p>
		<input type="submit" name="delete" value="チェックした商品を削除" />
	</p>



</body>
</html>