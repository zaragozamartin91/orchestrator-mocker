package com.ast.mock.orchestrator;

import com.cobiscorp.cobis.cts.domains.sp.IResultSetBlock;
import com.cobiscorp.cobis.cts.domains.sp.IResultSetData;
import com.cobiscorp.cobis.cts.domains.sp.IResultSetHeader;
import com.cobiscorp.cobis.cts.domains.sp.IResultSetRow;
import com.cobiscorp.cobis.cts.dtos.sp.ResultSetHeaderColumn;
import com.cobiscorp.cobis.cts.dtos.sp.ResultSetRow;
import com.cobiscorp.cobis.cts.dtos.sp.ResultSetRowColumnData;

public class ResultSetBlockStub implements IResultSetBlock {
	IResultSetHeader metaData;
	IResultSetData data;
	int type;

	public ResultSetBlockStub() {
		this.metaData = new ResultSetHeaderStub();
		this.data = new ResultSetDataStub();
	}

	public ResultSetBlockStub(IResultSetHeader metaData, IResultSetData data, int type) {
		this.metaData = metaData;
		this.data = data;
		this.type = type;
	}

	public IResultSetHeader getMetaData() {
		return metaData;
	}

	public void setMetaData(IResultSetHeader metaData) {
		this.metaData = metaData;
	}

	public IResultSetData getData() {
		return data;
	}

	public void setData(IResultSetData data) {
		this.data = data;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public ResultSetBlockStub addHeader(String name, int type, int len) {
		this.getMetaData().addColumnMetaData(new ResultSetHeaderColumn(name, type, len));
		return this;
	}

	public ResultSetBlockStub addDataRow(String... values) {
		IResultSetRow row = new ResultSetRow();
		for (String value : values) {
			boolean isNull = value == null;
			row.getColumns().add(new ResultSetRowColumnData(isNull, value));
		}

		this.getData().addRow(row);
		return this;
	}

	@Override public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof ResultSetBlockStub)) {
			return false;
		}

		ResultSetBlockStub that = (ResultSetBlockStub) o;

		if (type != that.type) {
			return false;
		}
		if (metaData != null ? !metaData.equals(that.metaData) : that.metaData != null) {
			return false;
		}
		return data != null ? data.equals(that.data) : that.data == null;
	}

	@Override public int hashCode() {
		int result = metaData != null ? metaData.hashCode() : 0;
		result = 31 * result + (data != null ? data.hashCode() : 0);
		result = 31 * result + type;
		return result;
	}

	@Override
	public String toString() {
		return "ResultSetBlockStub{" +
				"metaData=" + metaData +
				", data=" + data +
				", type=" + type +
				'}';
	}
}
