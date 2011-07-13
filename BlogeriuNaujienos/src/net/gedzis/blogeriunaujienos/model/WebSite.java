package net.gedzis.blogeriunaujienos.model;

public class WebSite {
	private String title;
	private String description;
	private String feedURL;

	public WebSite(String title, String description, String feedURL) {
		super();
		this.title = title;
		this.description = description;
		this.feedURL = feedURL;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFeedURL() {
		return feedURL;
	}

	public void setFeedURL(String feedURL) {
		this.feedURL = feedURL;
	}

}
