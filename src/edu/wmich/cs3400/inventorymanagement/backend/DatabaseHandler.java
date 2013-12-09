package edu.wmich.cs3400.inventorymanagement.backend;

import java.util.List;

import android.content.Context;
import android.util.Log;

/**
 * Handles a static instance of the "Database" class so it can be accessed from
 * anywhere in the program. If anyone comes up with a better solution than this
 * feel free, I was trying to avoid passing it around as a parameter.
 */

public class DatabaseHandler {

	private static Database db;

	/**
	 * Initializes the Database object "db". Must be called at beginning of
	 * programs execution.
	 * 
	 * @param context
	 *            the "context" of the app, Database needs it
	 */
	public static void init(Context context) {
		db = new Database(context);
	}

	/**
	 * A method to call another method.
	 * This is a sin, open to other options.
	 * 
	 * @param item
	 *            the item to add to the database
	 */
	public static void addItem(InventoryItem item) {
		db.addItem(item);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public static InventoryItem getItem(int id) {

		try {
			return db.getItem(id);
		} catch (Exception e) {
			Log.i("error", String.valueOf(id) + "not found");
			return null;
		}
	}

	public static void updateItem(InventoryItem item) {
		try {
			db.updateItem(item);
		} catch (Exception e) {
			Log.i("error", "can't update item that doesn't exist in database");
		}
	}

	public static void deleteItem(InventoryItem item) {
		try {
			db.deleteItem(item);
		} catch (Exception e) {
			Log.i("error",
					"cannot delete an item that doesn't exist in database");
		}
	}

	public static void listAllData() {

		for (InventoryItem item : db.getAllInventoryItems()) {

		}
	}

	public static List<InventoryItem> getListOfAll() {
		return db.getAllInventoryItems();
	}

	public static void clearDatabase() {
		for (InventoryItem item : db.getAllInventoryItems()) {
			db.deleteItem(item);
		}
	}

	public static int getUniqueKey() {
		return db.getMaxId() + 1;
	}
}
