package edu.wmich.cs3400.inventorymanagement.backend;



/**
 * Stores data representing the inventory items the entire project is based
 * around.
 */
public class InventoryItem {

	private int hiddenId;
	private String name;
	private String supplier;
	private long userDefinedId;
	private double purchasePrice;
	private double salePrice;;
	private double currentQuantity;
	private double minQuantity;
	private double maxQuantity;
	private String unit;
	private int expiration;

	/**
	 * Constructor for first creation of new items that do not currently exist
	 * in the database.
	 * 
	 * @param name
	 * @param purchasePrice
	 */
	public InventoryItem(String name, String supplier, long userDefinedId, double purchasePrice, double salePrice, double currentQuantity, double minQuantity, double maxQuantity, String unit, int expiration ) {
		this.name = name;
		this.supplier = supplier;
		this.userDefinedId = userDefinedId;
		this.purchasePrice = purchasePrice;
		this.salePrice = salePrice;
		this.currentQuantity = currentQuantity;
		this.minQuantity = minQuantity;
		this.maxQuantity = maxQuantity;
		this.unit = unit;
		this.expiration = expiration;
				
		this.hiddenId = DatabaseHandler.getUniqueKey();
	}

	/**
	 * Full constructor, used for inventory items that have already existed and
	 * do not need a new id.
	 * 
	 * @param hiddenId
	 * @param name
	 * @param purchasePrice
	 */
	public InventoryItem(int hiddenId, String name, String supplier, long userDefinedId, double purchasePrice, double salePrice, double currentQuantity, double minQuantity, double maxQuantity, String unit, int expiration) {
		this.name = name;
		this.supplier = supplier;
		this.userDefinedId = userDefinedId;
		this.purchasePrice = purchasePrice;
		this.salePrice = salePrice;
		this.currentQuantity = currentQuantity;
		this.minQuantity = minQuantity;
		this.maxQuantity = maxQuantity;
		this.unit = unit;
		this.expiration = expiration;
		
		this.hiddenId = hiddenId;
	}

	/**
	 * Empty constructor so manual setting of fields is possible
	 */
	public InventoryItem() {

	}

	public int getHiddenId() {
		return hiddenId;
	}

	public void setHiddenId(int hiddenId) {
		this.hiddenId = hiddenId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public long getUserDefinedId() {
		return userDefinedId;
	}

	public void setUserDefinedId(long userDefinedId) {
		this.userDefinedId = userDefinedId;
	}

	public double getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
	
	public double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(double salePrice) {
		this.salePrice = salePrice;
	}

	public double getCurrentQuantity() {
		return currentQuantity;
	}

	public void setCurrentQuantity(double currentQuantity) {
		this.currentQuantity = currentQuantity;
	}

	public double getMinQuantity() {
		return minQuantity;
	}

	public void setMinQuantity(double minQuantity) {
		this.minQuantity = minQuantity;
	}

	public double getMaxQuantity() {
		return maxQuantity;
	}

	public void setMaxQuantity(double maxQuantity) {
		this.maxQuantity = maxQuantity;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public int getExpiration() {
		return expiration;
	}

	public void setExpiration(int expiration) {
		this.expiration = expiration;
	}
}
