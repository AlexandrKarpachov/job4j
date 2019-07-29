package ru.job4j.magnit;

import com.google.common.base.Charsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;


/**
 * @author Aleksandr Karpachov
 * @version $Id$
 * @since 27.07.2019
 */
public class ConvertXSQT {
	private static final Logger LOG = LoggerFactory.getLogger(StoreXML.class);

	/**
	 * converts xml data to another view by input scheme
	 * @param source file with data
	 * @param dest file for saving results.
	 */
	public void convert(File source, File dest, File scheme) {
		var src = this.readFile(source);
		var schemeValue = this.readFile(scheme);
		TransformerFactory factory = TransformerFactory.newInstance();
		try {
			var transformer = factory.newTransformer(
					new StreamSource(
							new ByteArrayInputStream(schemeValue.getBytes()))
			);
			transformer.transform(new StreamSource(
							new ByteArrayInputStream(src.getBytes())),
					new StreamResult(dest)
			);
		} catch (TransformerException e) {
			LOG.error(e.getMessage(), e);
		}
	}

	/**
	 * Method reads all data from input file
	 */
	private String readFile(File file) {
		var result = "";
		try {
			result = Files.readString(file.toPath(), Charsets.UTF_8);
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
		}
		return result;
	}
}
