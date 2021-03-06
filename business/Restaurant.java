package business;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import data.FileWriter;

public class Restaurant implements IRestaurantProcessing {
	static HashMap<Order, ArrayList<MenuItem>> hash;
	static List<MenuItem> items = new ArrayList<MenuItem>();

	/* aici se creeaza constructorul pentru clasa Restaurant */
	public Restaurant() {
		hash = new HashMap<Order, ArrayList<MenuItem>>();

	}

	public List<MenuItem> getMenu() {
		return items;
	}

	public List<MenuItem> getOrderItems(Order o) {
		return hash.get(o);
	}

	/*
	 * in acesta metoda se realizeaza adaugarea unui obiect de tipul Baseproduct sau
	 * CompositeProduct in tabelul pentru elementele dintr-un meniu
	 */
	public void insertMenuItem(int id, String name, float price, ArrayList<MenuItem> item) {
		if (item.size() == 0) {
			BaseProduct product = new BaseProduct(id, name, price);
			items.add(product);
		} else {
			CompositeProduct product = new CompositeProduct(id, name, price, item);
			items.add(product);
		}

	}

	/*
	 * in acesta metoda se realizeaza crearea unei comenzi de la o anumita masa
	 */
	public Order createOrder(int id, int table, Date d, ArrayList<MenuItem> item) {

		Order o = new Order(id, d, table);
		hash.put(o, item);
		return o;

	}

	/*
	 * in acesta metoda se realizeaza editarea unui obiect de tipul Baseproduct sau
	 * CompositeProduct din tabelul pentru elementele dintr-un meniu
	 */
	public void editMenuItem(int id, String name, float price, ArrayList<MenuItem> item) {
		if (item.size() == 0) {
			for (MenuItem m : items) {
				if (m.getId() == id) {
					m.setName(name);
					m.setPrice(price);
				}
			}
		} else {
			for (MenuItem m : items) {
				if (m.getId() == id) {
					m.setName(name);
					((CompositeProduct) m).setMenu(item);
					m.setPrice(price);
				}
			}
		}

	}

	/*
	 * in acesta metoda se realizeaza stergerea unui obiect de tipul Baseproduct sau
	 * CompositeProduct din tabelul pentru elementele dintr-un meniu
	 */
	public void delete(int id) {
		MenuItem menuItem = null;
		for (MenuItem m : items) {
			if (m.getId() == id) {
				menuItem = m;
			}
		}
		items.remove(menuItem);

	}

	/* aici se calculeaza costul total al unei comenzi */
	public float computePrice(Order o) {
		float total = 0;
		ArrayList<MenuItem> menu = hash.get(o);
		for (MenuItem m : menu) {
			total += m.getPrice();
		}
		return total;
	}

	/* aici se genereaza, in format .txt, o factura pentru o anumita comanda */
	public void computeBill(Order o, float price) {

		try {
			FileWriter write = new FileWriter();
			write.createBill(o, price);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
