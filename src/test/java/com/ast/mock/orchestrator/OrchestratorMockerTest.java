package com.ast.mock.orchestrator;

import com.ast.mock.orchestrator.stub.ProcedureRequestStub;
import com.ast.orchestration.base.data.ConnectorData;
import com.ast.orchestration.base.data.CustomResponse;
import com.ast.orchestration.base.data.SpData;
import com.ast.orchestration.base.impl.Orchestrator;
import com.cobiscorp.cobis.cts.domains.IMessageBlock;
import com.cobiscorp.cobis.cts.domains.IProcedureRequest;
import com.cobiscorp.cobis.cts.domains.IProcedureResponse;
import com.cobiscorp.cobis.cts.domains.sp.IResultSetBlock;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.assertEquals;

public class OrchestratorMockerTest {
	@Test
	public void stubRunSp() throws Exception {
		IProcedureRequest req = ProcedureRequestBuilder.buildNew()
				.addInputParam("key_1", 2, "pepe")
				.build();
		SpData spdata = new SpData(req, "database", "somesp", "1234");
		CustomResponse response = CustomResponseBuilder.buildNew()
				.withReturnCode(4)
				.withMessage(12, "message", 45)
				.build();
		Orchestrator orchestrator = OrchestratorMocker.stub(new OrchestratorStub())
				.stubRunSp(spdata, response)
				.get();

		CustomResponse response1 = orchestrator.runSp(spdata);
		assertEquals(response, response1);
	}

	@Test
	public void stubRunSp1() throws Exception {
		IProcedureRequest req = ProcedureRequestBuilder.buildNew()
				.addInputParam("key_1", 2, "pepe")
				.build();
		SpData spdata = new SpData();
		CustomResponse stubResponse1 = CustomResponseBuilder.buildNew()
				.withReturnCode(4)
				.withMessage(12, "message", 45)
				.build();
		CustomResponse stubResponse2 = CustomResponseBuilder.buildNew()
				.withReturnCode(5)
				.withMessage(13, "message_2", 46)
				.build();
		Orchestrator orchestrator = OrchestratorMocker.stub(new OrchestratorStub())
				.stubRunSp(stubResponse1, stubResponse2)
				.get();

		CustomResponse expResponse1 = orchestrator.runSp(spdata);
		CustomResponse expResponse2 = orchestrator.runSp(spdata);
		assertEquals(stubResponse1, expResponse1);
		assertEquals(stubResponse2, expResponse2);
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
		IProcedureRequest request = ProcedureRequestBuilder.buildNew()
				.addInputParam("name", 7, "value")
				.addInputParam("name_2", 8, "value_2")
				.build();

		IResultSetBlock resultset = ResultSetBuilder.buildNew()
				.withMetadata(
						"uno", 1, 1,
						"dos", 2, 2
				)
				.withData(
						"hola", "como",
						"estas", "todo",
						"bien", "asd"
				)
				.build();

		int msg = 1;
		IProcedureResponse expRes = ProcedureResponseBuilder.buildNew()
				.withMessage(msg, "msg_" + msg++)
				.withMessage(msg, "msg_" + msg++)
				.withReturnCode(999)
				.withResultset(resultset)
				.withParam("param", 1, 5, "holam")
				.build();

		int idx = 1;
		Collection<IMessageBlock> messages = expRes.getMessages();
		for (IMessageBlock message : messages) {
			assertEquals(idx, message.getMessageNumber());
			assertEquals("msg_" + idx++, message.getMessageText());
		}

		assertEquals(1, expRes.getResultSetListSize());

		Orchestrator orchestrator = OrchestratorMocker.stub(new OrchestratorStub())
				.stubInitProcedureResponse(request, expRes)
				.get();

		IProcedureResponse res = orchestrator.initProcedureResponse(request);

		assertEquals(expRes, res);
	}

	@Test
	public void stubInitProcedureResponse1() throws Exception {
		IProcedureRequest request = ProcedureRequestBuilder.buildNew().build();

		int msg = 1;
		IProcedureResponse expRes_1 = ProcedureResponseBuilder.buildNew()
				.withMessage(msg, "msg_" + msg++)
				.withMessage(msg, "msg_" + msg++)
				.withReturnCode(999)
				.withParam("param", 1, 5, "holam")
				.build();
		IProcedureResponse expRes_2 = ProcedureResponseBuilder.buildNew()
				.withMessage(msg, "msg_" + msg++)
				.withMessage(msg, "msg_" + msg++)
				.withReturnCode(999)
				.withParam("param", 1, 5, "holam")
				.build();

		Orchestrator orchestrator = OrchestratorMocker.stub(new OrchestratorStub())
				.stubInitProcedureResponse(expRes_1, expRes_2)
				.get();

		IProcedureResponse res_1 = orchestrator.initProcedureResponse(request);
		IProcedureResponse res_2 = orchestrator.initProcedureResponse(request);

		assertEquals(expRes_1, res_1);
		assertEquals(expRes_2, res_2);
	}

}