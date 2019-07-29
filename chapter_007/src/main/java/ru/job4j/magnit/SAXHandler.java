package ru.job4j.magnit;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class SAXHandler extends DefaultHandler {
	private int sum;

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) {
		if (qName.equalsIgnoreCase("entry")) {
			String id = attributes.getValue("href");
			sum += Integer.parseInt(id);
		}
	}

	public int getSum() {
		return sum;
	}
}
