package ru.job4j.magnit;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Objects;


/**
 * @author Aleksandr Karpachov
 * @version $Id$
 * @since 26.07.2019
 */


@XmlRootElement
public class Entry implements Serializable {

	private int field;

	public Entry() {
	}

	public Entry(int field) {
		this.field = field;
	}

	@XmlElement
	public int getField() {
		return field;
	}

	public void setField(int field) {
		this.field = field;
	}

	@Override
	public String toString() {
		return String.format("Entry {field = %d}", field);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Entry)) {
			return false;
		}
		Entry entry = (Entry) o;
		return field == entry.field;
	}

	@Override
	public int hashCode() {
		return Objects.hash(field);
	}
}