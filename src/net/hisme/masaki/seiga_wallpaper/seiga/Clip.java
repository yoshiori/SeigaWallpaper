package net.hisme.masaki.seiga_wallpaper.seiga;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import net.hisme.masaki.seiga_wallpaper.HTTP_Client;
import net.hisme.masaki.seiga_wallpaper.App;
import net.hisme.masaki.seiga_wallpaper.R;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class Clip {

	protected int clip_id;
	protected List<String> image_urls;

	/**
	 * access to NicoSeiga clip
	 * 
	 * @throws Exception
	 */
	public Clip() throws Exception {
		image_urls = new ArrayList<String>();
		get_image_urls();
	}

	private void get_image_urls() throws Exception {
		String url = App.li.getString(R.string.api_url)
				+ String.format("/%d", App.li.clip_id());
		Document document = DocumentBuilderFactory.newInstance()
				.newDocumentBuilder().parse(HTTP_Client.get_stream(url));
		XPath xpath = XPathFactory.newInstance().newXPath();
		NodeList nodes = (NodeList) xpath.evaluate("/seiga/file", document,
				XPathConstants.NODESET);
		int len = nodes.getLength();
		for (int i = 0; i < len; i++) {
			image_urls.add(xpath.evaluate("./text()", nodes.item(i)));
		}
	}

	/**
	 * store urls
	 * 
	 * @throws Exception
	 */
	public void save() throws Exception {
		App.li.image_url_list().save(image_urls);
	}
}
