package com.ast.mock.orchestrator;

import com.cobiscorp.cobis.cts.domains.sp.IResultSetHeader;
import com.cobiscorp.cobis.cts.domains.sp.IResultSetHeaderColumn;
import com.cobiscorp.cobis.cts.dtos.sp.ResultSetHeaderColumn;

import java.util.ArrayList;
import java.util.List;

public class ResultSetHeaderStub implements IResultSetHeader {
	List<IResultSetHeaderColumn> cols = new ArrayList<IResultSetHeaderColumn>();

	public ResultSetHeaderStub() {
	}

	public ResultSetHeaderStub(List<IResultSetHeaderColumn> cols) {
		this.cols = cols;
	}

	/**
	 * Crea un encabezado de forma dinamica.
	 *
	 * @param columns Caracteristicas del encabezado agrupadas de a 3 (nombre, tipo, longitud).
	 * @return Nuevo encabezado
	 */
	public static ResultSetHeaderStub buildFull(Object... columns) {
		if (columns.length == 0) {
			throw new IllegalArgumentException("Cantidad de argumentos invalida");
		}
		if (columns.length % 3 != 0) {
			throw new IllegalArgumentException("Cantidad de argumentos invalida");
		}

		List<IResultSetHeaderColumn> cols = new ArrayList<IResultSetHeaderColumn>();
		int idx = 0;
		while (idx < columns.length) {
			String name = (String) columns[idx++];
			int type = (Integer) columns[idx++];
			int length = (Integer) columns[idx++];
			cols.add(ResultSetHeaderColumnStub.build(name, type, length));
		}

		return new ResultSetHeaderStub(cols);
	}

	public int getColumnsNumber() {
		return cols.size();
	}

	public IResultSetHeaderColumn getColumnMetaData(int i) {
		return cols.get(i);
	}

	public void addColumnMetaData(IResultSetHeaderColumn iResultSetHeaderColumn) {
		cols.add(iResultSetHeaderColumn);
	}

	public List getColumnsMetaData() {
		return cols;
	}

	public IResultSetHeaderColumn[] getColumnsMetaDataAsArray() {
		return cols.toArray(new IResultSetHeaderColumn[] {});
	}

	public ResultSetHeaderStub addHeaders(ResultSetHeaderColumn... rsHeaders) {
		for (ResultSetHeaderColumn rhc : rsHeaders) {
			cols.add(rhc);
		}
		return this;
	}

	public ResultSetHeaderStub addColumn(String name, int type, int len) {
		cols.add(new ResultSetHeaderColumn(name, type, len));
		return this;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		ResultSetHeaderStub that = (ResultSetHeaderStub) o;

		return cols != null ? cols.equals(that.cols) : that.cols == null;
	}

	@Override
	public int hashCode() {
		return cols != null ? cols.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "ResultSetHeaderStub{" +
				"cols=" + cols +
				'}';
	}
}
