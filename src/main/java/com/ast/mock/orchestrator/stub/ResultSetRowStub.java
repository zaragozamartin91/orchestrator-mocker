package com.ast.mock.orchestrator.stub;

import com.cobiscorp.cobis.cts.domains.sp.IResultSetRow;
import com.cobiscorp.cobis.cts.domains.sp.IResultSetRowColumnData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ResultSetRowStub implements IResultSetRow {
	boolean lenientMissingRow = false;
	List<IResultSetRowColumnData> cols = new ArrayList<IResultSetRowColumnData>();

	public ResultSetRowStub(IResultSetRowColumnData... items) {
		cols = new ArrayList<IResultSetRowColumnData>(Arrays.asList(items));
	}

	public ResultSetRowStub(List<IResultSetRowColumnData> cols) {
		this.cols = cols;
	}

	public static ResultSetRowStub build(boolean lenientMissingRow, Object... colValues) {
		List<IResultSetRowColumnData> cols = new ArrayList<IResultSetRowColumnData>();
		for (Object colValue : colValues) {
			cols.add(new ResultSetRowColumnDataStub(colValue));
		}
		ResultSetRowStub resultSetRowStub = new ResultSetRowStub(cols);
		if (lenientMissingRow) {
			resultSetRowStub.setLenientMissingRow();
		}
		return resultSetRowStub;
	}

	public static ResultSetRowStub build(Object... colValues) {
		return build(false, colValues);
	}

	public int getColumnsNumber() {
		return cols.size();
	}

	public ResultSetRowStub setLenientMissingRow() {
		return setLenientMissingRow(true);
	}

	public ResultSetRowStub setLenientMissingRow(boolean lenientMissingRow) {
		this.lenientMissingRow = lenientMissingRow;
		return this;
	}

	/**
	 * Obtiene el valor de una columna.
	 *
	 * @param i Indice de columna. Empieza en 1.
	 * @return Valor de columna
	 */
	public IResultSetRowColumnData getRowData(int i) {
		if (i <= 0) {
			throw new IllegalArgumentException("Los indices comienzan en 1");
		}
		i -= 1;
		if (i >= cols.size() && lenientMissingRow) {
			int r = new Random().nextInt();
			return new ResultSetRowColumnDataStub("Value_" + r);
		}
		return cols.get(i);
	}

	public void addRowData(int position, IResultSetRowColumnData iResultSetRowColumnData) {
		if (position <= 0) {
			throw new IllegalArgumentException("Los indices comienzan en 1");
		}
		cols.add(position - 1, iResultSetRowColumnData);
	}

	public void addRowData(IResultSetRowColumnData iResultSetRowColumnData) {
		cols.add(iResultSetRowColumnData);
	}

	public List getColumns() {
		// TODO: DEBERIA DEVOLVER UNA COPIA ?
		return cols;
	}

	public IResultSetRowColumnData[] getColumnsAsArray() {
		return cols.toArray(new IResultSetRowColumnData[] {});
	}

	public ResultSetRowStub copy() {
		ResultSetRowStub rowMock = new ResultSetRowStub();
		rowMock.lenientMissingRow = this.lenientMissingRow;
		rowMock.cols = new ArrayList<IResultSetRowColumnData>(this.cols);
		return rowMock;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		ResultSetRowStub that = (ResultSetRowStub) o;

		return cols != null ? cols.equals(that.cols) : that.cols == null;
	}

	@Override
	public int hashCode() {
		return cols != null ? cols.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "ResultSetRowStub{" +
				"cols=" + cols +
				'}';
	}
}
