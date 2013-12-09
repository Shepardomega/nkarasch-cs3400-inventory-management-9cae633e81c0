package edu.wmich.cs3400.inventorymanagement;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import edu.wmich.cs3400.inventory.R;
import edu.wmich.cs3400.inventorymanagement.backend.InventoryItem;

public class DataActivity extends Activity {
	
	InventoryItem item;
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		ActionBarManager.onCreate(this);
		setContentView(R.layout.activity_data);
		
		Button profitbtn = (Button) findViewById(R.id.button1);
		profitbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), ProfitMargin.class);
                startActivityForResult(myIntent, 0);
            }

        });
		Button itemgraph = (Button) findViewById(R.id.button2);
		itemgraph.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), ItemGraphActivity.class);
                startActivityForResult(myIntent, 0);
            }

        });
        Button alert = (Button) findViewById(R.id.button3);
        alert.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), AlertActivity.class);
                startActivityForResult(myIntent, 0);
            }

        });
        Button activity1 = (Button) findViewById(R.id.button4);
        activity1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), ActivityActivity.class);
                startActivityForResult(myIntent, 0);
            }

        });
		
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
