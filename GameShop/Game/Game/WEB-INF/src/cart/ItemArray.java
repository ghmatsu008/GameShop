package cart;

import java.util.ArrayList;

public class ItemArray {
	
	protected ArrayList mList;
	
	
	public ArrayList getList(){
		return this.mList;
	}
	
	public void setList(ArrayList list){
		this.mList = list;
	}
	
	public void addItem(CartItem item){
		mList.add(item);
	}
	
	public CartItem getItem(int index){
		return (CartItem)mList.get(index);
	}
	
	public int getSize(){
		return mList.size();
	}
	public int getTotalPrice(){
		int totalPrice = 0;
		
		for(int i=0; i<mList.size(); i++ ){
			totalPrice += ((CartItem)mList.get(i)).getPrice();
		}
		return totalPrice;
	}
	
}
