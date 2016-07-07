package cart;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Hashtable;

public class MainController extends HttpServlet{
	
	private Hashtable mCommands;
	
	private String mCommandsTable[][] = {
			{"init","CommandInitCart"},
			{"view", "CommandView"},
			{"add", "CommandAdd"},
			{"buy", "CommandBuy"},
			{"remove", "CommandRemove"}
	};
	
	
	public void init() throws ServletException {
		
		ItemArray array = new ItemDao().findAll();
		
		ServletContext application = getServletContext();
		application.setAttribute("array", array);
		
		mCommands = new Hashtable();
		for(int i=0; i < mCommandsTable.length; i++){
			mCommands.put(mCommandsTable[i][0],mCommandsTable[i][1]);
		}	
	}
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doPost(request, response);
	}
	
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ItemDao dao = new ItemDao(); 
		String action = request.getParameter("action");
		
		if(action == null) action="init";

		String className = (String)mCommands.get(action);
		Command command;
		
		try{
			
			command = (Command)(Class.forName("cart."+className).newInstance());
			
			String url = command.execute(request, response);
			ServletContext context = getServletContext();
			response.sendRedirect(url);
//			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
//			dispatcher.forward(request, response);
			
		}catch(InstantiationException e){
			e.printStackTrace();
		}catch(IllegalAccessException e){
			e.printStackTrace();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
	}
	

}
