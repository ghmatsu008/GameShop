package cart;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;



public class CommandAdd implements Command{
	
	public String execute(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException{
		
		
		ItemDao dao = new ItemDao(); 
		HttpSession session = request.getSession(true);
		Cart cart = (Cart)session.getAttribute("cart");
		
		if(cart == null){
			cart = new Cart();
			cart.setList(new ArrayList());
		}
		
		ServletContext application = session.getServletContext();
		ItemArray array = (ItemArray)application.getAttribute("array");
		
		String input = request.getParameter("index");
		String str = request.getParameter("logId");
		int index = 0;
		int logId = 0;
		
		if(input != null && str !=null){
			index = Integer.parseInt(input);
			logId = Integer.parseInt(str);
		}
		CartItem ct = array.getItem(index-1);
System.out.println(index+"=index addClass");
		cart.addItem(ct);
		dao.cartIn(ct, logId);
		
//		session.setAttribute("cartArray",cartArray);

		
		return "http://localhost:8080/GameShop/cart2.jsp";
		}

}
