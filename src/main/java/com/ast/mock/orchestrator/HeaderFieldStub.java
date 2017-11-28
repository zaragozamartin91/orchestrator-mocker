package com.ast.mock.orchestrator;

import com.cobiscorp.cobis.cts.domains.IHeaderField;

public class HeaderFieldStub implements IHeaderField {
	String name;
	char type;
	String value;

	public HeaderFieldStub(String name, char type, String value) {
		this.name = name;
		this.type = type;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public char getType() {
		return type;
	}

	public void setType(char type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public IHeaderField clone() {
		return new HeaderFieldStub(name, type, value);
	}

	@Override
	public String toString() {
		return "HeaderFieldStub{" +
				"name='" + name + '\'' +
				", type=" + type +
				", value='" + value + '\'' +
				'}';
	}

	@Override public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof HeaderFieldStub)) {
			return false;
		}

		HeaderFieldStub that = (HeaderFieldStub) o;

		if (type != that.type) {
			return false;
		}
		if (name != null ? !name.equals(that.name) : that.name != null) {
			return false;
		}
		return value != null ? value.equals(that.value) : that.value == null;
	}

	@Override public int hashCode() {
		int result = name != null ? name.hashCode() : 0;
		result = 31 * result + (int) type;
		result = 31 * result + (value != null ? value.hashCode() : 0);
		return result;
	}
}
