package net.gedzis.blogeriunaujienos;

import static net.gedzis.blogeriunaujienos.common.Constants.FEED_CONTENT;
import static net.gedzis.blogeriunaujienos.common.Constants.FEED_TITLE;
import static net.gedzis.blogeriunaujienos.common.Constants.FEED_URL;
import static net.gedzis.blogeriunaujienos.common.Constants.SELECTED_ITEM;

import java.util.ArrayList;
import java.util.List;

import net.gedzis.blogeriunaujienos.common.MainData;
import net.gedzis.blogeriunaujienos.model.FeedItem;
import net.gedzis.blogeriunaujienos.model.WebSite;
import net.gedzis.blogeriunaujienos.rss.BaseFeedParser;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FeedListTab extends ListActivity implements Runnable {
	private List<FeedItem> messages;
	private WebSite currentWebsite;
	private ProgressDialog pd;
	private List<String> titles;
	public MainData data;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.feed_items);
		Bundle extras = getIntent().getExtras();
		data = MainData.getInstance();
		if (extras != null) {
			int itemNumber = extras.getInt(SELECTED_ITEM);
			currentWebsite = data.getWebsites().get(itemNumber);
		}
		this.setTitle(currentWebsite.getTitle());
		loadFeed();
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Intent i = new Intent(this, ActicleViewActivity.class);
		i.putExtra(FEED_CONTENT, messages.get(position).getDescription());
		i.putExtra(FEED_URL, messages.get(position).getLink().toString());
		i.putExtra(FEED_TITLE, messages.get(position).getTitle());
		startActivity(i);
	}

	private void loadFeed() {
		if (!isNetworkAvailable()) {
			new AlertDialog.Builder(this).setMessage(
					R.string.error_no_internet_connection).setTitle(
					R.string.alert_dialog_title).setCancelable(true)
					.setNeutralButton(android.R.string.cancel,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
								}
							}).show();
		} else {
			pd = ProgressDialog.show(this, this
					.getString(R.string.dialog_loading_dialog_title), this
					.getString(R.string.dialog_loading__dialog_message), true,
					false);
			Thread thread = new Thread(this);
			thread.start();

		}
	}

	public boolean isNetworkAvailable() {
		ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager
				.getActiveNetworkInfo();
		return activeNetworkInfo != null;
	}

	public void displayList() {
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				R.layout.feed_item_row, R.id.article_caption, titles);
		this.setListAdapter(adapter);
	}

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			pd.dismiss();
			displayList();
		}
	};

	public void run() {
		try {
			BaseFeedParser parser = new BaseFeedParser(currentWebsite
					.getFeedURL());
			messages = parser.parse();
			titles = new ArrayList<String>(messages.size());
			for (FeedItem msg : messages) {
				titles.add(msg.getTitle());
			}
			handler.sendEmptyMessage(0);
		} catch (Throwable t) {
			Log.e("AndroidNews", t.getMessage(), t);
		}
		// TODO Auto-generated method stub

	}

}