package test;

import java.sql.Date;

public class ItemData {

	private int id;
	private String asin;
	private String jan;
	private String nam;
	private int idc;
	private int price;
	private int u_price;
	private int ranck;
	private Date date;
	private String memo;
	private String c_nam;

//	public ItemData(int id, String asin, String nam, int idc, int price,
//			int u_price, int ranck, Date date, String memo) {
//
//		this.id = id;
//		this.asin = asin;
//		this.nam = nam;
//		this.idc = idc;
//		this.price = price;
//		this.u_price = u_price;
//		this.ranck = ranck;
//		this.date = date;
//		this.memo = memo;
//
//	}

	public int getId() {
		return id;
	}

	public String getAsin() {
		return asin;
	}
	
	public String getJan() {
		return jan;
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

	public int getU_price() {
		return u_price;
	}

	public int getRanck() {
		return ranck;
	}

	public Date getDate() {
		return date;
	}

	public String getMemo() {
		return memo;
	}
	
	public String getCnam() {
		return c_nam;
	}

	
	
	
	public void setId(int id) {
		this.id = id;
	}

	public void setAsin(String asin) {
		this.asin = asin;
	}
	
	public void setJan(String jan) {
		this.jan = jan;
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

	public void setU_price(int u_price) {
		this.u_price = u_price;
	}

	public void setRanck(int ranck) {
		this.ranck = ranck;
	}

	public void setString(Date date) {
		this.date = date;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	public void setCnam(String str) {
		this.c_nam = str;
	}

}
