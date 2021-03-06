package business;

import java.util.ArrayList;
import java.util.Date;

public interface IRestaurantProcessing {
	
	public void insertMenuItem(int id, String nume, float price, ArrayList<MenuItem> menuItem);
    public Order createOrder(int id, int table, Date data, ArrayList<MenuItem> menuItem);
    public void editMenuItem(int id, String nume, float price,ArrayList<MenuItem> menuItem);
    public void delete(int id);
    public void computeBill(Order o, float price);
}
