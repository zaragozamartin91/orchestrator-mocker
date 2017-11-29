package com.ast.mock.orchestrator.stub;

import com.cobiscorp.cobis.cts.domains.sp.IResultSetHeaderColumn;

public class ResultSetHeaderColumnStub implements IResultSetHeaderColumn {
    String name;
	int type;
	int length;

	public ResultSetHeaderColumnStub() { }

	public ResultSetHeaderColumnStub(String name, int type, int length) {
		this.name = name;
		this.type = type;
		this.length = length;
	}

	public static ResultSetHeaderColumnStub build(String name, int type, int length) { return new ResultSetHeaderColumnStub(name, type, length); }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	@Override
	public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

		ResultSetHeaderColumnStub that = (ResultSetHeaderColumnStub) o;

        if (type != that.type) {
            return false;
        }
        if (length != that.length) {
            return false;
        }
		return name != null ? name.equals(that.name) : that.name == null;
	}

	@Override
	public int hashCode() {
		int result = name != null ? name.hashCode() : 0;
		result = 31 * result + type;
		result = 31 * result + length;
		return result;
	}

	@Override
	public String toString() {
		return "ResultSetHeaderColumnStub{" +
				"name='" + name + '\'' +
				", type=" + type +
				", length=" + length +
				'}';
	}
}
