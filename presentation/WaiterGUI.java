package presentation;



import business.Order;
import business.Restaurant;
import business.MenuItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WaiterGUI {

    private List<Order> orders= new ArrayList<Order>();
    private Restaurant restaurant= new Restaurant();
 

    private JFrame waiterFrame;
    JTable table=new JTable();
   
    JTextField text1= new JTextField(25);
    JTextField text2= new JTextField(25);
    JTextField text3= new JTextField(25);


    public WaiterGUI()
    {
        interfaceStart();
        waiterFrame.setVisible(true);
    }

    private void interfaceStart()
    {
        waiterFrame= new JFrame("Waiter");
        waiterFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        waiterFrame.setLayout(new FlowLayout());
		waiterFrame.setSize(800, 400);

        JButton addOrder= new JButton("Add Order");
        JButton generateBill= new JButton("Generate Bill");
        JButton calculatePrice= new JButton("Calculate Price");

        JLabel l0=new JLabel("*Note: in order to add more order items you must respect the following format: a->b");
        JLabel l1= new JLabel("ID: ");
        JLabel l2= new JLabel("TABLE NUMBER: ");
        JLabel l3= new JLabel("ORDER ITEM ID'S: ");
        JLabel l4= new JLabel("Waiter");
        l4.setFont(new Font("Calibri", Font.BOLD, 35));

        JPanel panel0 = new JPanel();
        JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		JPanel panel4 = new JPanel();
		JPanel panel6 = new JPanel();
		JPanel p = new JPanel();
		
		table = createTable();
        table.setVisible(true);
        table.revalidate();
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JScrollPane sp = new JScrollPane(table);
        panel6.add(sp);
        panel6.revalidate();
		
        
		panel1.add(l4);
		panel0.add(l0);
		panel2.add(l1);
		panel2.add(text1);
		panel2.add(addOrder);
		panel3.add(l3);
		panel3.add(text2);
		panel3.add(generateBill);
		panel4.add(l2);
		panel4.add(text3);
		panel4.add(calculatePrice);
		
		p.add(panel0);
		p.add(panel1);
		p.add(panel2);
		p.add(panel4);
		p.add(panel3);
		p.add(panel6);
		
		
		
        
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));

        addOrder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                int id= Integer.parseInt(text1.getText());
                int tableId= Integer.parseInt(text3.getText());
                Date data = new Date(System.currentTimeMillis());
                String items = text2.getText();
                String[] str = items.split("->");

                ArrayList<MenuItem> menu= new ArrayList<MenuItem>();
                for(String s:str)
                {
                    int aux=Integer.parseInt(s);
                    for(MenuItem m:restaurant.getMenu())
                    {
                        if(aux==m.getId())
                        {
                            menu.add(m);
                        }
                    }
                }
                orders.add(restaurant.createOrder(id, tableId, data, menu));
                table = createTable();
                table.setVisible(true);
                table.repaint();
                table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
                JScrollPane sp = new JScrollPane(table);
                panel6.removeAll();
                panel6.add(sp);
                panel6.revalidate();
            }
        });


        generateBill.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                int id= Integer.parseInt(text1.getText());
                Order or = null;
                for(Order o: orders)
                {
                    if(o.getId()==id)
                    {
                        or = o;
                        break;
                    }
                }
                float price=restaurant.computePrice(or);
                restaurant.computeBill(or,price);
            }
        });

        calculatePrice.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                int id= Integer.parseInt(text1.getText());
                Order or = null;
                for(Order o: orders)
                {
                    if(o.getId()==id)
                    {
                        or = o;
                        break;
                    }
                }

                JOptionPane.showMessageDialog(null, "The total price for the order #"+id+" is "+restaurant.computePrice(or)+"$");
            }
        });


        waiterFrame.setContentPane(p);
        waiterFrame.setVisible(true);

    }

    public JTable createTable ()
    {
        String [] columns= {"ID", "Date","Table", "Items"};
        String[][] data= new String[orders.size()][4];
        int i=0;
        for(Order o:orders)
        {
            data[i][0]=o.getId()+"";
            data[i][1]=o.getDate().toString();
            data[i][2]=o.getTable()+"";
            data[i][3]="";

            for(MenuItem m : restaurant.getOrderItems(o))
            {
                data[i][3] +=m.getId() + "->";
            }

            i++;

        }
        return new JTable(data,columns);

    }
  

}

