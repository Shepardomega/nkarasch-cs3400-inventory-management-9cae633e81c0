package edu.wmich.cs3400.inventorymanagement.backend;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Handles all direct interaction with the SQLite database, no direct database
 * interaction will occur elsewhere in program.
 */
public class Database extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;

	private static final String DATABASE_NAME = "itemManager";

	private static final String TABLE_INVENTORY = "items";

	private static final String KEY_ID = "id";
	private static final String KEY_NAME = "name";
	private static final String KEY_PRICE = "price";

	public Database(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite
	 * .SQLiteDatabase)
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_INVENTORY_TABLE = "CREATE TABLE " + TABLE_INVENTORY + "("
				+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
				+ KEY_PRICE + " TEXT" + ")";

		db.execSQL(CREATE_INVENTORY_TABLE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite
	 * .SQLiteDatabase, int, int)
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		// drop older table if it exists
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_INVENTORY);

		// recreate table
		onCreate(db);
	}

	/**
	 * Inserts InventoryItem's values into database
	 * 
	 * @param item
	 *            inventory item to insert into the database
	 */
	public void addItem(InventoryItem item) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_NAME, item.getName());
		values.put(KEY_PRICE, item.getPrice());

		// Inserting Row
		db.insert(TABLE_INVENTORY, null, values);
		db.close(); // Closing database connection
	}

	/**
	 * Queries based on items id. This is the same id used during QR code
	 * generation and is generated based on the maximum id found during
	 * InventoryItem creation.
	 * 
	 * @param id
	 *            the id of the item to query
	 * @return the queried item in InventoryItem form
	 */
	public InventoryItem getItem(int id) {

		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_INVENTORY, new String[] { KEY_ID,
				KEY_NAME, KEY_PRICE }, KEY_ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		InventoryItem item = new InventoryItem(Integer.parseInt(cursor
				.getString(0)), cursor.getString(1), Double.parseDouble(cursor
				.getString(2)));

		return item;
	}

	/**
	 * Generates an InventoryItem for each item in the database, adds them to an
	 * ArrayList and returns it.
	 * 
	 * @return list of all items in database in their java object format
	 */
	public List<InventoryItem> getAllInventoryItems() {

		List<InventoryItem> itemList = new ArrayList<InventoryItem>();

		String selectQuery = "SELECT  * FROM " + TABLE_INVENTORY;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				InventoryItem item = new InventoryItem();
				item.setId(Integer.valueOf(cursor.getString(0)));
				item.setName(cursor.getString(1));
				item.setPrice(Double.valueOf(cursor.getString(2)));
				// Adding contact to list
				itemList.add(item);
			} while (cursor.moveToNext());
		}

		return itemList;
	}

	/**
	 * Counts number of items in the entire database.
	 * 
	 * @return the number of items
	 */
	public int getInventoryItemCount() {

		try {
			String countQuery = "SELECT  * FROM " + TABLE_INVENTORY;
			SQLiteDatabase db = this.getReadableDatabase();
			Cursor cursor = db.rawQuery(countQuery, null);
			int count = cursor.getCount();
			cursor.close();

			return count;
		} catch (Exception e) {
			Log.i("exception", e.getMessage());
			return 0;
		}
	}

	/**
	 * Updates database values of the item passed in.
	 * 
	 * @param item
	 *            to update
	 * @return the number of rows affected by the update
	 */
	public int updateItem(InventoryItem item) {

		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_NAME, item.getName());
		values.put(KEY_PRICE, item.getPrice());

		return db.update(TABLE_INVENTORY, values, KEY_ID + " = ?",
				new String[] { String.valueOf(item.getId()) });

	}

	/**
	 * Deletes item passed in from database.
	 * 
	 * @param item
	 *            item to delete from database
	 */
	public void deleteItem(InventoryItem item) {

		SQLiteDatabase db = this.getWritableDatabase();

		db.delete(TABLE_INVENTORY, KEY_ID + "=" + String.valueOf(item.getId()),
				null);
		db.close();

	}

	/**
	 * Calculates and returns the highest ID in the database, used and iterated
	 * on creation of new inventory items.
	 * 
	 * @return highest id number in database, 0 if empty
	 */
	public int getMaxId() {
		String query = "SELECT MAX(id) AS max_id FROM " + TABLE_INVENTORY;

		try {
			SQLiteDatabase db = this.getReadableDatabase();
			Cursor cursor = db.rawQuery(query, null);

			int id = 0;
			if (cursor.moveToFirst()) {
				do {
					id = cursor.getInt(0);
				} while (cursor.moveToNext());
			}

			cursor.close();

			return id;
		} catch (Exception e) {
			return 0;
		}
	}
}