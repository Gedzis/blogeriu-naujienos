package net.gedzis.blogeriunaujienos.rss;

import static net.gedzis.blogeriunaujienos.common.Constants.CHANNEL;
import static net.gedzis.blogeriunaujienos.common.Constants.DESCRIPTION;
import static net.gedzis.blogeriunaujienos.common.Constants.ITEM;
import static net.gedzis.blogeriunaujienos.common.Constants.LINK;
import static net.gedzis.blogeriunaujienos.common.Constants.PUB_DATE;
import static net.gedzis.blogeriunaujienos.common.Constants.RSS;
import static net.gedzis.blogeriunaujienos.common.Constants.TITLE;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import net.gedzis.blogeriunaujienos.model.FeedItem;
import android.sax.Element;
import android.sax.EndElementListener;
import android.sax.EndTextElementListener;
import android.sax.RootElement;
import android.util.Xml;

public class BaseFeedParser {

	private final URL feedUrl;

	public BaseFeedParser(String feedUrlString) {
		try {
			this.feedUrl = new URL(feedUrlString);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}

	protected InputStream getInputStream() {
		try {
			return feedUrl.openConnection().getInputStream();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public List<FeedItem> parse() {
		final FeedItem currentFeed = new FeedItem();
		RootElement root = new RootElement(RSS);
		final List<FeedItem> feed = new ArrayList<FeedItem>();
		Element itemlist = root.getChild(CHANNEL);
		Element item = itemlist.getChild(ITEM);
		item.setEndElementListener(new EndElementListener() {
			public void end() {
				feed.add(currentFeed.copy());
			}
		});
		item.getChild(TITLE).setEndTextElementListener(
				new EndTextElementListener() {
					public void end(String body) {
						currentFeed.setTitle(body);
					}
				});
		item.getChild(LINK).setEndTextElementListener(
				new EndTextElementListener() {
					public void end(String body) {
						currentFeed.setLink(body);
					}
				});
		item.requireChild(DESCRIPTION).setEndTextElementListener(
				new EndTextElementListener() {
					public void end(String body) {
						currentFeed.setDescription(body);
					}
				});
		item.getChild(PUB_DATE).setEndTextElementListener(
				new EndTextElementListener() {
					public void end(String body) {
						currentFeed.setDate(body);

					}
				});
		try {
			Xml.parse(this.getInputStream(), Xml.Encoding.UTF_8, root
					.getContentHandler());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return feed;
	}
}