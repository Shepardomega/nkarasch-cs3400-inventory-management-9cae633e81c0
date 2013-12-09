package edu.wmich.cs3400.inventorymanagement;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import edu.wmich.cs3400.inventory.R;
import edu.wmich.cs3400.inventorymanagement.backend.DatabaseHandler;
import edu.wmich.cs3400.inventorymanagement.backend.InventoryItem;
 
public class HomeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
 
		super.onCreate(savedInstanceState);
		
		ActionBarManager.onCreate(this);
			 
        setContentView(R.layout.activity_main);
		DatabaseHandler.init(this);		
		DatabaseHandler.clearDatabase();
		InventoryItem item = new InventoryItem("Ostriches", "Walmart", 527865854560L, 5.25, 7.42, 25, 3, 50, "pounds", 12092013);
		DatabaseHandler.addItem(item);
		InventoryItem retrievedItem = DatabaseHandler.getItem(item.getHiddenId());
		Log.i("name", retrievedItem.getName());
		Log.i("supplier", retrievedItem.getSupplier());
		Log.i("unit", retrievedItem.getUnit());
		Log.i("minQuantity", String.valueOf(retrievedItem.getMinQuantity()));
		Log.i("maxQuantity", String.valueOf(retrievedItem.getMaxQuantity()));
		Log.i("expiration", String.valueOf(retrievedItem.getExpiration()));
		Log.i("publicid", String.valueOf(retrievedItem.getUserDefinedId()));
		Log.i("unit", retrievedItem.getUnit()); 
		Log.i("purchase", String.valueOf(retrievedItem.getPurchasePrice()));
		Log.i("sale", String.valueOf(retrievedItem.getSalePrice()));
		Log.i("id", String.valueOf(item.getHiddenId()));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		ActionBarManager.onCreateOptionsMenu(this, menu);
        
        return super.onCreateOptionsMenu(menu);
	} 
 
    @Override 
    public boolean onOptionsItemSelected(MenuItem item) {
    	return ActionBarManager.switchActivity(this, item.getItemId());
    }
} 






