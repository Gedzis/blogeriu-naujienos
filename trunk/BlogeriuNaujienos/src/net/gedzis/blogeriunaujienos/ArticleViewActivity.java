package net.gedzis.blogeriunaujienos;

import static net.gedzis.blogeriunaujienos.common.Constants.FEED_CONTENT;
import static net.gedzis.blogeriunaujienos.common.Constants.FEED_TITLE;
import static net.gedzis.blogeriunaujienos.common.Constants.FEED_URL;
import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.FrameLayout;

public class ArticleViewActivity extends Activity {

	private WebView myWebView;

	private static final FrameLayout.LayoutParams ZOOM_PARAMS =

	new FrameLayout.LayoutParams(

	ViewGroup.LayoutParams.FILL_PARENT,

	ViewGroup.LayoutParams.WRAP_CONTENT,

	Gravity.BOTTOM);

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		this.setContentView(R.layout.article_view);

		this.myWebView = (WebView) this.findViewById(R.id.webView);

		FrameLayout mContentView = (FrameLayout) getWindow().

		getDecorView().findViewById(android.R.id.content);

		final View zoom = this.myWebView.getZoomControls();

		mContentView.addView(zoom, ZOOM_PARAMS);

		zoom.setVisibility(View.GONE);

		Bundle extras = getIntent().getExtras();
		String data = null;
		String link = null;
		String title = null;
		if (extras != null) {
			data = extras.getString(FEED_CONTENT);
			link = extras.getString(FEED_URL);
			title = extras.getString(FEED_TITLE);
		}
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
		sb.append("<h1>" + title + "</h1></br>");
		sb.append(data);
		sb.append("</br><a href=" + '"' + link + '"' + ">"
				+ this.getString(R.string.article_view_read_all) + "</a>");
		this.myWebView.loadData(sb.toString(), "text/html", "UTF-8");

	}
}
