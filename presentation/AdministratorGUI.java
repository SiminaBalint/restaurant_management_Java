package presentation;

import business.CompositeProduct;
import business.Restaurant;
import business.MenuItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AdministratorGUI {
	private JFrame adminFrame;
	JTable table = new JTable();
	JTextField text1 = new JTextField(25);
	JTextField text2 = new JTextField(25);
	JTextField text3 = new JTextField(25);
	JTextField text4 = new JTextField(25);
	Restaurant restaurant = new Restaurant();

	public AdministratorGUI() {
		interfaceStart();
		adminFrame.setVisible(true);
	}

	private void interfaceStart() {
		adminFrame = new JFrame("Administrator");
		adminFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		adminFrame.setLayout(new FlowLayout());
		adminFrame.setSize(800, 400);

		JButton addMenuItem = new JButton("Add menu item");
		JButton editMenuItem = new JButton("Edit menu item");
		JButton delete = new JButton("Delete");

		JLabel l0 = new JLabel("*Note: in order to add composite products you must respect the following format: a->b");
		JLabel l1 = new JLabel("ID:");
		JLabel l2 = new JLabel("NAME:");
		JLabel l3 = new JLabel("PRICE: ");
		JLabel l4 = new JLabel("IDS: ");
		JLabel l5 = new JLabel("Administrator");
		l5.setFont(new Font("Calibri", Font.BOLD, 35));

		JPanel panel0 = new JPanel();
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		JPanel panel4 = new JPanel();
		JPanel panel5 = new JPanel();
		JPanel panel6 = new JPanel();
		JPanel p = new JPanel();

		table = createTable(restaurant.getMenu());
		table.setVisible(true);
		table.revalidate();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		JScrollPane sp = new JScrollPane(table);
		panel6.add(sp);
		panel6.revalidate();

		panel1.add(l5);
		panel0.add(l0);
		panel2.add(l1);
		panel2.add(text1);
		panel2.add(addMenuItem);
		panel3.add(l2);
		panel3.add(text2);
		panel3.add(editMenuItem);
		panel4.add(l3);
		panel4.add(text3);
		panel4.add(delete);
		
		panel5.add(l4);
		panel5.add(text4);
		

		p.add(panel0);
		p.add(panel1);
		p.add(panel2);
		p.add(panel3);
		p.add(panel4);
		p.add(panel5);
		p.add(panel6);

		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));

		addMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(text1.getText());
				String name = text2.getText();
				float price = Float.parseFloat(text3.getText());
				String ids = text4.getText();
				ArrayList<MenuItem> menuItem = new ArrayList<MenuItem>();
				panel6.removeAll();
				if (ids.equals("")) {
					restaurant.insertMenuItem(id, name, price, menuItem);
				} else {
					String[] s = ids.split("->");

					for (String s1 : s) {

						int x = Integer.parseInt(s1);

						for (MenuItem m : restaurant.getMenu()) {
							if (m.getId() == x) {
								menuItem.add(m);
							}
						}
					}
					restaurant.insertMenuItem(id, name, price, menuItem);
				}
				table = createTable(restaurant.getMenu());
				table.setVisible(true);
				table.revalidate();
				table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
				JScrollPane sp = new JScrollPane(table);
				panel6.add(sp);
				panel6.revalidate();
			}
		});

		
		editMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(text1.getText());
				String name = text2.getText();
				float price = Float.parseFloat(text3.getText());
				ArrayList<MenuItem> menuItem = new ArrayList<MenuItem>();
				String ids = text4.getText();
				panel6.removeAll();
				if (ids.equals("")) {
					restaurant.editMenuItem(id, name, price, menuItem);
				} else {
					String[] s = ids.split("->");

					for (String s1 : s) {

						int x = Integer.parseInt(s1);

						for (MenuItem m : restaurant.getMenu()) {
							if (m.getId() == x) {
								menuItem.add(m);
							}
						}
					}
					restaurant.editMenuItem(id, name, price, menuItem);
				}
				table = createTable(restaurant.getMenu());
				table.setVisible(true);
				table.revalidate();
				table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
				JScrollPane sp = new JScrollPane(table);
				panel6.add(sp);
				panel6.revalidate();
			}
		});

		

		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(text1.getText());
				panel6.removeAll();
				restaurant.delete(id);
				table = createTable(restaurant.getMenu());
				table.setVisible(true);
				table.revalidate();
				table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
				JScrollPane sp = new JScrollPane(table);
				panel6.add(sp);
				panel6.revalidate();
			}
		});

		adminFrame.setContentPane(p);
		adminFrame.setVisible(true);

	}

	public JTable createTable(List<MenuItem> item) {
		String[] columns = { "ID", "Name", "Price", "Items IDS" };
		String[][] data = new String[item.size()][4];
		int i = 0;
		for (MenuItem m : item) {
			data[i][0] = m.getId() + "";
			data[i][1] = m.getName();
			data[i][2] = m.getPrice() + "";
			data[i][3] = "";
			if (m instanceof CompositeProduct) {
				for (MenuItem m1 : ((CompositeProduct) m).getMenu()) {
					data[i][3] += m1.getId() + "->";
				}
			}
			i++;

		}
		return new JTable(data, columns);

	}
}
