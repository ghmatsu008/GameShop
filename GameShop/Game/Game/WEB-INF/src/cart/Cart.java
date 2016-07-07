package cart;

import test.*;
import java.util.ArrayList;
import java.util.List;

public class Cart extends ItemArray{
		
		public Cart(){}
		
		
		public void removeItem(int num){
			mList.remove(num);
		}
		
		
		public int getTotalPrice(){
			int totalPrice = 0;
			
			for(int i=0; i<mList.size(); i++ ){
				totalPrice += ((CartItem)mList.get(i)).getPrice();
			}
			return totalPrice;
		}

}
