package cart;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CommandInitCart implements Command{

	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		
		HttpSession session = request.getSession(true);
		Cart cart = (Cart)session.getAttribute("cart");
		
		if(cart != null){
			cart = new Cart();
			cart.setList(new ArrayList());
		}
		
		session.setAttribute("cart",cart);
		return "/cart.jsp";
		
		
	}
}
