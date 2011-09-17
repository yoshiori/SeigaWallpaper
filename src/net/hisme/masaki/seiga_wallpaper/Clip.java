package net.hisme.masaki.seiga_wallpaper;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class Clip {

	protected int clip_id;
	protected List<String> image_urls;

	public Clip(int id) throws Exception {
		image_urls = new ArrayList<String>();
		get_image_urls();
	}

	public List<String> image_urls() {
		return this.image_urls;
	}

	private void get_image_urls() throws Exception {
		String url = String.format("http://afternoon-snow-8621.heroku.com/%d",
				this.clip_id);
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
}
