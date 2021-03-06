package business;

import java.util.Date;

public class Order {
	private int id;
	private Date date;
	private int table;
	
	public Order(int id, Date date, int table) {
		super();
		this.id=id;
		this.date=date;
		this.table=table;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getTable() {
		return table;
	}
	public void setTable(int table) {
		this.table = table;
	}
	@Override
	public int hashCode() {
		final int prime = 41;
		int result = 1;
		result = prime * result + date.hashCode();
		result = prime * result + id;
		result = prime * result + table;
		return result;
	}
}
