package com.ast.mock.orchestrator;

import com.ast.mock.orchestrator.stub.ProcedureRequestStub;
import com.ast.orchestration.base.data.ConnectorData;
import com.ast.orchestration.base.data.CustomResponse;
import com.ast.orchestration.base.data.SpData;
import com.ast.orchestration.base.impl.Orchestrator;
import com.cobiscorp.cobis.cts.domains.IProcedureRequest;
import com.cobiscorp.cobis.cts.domains.sp.IResultSetBlock;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OrchestratorMockerTest {
	@Test
	public void stubRunSp() throws Exception {
	}

	@Test
	public void stubRunSp1() throws Exception {
	}

	@Test
	public void stubRunConnector() throws Exception {
		IProcedureRequest request = new ProcedureRequestStub();
		String type = "type";
		String trn = "1234";
		ConnectorData data = new ConnectorData(request, type, trn);

		CustomResponse response = CustomResponseBuilder.buildNew().withMessage(1, "pepe", 456).build();

		Orchestrator orchestrator = OrchestratorMocker.stub(new OrchestratorStub()).stubRunConnector(data, response).get();

		assertEquals(response, orchestrator.runConnector(data));
	}

	@Test
	public void stubRunConnector1() throws Exception {
		IProcedureRequest request = new ProcedureRequestStub();
		String type = "type";
		String trn = "1234";
		ConnectorData data = new ConnectorData(request, type, trn);

		CustomResponse response_1 = CustomResponseBuilder.buildNew().withMessage(1, "pepe", 456).build();
		CustomResponse response_2 = CustomResponseBuilder.buildNew().withMessage(2, "posting", 789).build();

		Orchestrator orchestrator = OrchestratorMocker.stub(new OrchestratorStub()).stubRunConnector(response_1, response_2).get();

		assertEquals(response_1, orchestrator.runConnector(data));
		assertEquals(response_2, orchestrator.runConnector(data));
	}

	@Test
	public void stubInitProcedureRequestMulti() throws Exception {
		IProcedureRequest req_1 = new ProcedureRequestStub();
		req_1.setSpName("some sp");
		req_1.addInputParam("key", 123, "value");

		IProcedureRequest req_2 = new ProcedureRequestStub();
		req_2.setSpName("other sp");
		req_2.addInputParam("key_2", 123, "value_2");

		Orchestrator orchestrator = OrchestratorMocker.stub(new OrchestratorStub())
				.stubInitProcedureRequestMulti(req_1, req_2)
				.get();

		assertEquals(req_1, orchestrator.initProcedureRequest(req_1));
		assertEquals(req_2, orchestrator.initProcedureRequest(req_2));
	}

	@Test
	public void stubInitProcedureRequest() throws Exception {
		IProcedureRequest inProcReq = ProcedureRequestBuilder.buildNew()
				.addInputParam("name_1", 2, "value_1")
				.build();

		IResultSetBlock rsblock = ResultSetBuilder.buildNew()
				.withMetadata("name_1", 1, "name_1".length())
				.build();
		IProcedureRequest outProcReq = ProcedureRequestBuilder.buildNew()
				.addResultSetParam("name_1", rsblock)
				.build();

		Orchestrator orchestrator = OrchestratorMocker.stub(new OrchestratorStub())
				.stubInitProcedureRequest(inProcReq, outProcReq)
				.get();
		assertEquals(outProcReq, orchestrator.initProcedureRequest(inProcReq));
	}

	@Test(expected = ClassCastException.class)
	public void runSpAndThrowException() throws Exception {
		SpData spdata = new SpData();

		Orchestrator orchestrator = OrchestratorMocker.stub(new OrchestratorStub())
				.stubRunSpAndThrow(ClassCastException.class, spdata)
				.get();

		orchestrator.runSp(spdata);
	}

	@Test(expected = ClassCastException.class)
	public void stubRunSpAndThrowWithAnyInput() throws Exception {
		SpData spdata = new SpData();
		Orchestrator orchestrator = OrchestratorMocker.stub(new OrchestratorStub())
				.stubRunSpAndThrowWithAnyInput(ClassCastException.class)
				.get();

		orchestrator.runSp(spdata);
	}

	@Test
	public void stubInitProcedureResponse() throws Exception {
	}

	@Test
	public void stubInitProcedureResponse1() throws Exception {
	}

}