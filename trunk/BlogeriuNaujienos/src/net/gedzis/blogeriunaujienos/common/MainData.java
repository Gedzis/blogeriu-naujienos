package net.gedzis.blogeriunaujienos.common;

import java.util.ArrayList;
import java.util.List;

import net.gedzis.blogeriunaujienos.model.WebSite;

public class MainData {
	private List<WebSite> websites = new ArrayList<WebSite>();

	public MainData() {
		super();
		addSites();
	}

	private void addSites() {
		WebSite website = new WebSite(
				"Gedzis Blog'as",
				"Asmeninis Gedimino Ubarto tinklaraštis apie programavimą, studentišką gyvenimą",
				"http://feeds.feedburner.com/GedzisBlogas?format=xml");
		websites.add(website);

		website = new WebSite(
				"SkaitykIT",
				"Tai yra tinklaraštis, skirtas apžvelgti interneto naujienoms, pateikti lankytojams įvairias naudingas nuorodas, duoti patarimus programuotojams ir tinklapių kūrėjams bei apžvelgti tam tikrų nišų tinklapius Lietuvoje ir pasaulyje.",
				"http://www.skaitykit.lt/feed");
		websites.add(website);

		website = new WebSite("Blogeriai.net populiariausi dienos įrašai",
				"Blogeriai.net populiariausi dienos įrašai",
				"http://feeds.feedburner.com/blogeriai-net-top?format=xml");
		websites.add(website);

		website = new WebSite(
				"Radiocool.lt",
				"Elektronika, naujienos, internetas. Viskas sekasi geriau praktikuojantis, išskyrus kėlimasis rytais",
				"http://rss.radiocool.lt/radiocool?format=xml");
		websites.add(website);
		website = new WebSite(
				"Nežinau.lt",
				"Tinklaraštis apie interneto naujienas, technologijas, asmeninį produktyvumą. Įrangos apžvalgos ir kasdieninė įdomiausių radinių lietuviškoje blogosferoje.",
				"http://feeds.feedburner.com/nezinau?format=xml");
		websites.add(website);

	}

	public List<WebSite> getWebsites() {
		return websites;
	}

}
