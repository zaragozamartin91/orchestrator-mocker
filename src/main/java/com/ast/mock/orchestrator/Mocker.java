package com.ast.mock.orchestrator;

import com.ast.mock.orchestrator.stub.HeaderFieldStub;
import com.ast.mock.orchestrator.stub.ProcedureRequestStub;
import com.ast.mock.orchestrator.stub.ResultSetHeaderStub;
import com.ast.mock.orchestrator.stub.ResultSetRowStub;
import com.cobiscorp.cobis.cts.domains.IHeaderField;
import com.cobiscorp.cobis.cts.domains.IProcedureRequest;
import com.cobiscorp.cobis.cts.domains.sp.IResultSetHeader;
import com.cobiscorp.cobis.cts.domains.sp.IResultSetRow;

public class Mocker {
	private static Mocker ourInstance = new Mocker();

	private Mocker() { }

	public static Mocker stub() { return ourInstance; }

	public IProcedureRequest procedureRequest() {
		IProcedureRequest procedureRequest = new ProcedureRequestStub();
		return procedureRequest;
	}

	/**
	 * Crea un stub de un campo header.
	 *
	 * @param name  Nombre.
	 * @param type  Tipo.
	 * @param value Valor.
	 * @return Nueva instancia de campo header.
	 */
	public IHeaderField headerField(String name, char type, String value) {
		return new HeaderFieldStub(name, type, value);
	}

	/**
	 * Crea un stub de una fila de resultset.
	 *
	 * @param values Valores de cada columna de la fila.
	 * @return Nueva fila de resultset.
	 */
	public IResultSetRow resultSetRow(Object... values) {
		return ResultSetRowStub.build(values);
	}

	/**
	 * Crea un stub de encabezado de resultset.
	 *
	 * @param columns Caracteristicas del encabezado agrupadas de a 3 (nombre, tipo, longitud) [String,int,int].
	 * @return Nuevo encabezado.
	 */
	public IResultSetHeader resultSetHeader(Object... columns) {
		return ResultSetHeaderStub.buildFull(columns);
	}
}
