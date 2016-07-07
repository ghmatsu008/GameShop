<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="test.*" %>
<jsp:useBean id="items" scope="request" type="test.ItemData[]"></jsp:useBean>
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

	<h3>GameShop  &nbsp; slellercentral </h3>
		<A HREF="sellerTop.jsp">TOP</A>
	<br>
	<hr>
	
<h2>商品一覧</h3><br/>

	<form action="DbDelete" method="Get">
		<p>
		 <input type="submit" name="delete" value="チェックした商品を削除" />
	 	</p>
	 <center>
		<table border="1">
			<% for( int i=0; i<items.length; i++ ){ %>
			<tr>
				<td><input type="checkbox" name="delete_id_<%= i %>" value="<%= items[i].getId() %>" /></td>
				<td><%= items[i].getId() %></td>
				<td><%= items[i].getNam() %></td>
				<td><%= items[i].getPrice() %>円</td>
			</tr>
			<% } %>
	</table>
</center>
	<p>
		<input type="submit" name="delete" value="チェックした商品を削除" />
	</p>

</form>


</body>
</html>