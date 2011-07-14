package net.gedzis.blogeriunaujienos;

import static net.gedzis.blogeriunaujienos.common.Constants.SELECTED_ITEM;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.gedzis.blogeriunaujienos.common.MainData;
import net.gedzis.blogeriunaujienos.model.WebSite;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class BlogeriuNaujienos extends ListActivity {
	public MainData data;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		data = MainData.getInstance();
		ArrayAdapter<String> websitesList = new ArrayAdapter<String>(this,
				R.layout.website_row, R.id.website_caption, loadWebsites());
		setListAdapter(websitesList);
	}

	/** Įkeliame tinklapius. */
	public List<String> loadWebsites() {
		List<String> websites = new ArrayList<String>();
		for (Iterator<WebSite> it = data.getWebsites().iterator(); it.hasNext();) {
			WebSite website = it.next();
			websites.add(website.getTitle());
		}
		return websites;
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Intent i = new Intent(this, WebsiteTabActivity.class);
		i.putExtra(SELECTED_ITEM, position);
		startActivity(i);
	}

	public boolean isNetworkAvailable() {
		ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager
				.getActiveNetworkInfo();
		return activeNetworkInfo != null;
	}
}