package edu.wmich.cs3400.inventorymanagement;

import android.app.ActionBar;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.SearchView;
import android.widget.TextView;
import edu.wmich.cs3400.inventory.R;

public class ActionBarManager {

	static boolean switchActivity(Activity activity, int actionID) {
				
		switch (actionID) {
		case android.R.id.home:
			activity.startActivity(new Intent(activity.getApplicationContext(),
					HomeActivity.class));
			activity.overridePendingTransition(R.anim.fadein, R.anim.fadeout);
			return true;
		case R.id.action_scan:
			Intent intent = new Intent(activity.getApplicationContext(),
					SimpleManagementActivity.class);
			
			intent.putExtra("ItemID", 1); 
			activity.startActivity(intent);
			activity.overridePendingTransition(R.anim.fadein, R.anim.fadeout);
			return true;
		case R.id.action_search:
			return true;
		case R.id.action_data:
			activity.startActivity(new Intent(activity.getApplicationContext(),
					DataActivity.class));
			activity.overridePendingTransition(R.anim.fadein, R.anim.fadeout);
			return true;
		case R.id.action_setup:
			activity.startActivity(new Intent(activity.getApplicationContext(),
					SetupActivity.class));
			activity.overridePendingTransition(R.anim.fadein, R.anim.fadeout);
			return true;
		default:
			return false;
		}
	}

	static void onCreateOptionsMenu(Activity activity, Menu menu){
        MenuInflater inflater = activity.getMenuInflater();
        inflater.inflate(R.menu.action_bar, menu); 

        // setup search field
        SearchManager searchManager =
               (SearchManager) activity.getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(activity.getComponentName()));
        int id = searchView.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
        TextView textView = (TextView) searchView.findViewById(id);
        textView.setTextColor(Color.WHITE); 
	}
	
	static void onCreate(Activity activity){
		ActionBar actionBar = activity.getActionBar();
		actionBar.setHomeButtonEnabled(true); 
	}
}