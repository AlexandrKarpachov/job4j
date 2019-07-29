package ru.job4j.magnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;

/**
 * @author Aleksandr Karpachov
 * @version $Id$
 * @since 27.07.2019
 */
public class StoreXML {
	private final File target;
	private static final Logger LOG = LoggerFactory.getLogger(StoreXML.class);

	StoreXML(File target) {
		this.target = target;
	}

	public boolean save(List<Entry> list) {
		var result = true;
		var structure = new Entries(list);
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Entries.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(structure, this.target);
		} catch (Exception e) {
			result = false;
			LOG.error(e.getMessage(), e);
		}
		return result;
	}

	@XmlRootElement
	@XmlAccessorType(XmlAccessType.FIELD)
	static class Entries implements Serializable {
		@XmlElement(name = "entry")
		private List<Entry> entries = new ArrayList<>();

		public Entries() {
		}

		public Entries(List<Entry> entries) {
			this.entries = entries;
		}

		public List<Entry> getEntries() {
			return entries;
		}

		public void setEntries(List<Entry> entries) {
			this.entries = entries;
		}
	}

}

