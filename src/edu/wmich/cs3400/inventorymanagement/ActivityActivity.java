package edu.wmich.cs3400.inventorymanagement;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ImageView;
import edu.wmich.cs3400.inventory.R;
import edu.wmich.cs3400.inventorymanagement.backend.DatabaseHandler;
import edu.wmich.cs3400.inventorymanagement.backend.InventoryItem;


public class ActivityActivity extends Activity {
	InventoryItem item;
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		ActionBarManager.onCreate(this);
		setContentView(R.layout.activity_activity);
		
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
