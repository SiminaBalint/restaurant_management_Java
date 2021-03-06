package business;



public abstract class MenuItem {
	
	public MenuItem(){}
	
	public abstract void setId(int id);
	public abstract int getId();
	
	public abstract void setName(String name);
	public abstract String getName();
	
	public abstract void setPrice(float price);
	public abstract float getPrice();
	
	public abstract float computePrice();
}
