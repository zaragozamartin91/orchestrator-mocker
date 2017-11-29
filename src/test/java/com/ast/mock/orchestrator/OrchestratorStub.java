package com.ast.mock.orchestrator;

import com.ast.orchestration.base.data.CustomResponse;
import com.ast.orchestration.base.impl.Orchestrator;
import com.cobiscorp.cobis.cts.domains.IProcedureRequest;
import com.cobiscorp.cobis.cts.domains.IProcedureResponse;

import java.util.Map;

public class OrchestratorStub extends Orchestrator {
	@Override public IProcedureResponse postRun(IProcedureResponse iProcedureResponse) {
		return null;
	}

	@Override public IProcedureRequest preRun(IProcedureRequest iProcedureRequest) {
		return null;
	}

	@Override public CustomResponse run(IProcedureRequest iProcedureRequest, Map<String, Object> map) throws Exception {
		return null;
	}
}
