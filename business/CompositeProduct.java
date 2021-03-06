package business;

import java.util.ArrayList;

public class CompositeProduct extends MenuItem {
	private int id;
	private String name;
	private float price;
	private ArrayList<MenuItem> menu;
	
	public CompositeProduct(int id, String name,float price, ArrayList<MenuItem> menu) {
		this.id=id;
		this.name=name;
		this.price=price;
		this.menu=menu;
	}
	
	@Override
	public int getId() {
		return id;
	}
	@Override
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String getName() {
		return name;
	}
	@Override
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public float getPrice() {
		return price;
	}@Override
	public void setPrice(float price) {
		this.price = price;
	}
	public ArrayList<MenuItem> getMenu() {
		return menu;
	}
	public void setMenu(ArrayList<MenuItem> menu) {
		this.menu = menu;
	}
	@Override
	public float computePrice() {
        this.price=0;
        for(MenuItem m:menu)
        {
            this.price+=m.getPrice();
        }
        return this.price;
    }
}
