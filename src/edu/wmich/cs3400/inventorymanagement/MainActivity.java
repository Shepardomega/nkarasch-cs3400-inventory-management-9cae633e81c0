package edu.wmich.cs3400.inventorymanagement;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.Window;
import android.view.WindowManager;
import edu.wmich.cs3400.inventory.R;
import edu.wmich.cs3400.inventorymanagement.backend.DatabaseHandler;
import edu.wmich.cs3400.inventorymanagement.backend.InventoryItem;
import edu.wmich.cs3400.inventorymanagement.backend.PDFGenerator;

public class MainActivity extends Activity {

	/**
	 * oncreate is kind of a constructor for anyone confused
	 * 
	 * nothing in here is really supposed to be final, it's just sloppy unit testing
	 */
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);
         
		DatabaseHandler.init(this);
		
		//this stuff is pretty much just weak unit tests
		DatabaseHandler.clearDatabase();
		
		DatabaseHandler.addItem(new InventoryItem("chicken", 5.50));
		DatabaseHandler.addItem(new InventoryItem("beer", 5.50));
		DatabaseHandler.addItem(new InventoryItem("hot dawgs", 5.50));
		
		InventoryItem item = DatabaseHandler.getItem(1);
		Log.i(item.getName(), String.valueOf(item.getPrice()));
		item.setName("steak");
		item.setPrice(1.2535);
		
		DatabaseHandler.updateItem(item);
		PDFGenerator.generatePDF(this, DatabaseHandler.getListOfAll(), false );
		DatabaseHandler.listAllData();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.layout.options, menu);
 
        return super.onCreateOptionsMenu(menu);
	}
}
