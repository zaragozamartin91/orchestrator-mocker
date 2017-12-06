package com.ast.mock.orchestrator;

import com.ast.orchestration.base.data.CustomResponse;
import com.ast.orchestration.base.impl.Orchestrator;
import com.cobiscorp.cobis.cts.domains.IProcedureRequest;
import com.cobiscorp.cobis.cts.domains.IProcedureResponse;

import java.util.Map;

public class ConfigurableOrchestrator extends Orchestrator {
	@Override public IProcedureResponse postRun(IProcedureResponse iProcedureResponse) {
		return null;
	}

	@Override public IProcedureRequest preRun(IProcedureRequest iProcedureRequest) {
		return null;
	}

	@Override public CustomResponse run(IProcedureRequest iProcedureRequest, Map<String, Object> map) throws Exception {
		String value = configurator.get(map.get("key").toString());
		return CustomResponseBuilder.buildNew()
				.withMessage(1, value, 1)
				.build();
	}
}
