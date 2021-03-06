package business;

public class BaseProduct extends MenuItem {
	private int id;
	private String name;
	private float price;

	public BaseProduct(int id, String name, float price) {
		this.id = id;
		this.name = name;
		this.price = price;
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
	public float getPrice() {
		return price;
	}

	@Override
	public void setPrice(float price) {
		this.price = price;
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
	public float computePrice() {
		return this.price;
	}

	
}
