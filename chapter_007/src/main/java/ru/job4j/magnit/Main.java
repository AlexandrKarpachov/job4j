package ru.job4j.magnit;

import java.io.File;
import javax.xml.parsers.*;
import org.xml.sax.*;

import java.io.*;


public class Main {
	Arguments arguments;

	public Main(Arguments arguments) {
		this.arguments = arguments;
	}

	public static void main(String[] args) {
		var arguments = new Arguments(args).init();
		if (arguments.check()) {
			var start = new Main(arguments);
			start.createXML();
			System.out.println(start.count());
		} else {
			System.out.println("Enter correct input data");
		}
	}


	public int count() {
		var result = 0;
		var saxParserFactory = SAXParserFactory.newInstance();
		try {
			SAXParser saxParser = saxParserFactory.newSAXParser();
			var handler = new SAXHandler();
			saxParser.parse(new File(this.arguments.getTarget()), handler);
			result = handler.getSum();

		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public void createXML() {
		var config = new Config().init();
		var storeSQL = new StoreSQL(config).init();
		var src = new File(this.arguments.getSource());
		var storeXML = new StoreXML(src);
		var target = new File(this.arguments.getTarget());
		var stylesheet = new File(this.arguments.getScheme());
		var convert = new ConvertXSQT();
		storeSQL.generate(this.arguments.getSize());
		storeXML.save(storeSQL.load());
		convert.convert(src, target, stylesheet);
	}
}