package net.gedzis.blogeriunaujienos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.gedzis.blogeriunaujienos.common.MainData;
import net.gedzis.blogeriunaujienos.model.WebSite;
import android.app.ListActivity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class BlogeriuNaujienos extends ListActivity {
	public MainData data;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		data = MainData.getInstance();
	}

	/** Įkeliame tinklapius. */
	public void loadWebsites() {
		List<String> items = new ArrayList<String>();
		for (Iterator<WebSite> it = data.getWebsites().iterator(); it.hasNext();) {
			WebSite website = it.next();
			items.add(website.getTitle());
		}
		ArrayAdapter<String> websitesList = new ArrayAdapter<String>(this,
				R.layout.website_row, R.id.website_caption, items);
		setListAdapter(websitesList);
	}

	public boolean isNetworkAvailable() {
		ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager
				.getActiveNetworkInfo();
		return activeNetworkInfo != null;
	}
}