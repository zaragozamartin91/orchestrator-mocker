package com.ast.mock.orchestrator;

import com.cobiscorp.cobis.cts.domains.sp.IResultSetRowColumnData;

public class ResultSetRowColumnDataStub implements IResultSetRowColumnData {
	Object value;

	public ResultSetRowColumnDataStub() { }

	public ResultSetRowColumnDataStub(Object value) {
		this.value = value;
	}

	public String getValue() {
		if (value == null) {
			throw new NullPointerException("Value es null!");
		}
		return value.toString();
	}

	public boolean isNull() {
		return value == null;
	}

	public Object getValueObj() {
		return value;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		ResultSetRowColumnDataStub that = (ResultSetRowColumnDataStub) o;

		return value != null ? value.equals(that.value) : that.value == null;
	}

	@Override
	public int hashCode() {
		return value != null ? value.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "ResultSetRowColumnDataStub{" +
				"value=" + value +
				'}';
	}
}
