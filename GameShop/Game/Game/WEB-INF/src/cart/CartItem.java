package cart;

import java.io.Serializable;
import java.sql.Date;

public class CartItem implements Serializable{

		private int id;
		private String nam;
		private int idc;
		private int price;
		private String c_nam;

		public CartItem() {
	
		}
		
		public CartItem(int id, String nam, int pr){
			this.id = id;
			this.nam = nam;
			this.price = pr;
		}

		public int getId() {
			return id;
		}


		public String getNam() {
			return nam;
		}

		public int getIdc() {
			return idc;
		}

		public int getPrice() {
			return price;
		}

		public String getCnam() {
			return c_nam;
		}

		
		
		public void setId(int id) {
			this.id = id;
		}


		public void setNam(String nam) {
			this.nam = nam;
		}

		public void setIdc(int idc) {
			this.idc = idc;
		}

		public void setPrice(int price) {
			this.price = price;
		}
		
		public void setCnam(String str) {
			this.c_nam = str;
		}

	
}
