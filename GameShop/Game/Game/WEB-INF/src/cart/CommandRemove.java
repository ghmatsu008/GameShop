package cart;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CommandRemove implements Command{
	
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		
		HttpSession session = request.getSession(true);
		ItemDao dao = new ItemDao();
		Cart cart = (Cart)session.getAttribute("cart");
		
		String input = request.getParameter("index");
		String input2 = request.getParameter("log_id");
		int index = Integer.parseInt(input);
		int log_id = Integer.parseInt(input2);
	System.out.println(index+"remo");
//		cart.removeItem(index);
		dao.remove(log_id,index);
		
		return "http://localhost:8080/GameShop/cart2.jsp";
	}

}
