package com.ast.mock.orchestrator;

import com.ast.orchestration.base.data.CustomResponse;
import com.ast.orchestration.base.impl.Orchestrator;
import com.cobiscorp.cobis.cts.domains.IMessageBlock;
import com.cobiscorp.cobis.cts.domains.IProcedureRequest;
import com.cobiscorp.cobis.cts.domains.sp.IResultSetBlock;
import com.cobiscorp.cobis.cts.domains.sp.IResultSetData;
import com.cobiscorp.cobis.cts.domains.sp.IResultSetHeader;
import com.cobiscorp.cobis.cts.domains.sp.IResultSetRow;

import java.util.ArrayList;
import java.util.List;

public class Mocker {
	private static Mocker ourInstance = new Mocker();

	public static Mocker stub() { return ourInstance; }

	private Mocker() { }

	public List<IResultSetRow> resultSetRows(Object... values) {
		ResultSetDataStub resultSetDataMock = ResultSetDataStub.buildFull(3, false, values);
		List<IResultSetRow> rows = resultSetDataMock.getRows();
		return rows;
	}

	public IProcedureRequest procedureRequest() {
		IProcedureRequest procedureRequest = new ProcedureRequestStub();
		return procedureRequest;
	}

	public OrchestratorStub orchestrator(Orchestrator orch) { return OrchestratorStub.buildNewSpy(orch); }

	/**
	 * Crea un mock de custom response de error.
	 *
	 * @param returnCode Codigo de retorno.
	 * @param messages   Mensajes.
	 * @return Custom response de error.
	 */
	public CustomResponse errCustomResponse(int returnCode, String... messages) {
		CustomResponse response = new CustomResponse();
		response.setReturnCode(returnCode);

		List<IMessageBlock> msgs = new ArrayList<IMessageBlock>();
		for (String message : messages)
			msgs.add(new MessageBlockStub(1, message, message.length()));
		response.setMessages(msgs);

		return response;
	}

	/**
	 * Crea un Custom response con un unico resultset.
	 *
	 * @param metadata Encabezado del resultset.
	 * @param data     Datos del resultset.
	 * @param type     Tipo de resultset.
	 * @return Custom response nuevo.
	 */
	public CustomResponse simpleCustomResponse(IResultSetHeader metadata, IResultSetData data, int type) {
		CustomResponse response = new CustomResponse();
		List<IResultSetBlock> resultsets = new ArrayList<IResultSetBlock>();

		IResultSetBlock resultset = new ResultSetBlockStub(metadata, data, type);
		resultsets.add(resultset);

		response.setResultsetList(resultsets);

		return response;
	}
}
