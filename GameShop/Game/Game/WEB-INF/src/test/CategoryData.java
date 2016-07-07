package test;

public class CategoryData {

	private short id;
	private String name;
	
	public CategoryData( short id , String name ){
		
		this.id = id;
		this.name = name;
		
	}

	public short getid(){
		return this.id;
	}
	
	public String getname(){
		return this.name;
	}
  
}
