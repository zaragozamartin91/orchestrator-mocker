package com.ast.mock.orchestrator;

import com.ast.orchestration.base.impl.Orchestrator;
import com.cobiscorp.cobis.cts.domains.IProcedureRequest;
import com.cobiscorp.cobis.cts.domains.sp.IResultSetRow;

import java.util.List;

public class Mocker {
	private static Mocker ourInstance = new Mocker();

	private Mocker() { }

	public static Mocker stub() { return ourInstance; }

	public List<IResultSetRow> resultSetRows(Object... values) {
		ResultSetDataStub resultSetDataMock = ResultSetDataStub.buildFull(3, false, values);
		List<IResultSetRow> rows = resultSetDataMock.getRows();
		return rows;
	}

	public IProcedureRequest procedureRequest() {
		IProcedureRequest procedureRequest = new ProcedureRequestStub();
		return procedureRequest;
	}

}
