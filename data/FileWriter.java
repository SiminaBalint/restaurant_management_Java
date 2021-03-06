package data;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import business.Order;

public class FileWriter {

	public void createBill(Order o, float price) {
		File file = new File("Order" + o.getId() + ".txt");
		if (!file.exists()) {
			try {
				file.createNewFile();
				PrintWriter printWriter = new PrintWriter(file);
				printWriter.println("Order " + o.getId() + " has been created at the date " + o.getDate()
						+ " for the table with the number " + o.getTable() + " and is worth " + price + "$");
				printWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
