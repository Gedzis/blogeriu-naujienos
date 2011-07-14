package net.gedzis.blogeriunaujienos;

import static net.gedzis.blogeriunaujienos.common.Constants.SELECTED_ITEM;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

public class WebsiteTabActivity extends TabActivity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.website_tab_page);
		Bundle extras = getIntent().getExtras();
		int itemNumber = 0;
		if (extras != null) {
			itemNumber = extras.getInt(SELECTED_ITEM);
		}
		Resources res = getResources(); // Resource object to get Drawables
		TabHost tabHost = getTabHost(); // The activity TabHost
		TabHost.TabSpec spec; // Resusable TabSpec for each tab
		Intent intent; // Reusable Intent for each tab
		// Create an Intent to launch an Activity for the tab (to be reused)
		intent = new Intent().setClass(this, FeedListTab.class);
		intent.putExtra(SELECTED_ITEM, itemNumber);

		// Initialize a TabSpec for each tab and add it to the TabHost
		spec = tabHost.newTabSpec("feed").setIndicator(
				this.getString(R.string.tab_dialog_feed_tab),
				res.getDrawable(R.drawable.ic_tab_rss)).setContent(intent);

		tabHost.addTab(spec);

		// Do the same for the other tabs
		intent = new Intent().setClass(this, AboutTab.class);
		intent.putExtra(SELECTED_ITEM, itemNumber);
		spec = tabHost.newTabSpec("about").setIndicator(
				this.getString(R.string.tab_dialog_about_tab),
				res.getDrawable(R.drawable.ic_tab_info)).setContent(intent);
		tabHost.addTab(spec);
		tabHost.setCurrentTab(2);
	}

}
