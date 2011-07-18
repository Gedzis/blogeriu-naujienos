package net.gedzis.blogeriunaujienos;

import static net.gedzis.blogeriunaujienos.common.Constants.SELECTED_ITEM;
import net.gedzis.blogeriunaujienos.common.MainData;
import net.gedzis.blogeriunaujienos.model.WebSite;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class AboutTab extends Activity {
	private WebSite currentWebsite;
	public MainData data;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about_tab);
		data = MainData.getInstance();
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			int itemNumber = extras.getInt(SELECTED_ITEM);

			currentWebsite = data.getWebsites().get(itemNumber);
		}
		TextView textview = (TextView) findViewById(R.id.about_text);
		textview.setText(currentWebsite.getDescription());

	}
}