package edu.wmich.cs3400.inventorymanagement;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import edu.wmich.cs3400.inventory.R;
import edu.wmich.cs3400.inventorymanagement.backend.DatabaseHandler;
import edu.wmich.cs3400.inventorymanagement.backend.InventoryItem;

public class SimpleManagementActivity extends Activity {
	
	InventoryItem item;
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		ActionBarManager.onCreate(this);
		setContentView(R.layout.activity_simple_management);
		
		item = DatabaseHandler.getItem((Integer) getIntent().getExtras().get("ItemID"));
		
		createTable();
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

	private void createTable() {
		
	/*	TableRow row = new TableRow(this);
		TableLayout tableLayout = new TableLayout(this);

	 	tableLayout.setGravity(Gravity.FILL_HORIZONTAL);
	 	
		row = new TableRow(this);

		TextView text = new TextView(this);
		TextView text2 = new TextView(this);


		tableLayout.addView(row);
		
		Object[] keySet = item.getListProperties().keySet().toArray();
		
		for (int i = 0; i < keySet.length; i++) {
			row = new TableRow(this);
			text = new TextView(this);
			text2 = new TextView(this);
			
			text.setBackgroundColor(Color.BLACK);
			text2.setBackgroundColor(Color.BLACK);
			
			text.setTextColor(Color.WHITE);
			text2.setTextColor(Color.WHITE);
			
			text.setTextSize(40);
			text2.setTextSize(40);
			
			text.setText((String)keySet[i]);
			text2.setText("\t\t\t\t\t\t" + item.getListProperties().get(keySet[i]));
			
			row.addView(text);
			row.addView(text2);
			
			tableLayout.addView(row);
		}*/
		

		setContentView(R.layout.display_item);
		
		final TextView newName = (TextView) findViewById(R.id.display_item_name);
		newName.setText(item.getName());
		
		final TextView newSupplier = (TextView) findViewById(R.id.display_item_supplier);
		newSupplier.setText(item.getSupplier());
		
		final TextView newUnit = (TextView) findViewById(R.id.display_item_unit);
		newUnit.setText(item.getUnit());
		
		final TextView newMinQuantity = (TextView) findViewById(R.id.display_item_min_quantity);
		String doubleToString = Double.toString(item.getMinQuantity());
		newMinQuantity.setText(doubleToString);
		
		final TextView newMaxQuantity = (TextView) findViewById(R.id.display_item_max_quantity);
		String doubleToString2 = Double.toString(item.getMaxQuantity());
		newMaxQuantity.setText(doubleToString2);
		
//		final TextView newExpiration = (TextView) findViewById(R.id.display_item_expiration);
//		newExpiration.setText(item.getExpiration());
		
		final TextView newUserDefinedId = (TextView) findViewById(R.id.display_item_public_id);
		String longToString = Long.toString(item.getUserDefinedId());
		newUserDefinedId.setText(longToString);
		
		final TextView newPurchasePrice = (TextView) findViewById(R.id.display_item_purchase);
		String doubleToString3 = Double.toString(item.getPurchasePrice());
		newPurchasePrice.setText(doubleToString3);
		
		final TextView newSalePrice = (TextView) findViewById(R.id.display_item_sale);
		String doubleToString4 = Double.toString(item.getSalePrice());
		newSalePrice.setText(doubleToString4);
		
		
		//setContentView(tableLayout);
	} 
} 
