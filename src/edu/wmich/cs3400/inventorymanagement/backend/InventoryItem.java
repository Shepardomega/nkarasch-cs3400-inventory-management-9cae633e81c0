package edu.wmich.cs3400.inventorymanagement.backend;


/**
 * Stores data representing the inventory items the entire project is based
 * around.
 */
public class InventoryItem {

	private String name;
	private double price;
	private int id;

	/**
	 * Constructor for first creation of new items that do not currently exist
	 * in the database.
	 * 
	 * @param name
	 * @param price
	 */
	public InventoryItem(String name, double price) {
		this.name = name;
		this.price = price;
		this.id = DatabaseHandler.getUniqueKey();
	}

	/**
	 * Full constructor, used for inventory items that have already existed and
	 * do not need a new id.
	 * 
	 * @param id
	 * @param name
	 * @param price
	 */
	public InventoryItem(int id, String name, double price) {
		this.name = name;
		this.price = price;
		this.id = id;
	}

	/**
	 * Empty constructor so manual setting of fields is possible
	 */
	public InventoryItem() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
